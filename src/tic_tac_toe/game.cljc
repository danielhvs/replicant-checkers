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

(defn tic [game [from-y from-x] [to-y to-x]]
  (let [player (:current-player game)]
    (-> game
        (assoc-in [:board from-y from-x] nil)
        (assoc-in [:board to-y to-x] player)
        (assoc :current-player (next-player player)))))
