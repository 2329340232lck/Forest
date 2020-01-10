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
            mouseName: '',
            total: 0,
            size: 10,
            current: 0,
        },
        //表单数据
        formData: {
            mouseName: '',
            mouseFood: '',
            mouseBreed: '',
            mouseEnemy: '',
            mouseControlPlan: '',
            mouseHarm: '',
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
            mouseName: [{required: true, message: '请输入鼠害名称！', trigger: 'change'}],
            mouseFood: [{required: true, message: '请输入食物！', trigger: 'change'}],
            mouseBreed: [{required: true, message: '请输入繁殖方法！', trigger: 'change'}],
            mouseEnemy: [{required: true, message: '请输入天敌！', trigger: 'change'}],
            mouseControlPlan: [{required: true, message: '请输入控制方案！', trigger: 'change'}]
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
                url: '/mouse/queryMouseInfo.ajax',
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
                mouseName: '',
                mouseFood: '',
                mouseBreed: '',
                mouseEnemy: '',
                mouseControlPlan: '',
                mouseHarm: '',
            };
        },
        //表单提交
        submitForm: function (form) {
            that.$refs[form].validate((valid) => {
                if (valid) {
                    let url = that.dialogCache.isInsert ? '/mouse/insertMouseInfo.ajax' : '/mouse/updateMouseInfo.ajax';
                    $.ajax({
                        url: url,
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
                    url: '/mouse/deleteMouseInfo.ajax',
                    data: {mouseId: row.mouseId},
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
                mouseId: row.mouseId,
                mouseName: row.mouseName,
                mouseFood: row.mouseFood,
                mouseBreed: row.mouseBreed,
                mouseEnemy: row.mouseEnemy,
                mouseControlPlan: row.mouseControlPlan,
                mouseHarm: row.mouseHarm,
            };
        },
        //重置表单
        resetForm: function (form) {
            that.formData = {
                mouseName: '',
                mouseFood: '',
                mouseBreed: '',
                mouseEnemy: '',
                mouseControlPlan: '',
                mouseHarm: '',
            };
            that.$nextTick(function () {
                that.$refs[form].clearValidate();
            });
        }
    }
});