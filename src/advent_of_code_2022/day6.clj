(ns advent-of-code-2022.day6)

;; subs n (+ 4 n)

(let [n (atom 1)
      found? (atom nil)]
  (while (nil? @found?)
    ;;get next four, check if they're different
    ;; if they're different, inc n
    (let [next-four (subs "" @n (+ 4 @n))]
      (if (all-unique? next-four)
        (swap! found? (fn [x y] y) (+ 4 @n)))
      )
    ))