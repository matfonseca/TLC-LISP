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

 (deftest revisar-fnc-test
  (testing "Prueba de la funcion: revisar-fnc"
  (is (= '(*error* too-few-args) (revisar-fnc '(*error* too-few-args))))
  (is (= nil (revisar-fnc '(too-few-args))))
  (is (= nil (revisar-fnc '*error*)))
  (is (= nil (revisar-fnc nil)))
  (is (= nil (revisar-fnc ())))
  )

 )

 (deftest revisar-lae-test
  (testing "Prueba de la funcion: revisar-lae"
  (is (= nil (revisar-lae '(1 2 3))))
  (is (= nil (revisar-lae nil)))
  (is (= nil (revisar-lae ())))
  (is (= '(*error* too-few-args) (revisar-lae '(1 (*error* too-few-args) 3))))
  (is (= '(*error* too-few-args) (revisar-lae '(1 (*error* too-few-args) (*error* too-many-args) 3))))
  )

 )

 (deftest actualizar-amb-test
  (testing "Prueba de la funcion: actualizar-amb"
  (is (= '(a 1 b 2 c 3 d 4) (actualizar-amb '(a 1 b 2 c 3) 'd 4)))
  (is (= '(a 1 b 4 c 3) (actualizar-amb '(a 1 b 2 c 3) 'b 4)))
  (is (= '(a 1 b 2 c 3) (actualizar-amb '(a 1 b 2 c 3) 'b (list '*error* 'mal 'hecho))))
  (is (= '(b 7) (actualizar-amb () 'b 7)))
  )
 )

 (deftest buscar-test
  (testing "Prueba de la funcion: buscar"
  (is (= 3 (buscar 'c '(a 1 b 2 c 3 d 4 e 5))))
  (is (= '(*error* unbound-symbol f) (buscar 'f '(a 1 b 2 c 3 d 4 e 5))))
  )
 )
