/**
 * Created by Administrator on 2017/10/24 0024.
 * 监控点显示
 */

var ZBOAT_DATA={
    total:4,
    items:[{id:1,name:"乌鲁木齐",Y:118.23520,X:25.49392},
        {id:2,name:"拉萨",Y:118.23593, X:25.49326},
        {id:3,name:"西宁",Y:118.23648,X:25.49347},
        {id:4,name:"兰州",Y:118.23671,X:25.49311}
    ]
};

var CBOAT_DATA={
    total:4,
    items:[{id:1,name:"乌鲁木齐",Y:118.23553,X:25.49361},
        {id:2,name:"拉萨",Y:118.23624, X:25.49320},
        {id:3,name:"西宁",Y:118.23684,X:25.49328},
        {id:4,name:"兰州",Y:118.23721,X:25.49312}
    ]
};

var YBOAT_DATA={
    total:4,
    items:[{id:1,name:"乌鲁木齐",Y:118.23573,X:25.49349},
        {id:2,name:"拉萨",Y:118.23562, X:25.49287},
        {id:3,name:"西宁",Y:118.23646,X:25.49334},
        {id:4,name:"兰州",Y:118.23526,X:25.49385}
    ]
};

var ZCAR_DATA={
    total:4,
    items:[{id:1,name:"乌鲁木齐",Y:118.23626,X:25.49293},
        {id:2,name:"拉萨",Y:118.23588, X:25.49407},
        {id:3,name:"西宁",Y:118.23771,X:25.49342},
        {id:4,name:"兰州",Y:118.23556,X:25.49222}
    ]
};
var PEOPLE_DATA={
    total:4,
    items:[{id:1,name:"乌鲁木齐",Y:118.23756,X:25.49231},
        {id:2,name:"拉萨",Y:118.23391, X:25.49325},
        {id:3,name:"西宁",Y:118.23519,X:25.49456},
        {id:4,name:"兰州",Y:118.23874,X:25.49398}
    ]
};

var SX_DATA={
    total:3,
    items:[{id:1,name:"乌鲁木齐",Y:118.23572,X:25.49330},
        {id:2,name:"拉萨",Y:118.23501, X:25.49417},
        {id:3,name:"西宁",Y:118.23815,X:25.49316}
    ]
};


polylineJson={
    "paths": [[[118.23538,25.49264], [118.23550,25.49277], [118.23562,25.49289],
        [118.23576,25.49303],[118.23589,25.49321], [118.23612,25.49322],
        [118.23633,25.49321], [118.23653,25.49316], [118.23671,25.49311]]],
    "spatialReference":{"wkid":4326}
};

prouteJson={
    "paths": [[[118.23538,25.49264], [118.23550,25.49277], [118.23562,25.49289],
        [118.23576,25.49303],[118.23589,25.49321], [118.23612,25.49322],
        [118.23633,25.49321], [118.23653,25.49316], [118.23671,25.49311],
        [118.23687,25.49308],[118.23704,25.49303],[118.23721,25.49304],[118.23728,25.49313]]],
    "spatialReference":{"wkid":4326}
};
var ring = [[118.23601,25.49372],
    [118.23593,25.49369],[118.23584,25.49364],[118.23578,25.49360],
    [118.23575,25.49353],[118.23575,25.49343],[118.23580,25.49336],
    [118.23586,25.49330],[118.23595,25.49325],[118.23606,25.49321],
    [118.23619,25.49318],[118.23632,25.49317],[118.23642,25.49316],
    [118.23654,25.49317],[118.23662,25.49319],[118.23670,25.49324],
    [118.23674,25.49330],[118.23671,25.49338],[118.23664,25.49344],
    [118.23656,25.49350],[118.23647,25.49355],[118.23636,25.49361],
    [118.23626,25.49364],[118.23614,25.49368],[118.23601,25.49372]];

function show(data,image){
    var items= data.items;
    for(var i=0;i<data.total;i++){
        var pt=new esri.geometry.Point(items[i].Y,items[i].X,new esri.SpatialReference({wkid:4326}));
        var symbol = new esri.symbol.PictureMarkerSymbol(image, 25, 25);
        var graphic = new esri.Graphic(pt,symbol,items[i]);
        allMap.graphics.add(graphic);
    }
};

//摄像头控制
/*function showCream(){
    var visible = new Array();
    for(i=0;i<=17;i++){
        visible [i]= i;
    };
    layer.setVisibleLayers(visible);

};*/
//执法船
function showZboat(){
    Zboat = '../resources/cmd/image/zboat.png';
    show(ZBOAT_DATA,Zboat);
};

//采沙船
function showCboat(){
    Cboat = '../resources/cmd/image/cboat.png';
    show(CBOAT_DATA,Cboat);
};

//运沙船
function showYboat(){
    Yboat = '../resources/cmd/image/yboat.png';
    show(YBOAT_DATA,Yboat);
};
//执法车
function showZcar(){
    Zcar = '../resources/cmd/image/che.png';
    show(ZCAR_DATA,Zcar);
};

