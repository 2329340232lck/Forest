var that;
var validate = (rule, value, callback) => {
    if (!value) {
        return callback(new Error('确认密码不能为空!'))
    } else if (value != that.formData.userPassword) {
        return callback(new Error('确认密码不一致!'))
    } else {
        callback();
    }
};
new Vue({
    //标识渲染哪个元素
    el: '#main',
    //存放绑定的数据
    data: {
        //表格数据
        tableData: [],
        //查询数据
        dataQuery: {
            startDate: '',
            endDate: '',
            total: 0,
            size: 10,
            current: 0,
        },
        //加载层
        loading: true,
        //数据缓存
        dataCache: {
            dateCache: '',
        },
    },
    //VUE钩子函数，在DOM装载完成后调用
    mounted: function () {
        that = this;
        this.$options.methods.initTable();
    },
    //methods存放事件方法函数
    methods: {
        //初始化表格
        initTable: function () {
            $.ajax({
                url: '/log/queryLogInfo.ajax',
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
        //删除
        deleteInfo: function (row) {
            that.$confirm('确认删除吗？', '删除', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(function () {
                $.ajax({
                    url: '/user/deleteUserInfo.ajax',
                    data: {userId: row.userId},
                    success: function () {
                        that.$message({message: '删除完成', type: 'success'});
                        that.initTable();
                    }
                });
            }).catch(function () {
                that.$message('取消删除');
            });
        },
        //时间选择变更
        dateChangeHandle: function (value) {
            console.log(value);
            if (value != null) {
                that.dataQuery.startDate = value[0];
                that.dataQuery.endDate = value[1];
            } else {
                that.dataQuery.startDate = '';
                that.dataQuery.endDate = '';
            }

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

