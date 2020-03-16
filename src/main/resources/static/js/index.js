$(function () {
    /**
     * 菜单选中
     */
    $('.mt-2 a').click(function () {
        var nav = $('a.active');
        nav.removeClass('active');
        $(this).addClass('active');
    });

    /**
     * 加载内容
     */
    $('.menu_link').click(function () {
        var url = $(this).attr('href');
        if (url == undefined || $.trim(url).length == 0) {
            return false;
        }
        $('.content_frame').attr('src', url);

        $('.text-dark').text($(this).find('p').html());
        $('.breadcrumb').find('li').eq(1).text($(this).find('p').html());

        return false;
    });

    /**
     * 登出
     */
    $('.logout').click(function () {
        layer.msg('确定退出系统吗？', {
            icon: 3
            ,btn: ['确定', '取消']
            ,yes: function(index){
                layer.close(index);
                window.location = 'logout';
            }
        });
    })

})