var columns = [
    {checkbox: true},
    {field: 'name', title: '菜单名称', width: 200},
    {field: 'url', title: '菜单url', width: 300},
    {field: 'icon', title: '图标', width: 200},
    {field: 'orderby', title: '排序值', width: 100},
    {field: 'createDate', title: '创建时间'},
];

//加载子菜单
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
            {field: '', title: ''},
            {checkbox: true},
            {field: 'name', title: '二级菜单', width: 200},
            {field: 'url', title: '菜单url', width: 300},
            {field: 'icon', title: '图标', width: 200},
            {field: 'orderby', title: '排序值', width: 100},
            {field: 'createDate', title: '创建时间'},
        ],
    });
};

createTable('#table', ctx + '/system/menu/list', columns, true,InitSubTable);

$("#add").click(function(){
    var parentId = 0;
    var row=$("#table").bootstrapTable('getSelections');
    if(row.length > 0){
        parentId = row[0].id;
    }

    createModal('添加菜单', ctx + '/system/menu/add/1/' + parentId);
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

    createModal('修改菜单', ctx + '/system/menu/add/2/'+ id);
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
