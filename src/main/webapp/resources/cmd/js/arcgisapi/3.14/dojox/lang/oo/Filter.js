//>>built
define("dojox/lang/oo/Filter",["dijit","dojo","dojox"],function(g,c,f){c.provide("dojox.lang.oo.Filter");(function(){var d=f.lang.oo,c=d.Filter=function(a,b){this.bag=a;this.filter="object"==typeof b?function(){return b.exec.apply(b,arguments)}:b},e=function(a){this.map=a};e.prototype.exec=function(a){return this.map.hasOwnProperty(a)?this.map[a]:a};d.filter=function(a,b){return new c(a,new e(b))}})()});