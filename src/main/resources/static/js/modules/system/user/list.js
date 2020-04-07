var columns = [
    {checkbox: true},
    {field: 'id', title: 'ID'},
    {field: 'username', title: '用户名'},
    {field: 'email', title: '邮箱'},
    {field: 'mobile', title: '电话'},
    {field: 'createDate', title: '创建日期'}
];

createTable('#table', ctx + '/system/user/list', columns);

$("#add").click(function(){
    createModal('添加用户信息', ctx + '/system/user/add');
})

$("#edit").click(function(){
    var row = getSelectRows("#table");

    createModal('修改用户信息', ctx + '/system/user/add/'+ row[0].id);
})

$("#delete").click(function(){
    var row = getSelectRows("#table");

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
