(ns sentiment-sort.core-test
  (:require [clojure.test :refer :all]
            [sentiment-sort.core :refer :all]))

(deftest basic-tests
  (testing "basic test cases"
    (is (= '(-5 -4 -3 -2 -1 0) (sentiment-sort '(-5 -4 -3 -2 -1 0))))
    (is (= '(-5 -4 -3 -2 -1)   (sentiment-sort '(-1 -2 -3 -4 -5))))
    (is (= '(5 4 3 2 1)        (sentiment-sort '(5 4 3 2 1))))
    (is (= '(5 4 3 2 1)        (sentiment-sort '(1 2 3 4 5))))
    (is (= '(-4 3)             (sentiment-sort '(3 -4))))
    (is (= '(2 -1)             (sentiment-sort '(-1 2))))
    (is (= '(5 4)              (sentiment-sort '(4 5))))
    (is (= '(-5 -4)            (sentiment-sort '(-4 -5)))))

  )

(deftest mixed_ratings
  (testing "mixed ratings"
    (is (= '(-5 4 -4 3 2 -1 0 0) (sentiment-sort '(3 4 -1 0 2 -4 0 -5))))
    (is (= '() (sentiment-sort '())))))


(deftest first-n-ratings
  (testing "first N ratings"
    (is (= '(-5 4) (sentiment-sort '(3 4 -1 0 2 -4 0 -5) stable-sort-priority 2)))
    (is (= '(-5 4 -4 3 2 -1 0 0) (sentiment-sort '(3 4 -1 0 2 -4 0 -5) stable-sort-priority 10)))))

(deftest test-positive-first
  (testing "positive sort priority"
    (is (= '(5 -5) (sentiment-sort '(5 -5) positive-sort-priority)))
    (is (= '(5 -5) (sentiment-sort '(-5 5) positive-sort-priority)))
    (is (= '(-5 4 -4 3 -3 2 -1) (sentiment-sort '(3 4 -1 2 -4 -5 -3) positive-sort-priority)))))

(deftest test-negative-first
  (testing "negative sort priority"
    (is (= '(-3 3) (sentiment-sort '(3 -3) negative-sort-priority)))
    (is (= '(-3 3) (sentiment-sort '(-3 3) negative-sort-priority)))
    (is (= '(-5 -4 4 -3 3 2 -1) (sentiment-sort '(3 4 -1 2 -4 -5 -3) negative-sort-priority)))))
