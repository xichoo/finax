$(function () {
    /**
     * 页面刷新
     */
    var currenturl = window.location.hash;
    if(currenturl != undefined && currenturl != ''){
        var menua = $("a[href='"+ currenturl +"']");

        //加载右侧内容
        $('.content_frame').attr('src', currenturl.replace('#',''));
        $('.content_frame').height($('.content-wrapper').height() - 80);

        //菜单选中
        $(menua).addClass('active');
        $(menua).parent().parent().parent().addClass('menu-open');
        $(menua).parent().parent().show();

        //替换抬头
        $('.text-dark').text($(menua).find('p').html());
        $('.breadcrumb').empty();
        $('.breadcrumb').append('<li class="breadcrumb-item">主页</li>' +
            '<li class="breadcrumb-item active">'+ $(menua).find('p').html() +'</li>');
    }

    /**
     * 菜单选中
     */
    $('.mt-2 a').click(function () {
        var nav = $('a.active');
        nav.removeClass('active');
        $(this).addClass('active');
    });

    /**
     * 加载菜单
     */
    $('.menu_link').click(function () {
        var url = $(this).attr('href');
        if (url == undefined || $.trim(url).length == 0) {
            return false;
        }
        if(url == window.location.hash){
            return false;
        }

        $('.content_frame').attr('src', url.replace('#',''));
        $('.content_frame').height($('.content-wrapper').height() - 80);

        $('.text-dark').text($(this).find('p').html());
        $('.breadcrumb').empty();
        $('.breadcrumb').append('<li class="breadcrumb-item">主页</li>' +
            '<li class="breadcrumb-item active">'+ $(this).find('p').html() +'</li>');
    });

    /**
     * 登出
     */
    $('.logout').click(function () {
        layer.msg('确定退出系统吗？', {
            icon: 3,
            time: 0,
            btn: ['确定', '取消'],
            yes: function(index){
                layer.close(index);
                window.location = 'logout';
            }
        });
    })

})