$(function () {
    $('form').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'fas fa-check',
            invalid: 'fas fa-times',
            validating: 'fas fa-sync'
        },
        fields: {
            paramKey: {
                message: '系统参数验证失败',
                validators: {
                    notEmpty: {
                        message: '请输入参数名'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_]+$/,
                        message: '参数名只能包含大写、小写、数字和下划线'
                    },
                    remote: {
                        url: ctx + '/system/config/checkUsername',
                        message: "参数已存在",
                        dataType: 'json',
                        data: {
                            "id": $("input[name='id']").val() ,
                            "paramKey": $("input[name='paramKey']").val() ,
                        },
                        delay: 500,//延迟效果
                    },
                }
            },
            paramValue: {
                validators: {
                    notEmpty: {
                        message: '请输入参数值'
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
                url: ctx + '/system/config/add',
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