(ns sentiment-sort.core
  (:gen-class))

(def positive-sort-priority +1)
(def negative-sort-priority -1)
(def stable-sort-priority 0)

(defn custom-sort-func
  [sort-priority]
  (fn [x y]
    (let [a (Math/abs x)
          b (Math/abs y)]
      (cond
        (or (not= a b) (zero? sort-priority)) (> a b)
        (> 0 sort-priority) (> y x)
        :else (> x y)))))

(defn sentiment-sort
  "Sort customer ratings from -5 to 5 based on sentiment"

  ([ratings]
   (sort (custom-sort-func stable-sort-priority) ratings))

  ([ratings sort-priority]
   (sort (custom-sort-func sort-priority) ratings))

  ([ratings sort-priority N]
   (take N (sentiment-sort ratings sort-priority))))

