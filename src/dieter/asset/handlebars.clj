(ns dieter.asset.handlebars
  (:require
   dieter.asset
   dieter.asset.javascript
   [dieter.settings :as settings]
   [dieter.pools :as pools]
   [dieter.rhino :as rhino]
   [dieter.v8 :as v8]
   [clojure.string :as s]))

(defn filename-without-ext [file]
  (s/replace (.getName file) #"\..*$" ""))

(def pool (pools/make-pool))

(defn compile-str [pool preloads fn-name str]
  (if (= (:engine settings/*settings*) :rhino)
    (rhino/with-scope pool preloads
      (rhino/call fn-name str))
    (v8/with-scope pool preloads
      (v8/call fn-name str))))

(defn compile-hbs [string filename]
  (str "HandlebarsTemplates[\"" filename "\"]=Handlebars.template("
       (compile-str pool [] "Handlebars.precompile" string)
       ");"))

(defn preprocess-handlebars [file]
  (compile-str pool ["handlebars-1.0.rc.2.js"]
    (let [hbs (slurp file)
          filename (filename-without-ext file)]
      (compile-hbs hbs filename))))

(defrecord Handlebars [file]
  dieter.asset.Asset
  (read-asset [this options]
    (dieter.asset.javascript.Js. (:file this) (preprocess-handlebars (:file this)))))

