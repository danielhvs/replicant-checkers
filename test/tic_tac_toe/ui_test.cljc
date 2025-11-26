(ns tic-tac-toe.ui-test
  (:require
   [clojure.test :refer [deftest is testing]]
   [tic-tac-toe.game :as game]
   [tic-tac-toe.ui :as ui :refer [mark-o mark-x]]))

(deftest game->ui-data-test
  (testing "Converts game data to UI data"
    (is (= [[nil {:clickable? true, :content mark-o, :highlight? true} nil {:clickable? true, :content mark-o, :highlight? true} nil {:clickable? true, :content mark-o, :highlight? true} nil {:clickable? true, :content mark-o, :highlight? true}]
            [{:clickable? true, :content mark-o, :highlight? true} nil {:clickable? true, :content mark-o, :highlight? true} nil {:clickable? true, :content mark-o, :highlight? true} nil {:clickable? true, :content mark-o, :highlight? true} nil]
            [nil {:clickable? true, :content mark-o, :highlight? true} nil {:clickable? true, :content mark-o, :highlight? true} nil {:clickable? true, :content mark-o, :highlight? true} nil {:clickable? true, :content mark-o, :highlight? true}]
            [{:highlight? true} nil {:highlight? true} nil {:highlight? true} nil {:highlight? true} nil]
            [nil {:clickable? true, :content mark-x, :highlight? true} nil {:highlight? true} nil {:highlight? true} nil {:highlight? true}]
            [{:highlight? true} nil {:clickable? true, :content mark-x, :highlight? true} nil {:clickable? true, :content mark-x, :highlight? true} nil {:clickable? true, :content mark-x, :highlight? true} nil]
            [nil {:clickable? true, :content mark-x, :highlight? true} nil {:clickable? true, :content mark-x, :highlight? true} nil {:clickable? true, :content mark-x, :highlight? true} nil {:clickable? true, :content mark-x, :highlight? true}]
            [{:clickable? true, :content mark-x, :highlight? true} nil {:clickable? true, :content mark-x, :highlight? true} nil {:clickable? true, :content mark-x, :highlight? true} nil {:clickable? true, :content mark-x, :highlight? true} nil]]
           (-> (game/create-game)
               (game/move [5 0] [4 1])
               (ui/game->ui-data)
               :rows)))))
