(ns tic-tac-toe.ui-test
  (:require [tic-tac-toe.ui :as ui]
            [clojure.test :refer [deftest is testing]]))

(deftest game->ui-data-test
  #_(testing "Converts game data to UI data"
      (is (= (ui/game->ui-data
              {:size        3
               :tics        {[0 0] :x
                             [0 1] :o}
               :next-player :x}
              {:x "x"
               :o "o"})
             {:rows [[{:content "x"}
                      {:content "o"}
                      {:clickable? true
                       :on-click   [:tic 0 2]}]
                     [{:clickable? true, :on-click [:tic 1 0]}
                      {:clickable? true, :on-click [:tic 1 1]}
                      {:clickable? true, :on-click [:tic 1 2]}]
                     [{:clickable? true, :on-click [:tic 2 0]}
                      {:clickable? true, :on-click [:tic 2 1]}
                      {:clickable? true, :on-click [:tic 2 2]}]]}))))
