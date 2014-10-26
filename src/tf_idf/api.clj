(ns tf-idf.api
 (:use tf-idf.database)
 (:require [clojure.string :as str]
           [clojure.data.json :as json]))

(defn get-articles-api []
  (println "Inside get-articles-api!!!")
  (let [articles (get-articles)]
    (json/write-str articles)))
