var allMap;//全局map变量
var baselayer;//地图底图
var url = 'http://192.168.1.144:6080/arcgis/rest/services/Zjg_text/MapServer';
var layer = new esri.layers.ArcGISDynamicMapServiceLayer(url);
var moving;
var point,polyline,polygon;
if (typeof DCI == "undefined") { var DCI = {}; }
dojo.addOnLoad(function () {
    load2DMap();//初始化地图加载部分
});
(function () {
    dojo.require("esri.dijit.Legend");
    //dojo.require("Extension.DrawEx");
    //dojo.require("ExtensionDraw.DrawExt");
    dojo.require("esri.toolbars.draw");
    dojo.require("esri.geometry.Extent");
    dojo.require("esri.dijit.OverviewMap");
    dojo.require("esri.dijit.Scalebar");
    dojo.require("esri.tasks.FindTask");
    dojo.require("esri.tasks.FindParameters");
    dojo.require("esri.tasks.IdentifyTask");
    dojo.require("esri.tasks.IdentifyParameters");
})();
/**
 * 初始化地图加载
*/


function load2DMap() {
    var map = new esri.Map("map",
        { logo: false,
          //slider:false
        });//创建一个map对象，然后地图填充在div容器，通过div的ID（map）来关联;{}里面是构造地图的可选参数设置，logo设置图标是否显示，lods是设置瓦片地图的显示级别level有哪些，从配置文件config获取
    allMap = map;
    /*var url = 'http://192.168.1.144:6080/arcgis/rest/services/Zjg_text/MapServer';
    var layer = new esri.layers.ArcGISDynamicMapServiceLayer(url);//创建一个ArcGISTiledMapServiceLayer对象，解析arcgis的瓦片服务图层；MapConfig.imgMapUrl是layer对象的参数，请求发布地图服务的url，用来获取地图服务的数据来渲染*/
    map.addLayer(layer);//layer添加到地图map对象
    var visible = new Array();
    for(i=1;i<=17;i++){
        visible [i-1]= i;
    };
    layer.setVisibleLayers(visible);
    //设置地图初始范围
    var initExtent = new esri.geometry.Extent(118.232,25.491,118.240,25.495, new esri.SpatialReference({ wkid: 4326 }));
    map.setExtent(initExtent);

    //放大缩小
/*    $("#zoomOut").click(function(){
        map.setLevel(map.getLevel()+1);
    });
    $("#zoomIn").click(function(){
        alert(map.getLevel()-1);
        map.setLevel(map.getLevel()-1);
    });*/
    $(".clear").click(function () {
        allMap.graphics.clear();
    });


    //点击图标
    window.onload= function () {

        $("#map_zoom_slider").attr("class","zoom right_hide_zoom");
        $(".esriSimpleSliderIncrementButton").attr("class","plus");
        $(".esriSimpleSliderDecrementButton").attr("class","mini");
        loadcss();
        var objects =  $(".plus").children("span");
        for(var i=0;i<objects.length;i++){
            //遍历span标签,移除span
            objects[i].remove();
        }
        var objects=$(".mini").children("span");
        for(var i=0;i<objects.length;i++){
            //遍历span标签,移除span
            objects[i].remove();
        }


        dojo.connect(allMap.graphics, "onMouseOver", function(evt){
            allMap.infoWindow.resize(300, 200);
            allMap.infoWindow.setContent("<table><tr><td class='t' colspan='2'>我的信息:</td></tr><tr><td>纬度：</td><td>"+evt.graphic.attributes.X+"</td></tr><tr><td>经度:</td><td>"+evt.graphic.attributes.Y+"</td></tr><table>");
            allMap.infoWindow.setTitle(evt.graphic.attributes.name);
            allMap.infoWindow.show(evt.screenPoint, map.getInfoWindowAnchor(evt.screenPoint));
        });

        dojo.connect(allMap.graphics, "onMouseOut", function(){
            allMap.infoWindow.hide();
        })
    }



};


