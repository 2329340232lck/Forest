var that;
var validate = (rule, value, callback) => {
    if (!value) {
        return callback(new Error('确认密码不能为空!'))
    } else if (value != that.formData.userPassword) {
        return callback(new Error('确认密码不一致!'))
    } else {
        callback();
    }
};
new Vue({
    //标识渲染哪个元素
    el: '#main',
    //存放绑定的数据
    data: {
        //表格数据
        tableData: [],
        //查询数据
        dataQuery: {
            roleId: '',
            total: 0,
            size: 10,
            current: 0,
        },
        //表单数据
        formData: {
            userName: '',
            userPassword: '',
            rePassword: '',
            userRealName: '',
            roleId: '',
        },
        //加载层
        loading: true,
        //对话框缓存数据
        dialogCache: {
            title: '',
            visible: false,
            isInsert: false,
        },
        //数据缓存
        dataCache: {
            userLevel: [],
        },
        //表单验证
        submitRules: {
            userName: [{required: true, message: '请输入姓名！', trigger: 'change'}],
            userPassword: [{required: true, message: '请输入密码！', trigger: 'change'}],
            rePassword: [{required: true, validator: validate, trigger: 'change'}],
            userRealName: [{required: true, message: '请输入真实姓名！', trigger: 'change'}],
            roleId: [{required: true, message: '请选择权限！', trigger: 'change'}]
        }
    },
    //VUE钩子函数，在DOM装载完成后调用
    mounted: function () {
        that = this;
        this.$options.methods.initTable();
        this.$options.methods.initDataCache();
    },
    //methods存放事件方法函数
    methods: {
        //初始化数据;
        initDataCache: function () {
            $.ajax({
                url: '/dictionaries/queryDictionInfoByKey.ajax',
                data: {dictionariesTable: 'roleId'},
                success: function (res) {
                    // debugger;
                    that.dataCache.userLevel = res.resultData;
                }
            })
        },
        //初始化表格
        initTable: function () {
            $.ajax({
                url: '/user/queryUserInfo.ajax',
                data: that.dataQuery,
                success: function (res) {
                    let data = res.resultData;
                    that.tableData = data.records;
                    that.dataQuery.total = data.total;
                    that.dataQuery.current = data.current;
                    that.loading = false;
                }
            })
        },
        //添加
        openInsert: function () {
            that.dialogCache = {
                title: '新增',
                visible: true,
                isInsert: true
            };
            that.formData = {
                userName: '',
                userPassword: '',
                rePassword: '',
                userRealName: '',
                userLevel: '',
            }
        },
        //表单提交
        submitForm: function (form) {
            that.$refs[form].validate((valid) => {
                if (valid) {
                    let url = that.dialogCache.isInsert ? '/user/insertUserInfo.ajax' : '/user/updateUserInfo.ajax';
                    $.ajax({
                        url: url,
                        type: 'POST',
                        data: that.formData,
                        success: function () {
                            that.$message({type: 'success', message: '成功'});
                            that.initTable();
                            that.dialogCache.visible = false;
                        }
                    })
                } else {
                    return false;
                }
            })
        },
        //删除
        deleteInfo: function (row) {
            that.$confirm('确认删除吗？', '删除', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(function () {
                $.ajax({
                    url: '/user/deleteUserInfo.ajax',
                    data: {userId: row.userId},
                    success: function () {
                        that.$message({message: '删除完成', type: 'success'});
                        that.initTable();
                    }
                });
            }).catch(function () {
                that.$message('取消删除');
            });
        },
        //编辑
        editInfo: function (row) {
            that.dialogCache = {
                title: '编辑',
                visible: true,
                isInsert: false,
            };
            that.formData = {
                userId: row.userId,
                userName: row.userName,
                userPassword: row.userPassword,
                rePassword: row.userPassword,
                userRealName: row.userRealName,
                userLevel: row.userLevel,
            };
        },
        //重置表单
        resetForm: function (form) {
            that.formData = {
                userName: '',
                userPassword: '',
                rePassword: '',
                userRealName: '',
                userLevel: '',
            };
            that.$nextTick(function () {
                that.$refs[form].clearValidate();
            });
        },
    }
});

