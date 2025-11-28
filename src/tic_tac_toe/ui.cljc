(ns tic-tac-toe.ui)

(defn render-cell [{:keys [content on-click dim? highlight? clickable?]}]
  [:button.cell
   {:on {:click on-click}
    :class (cond-> []
             dim? (conj "cell-dim")
             highlight? (conj "cell-highlight")
             clickable? (conj "clickable"))}
   (when content
     [:div.cell-content
      {:replicant/mounting {:class "transparent"}
       :replicant/unmounting {:class "transparent"}}
      content])])

(defn render-board [{:keys [rows mouse-x mouse-y grabbing-color]}]
  [:div
   {:on {:mousemove [[:mouse]]}}
   [:audio {:id "audioPlayer" :src "blip.wav"}]
   (when grabbing-color
     [:div {:style {:position         "fixed"
                    :left             (str (- mouse-x 25) "px")
                    :top              (str (- mouse-y 25) "px")
                    :width            "45px"
                    :height           "45px"
                    :background-color grabbing-color
                    :border-radius    "50%"
                    :pointer-events   "none"
                    :z-index          9999}}])
   [:div.board
    (for [row rows]
      [:div.row
       (for [cell row]
         (render-cell cell))])]])

(defn- new-function [color]
  [:svg
   {:viewBox "0 0 1024 1024"
    :xmlns   "http://www.w3.org/2000/svg"
    :version 1}
   [:circle {:cx   "500"
             :cy   "500"
             :r    "500"
             :fill "#000000"}]
   [:circle {:cx   "500"
             :cy   "500"
             :r    "400"
             :fill color}]])

(def mark-x
  (new-function "#ff0000"))

(def mark-o
  (new-function "#0000ff"))

(defn game->ui-data [{:keys [board mouse-x mouse-y grabbing current-player]}]
  {:rows
   (map-indexed (fn [i row]
                  (map-indexed (fn [j cell]
                                 (if (zero? (mod (+ i j) 2))
                                   cell
                                   (cond-> {:highlight? true :clickable? true
                                            :on-click [[:tic [i j]]
                                                       [:play-audio]]}
                                     (= :o cell) (assoc :content mark-o)
                                     (= :x cell) (assoc :content mark-x))))
                               row))
                board)
   :mouse-y        mouse-y
   :mouse-x        mouse-x
   :grabbing-color (when grabbing
                     (current-player {:x "red" :o "blue"}))})
