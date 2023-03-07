(ns advent-of-code-2022.day7-test
  (:require [clojure.test :refer :all]
            [advent-of-code-2022.day7 :as sut]))

(def ez-in "$ cd /\n$ ls\ndir a\n14848514 b.txt\n8504156 c.dat\ndir d\n$ cd a\n$ ls\ndir e\n29116 f\n2557 g\n62596 h.lst\n$ cd e\n$ ls\n584 i\n$ cd ..\n$ cd ..\n$ cd d\n$ ls\n4060174 j\n8033020 d.log\n5626152 d.ext\n7214296 k")

(deftest day7-main-test
  (testing "main function returns sum of <100,000 dirs"
    (is (= 0 (sut/day7-main ez-in)))))