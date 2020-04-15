createTable('#table', ctx + '/system/operationLog/list', [
    {checkbox: true},
    {field: 'id', title: 'ID', width: 10, widthUnit: '%'},
    {field: 'username', title: '操作人', width: 15, widthUnit: '%'},
    {field: 'action', title: '动作', width: 20, widthUnit: '%', formatter: function(value,row,index) {
            var text = value;
            if(row.result != null){
                text += ' [ <span class="fas fa-times"></span> ]';
            }else{
                text += ' [ <span class="fas fa-check"></span> ]';
            }
            return text;
        }
    },
    {field: 'url', title: '请求地址', width: 20, widthUnit: '%'},
    {field: 'ip', title: '请求IP', width: 15, widthUnit: '%'},
    {field: 'createDate', title: '创建日期', width: 20, widthUnit: '%'},
]);

$("#changeType").change(function(){
    loadUrl($(this).children('option:selected').text(),
        ctx + '/system/'+ $(this).children('option:selected').val() +'/list');
})

$("#view").click(function(){
    var row = getSelectRows("#table");
    loadUrl('查看详情', ctx + '/system/'+ $('#changeType option:selected').val() +'/view/'+ row[0].id);
})



