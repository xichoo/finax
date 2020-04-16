$(function () {
    $('form').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'fas fa-check',
            invalid: 'fas fa-times',
            validating: 'fas fa-sync'
        },
        fields: {
            role: {
                message: '角色名验证失败',
                validators: {
                    notEmpty: {
                        message: '请输入角色名'
                    },
                    stringLength: {
                        min: 5,
                        max: 15,
                        message: '角色名长度必须在5到15位之间'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_]+$/,
                        message: '角色名只能包含大写、小写、数字和下划线'
                    },
                    remote: {
                        url: ctx + '/system/role/checkRolename',
                        message: "角色已存在",
                        dataType: 'json',
                        data: {
                            "id": $("input[name='id']").val() ,
                            "role": $("input[name='role']").val() ,
                        },
                        delay: 500,//延迟效果
                    },
                }
            },
        },
    });

    $("#submit").click(function(){
        var validator = $('form').data('bootstrapValidator');
        validator.validate();
        if(validator.isValid()){
            $.ajax({
                url: ctx + '/system/role/add',
                type: 'post',
                data: $('form').serialize(),
                success:function (data) {
                    switch (data.code){
                        case 0:
                            layer.msg(data.msg, {icon: 6});
                            back();
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