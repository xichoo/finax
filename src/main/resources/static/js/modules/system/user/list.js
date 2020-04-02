$('#table').bootstrapTable({
    method: "post",
    url: ctx + '/system/user/list',
    columns: [
        {checkbox: true},
        {field: 'id', title: 'ID'},
        {field: 'username', title: '用户名'},
        {field: 'email', title: '邮箱'},
        {field: 'mobile', title: '电话'},
        {field: 'createDate', title: '创建日期'},
    ],
    // 加载的json格式数据
    contentType: "application/x-www-form-urlencoded",
    toolbar: '#toolbar', //工具按钮用哪个容器
    pageNumber: 1, // 初始化加载第一页
    pagination: true,// 是否分页
    sidePagination: 'server',
    pageSize: 10,//单页记录数
    search: false, //显示表格搜索
    showRefresh: true,// 显示刷新按钮
    striped: true, // 显示行间隔色
    showColumns: true, // 选择显示的列
    height: 700,
    queryParams: queryParam,
})

function queryParam(params){
    var param = {
        limit: this.limit, // 页面大小
        offset: this.offset, // 页码
        pageNum: this.pageNumber,
        pageSize: this.pageSize
    };
    return param;
}

$("#add").click(function(){
    layer.open({
        title: '添加用户信息',
        type: 2,
        area: ['850px', '600px'],
        fixed: false, //不固定
        maxmin: true,
        content: 'add'
    });
})

$("#edit").click(function(){
    var row=$("#table").bootstrapTable('getSelections');
    if(row.length == 0){
        layer.msg('请选择行');
        return;
    }

    layer.open({
        title: '修改用户信息',
        type: 2,
        area: ['850px', '600px'],
        fixed: false, //不固定
        maxmin: true,
        content: 'add/'+ row[0].id,
    });
})

$("#delete").click(function(){
    var row=$("#table").bootstrapTable('getSelections');
    if(row.length == 0){
        layer.msg('请选择行');
        return;
    }

    layer.confirm('确认删除吗？', {
        btn: ['确定','取消'] //按钮
    }, function(){
        var ids = row[0].id;
        if(row.length > 1){
            for(var i=1;i<row.length;i++){
                ids = ids + ',' + row[i].id;
            }
        }
        $.ajax({
            url: ctx + '/system/user/delete/' + ids,
            success:function(data){
                switch (data.code){
                    case 0:
                        layer.msg(data.msg, {icon: 6});
                        $("#table").bootstrapTable('refresh');
                        break;
                    case 500:
                        layer.msg(data.msg , {icon: 5});
                        break;
                }
            }
        })
    });
})
