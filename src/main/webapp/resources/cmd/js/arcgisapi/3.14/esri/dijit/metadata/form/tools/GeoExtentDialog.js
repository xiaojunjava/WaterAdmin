// All material copyright ESRI, All Rights Reserved, unless otherwise specified.
// See http://js.arcgis.com/3.14/esri/copyright.txt for details.
//>>built
define("esri/dijit/metadata/form/tools/GeoExtentDialog","dojo/_base/declare dojo/_base/lang dojo/_base/array dojo/aspect dojo/dom-class dojo/dom-construct dojo/has dijit/_WidgetBase dojo/i18n!../../nls/i18nBase dijit/Dialog ./GeoExtent ../../../../kernel".split(" "),function(d,b,p,e,f,q,g,h,k,l,m,n){d=d([h],{dialog:null,title:k.geoExtent.title,gxeDocument:null,xmin:null,ymin:null,xmax:null,ymax:null,postCreate:function(){this.inherited(arguments)},onChange:function(a){},show:function(){var a=null,
c=new m({dialogBroker:this,gxeDocument:this.gxeDocument,xmin:this.xmin,ymin:this.ymin,xmax:this.xmax,ymax:this.ymax,onOkClick:b.hitch(this,function(b){if(b)this.onChange(b);a&&a.hide()}),onCancelClick:b.hitch(this,function(){a&&a.hide()})}),a=this.dialog=new l({"class":"gxeDialog gxePopupDialog",title:this.title,content:c,autofocus:!1});this.isLeftToRight()||f.add(a.domNode,"gxeRtl");this.own(e.after(a,"_position",function(){c._map&&c._map.reposition()},!0));this.own(a.on("hide",b.hitch(this,function(){setTimeout(b.hitch(this,
function(){c.destroyRecursive(!1);a.destroyRecursive(!1);this.destroyRecursive(!1)}),300)})));a.show().then(function(){c.initialize()})}});g("extend-esri")&&b.setObject("dijit.metadata.form.tools.GeoExtentDialog",d,n);return d});