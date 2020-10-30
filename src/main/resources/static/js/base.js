var SERVER_URL = "http://localhost:8888";

/**
 * 封装请求，同意验证后台处理，添加基础路径
 */
function http(param, successCallback, failureCallback) {
    if(successCallback == null){
        successCallback = function (res) {
            console.log(res)
        }
    }
    if(failureCallback == null){
        failureCallback = function (res) {
            console.log(res)
        }
    }
    param.success = function (res) {
        if (res.status == '1') {
            successCallback(res);
        } else if (res.status == '0') {
            failureCallback(res);
        } else{
            console.log("请求未找到匹配状态")
        }
    }
    param.url = SERVER_URL + param.url;
    $.ajax(param);
}

function showSuccess(message,after) {
    layui.use(["layer"],function () {
        var layer = layui.layer;
        if(message != null && message !== ''){
            layer.msg(message,{time:2000,icon:6});
            if(after != null){
                setTimeout(after,2000);
            }
        }else{
            throw new Error("messages不能为null");
        }
    })
}

function showError(message,after) {
    layui.use(["layer"],function () {
        var layer = layui.layer;
        if(message != null && message !== '') {
            layer.msg(message, {time: 2000, icon: 5, shift: 6});
            if(after != null){
                setTimeout(after,2000);
            }
        }
    })
}

/**
 * layer打开页面
 * @param windowTitle 页面标题
 * @param url 页面路径
 * @param width 宽度
 * @param height 高度
 */
function openPage(windowTitle,url,width,height){
    layui.use(['layer'],function(){
        var layer = layui.layer;
        if(width == null || width === ''){
            width = '80%';
        }
        if(height == null || height ===''){
            height = '60%';
        }

      layer.open({
            type:2,
            fix:false,
            area:[width,height],
            shadeClose:true,
            time:0,
            shade:0.4,
            title:windowTitle,
            resize:false,
            content:[SERVER_URL+url]
        });
    })
}

/**
 * 关闭当前layer打开的页面
 */
function closePage() {
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}