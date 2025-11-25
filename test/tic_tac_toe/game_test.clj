(ns tic-tac-toe.game-test
  (:require
   [clojure.test :refer [deftest is testing]]
   [tic-tac-toe.game :as game]
   [tic-tac-toe.ui :as ui]))

(deftest tic-tac-toe-test
  (testing "X is the starting player"
    (is (= (game/create-game {:size 3})
           {:next-player :x
            :size        3})))
  (testing "X places a tic"
    (is (= (-> (game/create-game {:size 3})
               (game/tic 0 0))
           {:size        3
            :tics        {[0 0] :x}
            :next-player :o})))
  (testing "O places a tic"
    (is (= (-> (game/create-game {:size 3})
               (game/tic 0 0)
               (game/tic 0 1))
           {:size        3
            :tics        {[0 0] :x
                          [0 1] :o}
            :next-player :x})))
  (testing "Can't tic-tac on the same spot"
    (is (= (-> (game/create-game {:size 3})
               (game/tic 0 0)
               (game/tic 0 0))
           {:size        3
            :next-player :o
            :tics        {[0 0] :x}})))
  (testing "Can't tic outside the board"
    (is (= (-> (game/create-game {:size 3})
               (game/tic 3 0))
           {:size        3
            :next-player :x}))
    (is (= (-> (game/create-game {:size 3})
               (game/tic 0 3))
           {:size        3
            :next-player :x})))
  (testing "Detects row victory"
    (is (= (-> (game/create-game {:size 3})
               (game/tic 0 0) ;; x
               (game/tic 1 0) ;; o
               (game/tic 0 1) ;; x
               (game/tic 1 1) ;; o
               (game/tic 0 2) ;; x
               (select-keys [:over? :victory]))
           {:over?   true
            :victory {:player :x
                      :path   [[0 0] [0 1] [0 2]]}})))
  (testing "Highlights winning path"
    (is (= (-> (game/create-game {:size 3})
               (game/tic 0 0) ;; x
               (game/tic 1 0) ;; o
               (game/tic 0 1) ;; x
               (game/tic 1 1) ;; o
               (game/tic 0 2) ;; x
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
