(ns tf-idf.api
 (:use tf-idf.database)
 (:use tf-idf.wiki-extractor)
 (:require [clojure.string :as str]
           [clojure.data.json :as json]))

(defn add-wiki-article [title] (json/write-str (get-wiki-article title)))

(defn get-article-api [id]
  (println (get-important-words-for-article id))
  (json/write-str (get-important-words-for-article id)))

(defn get-articles-api []
  (let [articles (get-articles)]
    (json/write-str articles)))
