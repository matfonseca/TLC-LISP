(ns final_project.utils)


(defn contain_value? [conjunto, elemento]
    (map-indexed list conjunto)
    (= true (some true? (map (partial = elemento) conjunto)))
)

(defn update_value_envs [conjunto, clave, valor]
    (cond
        ( empty? conjunto) (list clave valor)
        (= (nth conjunto 0) clave) (concat (list clave valor) (rest(rest conjunto)))
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
    if (= (nth envs 0) key)
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