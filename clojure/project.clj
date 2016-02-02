(defproject sentiment-sort "0.1.0-SNAPSHOT"
  :description "helpshift screening - sentiment sort"
  :url "https://git.yggdrasil.in/weemadarthur/sentiment-sort"
  :license {:name "MIT License"
            :url "https://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.7.0"]]
  :main ^:skip-aot sentiment-sort.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
