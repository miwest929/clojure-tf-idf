; tf.idf.computation is used for it's definition of get-files function
(ns tf-idf.wikipedia
  (:require [clojure.java.io :as io] [clojure.string :as string])
  (:use [tf-idf.computation]))

(defn first-contents [files] (slurp (first files)))

(defn accept-topic? [topic]
  (let [topic (string/lower-case topic)]
    (cond
      (.endsWith topic ".gif") false
      (.endsWith topic ".png") false
      (.endsWith topic ".jpg") false
      (boolean (re-find #"[0-9]+" topic)) false
      :else (println topic))))

(defn process-index [index-file]
  (with-open [rdr (io/reader index-file)]
    ; This causes a stream closed error
    (map
      (fn [line] (let [parts (string/split line #"\.gz") file (first parts) topic (rest parts)]
        {:file file :accept? (accept-topic? topic)}))
      (line-seq rdr))))
    ;(doall
    ;  (let [parts (string/split % #"\.gz") file (first parts) topic (rest parts)]
    ;    (map #(identity {:file file :accept? (accept-topic? topic)})))
    ;  (line-seq rdr))))

(defn load-wiki-dump [dir]
  (let [files (get-files dir)
        index-file (first files)]
    (process-index index-file)))
