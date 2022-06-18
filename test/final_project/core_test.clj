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

 (deftest fnc-append-test
  (testing "Prueba de la funcion: fnc-append"
  (is (= '(*error* too-few-args) (fnc-append '( (1 2) ))))
  (is (= '(*error* too-many-args) (fnc-append '( (1 2) (3) (4 5) (6 7) ))))
  (is (= '(*error* list expected 3) (fnc-append '( (1 2) 3 ))))
  (is (= '(*error* list expected A) (fnc-append '( (1 2) A ))))
  (is (= '(1 2 3) (fnc-append '( (1 2) (3)))))
  (is (= '(1 2 ) (fnc-append '( (1 2) nil ))))
  (is (= '(1 2 ) (fnc-append '( () (1 2) ))))
  (is (= nil (fnc-append '(nil nil))))
  (is (= nil (fnc-append '(() ()))))
  )
)
 
(deftest fnc-env-test
  (testing "Prueba de la funcion: fnc-env"
  (is (= '(a 1 b 2 c 3 d 4) (fnc-env () '(a 1 b 2) '(c 3 d 4))))
  (is (= '(*error* too-many-args) (fnc-env '(5) '(a 1 b 2) '(c 3 d 4))))
  )
)

(deftest fnc-equal-test
  (testing "Prueba de la funcion: fnc-equal"
  (is (= 't (fnc-equal '(1 1))))
  (is (= 't (fnc-equal '(A a))))
  (is (= 't (fnc-equal '("1" "1"))))
  (is (= 't (fnc-equal '(nil NIL))))
  (is (= nil (fnc-equal '(1 2))))
  (is (= nil (fnc-equal '(A B))))
  (is (= nil (fnc-equal '("1" 1))))
  (is (= '(*error* too-few-args) (fnc-equal ())))
  (is (= '(*error* too-few-args) (fnc-equal '(A))))
  (is (= '(*error* too-many-args) (fnc-equal '(A a A))))
  )
)

(deftest fnc-add-test
  (testing "Prueba de la funcion: fnc-add"
  (is (= '(*error* too-few-args) (fnc-add ())))
  (is (= '(*error* too-few-args) (fnc-add '(3))))
  (is (= 7 (fnc-add '(3 4))))
  (is (= 12 (fnc-add '(3 4 5))))
  (is (= 18 (fnc-add '(3 4 5 6))))
  (is (= '(*error* number-expected A) (fnc-add '(A 4 5 6))))
  (is (= '(*error* number-expected A) (fnc-add '(3 A 5 6))))
  (is (= '(*error* number-expected A) (fnc-add '(3 4 A 6))))
  )
)

(deftest fnc-sub-test
  (testing "Prueba de la funcion: fnc-add"
  (is (= '(*error* too-few-args) (fnc-add ())))
  (is (= '(*error* too-few-args) (fnc-add '(3))))
  (is (= 7 (fnc-add '(3 4))))
  (is (= 12 (fnc-add '(3 4 5))))
  (is (= 18 (fnc-add '(3 4 5 6))))
  (is (= '(*error* number-expected A) (fnc-add '(A 4 5 6))))
  (is (= '(*error* number-expected A) (fnc-add '(3 A 5 6))))
  (is (= '(*error* number-expected A) (fnc-add '(3 4 A 6))))
  )
)