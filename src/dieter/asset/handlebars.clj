(ns dieter.asset.handlebars
  (:require
   [dieter.asset :as asset]
   dieter.asset.javascript
   [dieter.pools :as pools])
  (:use [dieter.jsengine :only (run-compiler)]))

(def pool (pools/make-pool))

(defn compile-template [file]
  (run-compiler pool
                ["handlebars-1.0.rc.2.js"
                 "handlebars-wrapper.js"]
                "precompileHandlebars"
                file))

(defn preprocess-handlebars [file]
  (asset/memoize-file file compile-template))

(defrecord Handlebars [file]
  dieter.asset.Asset
  (read-asset [this]
    (dieter.asset.javascript.Js. (:file this) (preprocess-handlebars (:file this)))))
