var that;
new Vue({
    //标识渲染哪个元素
    el: '#main',
    //存放绑定的数据
    data: {
        //表格数据
        tableData: [],
        //查询数据
        dataQuery: {
            pestsName: '',
            pestsHost: '',
            total: 0,
            size: 10,
            current: 0,
        },
        //表单数据
        formData: {
            pestsName: '',
            pestsHost: '',
            pestsBreed: '',
            pestsEnemy: '',
            pestsHarm: '',
            pestsControlPlan: '',
        },
        //加载层
        loading: true,
        //对话框缓存数据
        dialogCache: {
            title: '',
            visible: false,
            isInsert: false,
        },
        //表单验证
        submitRules: {
            pestsName: [{required: true, message: '请输入害虫名称！', trigger: 'change'}],
            pestsHost: [{required: true, message: '请输入寄主！', trigger: 'change'}],
            pestsBreed: [{required: true, message: '请输入繁殖方式！', trigger: 'change'}],
            pestsEnemy: [{required: true, message: '请输入天敌！', trigger: 'change'}]
        }
    },
    //VUE钩子函数，在DOM装载完成后调用
    mounted: function () {
        that = this;
        this.$options.methods.initTable();
    },
    //methods存放事件方法函数
    methods: {
        //初始化数据;
        initDataCache: function () {

        },
        //初始化表格
        initTable: function () {
            $.ajax({
                url: '/pests',
                type: 'GET',
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
                pestsName: '',
                pestsHost: '',
                pestsBreed: '',
                pestsEnemy: '',
                pestsHarm: '',
                pestsControlPlan: '',
            };
        },
        //表单提交
        submitForm: function (form) {
            that.$refs[form].validate((valid) => {
                if (valid) {
                    let type = that.dialogCache.isInsert ? 'POST' : 'PUT';
                    $.ajax({
                        url: '/pests',
                        type: type,
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
                    url: '/pests',
                    type: 'DELETE',
                    data: {pestsId: row.pestsId},
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
                pestsId: row.pestsId,
                pestsName: row.pestsName,
                pestsHost: row.pestsHost,
                pestsBreed: row.pestsBreed,
                pestsEnemy: row.pestsEnemy,
                pestsHarm: row.pestsHarm,
                pestsControlPlan: row.pestsControlPlan,
            };
        },
        //重置表单
        resetForm: function (form) {
            that.formData = {
                pestsName: '',
                pestsHost: '',
                pestsBreed: '',
                pestsEnemy: '',
                pestsHarm: '',
                pestsControlPlan: '',
            };
            that.$refs[form].resetFields();
        }
    }
});