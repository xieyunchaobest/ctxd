
function changeColOver(src){ 
          
	src.bgColor = '#FFFFFF'; 
}

function changeColOut(src){ 
	src.bgColor = '#E5E8EC'; 
}

function currstyle(p)
{var oldColor="E5E8EC";
getTopNode(p).style.backgroundColor=p.checked?"#ffff99":oldColor;

}
function getTopNode(pNode){
if(pNode.tagName=="TR"){
return pNode;
}else{
return getTopNode(pNode.parentNode);
}
}