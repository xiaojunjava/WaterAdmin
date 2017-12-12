/**
 * Created by Administrator on 2017/6/9 0009.
 */

function alertlist() {
    var lon= 118.23671;
    var lat = 25.49311;
    //var Zoom = allMap.getZoom();
    var point = new esri.geometry.Point(lon, lat, new esri.SpatialReference({wkid: 4326}));
    var symbol = new esri.symbol.PictureMarkerSymbol('../resources/cmd/image/zboat.png', 30, 30);
    var locationGraphic = new esri.Graphic(point, symbol);
    allMap.graphics.add(locationGraphic);

    //infowindow显示窗口

    allMap.infoWindow.resize(300, 300);
    allMap.infoWindow.setTitle("<b class='t'>2001号执法船意外报警</b>");
    allMap.infoWindow.setContent("<table><tr><td>报警原因:偏离执法区域</td></tr><tr><td>经度：</td><td>" + lon + "</td></tr><tr><td>纬度:</td><td>" + lat + "</td></tr><table>");
    allMap.infoWindow.show(point, allMap.getInfoWindowAnchor(point));
    allMap.centerAt(point);
}

function closeInfo(){
    allMap.infoWindow.hide();
}


