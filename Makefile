.PHONY: dev test

dev:
	npx shadow-cljs watch app portfolio

test:
	clojure -M:dev -m kaocha.runner --watch
