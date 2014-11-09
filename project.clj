(defproject tf-idf "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/java.jdbc "0.3.4"]
                 [org.postgresql/postgresql "9.3-1102-jdbc41"]
                 [clojure-opennlp "0.3.2"]
                 [org.clojure/core.async "0.1.338.0-5c5012-alpha"]
                 [ring/ring-core "1.3.1"]
                 [ring/ring-jetty-adapter "1.3.1"]
                 [compojure "1.1.6"]
                 [org.clojure/data.json "0.2.5"]
                 [ring-cors "0.1.4"]
                 [clj-http "1.0.1"]
                 [hiccup "1.0.2"]]
  :main tf-idf.core)
