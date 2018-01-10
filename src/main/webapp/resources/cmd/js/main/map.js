var allMap;//全局map变量
var url ;
var layer;
var moving;
var polyline,polygon;
/**
 * 初始化地图加载
 */

function loadMap() {
    require(["esri/map", "tdlib/TDTLayer", "tdlib/TDTAnnoLayer", "esri/geometry/Point", "dojo/domReady!"],
        function (Map, TDTLayer, TDTAnnoLayer, Point) {
            map = new Map("map", {logo: false,slider:false});
            allMap = map;
            var basemap = new TDTLayer();//创建解析天地图服务底图图层类
            map.addLayer(basemap);
            var annolayer = new TDTAnnoLayer();//创建解析天地图服务标注图层类
            map.addLayer(annolayer);
            map.centerAndZoom(new Point({"x": 120.552290, "y": 31.918660, "spatialReference": {"wkid": 4326}}), 10);//默认跳转的中心点和地图级别
            map.graphics.clear();
        });
}

function onLoad(){
    //加载地图
    loadMap();
    //放大缩小
    $("#zoomOut").click(function(){
        map.setLevel(map.getLevel()+1);
    });
    $("#zoomIn").click(function(){
        map.setLevel(map.getLevel()-1);
    });
    $(".clear").click(function () {
        allMap.graphics.clear();
        allMap.infoWindow.hide();
    });

//点击图标
    /*dojo.connect(allMap.graphics, "onMouseOver", function(evt){
        if(evt.graphic.attributes==null){
        }else{
            if(evt.graphic.symbol.url!=Sx){
                allMap.infoWindow.resize(300, 200);
                allMap.infoWindow.setContent("<table><tr><td class='t' colspan='2'>我的信息:</td></tr><tr><td>纬度：</td><td>"+evt.graphic.attributes.X+"</td></tr><tr><td>经度:</td><td>"+evt.graphic.attributes.Y+"</td></tr><table>");
                allMap.infoWindow.setTitle(evt.graphic.attributes.name);
                allMap.infoWindow.show(evt.screenPoint, map.getInfoWindowAnchor(evt.screenPoint));
            }
        }

    });

    dojo.connect(allMap.graphics, "onMouseOut", function(evt){
        if(evt.graphic.attributes==null){
        }else {
            if (evt.graphic.symbol.url != Sx) {
                allMap.infoWindow.hide();
            }
        }
    })
    dojo.connect(allMap.graphics, "onClick", function(evt){
        if(evt.graphic.attributes==null){
        }else{
            if(evt.graphic.symbol.url== Sx){
                allMap.infoWindow.resize(300, 200);
                allMap.infoWindow.setContent("<table><tr><td class='t' colspan='2'>我的信息:</td></tr><tr><td>纬度：</td><td>"+evt.graphic.attributes.X+"</td></tr><tr><td>经度:</td><td>"+evt.graphic.attributes.Y+"</td></tr><tr><td colspan='2'><a href='#' onclick='openVideo();'>预览</a></td></tr><table>");
                allMap.infoWindow.setTitle(evt.graphic.attributes.name);
                allMap.infoWindow.show(evt.screenPoint, map.getInfoWindowAnchor(evt.screenPoint));
            }
        }
    });*/
    dojo.connect(allMap.graphics, "onClick", function(evt){
        if(evt.graphic.attributes==null){
        }else{
            allMap.infoWindow.resize(300, 200);
            allMap.infoWindow.setContent("<table><tr><td class='t' colspan='2'>我的信息:</td></tr><tr><td>纬度：</td><td>"+evt.graphic.attributes.X+"</td></tr><tr><td>经度:</td><td>"+evt.graphic.attributes.Y+"</td></tr><tr><td colspan='2'><a href='#' onclick='openVideo();'>预览</a></td></tr><table>");
            allMap.infoWindow.setTitle(evt.graphic.attributes.name);
            allMap.infoWindow.show(evt.screenPoint, map.getInfoWindowAnchor(evt.screenPoint));
        }
    });

};



