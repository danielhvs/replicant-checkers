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

(defn move-left [current-player [y x]]
  (let [move-fn (current-player {:x (fn [y x] [(dec y) (dec x)])
                                 :o (fn [y x] [(inc y) (inc x)])})]
    (move-fn y x)))

(defn move-right [current-player [y x]]
  (let [move-fn (current-player {:x (fn [y x] [(dec y) (inc x)])
                                 :o (fn [y x] [(inc y) (dec x)])})]
    (move-fn y x)))

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
  (let [possible-moves (set [(move-left current-player [from-y from-x])
                             (move-right current-player [from-y from-x])])
        piece-in-spot  (get-in board [to-y to-x])]
    (and (not piece-in-spot)
         (possible-moves [to-y to-x]))))

(defn- would-take? [{:keys [current-player board] :as game} from to]
  (let [opponent          (next-player current-player)
        move-left-fn      (partial move-left current-player)
        move-right-fn     (partial move-right current-player)
        [left-y left-x]   (move-left-fn from)
        [right-y right-x] (move-right-fn from)
        piece-in-spot     (get-in board to)]
    (when (not piece-in-spot)
      (when (or (= opponent (get-in board [right-y right-x]))
                (= opponent (get-in board [left-y left-x])))
        (or (= to (-> from move-right-fn move-right-fn))
            (= to (-> from move-left-fn move-left-fn)))))))

(defn diagonal-between [{:keys [current-player]} from to]
  (let [right (move-right current-player from)
        left  (move-left current-player from)]
    (cond
      (= to (move-right current-player right)) right
      (= to (move-left current-player left))   left)))

(defn- drop-piece [game piece [y x]]
  (cond
    (can-drop? game piece [y x])
    (-> game
        (dissoc :grabbing)
        (move piece [y x]))
    (would-take? game piece [y x])
    (let [[opponent-y opponent-x] (diagonal-between game piece [y x])]
      (-> game
          (dissoc :grabbing)
          (assoc-in [:board opponent-y opponent-x] nil)
          (move piece [y x])))
    :else
    (let [[piece-y piece-x] piece]
      (-> game
          (dissoc :grabbing)
          (assoc-in [:board piece-y piece-x] (:current-player game))))))

(defn tic [{:keys [current-player grabbing] :as game} [y x]]
  (let [piece (get-in game [:board y x])]
    (cond
      grabbing                 (drop-piece game grabbing [y x])
      (= piece current-player) (grab-piece game [y x])
      :else                    game)))
