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
            diseaseName: '',
            diseaseSymptom: '',
            total: 0,
            size: 10,
            current: 0,
        },
        //表单数据
        formData: {
            diseaseName: '',
            diseaseEtiology: '',
            diseaseHarm: '',
            diseaseImg: '',
            diseaseSymptom: '',
            diseasePeriod: '',
            diseaseControlPlan: '',
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
            diseaseName: [{required: true, message: '请输入疾病名称！', trigger: 'change'}],
            diseaseEtiology: [{required: true, message: '请输入病原！', trigger: 'change'}],
            diseaseHarm: [{required: true, message: '请输入主要危害！', trigger: 'change'}],
            diseaseSymptom: [{required: true, message: '请输入发病症状！', trigger: 'change'}],
            diseasePeriod: [{required: true, message: '请输入发病周期！', trigger: 'change'}]
        }
    },
    //VUE钩子函数，在DOM装载完成后调用
    mounted: function () {
        that = this;
        this.$options.methods.initTable();
    },
    //methods存放事件方法函数
    methods: {
        //文件超出上限回调
        handleExceed: function (files, fileList) {
            this.$message.warning('超出最大选择限制,只允许上传一张图片!');
        },
        //文件上传Before回调
        uploadBefore: function (file) {
            debugger;
            const isJpg = file.type === 'image/jpeg';
            const isLt2M = file.size / 1024 / 1024 < 2;
            console.log(file.type);
            if (!isJpg) {
                this.$message.warning('上传的图片只能是jpg格式');
            }
            if (isLt2M) {
                this.$message.warning('图片大小不能超过2M');
            }
        },
        //初始化表格
        initTable: function () {
            $.ajax({
                url: '/disease/queryDiseaseInfo.ajax',
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
                diseaseName: '',
                diseaseEtiology: '',
                diseaseHarm: '',
                diseaseImg: '',
                diseaseSymptom: '',
                diseasePeriod: '',
                diseaseControlPlan: '',
            };
        },
        //表单提交
        submitForm: function (form) {
            that.$refs[form].validate((valid) => {
                if (valid) {
                    let url = that.dialogCache.isInsert ? '/disease/insertDiseaseInfo.ajax' : '/disease/updateDiseaseInfo.ajax';
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
                    url: '/disease/deleteDiseaseInfo.ajax',
                    data: {diseaseId: row.diseaseId},
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
                diseaseId: row.diseaseId,
                diseaseName: row.diseaseName,
                diseaseEtiology: row.diseaseEtiology,
                diseaseHarm: row.diseaseHarm,
                diseaseImg: row.diseaseImg,
                diseaseSymptom: row.diseaseSymptom,
                diseasePeriod: row.diseasePeriod,
                diseaseControlPlan: row.diseaseControlPlan,
            };
        },
        //重置表单
        resetForm: function (form) {
            that.formData = {
                diseaseName: '',
                diseaseEtiology: '',
                diseaseHarm: '',
                diseaseImg: '',
                diseaseSymptom: '',
                diseasePeriod: '',
                diseaseControlPlan: '',
            };
            that.$nextTick(function () {
                that.$refs[form].clearValidate();
            });
        }
    }
});