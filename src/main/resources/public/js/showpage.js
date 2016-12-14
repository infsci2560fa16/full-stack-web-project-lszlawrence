/**
 * Created by lszlawrence on 2016/12/13.
 */
$('#showpic').click(function(e){
    $.ajax("/show", {
        method: "GET",
        dataType: "xml",
        contentType: "text/xml; charset=\"utf-8\"",
        success: function (xmlDoc) {
            var container = $('#showPage');
            $(xmlDoc).find('photos').each(function(){
                var photosId = $(this).find('photosId').text(),
                    userId = $(this).find('userId').text(),
                    date = $(this).find('date').text(),
                    address = $(this).find('address').text(),
                    category = $(this).find('category').text(),
                    introMessage = $(this).find('introMessage').text(),
                    recentComment = $(this).find('recentComment').text(),
                    devices = $(this).find('devices').text(),
                    filters = $(this).find('filters').text(),
                    lens = $(this).find('lens').text();

                var htmlText = '<img src="' + address + '" alt="1.jpeg">' + '\t\t<h1>' + devices + '</h1>';

                container.append(htmlText);
            });
        }
    });
});

$('#showusers').click(function(e){
  /* $.getJSON("/1",function(data){
        $("#showPage2").append(
            "<h1>" + data.id + "</h1>"+
                "\t<h1>" + data.email + "</h1>"
        );
   });*/

  $.ajax("/1",{
      method:"GET",
      dataType: "json",
      success:function(result){
          $.each(result, function(item){
              console.log(item.id);
          });
      }
  });
});