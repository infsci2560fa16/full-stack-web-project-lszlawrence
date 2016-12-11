/**
 * Created by lszlawrence on 2016/11/8.
 */
var slideIndex = 0;
carousel();

function carousel() {
    var i;
    var x = document.getElementsByClassName("home_slides_img");
    for (i = 0; i < x.length; i++) {
        x[i].style.display = "none";
    }
    slideIndex++;
    if (slideIndex > x.length) {slideIndex = 1;}
    x[slideIndex-1].style.display = "block";
    setTimeout(carousel, 2000); // Change image every 2 seconds
}

function welcome(){
    $.ajax({
        url: "/",
        success: function (result) {
            var data = JSON.parse(result);
            $(data).each(function(i, val){
                $("#welcome-message").append("<h2>" + val.name +  " " + "</h2>");
            });

        }});
}

$(document).ready(function () {
    $("#user-login").click(function () {
        $.post({
            url:'login',
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
        })
    })
})

