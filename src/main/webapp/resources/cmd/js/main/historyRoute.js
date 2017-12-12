require(['esri/layers/ArcGISDynamicMapServiceLayer', 'esri/geometry/Extent',
        'esri/SpatialReference','esri/map', 'dojo/parser','dojo/on','dojo/dom',"esri/Color",'dojo/domReady!'],
    function(ArcGISDynamicMapServiceLayer,Extent,SpatialReference,Map,parser,on,dom,Color) {


        var myExtent = new Extent(118.232, 25.491, 118.240, 25.495, new SpatialReference({wkid: 4326}));

        var map = new Map('allmap', {
            logo: false,
            extent: myExtent
        });
        var url = 'http://192.168.1.144:6080/arcgis/rest/services/Zjg_text/MapServer';
        var layer = new ArcGISDynamicMapServiceLayer(url);
        map.addLayer(layer);
    });


$('.layui-btn').click(function () {
    alert();
});