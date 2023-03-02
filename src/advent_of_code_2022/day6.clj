(ns advent-of-code-2022.day6
  (:require [clojure.java.io :as io]))

(def real-in (-> "2022/day6.txt" io/resource slurp))

(defn all-unique? [substring]
  (= (count substring) (count (set substring))))


(defn part2 [inputs]
  (let [n (atom 0)
        found? (atom nil)]
    (while (nil? @found?)
      ;;get next four, check if they're different
      ;; if they're different, inc n
      (let [next-four (subs inputs @n (+ 14 @n))]
        (if (all-unique? next-four)
          (swap! found? (fn [x y] y) (+ 14 @n)))
        (swap! n inc)))
    (+ 13 @n)))

(comment

  (part2 real-in)
  )