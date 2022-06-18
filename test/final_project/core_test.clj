(ns final_project.core-test
  (:require [clojure.test :refer :all]
  [final_project.core :refer :all]))

  (deftest controlar-aridad-test
  (testing "Prueba de la funcion: controlar-aridad"
  (is (= 3 (controlar-aridad '(a b c) 3)))
  (is (= (list '*error* 'too-many-args) (controlar-aridad '(a b c) 2)))
  (is (= (list '*error* 'too-few-args) (controlar-aridad '(a b c) 4)))
  )

 )

 (deftest igual?-test
  (testing "Prueba de la funcion: igual?"
  (is (= true (igual? 1 1)))
  (is (= false (igual? 1 2)))
  (is (= true (igual? 'a 'a)))
  (is (= true (igual? 'A 'A)))
  (is (= true (igual? 'a 'A)))
  (is (= true (igual? 'A 'a)))
  (is (= false (igual? 'a 'b)))
  (is (= true (igual? '(a b c) '(A B C))))
  (is (= false (igual? '(a b c) '(A B D))))
  (is (= true (igual? nil nil)))
  (is (= true (igual? nil 'NIL)))
  (is (= true (igual? 'NIL nil)))
  (is (= true (igual? 'NIL 'NIL)))
  (is (= true (igual? nil ())))
  (is (= true (igual? 'NIL ())))
  (is (= true (igual? () ())))
  (is (= false (igual? () '(nil))))
  (is (= true (igual? "a" "a" )))
  (is (= false (igual? "a" "A" )))
  (is (= false (igual? 'a "a" )))
  (is (= false (igual? 'a "A" )))
 )
 )

 (deftest error?-test
  (testing "Prueba de la funcion: error?"
  (is (= true (error? '(*error* too-few-args))))
  (is (= true (error? (list '*error* 'too-few-args))))
  (is (= true (error? (list '*ERROR* 'too-few-args))))
  (is (= true (error? (list '*Error* 'too-few-args))))
  (is (= true (error? (list '*error*))))
  (is (= false (error? (list 'too-few-args))))
  (is (= false (error? '*error*)))
  (is (= false (error? ())))
  (is (= false (error? nil)))
 )
 )