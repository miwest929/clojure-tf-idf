(ns tf-idf.wiki-extractor
  (:require [clj-http.client :as client] [clojure.java.io :as io] [clojure.set :as cset] [clojure.string :as string]))

(defn wiki-url [title] (str "http://en.wikipedia.org/wiki/" title))
(defn get-wiki-article [title]
  (client/get (wiki-url title)))
