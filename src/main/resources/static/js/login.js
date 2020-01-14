var that;
new Vue({
    el: '#home_index',
    data: {
        formData: {
            userName: '',
            userPassword: '',
        }
    },
    mounted: function () {
        that = this;
    },
    methods: {
        login() {
            $.ajax({
                url: '/user/login',
                type: 'POST',
                data: this.formData,
                success: function (res) {
                    that.$message.success('登录成功!');
                    localStorage.setItem("user", JSON.stringify(res.resultData));
                    window.location.href = '/index.html';
                },
                error: function (xhr) {
                    let result = xhr.responseJSON;
                    that.$message({
                        type: 'error',
                        message: '错误码:' + result.code + '\n' + result.resultMessage,
                    });
                }
            })
        }
    }
});