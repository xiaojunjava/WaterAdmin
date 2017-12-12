/* --------------------------------地图初始信息配置-------------------------------- */
function MapConfig() { }
MapConfig.mapInitParams = {
    fullExtent: {//全图范围
        xmin: 121.29585473952106,
        ymin: 39.49550677343447,
        xmax: 123.05162192862765,
        ymax: 139.982938778863684
    },
    extent: {//初始化范围
        xmin: 120.8696333,
        ymin: 38.65953686,
        xmax: 123.6199347,
        ymax: 40.202622,
    },
    spatialReference: {//地图空间参考坐标系
        wkid: 4326
    },
    lods: [//针对瓦片的地图服务的,用来控制瓦片级别的显示，有时候切片级别太多的话，可以只显示部分的级别地图
           //resolution scale这些值的获取参照发布的切片地图服务详情
           { "level": 0, "resolution": 0.00118973050291514, "scale": 500000 },
           { "level": 1, "resolution": 5.9486525145757E-4, "scale": 250000 },
           { "level": 2, "resolution": 2.3794610058302802E-4, "scale": 100000 },
           { "level": 3, "resolution": 5.710706413992673E-5, "scale": 24000 },
           { "level": 4, "resolution": 2.3794610058302804E-5, "scale": 10000 }
    ]
}
/*导航条配置参数*/
MapConfig.sliderConfig = {
    targetId: "mapDiv",
    minValue: 0,     
    maxValue: 4,    
    startValue: 0,  
    toolbarCss: ["toolBar", "toolBar_button", "toolBar_slider", "toolBar_mark"],
    marksShow: {
        countryLevel: null,
        provinceLevel: null,
        cityLevel: null,
        streetLevel: null
    }
};
/*地图调用*/
MapConfig.vecMapUrl = "http://192.168.1.144:6080/arcgis/rest/services/德化县/MapServer";//ArcGIS动态服务
/*图层目录构造*/
MapConfig.zNodes = [
    { id: 1, pId: 0, name: "图层目录", checked: true, iconOpen: "" + getRootPath() + "Content/images/legend/1_open.png", iconClose: "" + getRootPath() + "Content/images/legend/1_close.png" },
    { id: 11, pId: 1, name: "医院诊所", layerurl: MapConfig.vecMapUrl, layerid: "layer10", checked: true, icon: "" + getRootPath() + "Content/images/legend/4.png" },
    { id: 12, pId: 1, name: "消防中队", layerurl: MapConfig.vecMapUrl, layerid: "layer12", checked: true, icon: "" + getRootPath() + "Content/images/legend/5.png" },
    { id: 13, pId: 1, name: "公安交警", layerurl: MapConfig.vecMapUrl, layerid: "layer13", checked: true, icon: "" + getRootPath() + "Content/images/legend/2.png" },
    { id: 14, pId: 1, name: "宾馆酒店", layerurl: MapConfig.vecMapUrl, layerid: "layer20", checked: true, icon: "" + getRootPath() + "Content/images/legend/3.png" },
    { id: 15, pId: 1, name: "超市", layerurl: MapConfig.vecMapUrl, layerid: "layer21", checked: true, icon: "" + getRootPath() + "Content/images/legend/1.png" },
    { id: 16, pId: 1, name: "加油站", layerurl: MapConfig.vecMapUrl, layerid: "layer27", checked: true, icon: "" + getRootPath() + "Content/images/legend/2.png" }
];