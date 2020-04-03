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
            name: {
                validators: {
                    notEmpty: {
                        message: '请输入菜单名称'
                    },
                    stringLength: {
                        max: 10,
                        message: '菜单名称不能超过10个字'
                    },
                }
            },
            url: {
                validators: {
                    notEmpty: {
                        message: '请输入url'
                    },
                    stringLength: {
                        max: 30,
                        message: 'url不能超过30个字符'
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
                url: ctx + '/system/menu/add',
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