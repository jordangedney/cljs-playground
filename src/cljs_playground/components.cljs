(ns cljs-playground.components
(:require [sablono.core :as html :refer-macros [html]]
          [clojure.string :as s]
          [cljs-playground.macros :refer-macros [with-source-code]]))


(defn format-code [code-str]
  (->> (s/split code-str "(")
       (filter #(not (= "" %)))
       (map #(concat "(" %))
       (map #(s/join "" %))
       (s/join "\n")))


(with-source-code
  (defn playground [data]
    (html
     [:div.outer
      [:div.middle
       [:div.inner


        [:div.content

         [:h1 (:likes @data) " shades of grey"]
         [:div

          [:a {:href "#"
               :onClick #(swap! data update-in [:likes] inc)}
           "Thumbs up"]

          [:br]

          [:a {:href "#"
               :onClick #(swap! data update-in [:likes] dec)}
           "Thumbs down"]

          [:ul (for [n (range 1 10)]
                 [:li {key n} n])]

          [:input {:type "submit"
                   :onClick #(reset! data {:likes 0})
                   :value "Reset" }]
          [:br]

          [:pre [:code (format-code (str source))]]


          ]]]]])))
