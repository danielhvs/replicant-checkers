(ns tic-tac-toe.game-test
  (:require
   [clojure.test :refer [deftest is testing]]
   [tic-tac-toe.game :as game]
   [tic-tac-toe.ui :as ui]))

(conj nil [1 2])

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
               (game/tic [5 0] [4 1]))
           {:current-player :o
            :board          [[nil :o nil :o nil :o nil :o]
                             [:o nil :o nil :o nil :o nil]
                             [nil :o nil :o nil :o nil :o]
                             [nil nil nil nil nil nil nil nil]
                             [nil :x nil nil nil nil nil nil]
                             [nil nil :x nil :x nil :x nil]
                             [nil :x nil :x nil :x nil :x]
                             [:x nil :x nil :x nil :x nil]]})))
  #_(testing "Show ui"
      (is (= (-> (game/create-game)
                 (game/tic {:from [2 1] :to [3 0]})
                 (ui/game->ui-data {:x "x" :o "o"})
                 :rows)
             [[{:content "x", :highlight? true}
               {:content "x", :highlight? true}
               {:content "x", :highlight? true}]
              [{:content "o", :dim? true}
               {:content "o", :dim? true}
               {:dim? true}]
              [{:dim? true}
               {:dim? true}
               {:dim? true}]]))))
