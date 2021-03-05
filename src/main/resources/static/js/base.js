var SERVER_URL = "http://localhost:8888";
// 自动包含加密js
document.write ('<script src=" /js/cryptojs/rollups/aes.js"></script>');
/**
 * 封装请求，同意验证后台处理，添加基础路径
 */
function http(param, successCallback, failureCallback,dataEncrypt,resultDecrypt) {
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
    if(dataEncrypt){
        param.data = Encrypt(param.data);
    }
    param.success = function (res) {
        if(resultDecrypt){
           res = JSON.parse(Decrypt(res));
        }
        if (res.status == '1') {
            successCallback(res);
        } else if (res.status == '0') {
            failureCallback(res);
        } else{
            console.log("请求未找到匹配状态")
        }
    };
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
 * AES 解密
 * @param word 密文
 * @returns {string} 明文
 */
function Decrypt(word) {
    var key  = CryptoJS.enc.Utf8.parse(aesKey());
    var iv   = CryptoJS.enc.Utf8.parse(aesIv());
    var decrypted =CryptoJS.AES.decrypt(word,key,
        {
            iv:iv,
            mode:CryptoJS.mode.CBC,
            padding:CryptoJS.pad.Pkcs7
        });
    return decrypted.toString(CryptoJS.enc.Utf8);
}

/**
 * AES 加密
 * @param word 密文
 * @returns {string} 明文
 */
function Encrypt(word) {
    var key  = CryptoJS.enc.Utf8.parse(aesKey());
    var iv   = CryptoJS.enc.Utf8.parse(aesIv());
    var encrypted =CryptoJS.AES.encrypt(word,key,
        {
            iv:iv,
            mode:CryptoJS.mode.CBC,
            padding:CryptoJS.pad.Pkcs7
        });
    return encrypted.toString();
}

function aesKey(){
    return sessionStorage.getItem("transportAesKey");
}

function aesIv(){
    return sessionStorage.getItem("transportAesIv");
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