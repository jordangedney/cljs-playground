(ns cljs-playground.components
(:require [sablono.core :as html :refer-macros [html]]
          [clojure.string :as s]
          [cljs-playground.macros :refer-macros [with-source-code]]))


(defonce whos-turn (atom {:player-x true}))


(defn update-piece [index board]
  (let [piece-symbol (if (:player-x @whos-turn) "X" "O")
        square-is-unplayed (= (nth (:game-board @board) index) ".")]
    (if square-is-unplayed
      (do (swap! board update-in [:game-board] #(assoc % index piece-symbol))
          (swap! whos-turn update-in [:player-x] not)))))


(defn my-center-box [& page-content]
  (html [:div.outer [:div.middle [:div.inner [:div.content page-content]]]]))


(defn playground [data]
  (my-center-box
   [:div.grid-container
    (for [x (range 0 9)]
      [:a {:href "#"
           :onClick #(update-piece x data)}
       [:div.grid-item (nth (:game-board @data) x) ]])]

   [:br]

   [:div.button
    [:input {:type "submit"
             :onClick #(reset! data {:game-board (:blank-board @data)
                                     :blank-board (:blank-board @data)})
             :value "Reset" }]]))
