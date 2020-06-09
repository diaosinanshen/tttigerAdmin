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
        if (res.status == 1) {
            successCallback(res);
        } else if (res.status == 0) {
            failureCallback(res);
        } else{
            console.log("请求未找到匹配状态")
        }
    }
    param.url = SERVER_URL + param.url;
    $.ajax(param);
}

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
            area:[width,height],
            fix:false,
            maxmin:true,
            shadeClose:true,
            isOutAnim:false,
            time:0,
            shade:0.4,
            title:windowTitle,
            resize:false,
            content:[SERVER_URL+"/role/to-role-add","no"]
        })
    })
}
