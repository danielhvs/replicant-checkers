(ns tic-tac-toe.game)

(def next-player {:x :o :o :x})

(defn create-game []
  {:current-player :x
   :board          [[nil :o nil :o nil :o nil :o]
                    [:o nil :o nil :o nil :o nil]
                    [nil :o nil :o nil :o nil :o]
                    [nil nil nil nil nil nil nil nil]
                    [nil nil nil nil nil nil nil nil]
                    [:x nil :x nil :x nil :x nil]
                    [nil :x nil :x nil :x nil :x]
                    [:x nil :x nil :x nil :x nil]]})

(defn- grab-piece [game [y x]]
  (-> game
      (assoc :grabbing [y x])
      (assoc-in [:board y x] nil)))

(defn- move [game [from-y from-x] [to-y to-x]]
  (let [player (:current-player game)]
    (-> game
        (assoc-in [:board from-y from-x] nil)
        (assoc-in [:board to-y to-x] player)
        (assoc :current-player (next-player player)))))

(defn can-drop? [{:keys [current-player board]} [from-y from-x] [to-y to-x]]
  (let [y-fn           (current-player {:x dec :o inc})
        possible-moves (set [[(y-fn from-y) (inc from-x)]
                             [(y-fn from-y) (dec from-x)]])
        opponent-piece (get-in board [to-y to-x])]
    (and (not opponent-piece)
         (possible-moves [to-y to-x]))))

(defn- drop-piece [game piece [y x]]
  (if (can-drop? game piece [y x])
    (-> game
        (dissoc :grabbing)
        (move piece [y x]))
    game))

(defn tic [{:keys [current-player grabbing] :as game} [y x]]
  (let [piece (get-in game [:board y x])]
    (cond-> game
      grabbing                 (drop-piece grabbing [y x])
      (= piece current-player) (grab-piece [y x]))))
