createTable('#table', ctx + '/system/config/list', [
    {checkbox: true},
    {field: 'id', title: 'ID'},
    {field: 'paramKey', title: '参数名'},
    {field: 'paramValue', title: '参数值'},
    {field: 'remark', title: '说明'},
    {field: 'createDate', title: '创建日期'}
]);

$("#add").click(function(){
    createModal('添加系统参数', ctx + '/system/config/add');
})

$("#edit").click(function(){
    var row = getSelectRows("#table");
    createModal('修改系统参数', ctx + '/system/config/add/'+ row[0].id);
})

$("#delete").click(function(){
    delSelectRows("#table", ctx + '/system/config/delete/');
})
