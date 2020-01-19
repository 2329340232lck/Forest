var that;
new Vue({
    el: '#home_index',
    data: {
        userName: '',
        dataCache: {
            navMenu: [],
        },
        socket: null,
    },
    mounted: function () {
        //重定义this指针指向
        that = this;
        initAjax();
        this.$options.methods.initDataCache();
        this.$options.methods.initWebSocket();
    },
    methods: {
        initWebSocket: function () {
            if (typeof (WebSocket) === "undefined") {
                console.error("您的浏览器不支持WebSocket服务!");
                return null;
            }
            console.log("浏览器支持WebSocket，正在启动服务.......");
            let connectionUrl = 'http://localhost:8081/webSocket/' + getLocalStorageData("user").userName;
            connectionUrl = connectionUrl.replace("https", "ws").replace("http", "ws");
            console.debug(connectionUrl);
            if (that.socket != null) {
                that.socket.close();
                that.socket = null;
            }
            socket = new WebSocket(connectionUrl);
            console.log("WebSocket通讯连接建立完成!");
            socket.onopen = function () {

            };
            socket.onerror = function () {

            };
            socket.onclose = function () {

            };
            socket.onmessage = function (msg) {
                console.log(msg);
            }
        },
        initDataCache: function () {
            let local = getLocalStorageData("user");
            that.userName = local.userName;
            $.ajax({
                url: '/user/queryMenuInfo',
                data: {userId: local.userId},
                success: function (res) {
                    that.dataCache.navMenu = res.resultData;
                }
            });
        },
        click: function (url) {
            $.ajax({
                url: url,
                type: 'GET',
                success: function (res) {
                    $('#frame').html(res);
                }
            });
        },
        userLogout() {
            this.$confirm('确认退出吗?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(function () {
                $.ajax({
                    url: '/user/logout',
                    type: 'GET',
                    success: function () {
                        localStorage.clear();
                        location.href = '/public/login.html';
                    }
                })
            }).catch(() => {
            });
        }
    }
});