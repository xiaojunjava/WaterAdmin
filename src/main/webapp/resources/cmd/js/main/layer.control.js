/**
 * Created by Administrator on 2017/10/24 0024.
 * 监控点显示
 */
var Sx = '../resources/cmd/image/cream.png';
var ZBOAT_DATA={
    total:4,
    items:[{id:1,name:"执法船001",Y:120.479160,X:32.043300},
        {id:2,name:"执法船006",Y:120.666280, X:32.000220},
        {id:3,name:"执法船004",Y:120.685160,X:32.000510},
        {id:4,name:"执法船003",Y:120.762060,X:31.976630}
    ]
};
var CBOAT_DATA={
    total:4,
    items:[{id:1,name:"采砂船001",Y:120.726360,X:32.004580},
        {id:2,name:"采砂船002",Y:120.578730, X:32.024960},
        {id:3,name:"采砂船003",Y:120.704730,X:32.027870},
        {id:4,name:"采砂船004",Y:120.570830,X:31.999340}
    ]
};

var YBOAT_DATA={
    total:4,
    items:[{id:1,name:"运砂船001",Y:120.497360,X:32.062790},
        {id:2,name:"运砂船002",Y:120.769960, X:31.970220},
        {id:3,name:"运砂船003",Y:120.714680,X:32.036310},
        {id:4,name:"运砂船004",Y:120.787120,X:31.998470}
    ]
};

var ZCAR_DATA={
    total:4,
    items:[{id:1,name:"执法车001",Y:120.478130,X:31.996430},
        {id:2,name:"执法车002",Y:120.551610, X:31.994100},
        {id:3,name:"执法车003",Y:120.595890,X:31.948380},
        {id:4,name:"执法车004",Y:120.732540,X:31.980710}
    ]
};
var PEOPLE_DATA={
    total:4,
    items:[{id:1,name:"张三",Y:120.471950,X:32.022340},
        {id:2,name:"李四",Y:120.603450, X:31.982160},
        {id:3,name:"小明",Y:120.430760,X:31.982450},
        {id:4,name:"大华",Y:120.570490,X:31.985950}
    ]
};

var SX_DATA={
    total:3,
    items:[{id:1,name:"长江云1号",Y:120.379260,X:31.980410},
        {id:2,name:"长江云2号",Y:120.436590, X:32.019430},
        {id:3,name:"长江云3号",Y:120.838540,X:31.817410}
    ]
};


polylineJson={
    "paths": [[[120.449980,31.993520], [120.447920,32.007640], [120.443970,32.018410],
        [120.445350,32.028600],[120.454790,32.036600], [120.469720,32.038200],
        [120.485690,32.035580], [120.502000,32.029620], [120.517620,32.023070],
        [120.534100,32.017830],[120.547660,32.014190],[120.560020,32.011420]]],
    "spatialReference":{"wkid":4326}
};
people_routeJson={
    "paths": [[[120.449980,31.993520], [120.447920,32.007640], [120.443970,32.018410],
        [120.445350,32.028600],[120.454790,32.036600], [120.469720,32.038200],
        [120.485690,32.035580], [120.502000,32.029620], [120.517620,32.023070],
        [120.534100,32.017830],[120.547660,32.014190],[120.560020,32.011420]]],
    "spatialReference":{"wkid":4326}
};
prouteJson={
    "paths": [[[120.449980,31.993520], [120.447920,32.007640], [120.443970,32.018410],
        [120.445350,32.028600],[120.454790,32.036600], [120.469720,32.038200],
        [120.485690,32.035580], [120.502000,32.029620], [120.517620,32.023070],
        [120.534100,32.017830],[120.547660,32.014190],[120.560020,32.011420],
        [120.572890,32.009390],[120.585940,32.007200],[120.597950,32.003710]]],
    "spatialReference":{"wkid":4326}
};
var ring = [[120.506800,32.020160],
    [120.518130,32.015210],[120.530830,32.009530],[120.542850,32.006040],
    [120.556760,31.999920],[120.567570,31.998030],[120.577530,31.997160],
    [120.587830,31.994540],[120.595890,31.993520],[120.605340,31.991330],
    [120.628850,31.990750],[120.657520,31.992060],[120.682240,31.993810],
    [120.699410,31.996140],[120.713480,31.994390],[120.729100,31.991190],
    [120.733650,31.997450],[120.681720,32.008730],[120.654520,32.005310],
    [120.636840,32.003050],[120.619150,32.006620],[120.597180,32.012520],
    [120.573750,32.018850],[120.543450,32.025540],[120.506800,32.020160]];

function show(data,image){
    var items= data.items;
    for(var i=0;i<data.total;i++){
        var pt=new esri.geometry.Point(items[i].Y,items[i].X,new esri.SpatialReference({wkid:4326}));
        var symbol = new esri.symbol.PictureMarkerSymbol(image, 25, 25);
        var graphic = new esri.Graphic(pt,symbol,items[i]);
        allMap.graphics.add(graphic);
    }
};

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
    var point=new esri.geometry.Point(polylineJson.paths[0][0],new esri.SpatialReference({wkid:4326}));
    var pictureMarkerSymbol=new esri.symbol.PictureMarkerSymbol(img,25,51);
    graphic=new esri.Graphic(point,pictureMarkerSymbol);
    allMap.graphics.add(graphic);
    allMap.centerAt(point);
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
    if(x2-x1==0){
        var p=0;
    }else{
        var p=(y2-y1)/(x2-x1);//斜率
    }
    if(p==0){
        startNum=start++;
        endNum=end++;
        if (end < points.length)
            move(start, end);
    }
    else{
        var v=0.001;//距离  距离越小 位置越精确
        moving=setInterval(function(){
            startNum=start;
            endNum=end;
            //分别计算 x,y轴方向速度
            if(Math.abs(p)==Number.POSITIVE_INFINITY||p==0){//无穷大
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
            if (Math.abs(graphic.geometry.x - x2) <=v && Math.abs(graphic.geometry.y - y2) <=v) {
                clearInterval(moving);
                startNum=start++;
                endNum=end++;
                if (end < points.length)
                    move(start, end);
            }
        }, 75);
    }

}

//车头转向
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
    for(var i=allMap.graphics.graphics.length;i>0;i--){
        if(allMap.graphics.graphics[i-1].geometry.type==type){
            if(type=='point'){
                if(allMap.graphics.graphics[i-1].symbol.url==img){
                    allMap.graphics.remove(allMap.graphics.graphics[i-1]);
                }
            }
            if(type=='polyline'){
                if(allMap.graphics.graphics[i-1].symbol.style==style){
                    allMap.graphics.remove(allMap.graphics.graphics[i-1]);
                }
            }
            if(type=='polygon'){
                allMap.graphics.remove(allMap.graphics.graphics[i-1]);
            }
        }
    }
}


