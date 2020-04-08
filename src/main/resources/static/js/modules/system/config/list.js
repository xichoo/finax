createTable('#table', ctx + '/system/config/list', [
    {checkbox: true},
    {field: 'paramKey', title: '参数名', width: 20, widthUnit: '%'},
    {field: 'paramValue', title: '参数值', width: 20, widthUnit: '%'},
    {field: 'remark', title: '说明', width: 35, widthUnit: '%'},
    {field: 'createDate', title: '创建日期', width: 25, widthUnit: '%'},
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
