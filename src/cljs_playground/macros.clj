(ns cljs-playground.macros)


(defmacro infix
  "Use this macro when you pine for the notation of your childhood"
  [infixed]
  (list (second infixed) (first infixed) (last infixed)))


(defmacro with-source-code
  [& stuff-to-do]
  (concat (list 'let ['source (->> (str stuff-to-do)
                                   (drop 1)
                                   (drop-last)
                                   (apply str))])
          stuff-to-do))


(defmacro defn-source-meta
  "Use this macro to shove the function's source code to the meta data"
  [name & decls]
  (let [tdddest 5]
    (list* `defn (with-meta name (assoc (meta name)
                                        :source (concat '(defn-s) decls))) decls)))

(defmacro defn-s
  "Use this macro to shove the function's source code to the meta data"
  [name & decls]
  (list* `defn (with-meta name (assoc (meta name)
                                      :source (concat '(defn-s) decls))) decls))
