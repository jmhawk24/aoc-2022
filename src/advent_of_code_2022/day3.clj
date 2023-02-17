(ns advent-of-code-2022.day3
  (:require
    [clojure.java.io :as io]
    [clojure.set :as set]
    [clojure.string :as str]))

(def real-in (-> "2022/day3.txt" io/resource slurp))
(def ez-in "vJrwpWtwJgWrhcsFMMfFFhFp\njqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL\nPmmdzqPrVvPwwTWBwg\nwMqvLMZHhHMvwLHjbvcjnnSBnvTQFn\nttgJtRGJQctTZtZT\nCrZsJsPPZsGzwwsLwLmpwMDw")

(def priorities ".abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ")

(-> ez-in str/split-lines)

(defn split-into-compartments [line]
  (map
    #(apply str %)
    (partition-all (/ (count line) 2) line)))

(defn get-shared [comp1 comp2]
  (set/intersection (set comp1) (set comp2)))

(defn abc [adder line]
  (let [[comp1 comp2] (split-into-compartments line)]
    (+ adder (str/index-of priorities (first (get-shared comp1 comp2))))))

(reduce abc 0 (-> real-in str/split-lines))


;;;;;; part 2 ;;;;;;

(partition 3 (-> ez-in str/split-lines))
(def group '("vJrwpWtwJgWrhcsFMMfFFhFp" "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL" "PmmdzqPrVvPwwTWBwg"))

(defn get-group-badge-val [inc group-of-three]
  (+ inc (->> group-of-three
              (map set)
              (apply set/intersection)
              first
              (str/index-of priorities))))

(->> real-in
      str/split-lines
      (partition 3)
      (reduce get-group-badge-val 0))


(comment

  (as-> group $
        (map set $)
        (apply set/intersection $)
        (first $)
        (str/index-of priorities $))

  (str/index-of priorities \r)

  )