(ns final_project.utils)


(defn contain_value? [conjunto, elemento]
      (= true (some true? (map (partial = elemento) conjunto)))
)

(defn update_value_envs [conjunto, clave, valor]
    (cond
        ( empty? conjunto) (list clave valor)
        (= (nth conjunto 0) clave) (concat (list clave valor) (rest(rest conjunto)))
        :else  (concat (list (nth conjunto 0) (nth conjunto 1)) (update_value_envs (rest (rest conjunto)) clave valor))
        )
    )


