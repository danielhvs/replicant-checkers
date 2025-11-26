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
               (game/tic [5 0])
               (game/tic [4 1]))
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
               (game/tic [5 0])))))
  (testing "X grabs then move"
    (is (= {:current-player :o
            :board          [[nil :o nil :o nil :o nil :o]
                             [:o nil :o nil :o nil :o nil]
                             [nil :o nil :o nil :o nil :o]
                             [nil nil nil nil nil nil nil nil]
                             [nil :x nil nil nil nil nil nil]
                             [nil nil :x nil :x nil :x nil]
                             [nil :x nil :x nil :x nil :x]
                             [:x nil :x nil :x nil :x nil]]}
           (-> (game/create-game)
               (game/tic [5 0])
               (game/tic [4 1])))))
  (testing "X can't put the piece only in invalid diagonal"
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
               (game/tic [5 0])
               (game/tic [0 1])))))
  (testing "X can put the piece only in a valid diagonal"
    (is (= {:current-player :o
            :board          [[nil :o nil :o nil :o nil :o]
                             [:o nil :o nil :o nil :o nil]
                             [nil :o nil :o nil :o nil :o]
                             [nil nil nil nil nil nil nil nil]
                             [nil :x nil nil nil nil nil nil]
                             [nil nil :x nil :x nil :x nil]
                             [nil :x nil :x nil :x nil :x]
                             [:x nil :x nil :x nil :x nil]]}
           (-> (game/create-game)
               (game/tic [5 0])
               (game/tic [0 1])
               (game/tic [4 1]))))))
