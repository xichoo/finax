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
                }
            },
            name: {
                validators: {
                    notEmpty: {
                        message: '请输入编码名称'
                    },
                    stringLength: {
                        max: 20,
                        message: '名称不能超过20个字符'
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
                url: ctx + '/system/dict/add',
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