//执法人
function showPeople(){
    People = '../resources/cmd/image/people.png';
    show(PEOPLE_DATA,People);
};
//摄像头
function showSX(){
    Sx = '../resources/cmd/image/cream.png';
    show(SX_DATA,Sx);
};
//执法区域
function showArea(ring){
    var polygon = new esri.geometry.Polygon(new esri.SpatialReference({ wkid: 4326 }));
    polygon.addRing(ring);
    var symbol = new esri.symbol.SimpleFillSymbol(esri.symbol.SimpleFillSymbol.STYLE_SOLID, new esri.symbol.SimpleLineSymbol(esri.symbol.SimpleLineSymbol.STYLE_DASHDOT, new dojo.Color([125,129,130]),2), new dojo.Color([255,0,0,0.25]));
    var graphic = new esri.Graphic(polygon, symbol);
    allMap.graphics.add(graphic);

}

//显示路线
function showProute(prouteJson){
    var polyline=new esri.geometry.Polyline(prouteJson);
    var sys=new esri.symbol.SimpleLineSymbol(esri.symbol.SimpleLineSymbol.STYLE_SOLID,new esri.Color('red'),3);
    var graphic=new esri.Graphic(polyline,sys);
    allMap.graphics.add(graphic);
}


//轨迹回放
function showRoute(polylineJson,img) {
    var polyline=new esri.geometry.Polyline(polylineJson);
    var sys=new esri.symbol.SimpleLineSymbol(esri.symbol.SimpleLineSymbol.STYLE_DASH,new esri.Color([0,255,0]),3);
    var graphic2=new esri.Graphic(polyline,sys);
    allMap.graphics.add(graphic2);
    var point=new esri.geometry.Point(118.23538,25.49264,new esri.SpatialReference({wkid:4326}));
    var pictureMarkerSymbol=new esri.symbol.PictureMarkerSymbol(img,25,51);
    graphic=new esri.Graphic(point,pictureMarkerSymbol);
    allMap.graphics.add(graphic);

    if(typeof(moving)!="undefined"){
        clearInterval(moving); //清除移动
    }
    points = polylineJson.paths[0];
    graphic.geometry.x = points[0][0];
    graphic.geometry.y = points[0][1];
    allMap.graphics.redraw();
    move(0,1);
}

function move(start,end){
    var x1=points[start][0];
    var y1=points[start][1];
    var x2=points[end][0];
    var y2=points[end][1];

    var p=(y2-y1)/(x2-x1);//斜率
    var v=0.00001;//距离  距离越小 位置越精确
    moving=setInterval(function(){
        startNum=start;
        endNum=end;
        //分别计算 x,y轴方向速度
        if(Math.abs(p)==Number.POSITIVE_INFINITY){//无穷大
            graphic.geometry.y+=v;
        }
        else{
            if(x2<x1){
                graphic.geometry.x-=(1/Math.sqrt(1+p*p))*v;
                graphic.geometry.y-=(p / Math.sqrt(1 + p * p)) * v;
                //计算汽车角度
                graphic.symbol.angle =CalulateXYAnagle(x1,y1,x2,y2); //// (Math.PI / 2 - Math.atan(p)) * 180 / Math.PI+180
            }
            else{
                graphic.geometry.x+=(1/Math.sqrt(1+p*p))*v;
                graphic.geometry.y+=(p / Math.sqrt(1 + p * p)) * v;
                //计算汽车角度
                graphic.symbol.angle =CalulateXYAnagle(x1,y1,x2,y2); ////(Math.PI / 2 - Math.atan(p)) * 180 / Math.PI
            }
        }
        //图层刷新
        allMap.graphics.redraw();
        if (Math.abs(graphic.geometry.x - x2) <=0.00001 && Math.abs(graphic.geometry.y - y2) <=0.00001) {
            clearInterval(moving);
            startNum=start++;
            endNum=end++;
            if (end < points.length)
                move(start, end);
        }
    }, 50);
}
function CalulateXYAnagle(startx,starty,endx,endy){
    var tan=Math.atan(Math.abs((endy-starty)/(endx-startx)))*180/Math.PI+90;
    if (endx > startx && endy > starty)//第一象限
    {
        return -tan+180;
    }
    else if (endx > startx && endy < starty)//第二象限
    {
        return tan;
    }
    else if (endx < startx && endy > starty)//第三象限
    {
        return tan - 180;
    }
    else
    {
        return - tan;
    }
}



//清除图层
function remove(type,img,style){
    for(var i=allMap.graphics.graphics.length-1;i>0;i--){
        if(allMap.graphics.graphics[i].geometry.type==type){
            if(type=='point'){
                if(allMap.graphics.graphics[i].symbol.url==img){
                    allMap.graphics.remove(allMap.graphics.graphics[i]);
                }
            }
            if(type=='polyline'){
                if(allMap.graphics.graphics[i].symbol.style==style){
                    allMap.graphics.remove(allMap.graphics.graphics[i]);
                }
            }
            if(type=='polygon'){
                allMap.graphics.remove(allMap.graphics.graphics[i]);
            }

        }
    }
}

/*
function removePolyline(style){
    for(var i=allMap.graphics.graphics.length-1;i>0;i--){
        if(allMap.graphics.graphics[i].geometry.type == 'polyline'){
            if(allMap.graphics.graphics[i].symbol.style==style){
                allMap.graphics.remove(allMap.graphics.graphics[i]);
            }
        }
    }
}

function removePlygon(){
    for(var i=allMap.graphics.graphics.length-1;i>0;i--){
        if(allMap.graphics.graphics[i].geometry.type == 'polygon'){
            allMap.graphics.remove(allMap.graphics.graphics[i]);
        }
    }
}



*/

