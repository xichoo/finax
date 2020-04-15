createTable('#table', ctx + '/system/operationLog/list', [
    {field: 'id', title: 'ID', width: 10, widthUnit: '%'},
    {field: 'username', title: '操作人', width: 15, widthUnit: '%'},
    {field: 'action', title: '动作', width: 20, widthUnit: '%'},
    {field: 'url', title: '请求地址', width: 20, widthUnit: '%'},
    {field: 'ip', title: '请求IP', width: 15, widthUnit: '%'},
    {field: 'createDate', title: '创建日期', width: 20, widthUnit: '%'},
]);

$("#changeType").change(function(){
    loadUrl($(this).children('option:selected').text(),
        ctx + '/system/'+ $(this).children('option:selected').val() +'/list');
})



