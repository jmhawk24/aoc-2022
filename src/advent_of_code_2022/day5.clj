(ns advent-of-code-2022.day5
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(def ez-in "    [D]    \n[N] [C]    \n[Z] [M] [P]\n 1   2   3 \n\nmove 1 from 2 to 1\nmove 3 from 1 to 3\nmove 2 from 2 to 1\nmove 1 from 1 to 2")

(def real-in (-> "2022/day5.txt" io/resource slurp))


(defn make-index->column-map [last-line]
  (->> (filter #(not (empty? %)) (str/split last-line #" "))
       (reduce
         (fn [m n]
           (assoc m (str/index-of last-line n) (Integer/parseInt n)))
         {})))

(defn line->update-vecs [line ind->col-m]
  (let [line-vs (->> (filter #(not (empty? %)) (str/split line #" "))
                     (map second))
        line-i->v (keep-indexed
                    (fn [i x]
                      (if
                        (not (contains? (set [\space \[ \]]) x))
                        [i x]))
                    line)]
    (reduce (fn [updates-col v]
              (let [col (get ind->col-m (first v))]
                (conj updates-col [(last v) col])))
            []
            line-i->v)))

(defn update-stack-starter [stack new-items]
  (reduce
    (fn [m next]
      (update m (last next) conj (first next)))
    stack
    new-items)
  )

(defn process-line [m instruction-line]
  (let [[n- old new] (re-seq #"\d" instruction-line)]
    (reduce
      (fn [m' _count]
        (->
          (update m'
                  (Integer/parseInt new)
                  (fn [x y] (conj (vec x) (last y)))
                  (get m' (Integer/parseInt old)))
          (update
            (Integer/parseInt old)
            (fn [x] (pop (vec x))))))
      m
      (range (Integer/parseInt n-)))
    ))

;; map over the string input .
;; (keep-indexed f coll)
;; inc counter for each
;; if the value is alpha, assoc the char and the index





(defn read-instructions [inputs]
  (let [[setup-raw instrs-raw] (str/split inputs #"\n\n")
        setup (str/split-lines setup-raw)
        instrs (str/split-lines instrs-raw)
        index->column-map (make-index->column-map (last setup))
        starting-stack (reduce
                         (fn [cargo-m line] (->> (line->update-vecs line index->column-map)
                                                 (update-stack-starter cargo-m)))
                         {}
                         (drop-last setup))]
    (->>
      (reduce process-line starting-stack instrs)
      sort
      (map #(last (last %)))
      (apply str)
      )
    #_(prn starting-stack)))

(comment

  (read-instructions real-in)

  (process-line {2 '(\M \C \D), 1 '(\Z \N), 3 '(\P)} "1 2 1")

  ;; {2 '(\M \C \D), 1 '(\Z \N), 3 '(\P)}
  ;; "move 1 from 2 to 1" => {2 '(\M \C) 1 '(\Z \N \D) 3 '(\P)}
  ;; (reduce magic-fn starter-stack instructions)

  )