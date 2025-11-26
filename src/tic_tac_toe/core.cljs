(ns tic-tac-toe.core
  (:require [replicant.dom :as r]
            [tic-tac-toe.game :as game]
            [tic-tac-toe.ui :as ui]))

(defn start-new-game [store]
  (reset! store (game/create-game)))

(defn main [store]
  (let [el (js/document.getElementById "app")]
    ;; Globally handle DOM events
    (r/set-dispatch!
     (fn [_ event-handler-data]
       (prn event-handler-data)))
    (r/set-dispatch!
     (fn [_ [action & args]]
       (case action
         :tic (apply swap! store game/tic args))))
    ;; Render on every change
    (add-watch store ::render
               (fn [_ _ _ game]
                 (->> (ui/game->ui-data game)
                      (ui/render-board)
                      (r/render el))))))
