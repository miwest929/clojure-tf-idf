(ns tf-idf.computation
  (:require [clojure.java.io :as io] [clojure.set :as cset] [clojure.string :as string]))

(use 'opennlp.nlp)
(use 'opennlp.treebank)

; Helper functions for OpenNLP
(def tokenize (make-tokenizer "models/en-token.bin"))

(defn get-files [dir-path] (rest (file-seq (io/file dir-path))))
(defn get-words [txt] (map string/lower-case (tokenize txt)))
(defn get-article-words [article] (get-words (slurp article)))
(defn extract-articles [files] (map #(identity {"file" (.getName %) "words" (get-article-words %)}) files))
(defn inc-word-freq [freq-hash current-word]
  (let [value (freq-hash current-word)]
    (if (= value nil) (into {current-word 1} freq-hash)
      (into {current-word (+ 1 value)} (seq (cset/difference (set freq-hash) #{[current-word value]}))))))
(defn get-frequencies [words]
  (loop [words words freq-hash {}]
    (if (= (first words) nil) freq-hash (recur (rest words) (inc-word-freq freq-hash (first words))))
))
(defn derive-frequency-for-article [article] (get-frequencies (article "words")))
(defn compute-frequencies [articles] (map #(merge % {"freqs" (derive-frequency-for-article %)}) articles))

; TF-IDF calculation
; tf-idf(t, d, D) = tf(t, d) * idf(t, D)
; tf(t, d) = frequency(t, d)
; idf(t, D) = log( N / count_of_documents_where_term_appears )
(defn tf [article term] ((article "freqs") term))
(defn idf [term articles]
  (let [has-word? (fn [t d] (contains? (set (d "words")) t))]
    (Math/log (/ (count articles) (count (filter #(has-word? term %) articles))))
))
(defn tf-idf [articles article]
  (let [words (set (article "words"))]
    (sort-by #(- (:tf-idf %)) (map #(identity {:term % :tf-idf (* (idf % articles) (tf article %))}) words))
))
