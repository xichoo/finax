$('#table').bootstrapTable({
    method: "post",
    url: 'list',
    columns: [
        {field: 'id', title: 'ID'},
        {field: 'username', title: '用户名'},
        {field: 'email', title: '邮箱'},
        {field: 'mobile', title: '电话'},
        {field: 'errorcount', title: '锁定状态'},
        {field: 'createDate', title: '创建日期'},
        {field: 'tool',title: '操作', align: 'center',
            formatter:function(value,row,index){
                var element =
                    "<button class='btn btn-info btn-sm' data-id='"+row.id +"'>编辑</button> "+
                    "<button class='btn btn-danger btn-sm' data-id='"+row.id +"'>删除</button> ";
                return element;
            }
        }
    ],
    // 加载的json格式数据
    contentType: "application/x-www-form-urlencoded",
    toolbar: '#toolbar', //工具按钮用哪个容器
    pageNumber: 1, // 初始化加载第一页
    pagination: true,// 是否分页
    sidePagination: 'server',
    pageSize: 10,//单页记录数
    search: true, //显示表格搜索
    showRefresh: true,// 显示刷新按钮
    striped: true, // 显示行间隔色
    showColumns: true, // 选择显示的列
    height: 750,
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