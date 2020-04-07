var columns = [
    {checkbox: true},
    {field: 'id', title: 'ID'},
    {field: 'username', title: '用户名'},
    {field: 'email', title: '邮箱'},
    {field: 'mobile', title: '电话'},
    {field: 'createDate', title: '创建日期'}
];

createTable('#table', ctx + '/system/user/list', columns);

$("#add").click(function(){
    createModal('添加用户信息', ctx + '/system/user/add');
})

$("#edit").click(function(){
    var row = getSelectRows("#table");
    createModal('修改用户信息', ctx + '/system/user/add/'+ row[0].id);
})

$("#delete").click(function(){
    delSelectRows("#table", ctx + '/system/user/delete/');
})
