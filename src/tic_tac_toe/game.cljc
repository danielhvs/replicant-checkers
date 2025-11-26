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

(defn get-winning-path [{:keys [size tics]} y x]
  (let [row (mapv #(vector y %) (range 0 size))]
    (when (= 1 (count (set (map tics row))))
      row)))

(defn maybe-conclude [game y x]
  (if-let [path (get-winning-path game y x)]
    (-> (dissoc game :next-player)
        (assoc :over? true
               :victory {:player (get-in game [:tics [y x]])
                         :path path}))
    game))

(defn tic [game [from-y from-x] [to-y to-x]]
  (let [player (:current-player game)]
    (-> game
        (assoc-in [:board from-y from-x] nil)
        (assoc-in [:board to-y to-x] player)
        (assoc :current-player (next-player player)))))
