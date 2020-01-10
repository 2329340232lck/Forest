// layui.use(['layer', 'jquery'], function () {
//     var layer = layui.layer, $ = layui.jquery;
    $.ajaxSetup({
        global: true,                  // 布尔值，规定是否为请求触发全局 AJAX 事件处理程序。默认是 true。
        cache: false,                  // 布尔值，表示浏览器是否缓存被请求页面。默认是 true。
        async: true,                   // 布尔值，表示请求是否异步处理。默认是 true。
        timeout: 30000,                 // 设置本地的请求超时时间（以毫秒计）。
        beforeSend: function (request) {
        },
        success: function (resultInfoVo) {
            console.info("----------------------before send ajax------------------------------------");
        },
        complete: function (xmlHttpRequest, textStatus) {
            //当请求完成之后调用这个函数，无论成功或失败
            console.info("----------------------after ajax(end showDoing)------------------------------------");
        },
        error: function (xmlHttpRequest) {
            console.log("-----------------------error ajax--------------------------");
            let data = xmlHttpRequest.responseJSON;
            if (data.code == "504") {
                layer.prompt({
                    formType: 1,
                    title: xmlHttpRequest.responseJSON.resultMessage
                }, function (value, index, elem) {
                    alert(value); //得到value
                    layer.close(index);
                });
            } else if (data.code == '500') {
                layer.msg(data.resultMessage, {title: '错误', icon: 2})
            }
        }
    });
// });
//处理一些操作的工具类
//@Author lck
/**
 * @p 根据传入的键值获取字典列表
 * @param optionKey
 */
function getDictionariesValue(optionKey) {
    var data = null;
    $.ajax({
        url: '/dictionaries/queryDictionInfoByKey.ajax',
        data: {dictionariesTable: optionKey},
        async: false,
        success: function (res) {
            data = res.resultData;
        }
    });
    return data;
}

/**
 * 循环拼接optionValue
 * @param object
 */
function foreachOptionValue(value) {
    var str = '';
    for (var i in value) {
        var n = value[i];
        str += '<option value="' + n.dictionariesKey + '">' + n.dictionariesValue + '</option>'
    }
    return str;
}

/**
 * 功能等于上面两个函数合体
 */
function getOptionValue(optionKey) {
    var value = getDictionariesValue(optionKey);
    var str = foreachOptionValue(value);
    return str;
}
