var that;
new Vue({
    el: '#home_index',
    data: {
        userName: '',
        dataCache: {
            navMenu: [],
        },
    },
    mounted: function () {
        //重定义this指针指向
        that = this;
        this.$options.methods.initAJAXSetup();
        this.$options.methods.initDataCache();
    },
    methods: {
        initAJAXSetup: function () {
            let local = JSON.parse(localStorage.getItem("user"));
            $.ajaxSetup({
                //是否触发全局ajax事件
                global: true,
                //缓存
                cache: false,
                //超时时间
                timeout: 5000,
                //请求前调用
                beforeSend: function (XHR) {
                    XHR.setRequestHeader("token", localStorage.getItem("token"));
                },
                //请求失败调用
                error: function (XHR) {
                    let data = XHR.responseJSON.responseInfo;
                    that.$message.error("错误码:" + data.code + '\n' + data.resultMessage);
                },
                //请求发送完成调用
                complete: function (XHR) {
                }
            })
        },
        //初始化页面数据
        initDataCache: function () {
            let local = JSON.parse(localStorage.getItem("user"));
            that.userName = local.userName;
            //查询导航栏
            $.ajax({
                url: '/user/queryMenuInfo.ajax',
                data: {userId: local.userId},
                success: function (res) {
                    that.dataCache.navMenu = res.resultData;
                }
            });
        },
        //处理导航栏选择
        handleSelect(index, indexPath) {
            console.log(index, indexPath);
        },
        //导航栏点击事件
        click: function (url) {
            $.ajax({
                url: url,
                type: 'get',
                success: function (res) {
                    $('#frame').html(res);
                }
            });
        },
        //用户退出
        logout() {
            this.$confirm('确认退出吗?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(function () {
                $.ajax({
                    url: '/user/logout.ajax',
                    type: 'post',
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