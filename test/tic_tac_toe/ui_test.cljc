(ns tic-tac-toe.ui-test
  (:require
   [clojure.test :refer [deftest is testing]]
   [tic-tac-toe.game :as game]
   [tic-tac-toe.ui :as ui :refer [mark-o]]))

(deftest game->ui-data-test
  (testing "Converts game data to UI data"
    (is (= (-> (game/create-game)
               (ui/game->ui-data)
               :rows
               first)
           [nil
            {:clickable? true
             :content    mark-o
             :highlight? true
             :on-click   [:tic [0 1]]}
            nil
            {:clickable? true
             :content    mark-o
             :highlight? true
             :on-click   [:tic [0 3]]}
            nil
            {:clickable? true
             :content    mark-o
             :highlight? true
             :on-click   [:tic [0 5]]}
            nil
            {:clickable? true
             :content    mark-o
             :highlight? true
             :on-click   [:tic [0 7]]}]))))
