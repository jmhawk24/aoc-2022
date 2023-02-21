(ns advent-of-code-2022.day4
  (:require
    [clojure.java.io :as io]
    [clojure.set :as set]
    [clojure.string :as str]))

(def ez-in "2-4,6-8\n2-3,4-5\n5-7,7-9\n2-8,3-7\n6-6,4-6\n2-6,4-8")
(def real-in (-> "2022/day4.txt" io/resource slurp))

(defn set-contained? [[first-set second-set]]
  (let [first-size (count first-set)
        second-size (count second-set)
        combined (set/union first-set second-set)]
    (= (max first-size second-size) (count combined))))

(defn row-contained? [counter row]
  (if (->> row
           (map #(str/split % #"-"))
           (map #(set (range (Integer/parseInt (first %)) (+ 1 (Integer/parseInt (last %))))))
           set-contained?)
    (inc counter)
    counter))

(defn ends->range [[front end]]
  (range (Integer/parseInt front) (+ 1 (Integer/parseInt end))))

(ends->range ["2" "2"])

(defn row-overlaps? [row]
  (let [[p1 p2] (map #(str/split % #"-") row)]
    (prn p1 p2)
    (not (empty? (set/intersection (set (ends->range p1)) (set (ends->range p2)))))))

(defn rows-overlap? [counter row]
  (if
    (row-overlaps? row)
    (inc counter)
    counter))
(comment

  (let [rows (->> real-in str/split-lines (map #(str/split % #",")))]
    (reduce rows-overlap? 0 rows))
  (def row (first (->> ez-in str/split-lines (map #(str/split % #",")))))

  (row-overlaps? ["2-4" "4-5"])

  )

