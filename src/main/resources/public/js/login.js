

$(document).ready(function () {
    $("#user-login").click(function(e) {
        e.preventDefault();
        $.ajax({
            url:'/login',
            type:'POST',
            dataType:'json',
            data:{
                email:$("#name").val(),
                pwd:$("#pwd").val()
            },
            success:function (data) {
                window.location.href="/index";
            },
            fail:function () {

            }
        });
    });
});

