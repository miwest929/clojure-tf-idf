(ns tf-idf.core
  (:require [clojure.java.io :as io] [clojure.set :as cset] [clojure.string :as string])
  (:use [tf-idf.server] [tf-idf.computation] [tf-idf.database] [tf-idf.wikipedia])
  (:gen-class :main true))

(use 'opennlp.nlp)
(use 'opennlp.treebank)

(def local-wiki-path "resources/wiki")
(def wiki-dump-dir "resources/wiki-dump")

(defn extract-important-words [top-n]
   (let [docs (compute-frequencies (extract-articles (get-files local-wiki-path)))
         doc-terms (map #(identity {:article (% "file") :terms (take top-n (tf-idf docs %))}) docs)]
     (doseq [d doc-terms] (store-article (d :article) (d :terms)))
     (println doc-terms)
))

(defn -main
  "I may one day do something very useful"
  [task]
  (cond
    (= task "compute") (time (extract-important-words 20))
    (= task "dump") (println (load-wiki-dump wiki-dump-dir))
    (= task "server") (boot)
    :else (println "Task " task " not available.")))

