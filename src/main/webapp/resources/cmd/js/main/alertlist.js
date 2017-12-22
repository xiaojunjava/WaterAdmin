/**
 * Created by Administrator on 2017/6/9 0009.
 */
var YW_DATA={
    total:1,
    items:[{id:1,name:"执法船005",captain:"李明",Tnumber:"18666666666",Y:120.560020,X:32.011420,engine:3501,reason:"意外报警",color:"白色"}]
};

var CBoat_list={
    total:1,
    items:[{id:1,name:"执法船006",captain:"王刚",Tnumber:"18888888888",Y:120.666280, X:32.000220,engine:2017,reason:null ,color:"黄色"}]
};
function alertlist(data,pagePath) {
    var items= data.items;
    //var Zoom = allMap.getZoom();
    var point = new esri.geometry.Point  (items[0].Y, items[0].X, new esri.SpatialReference({wkid: 4326}));
    var symbol = new esri.symbol.PictureMarkerSymbol(pagePath, 25, 25);
    var locationGraphic = new esri.Graphic(point, symbol,items[0]);
    allMap.graphics.add(locationGraphic);

    //infowindow显示窗口

    allMap.infoWindow.resize(300, 300);
    allMap.infoWindow.setTitle("<b class='t'>"+items[0].name+"</b>");
    if(items[0].reason==null){
        allMap.infoWindow.setContent("<table><tr><td>船长："+items[0].captain+"</td></tr><tr><td>联系方式:"+items[0].Tnumber+"</td><table>");
    }else{
        allMap.infoWindow.setContent("<table><tr><td>报警原因:"+items[0].reason+"</td></tr><tr><td>船长："+items[0].captain+"</td></tr><tr><td>联系方式:"+items[0].Tnumber+"</td><table>");
    }
    allMap.infoWindow.show(point, allMap.getInfoWindowAnchor(point));
    allMap.centerAt(point);
}

function closeInfo(data){
    for(var i=allMap.graphics.graphics.length;i>0;i--){
        if(allMap.graphics.graphics[i-1].attributes!=null){
            if(allMap.graphics.graphics[i-1].attributes.name==data.items[0].name){
                allMap.graphics.remove(allMap.graphics.graphics[i-1])
            }
        }
    }
    allMap.infoWindow.hide();
}


