// All material copyright ESRI, All Rights Reserved, unless otherwise specified.
// See http://js.arcgis.com/3.14/esri/copyright.txt for details.
//>>built
define("esri/dijit/metadata/types/arcgis/form/BandUnitsElement","dojo/_base/declare dojo/_base/lang dojo/aspect dojo/has ../../../../../kernel ../../../base/etc/docUtil ../../../form/OpenElement dojo/i18n!../../../nls/i18nBase".split(" "),function(b,c,f,g,h,k,l,e){b=b([l],{postCreate:function(){this.inherited(arguments)},startup:function(){if(!this._started){this.inherited(arguments);var a=this._findMinInputWgt();a&&this.own(f.after(a,"emitInteractionOccurred",c.hitch(this,function(){this.inputWidget&&
this.inputWidget.emitInteractionOccurred()})))}},beforeValidateValue:function(a,b,d){if(null===d||0===c.trim(d).length)a=this._findMinInputWgt().getInputValue(),null===a||0===c.trim(a).length||(a=e.validation.pattern,d=e.validation.empty,b.isValid=!1,b.message=a.replace("{label}",b.label).replace("{message}",d))},_findMinInputWgt:function(){var a=this.parentElement.parentElement;return k.findInputWidget(a.parentElement.gxePath+"/minVal",a.domNode.parentNode)}});g("extend-esri")&&c.setObject("dijit.metadata.types.arcgis.form.BandUnitsElement",
b,h);return b});