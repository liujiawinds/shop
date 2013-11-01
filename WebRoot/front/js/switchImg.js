/*点击左侧产品小图片切换大图*/
$(function(){
	$(".pro_detail_left ul li img").livequery("click",function(){ 
		  var imgSrc = $(this).attr("src");
		  var big = imgSrc.replace("xs","l");
		  $("#bigImg").attr("src",big);
		  $("#thickImg").attr("href",imgSrc.replace("xs","xl"));
	});
});