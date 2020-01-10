var that;
new Vue({
    //标识渲染哪个元素
    el: '#main',
    data: {
        //表格数据
        tableData: [],
        //查询数据
        dataQuery: {
            resourceName: '',
            resourcePreventType: '',
            resourceType: '',
            startDate: '',
            endDate: '',
            total: 0,
            size: 10,
            current: 0,
        },
        //表单数据
        formData: {
            gradeId: '',
            userId: '',
            resources: [],
        },
        //子表单数据
        childData: {
            resourceId: null,
            resourceName: '',
            resourceNumber: 0,
            resourceType: '',
            resourceTypeName: '',
            resourcePreventType: '',
            resourcePreventTypeName: '',
        },
        //加载层
        loading: true,
        loading2: false,
        //对话框缓存数据
        dialogCache: {
            title: '',
            visible: false,
            visible2: false,
            isInsert: false,
        },
        //数据缓存
        dataCache: {
            dateCache: '',
            userName: '',
            resourceId: '',
            gradeCache: [],
            resourceCache: [],
        },
        //表单验证
        submitRules: {
            gradeId: [{required: true, message: '请选择领用小班！', trigger: 'change'}],
        },
        childFormRules: {
            resourceId: [{required: true, message: '请选择设备!', trigger: 'change'}],
            resourceNumber: [{required: true, message: '设备数量不能为空！', trigger: 'change'}]
        },
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
                url: '/grade/queryGradeInfo.ajax',
                success: function (res) {
                    // debugger;
                    that.dataCache.gradeCache = res.resultData.records;
                }
            });
            $.ajax({
                url: '/equipment/queryEquipmentInfo.ajax',
                success: function (res) {
                    // debugger;
                    that.dataCache.resourceCache = res.resultData.records;
                }
            });
        },
        //初始化表格
        initTable: function () {
            $.ajax({
                url: '/storeroom/queryStoreroomInfo.ajax',
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
        //表单提交
        submitForm: function (form) {
            that.$refs[form].validate((valid) => {
                if (valid) {
                    let url = that.dialogCache.isInsert ? '/storeroom/insertStoreroomInfo.ajax'
                        : '/storeroom/updateStoreroomInfo.ajax';
                    if (that.formData.resources.length == 0) {
                        this.$message({
                            message: '请至少添加一条出库记录！',
                            type: 'warning'
                        });
                        return;
                    }
                    $.ajax({
                        url: url,
                        //注意，ajax发送json数据需要设置contentType为application/json
                        contentType: 'application/json;charset=UTF-8',
                        data: JSON.stringify(that.formData),
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
        //时间选择
        dateChangeHandle: function (value) {
            if (value != null) {
                that.dataQuery.startDate = value[0];
                that.dataQuery.endDate = value[1];
            } else {
                that.dataQuery.startDate = '';
                that.dataQuery.endDate = '';
            }
        },
        //添加
        openInsert: function () {
            that.dialogCache = {
                title: '新增',
                visible: true,
                visible2: false,
                isInsert: true
            };
            let data = JSON.parse(localStorage.getItem("user"));
            that.formData.userId = data.userId;
            that.dataCache.userName = data.userRealName;
        },
        //编辑
        editInfo: function (row) {
            that.dialogCache = {
                title: '编辑',
                visible: true,
                visible2: false,
                isInsert: false,
            };
            that.formData.userId = row.userId;
            that.dataCache.userName = row.userName;
            that.loading2 = true;
            $.ajax({
                url: '/storeroom/getResources.ajax',
                data: {key: row.storeroomId},
                success: function (res) {
                    that.loading2 = false;
                    that.formData = {
                        storeroomId: row.storeroomId,
                        gradeId: row.gradeId,
                        userId: row.userId,
                        resources: res.resultData,
                    };
                }
            });
        },
        //删除
        deleteInfo: function (row) {
            that.$confirm('确认删除吗？', '删除', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(function () {
                $.ajax({
                    url: '/storeroom/deleteStoreroomInfo.ajax',
                    data: {storeroomId: row.storeroomId},
                    success: function () {
                        that.$message({message: '删除完成', type: 'success'});
                        that.initTable();
                    }
                });
            }).catch(function () {
                that.$message('取消删除');
            });
        },
        //处理选择变更
        selectChangeHandle: function (value) {
            that.childData.resourceId = value.resourceId;
            that.childData.resourceName = value.resourceName;
            that.childData.resourceType = value.resourceType;
            that.childData.resourceTypeName = value.resourceTypeName;
            that.childData.resourcePreventType = value.resourcePreventType;
            that.childData.resourcePreventTypeName = value.resourcePreventTypeName;
        },
        //打开新增设备表单
        openInsertResource: function (form) {
            that.dialogCache.visible2 = true;
            this.$nextTick(() => {
                //移除表单验证
                this.$refs[form].clearValidate();
            })
        },
        //添加设备
        insertResource: function (form) {
            this.$refs[form].validate((valida) => {
                if (valida) {
                    if (that.formData.resources.length > 0) {
                        let notFind = true;
                        let array = that.formData.resources;
                        for (let i = 0; i < array.length; i++) {
                            if (array[i].resourceId === that.childData.resourceId) {
                                array[i].resourceNumber += that.childData.resourceNumber;
                                notFind = false;
                                break;
                            }
                        }
                        if (notFind) {
                            that.formData.resources.push(that.childData);
                        }
                    } else {
                        that.formData.resources.push(that.childData);
                    }
                    that.dialogCache.visible2 = false;
                    that.$message("添加完成")
                } else {
                    return false;
                }
            })
        },
        //重置表单
        resetForm: function (form) {
            if (form === 'form') {
                that.formData = {
                    gradeId: '',
                    userId: '',
                    resources: [],
                };
            } else if (form === 'childForm') {
                that.childData = {
                    resourceId: null,
                    resourceName: null,
                    resourceNumber: 0,
                    resourceType: null,
                    resourceTypeName: null,
                    resourcePreventType: null,
                    resourcePreventTypeName: null,
                };
                that.dataCache.resourceId = '';
            }
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
        //移除资源
        removeResource: function (index, data) {
            data.splice(index, 1);
        }
    }
});

