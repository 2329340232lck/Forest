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
            resourceName: '',
            resourcePreventType: '',
            resourceType: '',
            total: 0,
            size: 10,
            current: 0,
        },
        //表单数据
        formData: {
            resourceName: '',
            resourcePreventType: '',
            resourceType: '',
            resourcePurpose: '',
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
            resourceType: [],
            resourcePreventType: [],
        },
        //表单验证
        submitRules: {
            resourceName: [{required: true, message: '请输入资源名称！', trigger: 'change'}],
            resourcePreventType: [{required: true, message: '请选择防治类型！', trigger: 'change'}],
            resourceType: [{required: true, message: '请选择资源类型!', trigger: 'change'}],
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
                url: '/dictionaries/queryDictionInfoByKey.ajax',
                data: {dictionariesTable: 'resourceType'},
                success: function (res) {
                    // debugger;
                    that.dataCache.resourceType = res.resultData;
                }
            });
            $.ajax({
                url: '/dictionaries/queryDictionInfoByKey.ajax',
                data: {dictionariesTable: 'resourcePreventType'},
                success: function (res) {
                    that.dataCache.resourcePreventType = res.resultData;
                }
            });
        },
        //初始化表格
        initTable: function () {
            $.ajax({
                url: '/equipment/queryEquipmentInfo.ajax',
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
                    let url = that.dialogCache.isInsert ? '/equipment/insertEquipmentInfo.ajax'
                        : '/equipment/updateEquipmentInfo.ajax';
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
        //添加
        openInsert: function () {
            that.dialogCache = {
                title: '新增',
                visible: true,
                isInsert: true
            };
            that.formData = {
                resourceName: '',
                resourcePreventType: '',
                resourceType: '',
                resourcePurpose: '',
            }
        },
        //删除
        deleteInfo: function (row) {
            that.$confirm('确认删除吗？', '删除', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(function () {
                $.ajax({
                    url: '/equipment/deleteEquipmentInfo.ajax',
                    data: {resourceId: row.resourceId},
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
            // console.log(row);
            that.dialogCache = {
                title: '编辑',
                visible: true,
                isInsert: false,
            };
            that.formData = {
                resourceId: row.resourceId,
                resourceName: row.resourceName,
                resourcePreventType: row.resourcePreventType,
                resourceType: row.resourceType,
                resourcePurpose: row.resourcePurpose,
            }
        },
        //重置表单
        resetForm: function (form) {
            that.formData = {
                resourceName: '',
                resourcePreventType: '',
                resourceType: '',
                resourcePurpose: '',
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

