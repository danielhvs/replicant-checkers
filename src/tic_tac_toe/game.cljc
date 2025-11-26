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

(defn grab-piece [game [y x]]
  (-> game
      (assoc :grabbing [y x])
      (assoc-in [:board y x] nil)))

(defn tic [game [y x]]
  (let [player (:current-player game)
        piece  (get-in game [:board y x])]
    (cond-> game
      (= piece player)
      (grab-piece [y x]))))

(defn move [game [from-y from-x] [to-y to-x]]
  (let [player (:current-player game)]
    (-> game
        (assoc-in [:board from-y from-x] nil)
        (assoc-in [:board to-y to-x] player)
        (assoc :current-player (next-player player)))))
