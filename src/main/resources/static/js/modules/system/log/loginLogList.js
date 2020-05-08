createTable('#table', ctx + '/system/log/loginLogList', true, [
    {checkbox: true},
    {field: 'id', title: 'ID', width: 10, widthUnit: '%'},
    {field: 'username', title: '登陆用户', width: 15, widthUnit: '%'},
    {field: 'ip', title: '登陆IP', width: 15, widthUnit: '%'},
    {field: 'broswer', title: '浏览器', width: 20, widthUnit: '%'},
    {field: 'os', title: '操作系统', width: 20, widthUnit: '%'},
    {field: 'createDate', title: '创建日期', width: 20, widthUnit: '%'},
]);

function changeType(th,log){
    $('#logurl').val(log)
    loadUrl(th.text, ctx + '/system/log/'+ log);
}



