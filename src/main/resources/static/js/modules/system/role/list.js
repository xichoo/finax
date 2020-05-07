createTable('#table', ctx + '/system/role/list', true, [
    {checkbox: true},
    {field: 'id', title: 'ID', width: 15, widthUnit: '%'},
    {field: 'role', title: '角色名', width: 25, widthUnit: '%'},
    {field: 'description', title: '说明', width: 25, widthUnit: '%'},
    {field: 'createDate', title: '创建日期', width: 35, widthUnit: '%'},

]);

$("#add").click(function(){
    loadUrl('添加角色信息', ctx + '/system/role/add');
})

$("#edit").click(function(){
    var row = getSelectRows("#table");
    loadUrl('修改角色信息', ctx + '/system/role/add?id='+ row[0].id);
})

$("#delete").click(function(){
    delSelectRows("#table", ctx + '/system/role/delete/');
})
