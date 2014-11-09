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

(defn get-article [id]
  (jdbc/query db-spec [(str "SELECT * FROM articles WHERE id = " id)]))

(defn get-important-words-for-article [id]
 (jdbc/query db-spec [(str "SELECT * FROM article_words WHERE article_id = " id)]))

(defn get-id-by-title [title]
  ((first (jdbc/query db-spec [(str "SELECT id FROM articles WHERE title = '" title "'")])) :id))

(defn article-exist? [article-title]
  (let [article (jdbc/query db-spec [(str "SELECT COUNT(*) FROM articles WHERE title = '" article-title "'")])]
    (> ((first article) :count) 0)))

(defn persist-article-word [id word tf-idf-value]
  (jdbc/insert! db-spec :article_words {:article_id id, :word word, :tf_idf tf-idf-value}))
(defn persist-article [title terms] (jdbc/insert! db-spec :articles {:title title}) (doseq [word terms] (persist-article-word (get-id-by-title title) (word :term) (word :tf-idf))))
(defn store-article [title terms] (if (not (article-exist? title)) (persist-article title terms) false))
