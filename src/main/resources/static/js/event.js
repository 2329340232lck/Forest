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
            eventName: '',
            eventDisasterStage: '',
            areaName: '',
            startDate: '',
            endDate: '',
            total: 0,
            size: 10,
            current: 0,
        },
        //表单数据
        formData: {
            eventName: '',
            eventStartTime: '',
            eventDisasterType: '',
            eventDisasterStage: '',
            eventLoss: '',
            eventExpertAdvice: '',
            eventRemark: '',
            eventDiscoverer: '',
            eventInfluenceArea: '',
            eventControlPlan: '',
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
            dateCache: '',
            disasterType: [],
            eventDiscoverer: [],
            areaName: [],
            eventDisasterStage: [],
        },
        //表单验证
        submitRules: {
            eventName: [{required: true, message: '请输入事件名称！', trigger: 'change'}],
            eventStartTime: [{required: true, message: '请选择事件发生时间！', trigger: 'change'}],
            eventDisasterType: [{required: true, message: '请选择灾害类型!', trigger: 'change'}],
            eventDisasterStage: [{required: true, message: '请选择灾情阶段！', trigger: 'change'}],
            eventDiscoverer: [{required: true, message: '请选择发现途径！', trigger: 'change'}],
            areaId: [{required: true, message: '请选择事件发生地区！', trigger: 'change'}],
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
                data: {dictionariesTable: 'disasterStage'},
                success: function (res) {
                    // debugger;
                    that.dataCache.eventDisasterStage = res.resultData;
                }
            });
            $.ajax({
                url: '/dictionaries/queryDictionInfoByKey.ajax',
                data: {dictionariesTable: 'resourcePreventType'},
                success: function (res) {
                    that.dataCache.disasterType = res.resultData;
                }
            });
            $.ajax({
                url: '/dictionaries/queryDictionInfoByKey.ajax',
                data: {dictionariesTable: 'eventDiscoverer'},
                success: function (res) {
                    that.dataCache.eventDiscoverer = res.resultData;
                }
            });
            $.ajax({
                url: '/area/queryAreaInfo.ajax',
                success: function (res) {
                    // debugger;
                    // console.log(res.resultData.records);
                    that.dataCache.areaName = res.resultData.records;
                }
            });
        },
        //初始化表格
        initTable: function () {
            $.ajax({
                url: '/event',
                type: 'GET',
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
                    let type = that.dialogCache.isInsert ? 'POST' : 'PUT';
                    $.ajax({
                        url: '/event',
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
                isInsert: true
            };
            that.formData = {
                eventName: '',
                eventStartTime: '',
                eventDisasterType: '',
                eventDisasterStage: '',
                eventLoss: '',
                eventExpertAdvice: '',
                eventRemark: '',
                eventDiscoverer: '',
                eventInfluenceArea: '',
                eventControlPlan: '',
                areaId: '',
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
                    url: '/event',
                    type: 'DELETE',
                    data: {eventId: row.eventId},
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
                eventId: row.eventId,
                eventName: row.eventName,
                eventStartTime: row.eventStartTime,
                eventDisasterType: row.eventDisasterType,
                eventDisasterStage: row.eventDisasterStage,
                eventLoss: row.eventLoss,
                eventExpertAdvice: row.eventExpertAdvice,
                eventRemark: row.eventRemark,
                eventDiscoverer: row.eventDiscoverer,
                eventInfluenceArea: row.eventInfluenceArea,
                eventControlPlan: row.eventControlPlan,
                areaId: row.areaId,
            }
        },
        //重置表单
        resetForm: function (form) {
            that.formData = {
                eventName: '',
                eventStartTime: '',
                eventDisasterType: '',
                eventDisasterStage: '',
                eventLoss: '',
                eventExpertAdvice: '',
                eventRemark: '',
                eventDiscoverer: '',
                eventInfluenceArea: '',
                eventControlPlan: '',
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

