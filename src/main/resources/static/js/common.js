//创建列表
var createTable = function(table, url, columns, detail,onExpandRow){
    $(table).bootstrapTable({
        url: url,
        method: "post",
        contentType: "application/x-www-form-urlencoded",
        toolbar: '#toolbar',
        sidePagination: 'server',
        pagination: true,
        showRefresh: true,
        showColumns: true,
        clickToSelect: true,
        queryParams: queryParam,
        columns: columns,
        detailView: detail,
        onExpandRow: onExpandRow,
        height: $(window).height(),
    })
}

function queryParam(params){
    var param = {
        limit: this.limit, // 页面大小
        offset: this.offset, // 页码
        pageNum: this.pageNumber,
        pageSize: this.pageSize
    };
    return param;
}

//创建模态框
var createModal = function(title, url){
    layer.open({
        title: title,
        type: 2,
        area: ['750px', '550px'],
        fixed: false, //不固定
        maxmin: true,
        content: url,
    });
}

//获取选中行
var getSelectRows = function(table){
    var row = $(table).bootstrapTable('getSelections');
    if(row.length == 0){
        layer.msg('请选择行');
        return;
    }
    return row;
}

//删除选中行
var delSelectRows = function(table, url){
    var row = $(table).bootstrapTable('getSelections');
    if(row.length == 0){
        layer.msg('请选择行');
        return;
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
            url: url + ids,
            success:function(data){
                switch (data.code){
                    case 0:
                        layer.msg(data.msg, {icon: 6});
                        $(table).bootstrapTable('refresh');
                        break;
                    case 500:
                        layer.msg(data.msg , {icon: 5});
                        break;
                }
            }
        })
    });
}