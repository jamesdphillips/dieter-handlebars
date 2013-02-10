var Element = {};
Element.firstChild = function () { return Element; };
Element.innerHTML = function () { return Element; };

var document = { createRange: false, createElement: function() { return Element; } };
var window = this;
this.document = document;

// Console
var console = window.console = {};
console.log = console.info = console.warn = console.error = function(){};

function precompileHandlebars(input, absolute, filename) {
  var no_ext = filename.substr(0, filename.lastIndexOf('.')) || filename;
  var pre = Handlebars.precompile(input);
  var tmpl = "HandlebarsTemplates ||= {}; HandlebarsTemplates['" + no_ext + "'] = Handlebars.template(" + pre + ");"
  return tmpl;
};
