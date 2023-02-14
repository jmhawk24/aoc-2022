(ns advent-of-code-2022.day1
  (:require
    [clojure.java.io :as io]
    [clojure.string :as str]))
(def inputs (-> "2022/day1_input.txt" io/resource slurp))

(defn read-cals [cal-list]
  (str/split cal-list #"\n"))

(defn split-by-elf [cal-coll]
  (partition-by #(= "" %) cal-coll))

(defn drop-empties [cal-str-by-elf-with-empties]
  (remove #(empty? (first %)) cal-str-by-elf-with-empties))

(defn combine-strs-to-ints [cal-str-by-elf]
  (map
    #(reduce
       (fn [cal-total next]
         (+
           cal-total
           (Integer/parseInt next)))
       0
       %)
    cal-str-by-elf))


(defn part1 [inputs]
  (->> (read-cals inputs)
       split-by-elf
       drop-empties
       combine-strs-to-ints
       (apply max)))

(defn part2 [inputs]
  (->> (read-cals a)
       split-by-elf
       drop-empties
       combine-strs-to-ints
       sort
       (take-last 3)
       (apply +)))