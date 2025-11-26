(ns tic-tac-toe.game-test
  (:require
   [clojure.test :refer [deftest is testing]]
   [tic-tac-toe.game :as game]
   [tic-tac-toe.ui :as ui]))

(deftest tic-tac-toe-test
  #_(testing "fresh game"
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
  #_(testing "X plays"
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
  (testing "Show ui"
    (is (= {:rows
            [[nil {:clickable? true, :content "o", :highlight? true} nil {:clickable? true, :content "o", :highlight? true} nil {:clickable? true, :content "o", :highlight? true} nil {:clickable? true, :content "o", :highlight? true}]
             [{:clickable? true, :content "o", :highlight? true} nil {:clickable? true, :content "o", :highlight? true} nil {:clickable? true, :content "o", :highlight? true} nil {:clickable? true, :content "o", :highlight? true} nil]
             [nil {:clickable? true, :content "o", :highlight? true} nil {:clickable? true, :content "o", :highlight? true} nil {:clickable? true, :content "o", :highlight? true} nil {:clickable? true, :content "o", :highlight? true}]
             [{:highlight? true} nil {:highlight? true} nil {:highlight? true} nil {:highlight? true} nil]
             [nil {:clickable? true, :content "x", :highlight? true} nil {:highlight? true} nil {:highlight? true} nil {:highlight? true}]
             [{:highlight? true} nil {:clickable? true, :content "x", :highlight? true} nil {:clickable? true, :content "x", :highlight? true} nil {:clickable? true, :content "x", :highlight? true} nil]
             [nil {:clickable? true, :content "x", :highlight? true} nil {:clickable? true, :content "x", :highlight? true} nil {:clickable? true, :content "x", :highlight? true} nil {:clickable? true, :content "x", :highlight? true}]
             [{:clickable? true, :content "x", :highlight? true} nil {:clickable? true, :content "x", :highlight? true} nil {:clickable? true, :content "x", :highlight? true} nil {:clickable? true, :content "x", :highlight? true} nil]]}
           (-> (game/create-game)
               (game/tic [5 0] [4 1])
               (ui/game->ui-data))))))
