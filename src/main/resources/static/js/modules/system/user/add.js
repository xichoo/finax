var index = parent.layer.getFrameIndex(window.name);

$(function () {
    $('form').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'fas fa-check',
            invalid: 'fas fa-times',
            validating: 'fas fa-sync'
        },
        fields: {
            username: {
                message: '用户名验证失败',
                validators: {
                    notEmpty: {
                        message: '请输入用户名'
                    },
                    stringLength: {
                        min: 5,
                        max: 15,
                        message: '用户名长度必须在5到15位之间'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_]+$/,
                        message: '用户名只能包含大写、小写、数字和下划线'
                    },
                    remote: {
                        url: ctx + '/system/user/checkUsername',
                        message: "用户已存在",
                        dataType: 'json',
                        data: {
                            "id": $("input[name='id']").val() ,
                            "username": $("input[name='username']").val() ,
                        },
                        delay: 500,//延迟效果
                    },
                }
            },
            password: {
                validators: {
                    stringLength: {
                        min: 6,
                        max: 18,
                        message: '密码必须在6到18位数之间'
                    },
                }
            }
        },
    });

    $("#submit").click(function(){
        var validator = $('form').data('bootstrapValidator');
        validator.validate();
        if(validator.isValid()){
            $.ajax({
                url: ctx + '/system/user/add',
                type: 'post',
                data: $('form').serialize(),
                success:function (data) {
                    switch (data.code){
                        case 0:
                            parent.layer.msg(data.msg, {icon: 6});
                            parent.layer.close(index);
                            parent.$("#table").bootstrapTable('refresh');
                            break;
                        case 500:
                            layer.msg(data.msg , {icon: 5});
                            break;
                    }
                }
            })
        }
    })

});