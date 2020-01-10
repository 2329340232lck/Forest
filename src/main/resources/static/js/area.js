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
            areaName: '',
            areaForestType: '',
            gradeName: '',
            total: 0,
            size: 10,
            current: 0,
        },
        //表单数据
        formData: {
            areaName: '',
            areaForestType: '',
            areaDominantSpecies: '',
            areaType: '',
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
            areaType: [],
        },
        //表单验证
        submitRules: {
            areaName: [{required: true, message: '请输入名称！', trigger: 'change'}],
            areaForestType: [{required: true, message: '请输入林种！', trigger: 'change'}],
            areaDominantSpecies: [{required: true, message: '请输入优势树种!', trigger: 'change'}],
            areaType: [{required: true, message: '请选择地类！', trigger: 'change'}],
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
                data: {dictionariesTable: 'areaType'},
                success: function (res) {
                    that.dataCache.areaType = res.resultData;
                }
            })
        },
        //初始化表格
        initTable: function () {
            $.ajax({
                url: '/area/queryAreaInfo.ajax',
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
                areaName: '',
                areaForestType: '',
                areaDominantSpecies: '',
                areaType: '',
            }
        },
        //表单提交
        submitForm: function (form) {
            that.$refs[form].validate((valid) => {
                if (valid) {
                    let url = that.dialogCache.isInsert ? '/area/insertAreaInfo.ajax' : '/area/updateAreaInfo.ajax';
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
                    url: '/area/deleteAreaInfo.ajax',
                    data: {areaId: row.areaId},
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
                areaId: row.areaId,
                areaName: row.areaName,
                areaForestType: row.areaForestType,
                areaDominantSpecies: row.areaDominantSpecies,
                areaType: row.areaType,
            }
        },
        //重置表单
        resetForm: function (form) {
            that.formData = {
                areaName: '',
                areaForestType: '',
                areaDominantSpecies: '',
                areaType: '',
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

