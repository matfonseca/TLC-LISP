(ns final_project.core-test
  (:require [clojure.test :refer :all]
  [final_project.core :refer :all]))

  (deftest controlar-aridad-test
  (testing "Prueba de la funcion: controlar-aridad"
  (is (= 3 (controlar-aridad '(a b c) 3)))
  (is (= '(*error* too-many-args) (controlar-aridad '(a b c) 2)))
  (is (= '(*error* too-few-args) (controlar-aridad '(a b c) 4)))
  )
 )
