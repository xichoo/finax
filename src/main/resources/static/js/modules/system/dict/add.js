$(function () {
    $('form').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'fas fa-check',
            invalid: 'fas fa-times',
            validating: 'fas fa-sync'
        },
        fields: {
            code: {
                validators: {
                    notEmpty: {
                        message: '请输入字典编码'
                    },
                    stringLength: {
                        max: 20,
                        message: '编码不能超过20个字符'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_]+$/,
                        message: '编码只能包含大写、小写、数字和下划线'
                    },
                    remote: {
                        url: ctx + '/system/dict/checkDictCode',
                        message: "编码已存在",
                        dataType: 'json',
                        data: {
                            "id": $("input[name='id']").val() ,
                            "code": $("input[name='code']").val() ,
                        },
                        delay: 500,//延迟效果
                    },
                }
            },
            name: {
                validators: {
                    notEmpty: {
                        message: '请输入字典名称'
                    },
                    stringLength: {
                        max: 20,
                        message: '名称不能超过20个字符'
                    },
                }
            },
            orderby: {
                validators: {
                    digits : {
                        message : '排序值必须是正整数'
                    }
                }
            }
        },
    });

    $("#submit").click(function(){
        var validator = $('form').data('bootstrapValidator');
        validator.validate();
        if(validator.isValid()){
            $.ajax({
                url: ctx + '/system/dict/add',
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