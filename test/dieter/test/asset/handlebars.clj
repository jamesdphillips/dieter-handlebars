(ns dieter.test.asset.handlebars
  (:use dieter.asset.handlebars)
  (:use clojure.test)
  (:require [clojure.java.io :as io]
            [dieter.settings :as settings]))

(deftest test-preprocess-handlebars
  (is (not= -1 (.indexOf
                (settings/with-options {:engine :v8}
                 (preprocess-handlebars (io/file "test/fixtures/assets/javascripts/view.hbs")))
                "HandlebarsTemplates[\"view\"]=Handlebars.template")))
  (is (not= -1 (.indexOf
                (settings/with-options {:engine :rhino}
                 (preprocess-handlebars (io/file "test/fixtures/assets/javascripts/view.hbs")))
                "HandlebarsTemplates[\"view\"]=Handlebars.template"))))

