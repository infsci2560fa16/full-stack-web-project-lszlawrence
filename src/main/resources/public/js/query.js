/**
 * Created by lszlawrence on 2016/11/8.
 */
$(document).ready(function(){
    $("#friends").click(function(){
        $("#friends-list").slideToggle("slow");
    });
    $("#relatives").click(function(){
        $("#relatives-list").slideToggle("slow");
    });
    $("#unclear").click(function(){
        $("#unclear-list").slideToggle("slow");
    });
});