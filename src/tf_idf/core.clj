(ns tf-idf.core
  (:require [clojure.java.io :as io] [clojure.set :as cset] [clojure.string :as string])
  (:use [tf-idf.server] [tf-idf.computation] [tf-idf.database])
  (:gen-class :main true))

(use 'opennlp.nlp)
(use 'opennlp.treebank)

(def local-wiki-path "resources/wiki")

(defn extract-important-words [top-n]
   (let [docs (compute-frequencies (extract-articles (get-files local-wiki-path)))]
     (doseq [d docs] (store-article (d "file")))
     (println (map #(identity {:article (% "file") :terms (map :term (take top-n (tf-idf docs %)))}) docs))
))

(defn -main
  "I may one day do something very useful"
  [task]
  (cond
    (= task "compute") (extract-important-words 5)
    (= task "server") (boot)
    :else (println "Task " task " not available.")))

