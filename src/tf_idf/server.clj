(ns tf-idf.server
  (:use ring.adapter.jetty)
  (:use compojure.core)
  (:use tf-idf.api)
  (:require [compojure.handler :as handler]
            [compojure.core :as cc]
            [compojure.route :as route]))

(require '[ring.middleware.cors :refer [wrap-cors]])

(defn json-response [data & [status]]
  {:status (or status 200)
   :headers {"Access-Control-Allow-Methods" "GET, PUT, OPTIONS, X-XSRF-TOKEN" "Access-Control-Allow-Headers" "Cache-Control, Pragma, Origin, Authorization, Content-Type, X-Requested-With" "Access-Control-Allow-Origin" "*" "Content-Type" "application/json"}
   :body data})

(cc/defroutes app-routes
  (cc/GET "/v1/articles" [] (json-response (get-articles-api)))
  (cc/GET "/v1/article/:id" [id] (json-response (get-article-api id)))
)

(def app (handler/api (wrap-cors app-routes :access-control-allow-origin #"http://localhost:3000"
                                            :access-control-allow-methods [:get :put :post :delete])))

(defn boot []
  (run-jetty #'app {:port 8080}))
