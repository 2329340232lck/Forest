function initAjax() {
    if (dependencyCheck()) {
        $.ajaxSetup({
            global: true,                  // 布尔值，规定是否为请求触发全局 AJAX 事件处理程序。默认是 true。
            cache: false,                  // 布尔值，表示浏览器是否缓存被请求页面。默认是 true。
            async: true,                   // 布尔值，表示请求是否异步处理。默认是 true。
            timeout: 30000,                 // 设置本地的请求超时时间（以毫秒计）。
            beforeSend: function (request) {
                //请求发送之前调用此函数
            },
            success: function (resultInfoVo) {
                // 请求成功后调用此函数
            },
            complete: function (xmlHttpRequest, textStatus) {
                //当请求完成之后调用这个函数，无论成功或失败
            },
            error: function (xmlHttpRequest) {
                //请求出错后调用此函数
                let data = xmlHttpRequest.responseJSON;
                if (data.code === "504") {
                    layer.prompt({
                        formType: 1,
                        title: xmlHttpRequest.responseJSON.resultMessage
                    }, function (value, index, elem) {
                        alert(value); //得到value
                        layer.close(index);
                    });
                } else if (data.code === '500') {
                    layer.msg(data.resultMessage, {title: '错误', icon: 2})
                }
            }
        });
    }
}

//检查所需资源文件是否引入
function dependencyCheck() {
    if (typeof ($) === "undefined") {
        console.error("所需资源文件未引入...");
        return false;
    } else {
        return true;
    }
}

//获取本地存储库数据
function getLocalStorageData(key) {
    return JSON.parse(localStorage.getItem(key));
}

//提供给layui的一些便捷操作....
function getDictionariesValue(optionKey) {
    if (dependencyCheck()) {
        let data = null;
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
}

function foreachOptionValue(value) {
    let str = '';
    for (let i in value) {
        let n = value[i];
        str += '<option value="' + n.dictionariesKey + '">' + n.dictionariesValue + '</option>'
    }
    return str;
}

function getOptionValue(optionKey) {
    var value = getDictionariesValue(optionKey);
    var str = foreachOptionValue(value);
    return str;
}
