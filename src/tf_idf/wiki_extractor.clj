(ns tf-idf.wiki-extractor
  (:require [clojure.java.io :as io] [clojure.set :as cset] [clojure.string :as string] [tf-idf.tika :as tika]))

(use 'tf-idf.tika)

(defn wiki-url [title] (str "http://en.wikipedia.org/wiki/" title))
(defn extract-text [url] ((tika/parse (java.net.URL. url)) :text))
(defn get-wiki-article [title]
  {:text (extract-text (wiki-url title))})
