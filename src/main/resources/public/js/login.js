

$(document).ready(function () {
    $("#user-login").click(function () {
        $.ajax({
            url:'login',
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