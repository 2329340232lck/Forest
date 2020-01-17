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
            expertName: '',
            expertSpeciality: '',
            expertCompany: '',
            total: 0,
            size: 10,
            current: 0,
        },
        //表单数据
        formData: {
            expertName: '',
            expertSex: '0',
            expertBirthDay: '',
            expertSpeciality: '',
            expertProfession: '',
            expertPhoneNumber: '',
            expertCompany: '',
            expertAddress: '',
            expertEmali: '',
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
            expertName: [{required: true, message: '请输入名称！', trigger: 'change'}],
            expertSex: [{required: true, message: '请选择性别！', trigger: 'change'}],
            expertBirthDay: [{required: true, message: '请输入出生年月!', trigger: 'change'}],
            expertSpeciality: [{required: true, message: '请输入专长！', trigger: 'change'}],
            expertProfession: [{required: true, message: '请输入专长！', trigger: 'change'}],
            expertCompany: [{required: true, message: '请输入工作单位！', trigger: 'change'}],
            expertPhoneNumber: [{required: true, message: '请输入电话号码！', trigger: 'change'}]
        }
    },
    mounted: function () {
        that = this;
        this.$options.methods.initDataCache();
        this.$options.methods.initTable();
    },
    methods: {
        //初始化数据;
        initDataCache: function () {
            // $.ajax({
            //     url: '/area/queryAreaInfo.ajax',
            //     success: function (res) {
            //         // console.log(res.resultData.records);
            //         that.dataCache.areaName = res.resultData.records;
            //     }
            // })
        },
        //初始化表格
        initTable: function () {
            $.ajax({
                url: '/expert',
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
                expertName: '',
                expertSex: '0',
                expertBirthDay: '',
                expertSpeciality: '',
                expertProfession: '',
                expertPhoneNumber: '',
                expertCompany: '',
                expertAddress: '',
                expertEmali: '',
            }
        },
        //表单提交
        submitForm: function (form) {
            that.$refs[form].validate((valid) => {
                if (valid) {
                    let type = that.dialogCache.isInsert ? 'POST' : 'PUT';
                    $.ajax({
                        url: '/expert',
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
                    url: '/expert',
                    type: 'DELETE',
                    data: {expertId: row.expertId},
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
                expertId: row.expertId,
                expertName: row.expertName,
                expertSex: row.expertSex,
                expertBirthDay: row.expertBirthDay,
                expertSpeciality: row.expertSpeciality,
                expertProfession: row.expertProfession,
                expertPhoneNumber: row.expertPhoneNumber,
                expertCompany: row.expertCompany,
                expertAddress: row.expertAddress,
                expertEmali: row.expertEmali,
            }
        },
        //重置表单
        resetForm: function (form) {
            that.formData = {
                expertName: '',
                expertSex: '0',
                expertBirthDay: '',
                expertSpeciality: '',
                expertProfession: '',
                expertPhoneNumber: '',
                expertCompany: '',
                expertAddress: '',
                expertEmali: '',
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

