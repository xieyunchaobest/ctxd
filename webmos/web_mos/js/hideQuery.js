function hideQue(queryDiv,listDiv,img,val1,val2){
	var display = document.getElementById(queryDiv).style.display;
	var clientHeight = document.body.clientHeight;
	if(display=='block'){
		document.getElementById(queryDiv).style.display="none";
		document.getElementById(img).src="../images/btn_up2_blue.gif";
		document.getElementById(listDiv).style.height = clientHeight-val1;
	}else{
		document.getElementById(queryDiv).style.display="block";
		document.getElementById(img).src="../images/btn_up1_blue.gif";
		document.getElementById(listDiv).style.height = clientHeight-val2;
	}
}

function unexpandQue(queryDiv,listDiv,img,val1,val2){
	var display = document.getElementById(queryDiv).style.display;
	var clientHeight = document.body.clientHeight;
	if(display=='block'){
		document.getElementById(queryDiv).style.display="none";
		document.getElementById(img).src="../images/expand.gif";
		document.getElementById(listDiv).style.height = clientHeight-val1;
	}else{
		document.getElementById(queryDiv).style.display="block";
		document.getElementById(img).src="../images/unexpand.gif";
		document.getElementById(listDiv).style.height = clientHeight-val2;
	}
}

function hideDiv(queryDiv,img){
	var display = document.getElementById(queryDiv).style.display;
	//var clientHeight = document.body.clientHeight;
	if(display=='block'){
		document.getElementById(queryDiv).style.display="none";
		document.getElementById(img).src="../images/btn_up2_blue.gif";
		//document.getElementById(listDiv).style.height = clientHeight-val1;
	}else{
		document.getElementById(queryDiv).style.display="block";
		document.getElementById(img).src="../images/btn_up1_blue.gif";
		//document.getElementById(listDiv).style.height = clientHeight-val2;
	}
}