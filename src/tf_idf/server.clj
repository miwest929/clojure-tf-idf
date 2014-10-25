(ns tf-idf.server
  (:use ring.adapter.jetty)
  (:use compojure.core)
  (:use tf-idf.api)
  (:require [compojure.handler :as handler]
            [compojure.core :as cc]
            [compojure.route :as route]))

(cc/defroutes app-routes
  (cc/GET "/v1/articles" [] (get-articles-api))
)

(def app (handler/api app-routes))

(defn boot []
  (run-jetty #'app {:port 8080}))
