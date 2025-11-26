(ns tic-tac-toe.game-test
  (:require
   [clojure.test :refer [deftest is testing]]
   [tic-tac-toe.game :as game]))

(deftest tic-tac-toe-test
  (testing "fresh game"
    (is (= (game/create-game)
           {:current-player :x
            :board          [[nil :o nil :o nil :o nil :o]
                             [:o nil :o nil :o nil :o nil]
                             [nil :o nil :o nil :o nil :o]
                             [nil nil nil nil nil nil nil nil]
                             [nil nil nil nil nil nil nil nil]
                             [:x nil :x nil :x nil :x nil]
                             [nil :x nil :x nil :x nil :x]
                             [:x nil :x nil :x nil :x nil]]})))
  (testing "X plays"
    (is (= (-> (game/create-game)
               (game/move [5 0] [4 1]))
           {:current-player :o
            :board          [[nil :o nil :o nil :o nil :o]
                             [:o nil :o nil :o nil :o nil]
                             [nil :o nil :o nil :o nil :o]
                             [nil nil nil nil nil nil nil nil]
                             [nil :x nil nil nil nil nil nil]
                             [nil nil :x nil :x nil :x nil]
                             [nil :x nil :x nil :x nil :x]
                             [:x nil :x nil :x nil :x nil]]})))
  (testing "X grabs a piece"
    (is (= {:current-player :x
            :grabbing       [5 0]
            :board          [[nil :o nil :o nil :o nil :o]
                             [:o nil :o nil :o nil :o nil]
                             [nil :o nil :o nil :o nil :o]
                             [nil nil nil nil nil nil nil nil]
                             [nil nil nil nil nil nil nil nil]
                             [nil nil :x nil :x nil :x nil]
                             [nil :x nil :x nil :x nil :x]
                             [:x nil :x nil :x nil :x nil]]}
           (-> (game/create-game)
               (game/grab [5 0]))))))
