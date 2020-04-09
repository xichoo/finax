createTable('#table', ctx + '/system/user/list', [
    {checkbox: true},
    {field: 'id', title: 'ID', width: 15, widthUnit: '%'},
    {field: 'username', title: '用户名', width: 20, widthUnit: '%'},
    {field: 'email', title: '邮箱', width: 20, widthUnit: '%'},
    {field: 'mobile', title: '电话', width: 20, widthUnit: '%'},
    {field: 'createDate', title: '创建日期', width: 25, widthUnit: '%'},
]);

$("#add").click(function(){
    loadUrl('添加用户信息', ctx + '/system/user/add');
})

$("#edit").click(function(){
    var row = getSelectRows("#table");
    loadUrl('修改用户信息', ctx + '/system/user/add/'+ row[0].id);
})

$("#delete").click(function(){
    delSelectRows("#table", ctx + '/system/user/delete/');
})
