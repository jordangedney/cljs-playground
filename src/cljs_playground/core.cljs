(ns cljs-playground.core
    (:require [cljs-playground.components :refer [playground]]))

(enable-console-print!)

;; define your app data so that it doesn't get over-written on reload
(defonce app-state (atom {:text "Hello world!" :likes 0 }))

;; (.dir js/console (.getElementById js/document "app"))

(defn render! []
  (.render js/ReactDOM
           (playground app-state)
           (.getElementById js/document "app")))

(add-watch app-state :on-change (fn [_ _ _ _] (render!)))

(render!)

(reset! app-state {:likes 100})

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
  )
