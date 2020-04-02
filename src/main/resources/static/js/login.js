$("#signin").click(function(){
    var username = $("input[name='username']").val();
    var password = $("input[name='password']").val();

    $.ajax({
        type:'post',
        data:$("#login-form").serialize(),
        beforeSend:function(){
            if(username == '' || password == ''){
                layer.msg('请输入用户名和密码', {icon: 5});
                return false;
            }
        },
        success:function(data){
            switch (data.code){
                case 1:
                    window.location = "index";
                    break;
                case 2:
                    sliderCaptcha();
                    break;
                case 3:
                    layer.msg(data.msg , {icon: 5});
                    break;
                case 500:
                    layer.msg(data.msg , {icon: 5});
                    break;
            }
        }
    })


})

function sliderCaptcha(){
    layer.open({
        title: '登陆验证',
        type: 2,
        area: ['360px', '350px'],
        skin: 'layui-layer-rim',
        content: ['sliderCaptcha', 'no']
    });
}