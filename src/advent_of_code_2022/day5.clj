(ns advent-of-code-2022.day5
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(def ez-in "    [D]    \n[N] [C]    \n[Z] [M] [P]\n 1   2   3 \n\nmove 1 from 2 to 1\nmove 3 from 1 to 3\nmove 2 from 2 to 1\nmove 1 from 1 to 2")

(def real-in (-> "2022/day5.txt" io/resource slurp))

;; new approach. grab all values from the last-line, then pull the index of each
;; [ 1  2  3  4 ] => {1 1, 3 2, 5 3, 7 4} that's index=>column
(defn make-index->column-map [last-line]
  (->> (filter #(not (empty? %)) (str/split last-line #" "))
       (reduce
         (fn [m n]
           (assoc m (str/index-of last-line n) (Integer/parseInt n)))
         {})))

(defn line->update-vecs [line ind->col-m]
  ;; get each value from the line
  (let [line-vs (->> (filter #(not (empty? %)) (str/split line #" "))
                     (map second))]
    (reduce (fn [updates-col v]
              (let [col (get ind->col-m (str/index-of line v))]
                (conj updates-col [v col])))
            []
            line-vs))
  ;; get index
  ;; get the column that maps to,
  ;; conj [value column] to a seq
  ;; return that seq
  )

(defn update-stack-starter [stack new-items]
  (reduce
    (fn [m next]
      (update m (last next) conj (first next)))
    stack
    new-items)
  ;; for each vec in new-items:
  ;;   conj first item onto the map at correct "column" of map (designated by index 1 of vec)
  ;;   (higher index means top of stack)
  ;;   return updated map for next vec in new-items
  )

(fn [setup-lines index-m]
  (reduce
    (fn [cargo-m line] (->> (line->update-vecs line index-m)
                            (update-stack-starter cargo-m)))
    {}
    setup-lines))

;; map through the last line to find index for each stack
;; then read each stack line, find the appropriate stack based on index in str
(defn read-instructions [inputs]
  (let [[setup-raw instrs-raw] (str/split inputs #"\n\n")
        setup (str/split-lines setup-raw)
        instrs (str/split-lines instrs-raw)
        index->column-map (make-index->column-map (last setup))]
    (reduce
      (fn [cargo-m line] (->> (line->update-vecs line index->column-map)
                              (update-stack-starter cargo-m)))
      {}
      (drop-last setup))))


;; build initial stacks
;; read instruction line
;; move num crates to stack indicated
;; repeat
(defn part1 [input]
  "CMZ")                                                    ; resultant string

(comment

  (read-instructions ez-in)


  ;; "    [D]    ", {1 1, 5 2, 3 9} => [["D" 2]]
  ;; ^^{"D" 2}, {} => {2 ["D"]}

  ;; reduce line->update-vecs
  ;; this is a LINE ;; this is ind->col map  ;;  output to thread to next expr
  ;; "[N] [C]    " {1 1, 5 2, 3 9} => [["N" 1] ["C" 2]]
  ;; [["N" 1] ["C" 2]], {} => {1 ["N"], 2 ["C"]}

  )