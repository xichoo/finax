/**
 * 创建列表
 * @param table
 * @param url
 * @param columns
 * @param detail 是否展示子表
 * @param onExpandRow 加载子表函数
 */
var createTable = function(table, url, columns, detail, onExpandRow){
    $(table).bootstrapTable({
        url: url,
        method: "post",
        contentType: "application/x-www-form-urlencoded",
        toolbar: '#toolbar',
        striped: true,
        cache: false,
        sidePagination: 'server',
        pagination: true,
        pageNumber: 1,
        pageSize: 10,
        showRefresh: true,
        showColumns: true,
        clickToSelect: true,
        queryParams: function(){
            var param = {
                limit: this.limit, // 页面大小
                offset: this.offset, // 页码
                pageNum: this.pageNumber,
                pageSize: this.pageSize
            };
            return param;
        },
        columns: columns,
        detailView: detail,
        onExpandRow: onExpandRow,
        height: $(window).height(),
    })
}

/**
 * 打开新地址
 * @param title
 * @param url
 */
var loadUrl = function(title, url){
    $(".content_frame", parent.document).attr('src', url);
    $(".content_frame", parent.document).height($(".content-wrapper",parent.document).height() - 80);
    //记住标题
    $("#oldTitle", parent.document).val($('.text-dark', parent.document).html());
    $('.text-dark', parent.document).text(title);
}

/**
 * 返回上页
 */
var back = function(){
    //重置标题
    $('.text-dark', parent.document).text($("#oldTitle", parent.document).val());
    window.history.back();
}

/**
 * 创建模态框
 * @param title
 * @param url
 */
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

/**
 * 获取选中行
 * @param table
 * @returns {*|jQuery}
 */
var getSelectRows = function(table){
    var row = $(table).bootstrapTable('getSelections');
    if(row.length == 0){
        layer.msg('请选择行');
        return;
    }
    return row;
}

/**
 * 删除选中行
 * @param table
 * @param url
 */
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