(ns tf-idf.database
  (:require [clojure.java.io :as io] [clojure.java.jdbc :as jdbc] [clojure.set :as cset] [clojure.string :as string]))

(use 'clojure.java.jdbc)

(def db-spec {
  :classname "org.postgresql.Driver"
  :subprotocol "postgresql"
  :subname "//localhost/tfidf_development"
  :user "root"
  :password ""
})

(defn get-articles []
  (jdbc/query db-spec ["SELECT * FROM articles"]))

(defn article-exist? [article-title]
  (let [article (jdbc/query db-spec [(str "SELECT COUNT(*) FROM articles WHERE title = '" article-title "'")])]
    (> ((first article) :count) 0)))

(defn store-article [article-title] (if (not (article-exist? article-title)) (jdbc/insert! db-spec :articles {:title article-title}) false))
