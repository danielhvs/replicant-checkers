(ns tic-tac-toe.scenes
  (:require [portfolio.ui :as portfolio]))

(defn main []
  (portfolio/start!
   {:config
    {:css-paths ["/styles.css"]
     :viewport/defaults
     {:background/background-color "#fdeddd"}}}))
