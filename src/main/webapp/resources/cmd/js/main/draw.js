/**线路预设
 * Created by Administrator on 2017/12/12 0012.
 */
var  toolbar, symbol;
require([
    "esri/toolbars/draw",
    "esri/graphic",

    "esri/symbols/SimpleMarkerSymbol",
    "esri/symbols/SimpleLineSymbol",
    "esri/symbols/SimpleFillSymbol",

    "dojo/parser", "dijit/registry",
    'dojo/on', 'dojo/dom',
    "esri/Color", "dojo/domReady!"
], function (Draw, Graphic,
    SimpleMarkerSymbol, SimpleLineSymbol, SimpleFillSymbol,
    parser, registry, on, dom, Color
) {

    loadMap();
    createToolbar();

    on(dom.byId('drawPolygon'), 'click', function activateTool() {
        //var tool = this.label.toUpperCase().replace(/ /g, "_");
        toolbar.activate(Draw.POLYGON);
        allMap.hideZoomSlider();
        symbol = new SimpleFillSymbol(SimpleFillSymbol.STYLE_NONE,new SimpleLineSymbol(SimpleLineSymbol.STYLE_SOLID,new Color([0, 0, 255]),2),new Color([0, 0, 255, 0.25]));
    });
    on(dom.byId('drawPolyline'), 'click', function activateTool() {
        //FREEHAND_POLYGON  FREEHAND_POLYLINE
        toolbar.activate(Draw.POLYLINE);
        allMap.hideZoomSlider();
        symbol= new SimpleLineSymbol(SimpleLineSymbol.STYLE_SOLID,new Color([0, 0, 255]),2);
    });
    function createToolbar() {
        toolbar = new Draw(allMap);
        toolbar.on("draw-end", addToMap);
    }

    function addToMap(evt) {
        toolbar.deactivate();
        allMap.showZoomSlider();
        //symbol = new SimpleFillSymbol(SimpleFillSymbol.STYLE_NONE,new SimpleLineSymbol(SimpleLineSymbol.STYLE_SOLID,new Color([0, 0, 255]),2),new Color([0, 0, 255, 0.25]));
        var graphic = new Graphic(evt.geometry, symbol);
        allMap.graphics.add(graphic);

        //***********下面要修改
        if(evt.geometry.type=="polygon"){
            //console.log(evt.geometry.rings[0]);
            var content=evt.geometry.rings[0];
            openRoutePlanWin(1,content);
        }else{
            //console.log(evt.geometry.paths[0]);
            var content=evt.geometry.paths[0];
            openRoutePlanWin(2,content);
        }

        // var routelist = '<div class="xianlu_li">'+
        //                     '<div class="xianlu_no">'+($(".xianlu_li").length+1) +'</div>'+
        //                     '<div class="xianlu_name">'+'<input type="text" style="width: 120px">'+'</div>'+
        //                     '<div class="xianlu_del">'+'<div></div>'+'</div>'+'</div>';
        // $(".xianlu_list").append(routelist);
    }

});