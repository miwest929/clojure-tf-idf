(ns tf-idf.server
  (:use ring.adapter.jetty))

(defn handler [request]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body "Hello World from Clojure Ring"})

(defn boot []
  (run-jetty #'handler {:port 8080}))
