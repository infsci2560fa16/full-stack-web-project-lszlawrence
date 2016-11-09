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

