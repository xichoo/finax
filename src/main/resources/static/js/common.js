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
