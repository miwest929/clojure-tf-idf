(ns tf-idf.api
 (:use tf-idf.database)
 (:require [clojure.string :as str]
           [clojure.data.json :as json]))

(defn get-article-api [id]
  (println (get-important-words-for-article id))
  (json/write-str (merge (get-article id) (:words (get-important-words-for-article id)))))

(defn get-articles-api []
  (let [articles (get-articles)]
    (json/write-str articles)))
