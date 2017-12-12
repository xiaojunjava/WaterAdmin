/**
 * js获取项目根路径，如： http://localhost/GGFW/
*/
function getRootPath() {
    //获取当前网址，如： http://localhost/GGFW/
    var curWwwPath = window.document.location.href;
    //获取主机地址之后的目录
    var pathName = window.document.location.pathname;
    var pos = curWwwPath.indexOf(pathName);
    //获取主机地址
    var localhostPaht = curWwwPath.substring(0, pos);
    //获取带"/"的项目名
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
    if (projectName == "")
        projectName = pathName;
    return (localhostPaht + projectName + "/resources/cmd/");
}
function getServerPath() {
    //获取当前网址，如： http://localhost/GGFW/
    var curWwwPath = window.document.location.href;
    //获取主机地址之后的目录
    var pathName = window.document.location.pathname;
    var pos = curWwwPath.indexOf(pathName);
    //获取主机地址
    var localhostPaht = curWwwPath.substring(0, pos);
    localhostPaht = localhostPaht.replace('http://', '');
    //获取带"/"的项目名
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
    if (projectName == "")
        projectName = pathName;
    return (localhostPaht + projectName + "/resources/cmd/");
}




