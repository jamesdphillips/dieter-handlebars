(ns dieter.test.asset.handlebars
  (:use dieter.asset.handlebars)
  (:use clojure.test)
  (:require [clojure.java.io :as io]))

(deftest test-preprocess-handlebars
  (is (not= -1 (.indexOf
                (preprocess-handlebars (io/file "test/fixtures/assets/javascripts/view.hbs"))
                "HandlebarsTemplates[\"view\"]=Handlebars.template"))))

