# Dieter Ember

Compile your handlebars templates into functions compatible with Ember.js.

## Usage

In your project.clj file

    [dieter/ember "0.2.0"]

Insert it into your ring middleware stack

```clojure
(use '[dieter             :only [asset-pipeline]])
(use '[dieter.asset.ember :only [map->Ember]])
(use '[dieter.asset       :only [register]])

(register "hbs" map->Ember)
(-> app
    (asset-pipeline config-options))
```

## License

Copyright (C) 2012 EdgeCase

Distributed under the Eclipse Public License, the same as Clojure.
