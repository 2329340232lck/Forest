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
            gradeName: '',
            areaName: '',
            total: 0,
            size: 10,
            current: 0,
        },
        //表单数据
        formData: {
            gradeName: '',
            gradeLeader: '',
            gradeLeaderPhone: '',
            gradeNumber: 0,
            areaId: '',
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
            areaName: [],
        },
        //表单验证
        submitRules: {
            gradeName: [{required: true, message: '请输入名称！', trigger: 'change'}],
            gradeLeader: [{required: true, message: '请输入负责人！', trigger: 'change'}],
            gradeLeaderPhone: [{required: true, message: '请输入负责人手机号!', trigger: 'change'}],
            gradeNumber: [{required: true, message: '人员不能为空！', trigger: 'change'}],
            areaId: [{required: true, message: '请选择区域！', trigger: 'change'}]
        }
    },
    //VUE钩子函数，在DOM装载完成后调用
    mounted: function () {
        that = this;
        this.$options.methods.initDataCache();
        this.$options.methods.initTable();
    },
    //methods存放事件方法函数
    methods: {
        //初始化数据;
        initDataCache: function () {
            $.ajax({
                url: '/area/queryAreaInfo.ajax',
                success: function (res) {
                    // debugger;
                    // console.log(res.resultData.records);
                    that.dataCache.areaName = res.resultData.records;
                }
            })
        },
        //初始化表格
        initTable: function () {
            $.ajax({
                url: '/grade/queryGradeInfo.ajax',
                data: that.dataQuery,
                success: function (res) {
                    let data = res.resultData;
                    if (that.dataQuery.current > data.pages) {
                        that.dataQuery.current = data.pages;
                        that.initTable();
                    }
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
                gradeName: '',
                gradeLeader: '',
                gradeLeaderPhone: '',
                gradeNumber: 0,
                areaId: '',
            }
        },
        //表单提交
        submitForm: function (form) {
            that.$refs[form].validate((valid) => {
                if (valid) {
                    let url = that.dialogCache.isInsert ? '/grade/insertGradeInfo.ajax' : '/grade/updateGradeInfo.ajax';
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
                    url: '/grade/deleteGradeInfo.ajax',
                    data: {gradeId: row.gradeId},
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
            console.log(row);
            that.dialogCache = {
                title: '编辑',
                visible: true,
                isInsert: false,
            };
            that.formData = {
                gradeId: row.gradeId,
                gradeName: row.gradeName,
                gradeLeader: row.gradeLeader,
                gradeLeaderPhone: row.gradeLeaderPhone,
                gradeNumber: row.gradeNumber,
                areaId: row.areaId,
            }
        },
        //重置表单
        resetForm: function (form) {
            that.formData = {
                gradeName: '',
                gradeLeader: '',
                gradeLeaderPhone: '',
                gradeNumber: 0,
                areaId: '',
            };
            that.$nextTick(function () {
                that.$refs[form].clearValidate();
            });
        },
        //分页变更
        sizeChangeHandle: function (size) {
            that.dataQuery.size = size;
            that.dataQuery.current = 1;
            that.initTable();
        },
        //当前页变更
        currentChangeHandle: function (current) {
            that.dataQuery.current = current;
            that.initTable();
        },
    }
});

