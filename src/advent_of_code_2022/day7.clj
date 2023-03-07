(ns advent-of-code-2022.day7
  (:require [clojure.java.io :as io]))

(def real-in (-> "2022/day7.txt" io/resource slurp))

(def ez-in "$ cd /\n$ ls\ndir a\n14848514 b.txt\n8504156 c.dat\ndir d\n$ cd a\n$ ls\ndir e\n29116 f\n2557 g\n62596 h.lst\n$ cd e\n$ ls\n584 i\n$ cd ..\n$ cd ..\n$ cd d\n$ ls\n4060174 j\n8033020 d.log\n5626152 d.ext\n7214296 k")

(defn day7-main [inputs])
;; $ cd /
;; $ ls
;; dir a
;; 14848514 b.txt
;; 8504156 c.dat
;; dir d
;;
;; (assoc-in parent-structure cd-stack results-of-ls)
