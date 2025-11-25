(ns tic-tac-toe.scenes
  (:require [portfolio.replicant :refer-macros [defscene]]
            [portfolio.ui :as portfolio]
            [tic-tac-toe.ui :as ui]))

(defn zebra-board [board]
  (map-indexed (fn [i row]
                 (map-indexed (fn [j cell]
                                (if (zero? (mod (+ i j) 2))
                                  cell
                                  (assoc cell :highlight? true)))
                              row))
               board))

(defscene empty-cell
  (ui/render-cell {:clickable? true}))

(defscene cell-with-x
  (ui/render-cell
   {:content ui/mark-x}))

(defscene cell-with-o
  (ui/render-cell
   {:content ui/mark-o}))

(defscene interactive-cell
  "Click the cell to toggle the tic on/off"
  :params (atom nil)
  [store]
  (ui/render-cell
   {:content  @store
    :on-click (fn [_]
                (swap! store #(if % nil ui/mark-x)))}))

(defscene dimmed-cell
  (ui/render-cell
   {:content ui/mark-o
    :dim? true}))

(defscene highlighted-cell
  (ui/render-cell
   {:content    ui/mark-o
    :highlight? true}))

(defscene empty-board
  (ui/render-board
   {:rows (zebra-board
           [[{} {} {} {} {} {} {} {}]
            [{} {} {} {} {} {} {} {}]
            [{} {} {} {} {} {} {} {}]
            [{} {} {} {} {} {} {} {}]
            [{} {} {} {} {} {} {} {}]
            [{} {} {} {} {} {} {} {}]
            [{} {} {} {} {} {} {} {}]
            [{} {} {} {} {} {} {} {}]])}))

(defscene partial-board
  (let [board [[{} {:content ui/mark-o} {} {} {} {} {} {}]
               [{} {} {:content ui/mark-x} {} {} {} {} {}]
               [{} {} {} {} {} {} {} {}]
               [{} {} {} {} {} {} {} {}]
               [{} {} {} {} {} {} {} {}]
               [{} {} {} {} {} {} {} {}]
               [{} {} {} {} {} {} {} {}]
               [{} {} {} {} {} {} {} {}]]]
    (ui/render-board
     {:rows (zebra-board board)})))

(defscene full-board
  (let [board [[{} {} {} {} {} {} {} {}]
               [{} {} {} {} {} {} {} {}]
               [{} {} {} {} {} {} {} {}]
               [{} {} {} {} {} {} {} {}]
               [{} {} {} {} {} {} {} {}]
               [{} {} {} {} {} {} {} {}]
               [{} {} {} {} {} {} {} {}]
               [{} {} {} {} {} {} {} {}]]
        full  (map-indexed (fn [i row]
                             (map-indexed (fn [j cell]
                                            (if (zero? (mod (+ i j) 2))
                                              cell
                                              (cond-> cell
                                                (> i 4) (assoc :content ui/mark-o)
                                                (< i 3) (assoc :content ui/mark-x))))
                                          row))
                           board)]
    (ui/render-board
     {:rows (zebra-board full)})))

(defn main []
  (portfolio/start!
   {:config
    {:css-paths ["/styles.css"]
     :viewport/defaults
     {:background/background-color "#fdeddd"}}}))
