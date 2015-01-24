function showCover(){
	if(!$("#coverShow").length > 0){
		$('body').prepend("<div id='cover'></div>"+
				"<div id='coverShow'>"+
				"	<table align='center' border='0' width='100%' cellspacing='0' cellpadding='0' "+
				"		style='border-collapse: collapse; height: 37px; min-height: 37px;'"+
				"			bgcolor='#127386'>"+
				"		<tr>"+
				"			<td align='center' bgcolor='#ffffff'><img  src='../images/loading.gif'/></td>"+
				"		</tr>"+
				"	</table>"+
				"</div>");
	}
	var cover = document.getElementById("cover");
	var covershow = document.getElementById("coverShow");
	cover.style.display = 'block';
	covershow.style.display = 'block';
}

function cancelCoverit(){
	var cover = document.getElementById("cover");
	var covershow = document.getElementById("coverShow");
	cover.style.display = 'none';
	covershow.style.display = 'none';
}
