(ns advent-of-code-2022.day2
  (:require
    [clojure.java.io :as io]
    [clojure.string :as str]))

(def ez-in "A Y\nB X\nC Z\n")

(def real-in (-> "2022/day2.txt" io/resource slurp))

(def choice-pts
  {:X 1
   :Y 2
   :Z 3})

(def get-outcomes-pt1
  {:A [:Y :X :Z]
   :B [:Z :Y :X]
   :C [:X :Z :Y]})

(defn outcome-points [my-choice opponent-choice]
  (let [outcome-score (.indexOf (get-outcomes-pt1 opponent-choice) my-choice)]
    (case outcome-score
      0 6
      1 3
      2 0)))

(defn instructions->score [instructions-str]
  (let [my-choice (keyword (last (str/split instructions-str #" ")))
        opponent-choice (keyword (first (str/split instructions-str #" ")))]
    (+ (choice-pts my-choice) (outcome-points my-choice opponent-choice))))
;; "A Y" -> 8 "B X" -> 0

(defn text-coll->scores [text-coll]
  (map instructions->score text-coll))


;;;;;; PART 2 ;;;;;;;
;; X = Lose 0, Y = Draw 3, Z = Win 6
;; their choice + outcome => our choice pts

(def mychoice
  {:A {:X 3
       :Y 1
       :Z 2}
   :B {:X 1
       :Y 2
       :Z 3}
   :C {:X 2
       :Y 3
       :Z 1}})

(def outcome-pts
  {:X 0
   :Y 3
   :Z 6})

(defn instructions->score-pt2 [instructions-str]
  (let [desired-result (keyword (last (str/split instructions-str #" ")))
        opponent-choice (keyword (first (str/split instructions-str #" ")))]
    (+ (get-in mychoice [opponent-choice desired-result]) (desired-result outcome-pts))))

(defn part2 [inputs]
   (->> (map instructions->score-pt2 inputs)
        (reduce +)))

(part2 (str/split-lines real-in))

