createTable('#table', ctx + '/system/config/list', true, [
    {checkbox: true},
    {field: 'paramKey', title: '参数名', width: 20, widthUnit: '%'},
    {field: 'paramValue', title: '参数值', width: 20, widthUnit: '%'},
    {field: 'remark', title: '说明', width: 35, widthUnit: '%'},
    {field: 'createDate', title: '创建日期', width: 25, widthUnit: '%'},
]);

$("#add").click(function(){
    loadUrl('添加系统参数', ctx + '/system/config/add');
})

$("#edit").click(function(){
    var row = getSelectRows("#table");
    loadUrl('修改系统参数', ctx + '/system/config/add?id='+ row[0].id);
})

$("#delete").click(function(){
    delSelectRows("#table", ctx + '/system/config/delete/');
})
