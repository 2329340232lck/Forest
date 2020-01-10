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
            visible: false,
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
        // this.$options.methods.initDataCache();
        this.$options.methods.initTable();
    },
    //methods存放事件方法函数
    methods: {
        //初始化数据;
        // initDataCache: function () {
        //     $.ajax({
        //         url: '/dictionaries/queryDictionInfoByKey.ajax',
        //         data: {dictionariesTable: 'disasterStage'},
        //         success: function (res) {
        //             // debugger;
        //             that.dataCache.eventDisasterStage = res.resultData;
        //         }
        //     });
        //     $.ajax({
        //         url: '/dictionaries/queryDictionInfoByKey.ajax',
        //         data: {dictionariesTable: 'resourcePreventType'},
        //         success: function (res) {
        //             that.dataCache.disasterType = res.resultData;
        //         }
        //     });
        //     $.ajax({
        //         url: '/dictionaries/queryDictionInfoByKey.ajax',
        //         data: {dictionariesTable: 'eventDiscoverer'},
        //         success: function (res) {
        //             that.dataCache.eventDiscoverer = res.resultData;
        //         }
        //     });
        //     $.ajax({
        //         url: '/area/queryAreaInfo.ajax',
        //         success: function (res) {
        //             debugger;
        //             // console.log(res.resultData.records);
        //             that.dataCache.areaName = res.resultData.records;
        //         }
        //     });
        // },
        //初始化表格
        initTable: function () {
            $.ajax({
                url: '/disaster/queryDisasterInfo.ajax',
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
                    let url = that.dialogCache.isInsert ? '/event/insertEventInfo.ajax' : '/event/updateEventInfo.ajax';
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
        editInfo: function (row) {
            that.dialogCache.visible = true;
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

