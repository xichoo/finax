$('#table').bootstrapTable({
    url: ctx + '/system/menu/list',
    method: "post",
    contentType: "application/x-www-form-urlencoded",
    toolbar: '#toolbar',
    sidePagination: 'server',
    pagination: true,
    showRefresh: true,
    showColumns: true,
    clickToSelect: true,
    queryParams: queryParam,
    height: 700,
    detailView: true,
    columns: [
        {checkbox: true},
        {field: 'name', title: '菜单名称'},
        {field: 'url', title: 'url'},
        {field: 'icon', title: '图标'},
        {field: 'orderby', title: '排序值'},
        {field: 'createDate', title: '创建日期'},
    ],
    onExpandRow: function (index, row, $detail) {
        InitSubTable(index, row, $detail);
    },
})

InitSubTable = function (index, row, $detail) {
    var parentId = row.id;
    var cur_table = $detail.html('<table id="cur_table"></table>').find('table');
    $(cur_table).bootstrapTable({
        url: ctx + '/system/menu/list',
        method: 'post',
        contentType: "application/x-www-form-urlencoded",
        queryParams: {parentId: parentId},
        clickToSelect: true,
        columns: [
            {checkbox: true},
            {field: 'name', title: '菜单名称'},
            {field: 'url', title: 'url'},
            {field: 'icon', title: '图标'},
            {field: 'orderby', title: '排序值'},
        ],
    });
};

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
    var parentId = 0;
    var row=$("#table").bootstrapTable('getSelections');
    if(row.length > 0){
        parentId = row[0].id;
    }

    layer.open({
        title: '添加菜单',
        type: 2,
        area: ['850px', '600px'],
        fixed: false, //不固定
        maxmin: true,
        content: ctx + '/system/menu/add/1/' + parentId,
    });
})

$("#edit").click(function(){
    var id;
    var row=$("#table").bootstrapTable('getSelections');
    var cur_row=$("#cur_table").bootstrapTable('getSelections');
    if(row.length == 0 && cur_row.length == 0){
        layer.msg('请选择行');
        return;
    }

    if(row.length > 0){
        id = row[0].id;
    }else if(cur_row.length > 0){
        id = cur_row[0].id;
    }

    layer.open({
        title: '修改菜单',
        type: 2,
        area: ['850px', '600px'],
        fixed: false, //不固定
        maxmin: true,
        content: ctx + '/system/menu/add/2/'+ id,
    });
})

$("#delete").click(function(){
    var row=$("#table").bootstrapTable('getSelections');
    var cur_row=$("#cur_table").bootstrapTable('getSelections');
    if(row.length == 0 && cur_row.length == 0){
        layer.msg('请选择行');
        return;
    }

    if(row.length == 0){
        row = cur_row;
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
            url: ctx + '/system/menu/delete/' + ids,
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
