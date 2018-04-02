(ns cljs-playground.components
(:require [sablono.core :as html :refer-macros [html]]
          [clojure.string :as s]
          [cljs-playground.macros :refer-macros [with-source-code]]))

(defn next-piece [piece]
  (case piece
    "X" "O"
    "O" "."
    "X"))

(defn toggle-piece [index board]
  (swap! board update-in [:game-board] #(assoc % index (next-piece (nth % index)))))
;  (update-in board [:game-board] (fn [b] (js/alert b)
;                                   (assoc b index (next-piece (nth b index))))))


(with-source-code
  (defn playground [data]
    (html
     [:div.outer [:div.middle [:div.inner [:div.content
        ; [:table.board
        ;  (for [x (range 1 4)]
        ;    [:tr (for [y (range 1 4)]
        ;           [:td [:a {:href "#"
        ;                     :onClick #(js/alert (str x " " y))}
        ;                 "."]])])

          [:div.grid-container
           (for [x (range 0 9)]
             [:a {:href "#"
                  :onClick #(toggle-piece x data)}
             [:div.grid-item (nth (:game-board @data) x) ]])
           ]

          [:br]

          [:div.button
          [:input {:type "submit"
                   :onClick #(reset! data {:game-board (:blank-board @data)
                                           :blank-board (:blank-board @data)})
                   :value "Reset" }]
           ]

          ]]]])))


                                        ;(reset! app-state {:likes 100})

; [:h1 (:likes @data) " shades of grey"]
; [:div
;
;  [:br]
;
;  [:h1 (:game-board @data)]
;
;  [:a {:href "#"
;       :onClick #(swap! data update-in [:likes] dec)}
;   "Thumbs down"]
;
;  [:ul (for [n (range 1 10)]
;         [:li {key n} n])]
;
;  [:input {:type "submit"
;           :onClick #(reset! data {:likes 0})
;           :value "Reset" }]

;  [:br]
