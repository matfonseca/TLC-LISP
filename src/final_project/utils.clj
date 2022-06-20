(ns final_project.utils
)

(defn _igual?
  [e1, e2]
  (
    cond
    (and (= e1 'NIL) (= e2 nil)) true
    (and (= e2 'NIL) (= e1 nil)) true
    (and (= e1 'NIL) (= e2 ())) true
    (and (= e2 'NIL) (= e1 ())) true
    (and (= e1 nil) (= e2 ())) true
    (and (= e2 nil) (= e1 ())) true
    (and (= e1 '(nil)) (= e2 ())) false
    (and (= e2 '(nil)) (= e1 ())) false
    (and (list? e1) (list? e2)) (every? true? (map (fn eq_components[components] (apply _igual? components) )(apply map list (list e1 e2))))
    (and (symbol? e1) (symbol? e2)) (= (clojure.string/lower-case e1) (clojure.string/lower-case e2))
    :else (= e1 e2)

  )
)

(defn contain_value? [conjunto, elemento]
    (map-indexed list conjunto)
    (= true (some true? (map (partial _igual? elemento) conjunto)))
)

(defn update_value_envs [conjunto, clave, valor]
    (cond
        ( empty? conjunto) (list clave valor)
        (_igual? (nth conjunto 0) clave) (concat (list clave valor) (rest(rest conjunto)))
        :else  (concat (list (nth conjunto 0) (nth conjunto 1)) (update_value_envs (rest (rest conjunto)) clave valor))
        )
    )


    (defn inc_index[elemento]
      (list (inc (nth elemento 0)) (nth elemento 1))
      )

    (defn filter_even_indexes [elemento]
      (odd? (nth elemento 0))
      )

    (defn mi_nth [index, elemento]
      (nth elemento index)
      )

    (defn contain_key? [clave, conjunto]
      (contain_value? (map (partial mi_nth 1)
      (filter filter_even_indexes
       (map inc_index (
                      map-indexed list conjunto))
       )
      ) clave
      )
    )

(defn get_value [key, envs]
    (
    if (_igual? (nth envs 0) key)
    (nth envs 1)
    (get_value key (rest(rest envs)))
    )
)

(defn not_number?[ele]
  (not(number? ele))
  )

(defn search_not_number[lista]
  (nth (filter not_number? lista) 0)
  )

(defn get_value_from_env [escalar, local_env, global_env]
  (cond
     (contain_key? escalar global_env) (get_value escalar global_env)
     (contain_key? escalar local_env) (get_value escalar local_env)
     :else (list '*error* 'unbound-symbol escalar)
      )
  )

(defn parse_read_value [value]
  (cond
    (= value '()) nil
    :else value
    )
  )

(defn get_true_value_from_if [if_expre, global_env, local_env]
  (
   if (symbol? (nth if_expre 2)) (get_value_from_env (nth if_expre 2) local_env global_env) (nth if_expre 2)
  )
  )

(defn get_false_value_from_if [if_expre, global_env, local_env]
    (
      if (< (count if_expre) 4)
      nil
      (if (symbol?(last if_expre)) (get_value_from_env (last if_expre) local_env global_env) (last if_expre))
    )
)

(defn get_first_par_args [expre]
  (list (first expre) (second expre) (nth expre 2))
  )


(defn remove_first_par_args [expre]
    (concat (list (first expre)) (rest( rest(rest expre))))
    )


(defn not_nil?[element]
  (not (nil? element))
  )
