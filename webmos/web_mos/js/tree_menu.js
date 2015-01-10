
var MAX_Z_INDEX=30
var col_menu=[]; //menu collection
function tree_menu(width,imagelist)
{
	var menu=this
	this.copywrite="BSS"
	this.item=[]
	this.isShow=false
	this.bar=null
	this.body=document.createElement("table")
	this.imagelist=imagelist
	var icons=imagelist==null?[]:imagelist.item
	document.body.insertAdjacentElement("beforeEnd",this.body)
	this.body.style.cssText="position:absolute;;border-collapse:collapse;border-spacing:1;border:1 solid #0B2565;background-color:white;color:black;display:none;shadow(color=gray,direction=135);table-layout:fixed;filter:progid:DXImageTransform.Microsoft.Shadow(color='#cccccc', Direction=135, Strength=4);"
	this.body.style.pixelWidth=width==null?200:width
	var run=function(cmd,a0,a1,a2)
	{
		if(typeof(cmd)=="string")
		{	try{return eval(cmd);}
			catch(E){alert("run script string error:\n"+cmd);}
		}
		else if(typeof(cmd)=="function"){return cmd(a0,a1,a2);}
	}
	this.add=function(Text,ico,exeType,exeArg,target)
	{
		var item=menu.body.insertRow()
		item.style.cssText="font-size:10pt;cursor:default;height:24;"
		var col1=item.insertCell()
		var col2=item.insertCell()
		col2.innerHTML=Text
		col2.style.cssText="height:24;padding-left:5;width:100%;"
		col1.style.cssText="width:26;background-color:buttonface;height:24;text-align:center;"
		var icon=new Image()
		if(typeof(ico)=="string")if(icons[ico]!=null)ico=icons[ico].src
		if(ico!=null && ico!=""){icon.src=ico;col1.insertAdjacentElement("afterBegin",icon);}
		item.subMenu=null
		item.enable=true
		item.execute=new Function()
		item.resetExe=new Function()
		item.remove=function(){item.removeNode(true);}
		exeType=exeType==null?"":exeType
		switch(exeType.toLowerCase())
		{
			case "hide":
				item.execute=function(){menu.hide();}
				break;
			case "url":
				if(typeof(exeArg)!="string")break;
				if(target==null||target=="")target="_blank";
				item.execute=function(){menu.hide();open(exeArg,target);}
				break;
			case "js":
				if(typeof(exeArg)!="string")break;
				item.execute=function(){menu.hide();eval(exeArg)}
				break;
			case "sub":
				if(typeof(exeArg.body)=="undefined")break;
				item.execute=function(){menu.show(exeArg);}
				col2.innerHTML ="<span style='width:90%;'>"+col2.innerHTML+"</span><span style='font-family:Wingdings 3;'>}</span>";
				item.subMenu=exeArg;
				break;
		}
		menu.item[menu.item.length]=item
		item.onmouseover=function()
		{
			col1.style.backgroundColor="#B7BED3"
			icon.style.filter="dropshadow(color=gray,offx=2,offy=2)"
			item.style.color=item.enable?"black":"gray";
			item.style.backgroundColor="#B7BED3";
			col1.style.border=col2.style.border="1 solid #0B2565"
			col1.style.borderRight=col2.style.borderLeft="0"
			for(var i=0;i<menu.item.length;i++){if(menu.item[i].subMenu!=null)menu.item[i].subMenu.hide();}
			if(item.subMenu!=null)menu.show(item.subMenu)
		}
		item.onmouseout=function()
		{
			col1.style.backgroundColor="buttonface"
			icon.style.filter=""
			item.style.backgroundColor="white";
			col1.style.border=col2.style.border=""
		}
		item.onmousedown=item.onmouseup=function(){event.cancelBubble=true;}
		item.onclick=function(){event.cancelBubble=true;if(this.enable)run(item.execute);}

                /*自己加入方法*/
                item.resetExe = function(Text2,ico2,exeType2,exeArg2,target2){
                  if(Text2!=null){
                    col2.innerHTML=Text2
                  }
                  if(exeType2!=null){
                    switch(exeType2.toLowerCase())
		     {
			case "hide":
				item.execute=function(){menu.hide();}
				break;
			case "url":
				if(typeof(exeArg2)!="string")break;
				if(target2==null||target2=="")target2="_self";
				item.execute=function(){menu.hide();open(exeArg2,target2);}
				break;
			case "js":
				if(typeof(exeArg2)!="string")break;
				item.execute=function(){menu.hide();eval(exeArg2)}
				break;
			case "sub":
				if(typeof(exeArg2.body)=="undefined")break;
				item.execute=function(){menu.show(exeArg2);}
				col2.innerHTML ="<span style='width:90%;'>"+col2.innerHTML+"</span><span style='font-family:Wingdings 3;'>}</span>";
				item.subMenu=exeArg2;
				break;
		     }
                  }
                }


		return item
	}
	this.addLink=function(url_,text,target,icon)
	{
		if(text==null || text=="")text=url_
		if(target==null || target=="")target="_blank"
		return menu.add(text,icon,"url",url_,target)
	}
	this.seperate=function(){var row=menu.body.insertRow();row.style.cssText="padding-left:0;font-size:10pt;height:8;cursor:default;width:100%;border:0;";row.insertCell().style.cssText="width:26;background-color:buttonface;height:100%;text-align:center;";var cell=row.insertCell();cell.style.cssText="height:8;padding-left:5;text-align:right;";cell.innerHTML="<hr width='97%'>";}
	this.show=function()
	{
		var a=arguments;
		var x,y,m=menu.body
		if(a.length==0)
		{
			x=event.clientX+document.body.scrollLeft+document.body.scrollLeft
			y=event.clientY+document.body.scrollTop
		}
		else if(a.length==1 && typeof(a[0])=="object")
		{
			if(typeof(a[0].body)!="undefined")
			{
				m=a[0].body
				m.style.display="block"
				if(m.style.pixelWidth<document.body.offsetWidth-event.x)
				{	x=menu.body.style.pixelLeft+menu.body.offsetWidth}
				else
				{	x=menu.body.style.pixelLeft-m.style.pixelWidth}
				if(m.offsetHeight<document.body.offsetHeight-event.clientY)
				{	y=event.clientY+document.body.scrollTop-event.offsetY}
				else
				{	y=event.clientY-m.offsetHeight+document.body.scrollTop-event.offsetY}
			}
			else
			{
				x=event.clientX+document.body.scrollLeft-event.offsetX-2
				y=event.clientY+document.body.scrollTop+a[0].offsetHeight-event.offsetY-4
			}
		}
		else if(a.length==2 && typeof(a[0])=="number" && typeof(a[1])=="number")
		{
			x=a[0];y=a[1];
		}
		else{alert("arguments type or number not match!");return;}
		for(var i=0;i<menu.item.length;i++)menu.item[i].style.color=menu.item[i].enable?"black":"gray"
		m.style.pixelLeft=x;
		m.style.pixelTop=y;
		m.style.display="block";
		m.style.zIndex=++MAX_Z_INDEX
		menu.isShow=true;
		if(menu.bar!=null)
		{
			menu.bar.style.backgroundColor="buttonface";
			menu.bar.style.border="1 solid gray"
			menu.bar.style.filter="progid:DXImageTransform.Microsoft.Shadow(color='white', Direction=100, Strength=6);"
		}
		//for(var i=2;i<=10;i++){setTimeout("document.all["+Menu.body.sourceIndex+"].style.filter='alpha(opacity="+(i*10)+")';",i*20);}
		event.cancelBubble=true;
	}
	this.hide=function()
	{
		menu.body.style.display="none";
		menu.isShow=false;
		if(menu.bar!=null)
		{	menu.bar.style.border="1 solid buttonface"
			menu.bar.style.backgroundColor="buttonface";
			menu.bar.style.filter=""
		}
	}
	this.hideAll=function(){for(var i=0;i<col_menu.length;i++)col_menu[i].hide();}
	this.hideOthers=function(){for(var i=0;i<col_menu.length;i++)if(col_menu[i]!=menu)col_menu[i].hide();}
	col_menu[col_menu.length]=this
	document.body.onclick=this.hideAll
}
function menu_bar_xp(top,left)
{
	var mb=this
	this.item=[]
	this.menu=[]
	this.body=document.createElement("div")
	document.body.insertAdjacentElement("beforeEnd",this.body)
	this.body.style.cssText="position:absolute;cursor:default;padding:2;background-color:buttonface;height:25;z-index:5;font-size:10pt;color:black;top:"+top+";left:"+left
	var chkShow=function(){for(var i=0;i<mb.menu.length;i++)if(mb.menu[i].isShow)return true;return false;}
	this.add=function(Text,menu)
	{
		var item=document.createElement("span")
		mb.body.insertAdjacentElement("beforeEnd",item)
		item.style.cssText="margin:0 7 0 3;padding:2 4 2 4;text-align:center;height:23;background-color:buttonface;border:1 solid buttonface"
		item.innerText=Text
		item.onmouseover=function()
		{
			this.style.border="1 solid #0B2565"
			this.style.backgroundColor="#B7BED3";
			if(chkShow()){for(var i=0;i<col_menu.length;i++)col_menu[i].hide();menu.show(item);}
		}
		item.onmouseout=function()
		{
			if(!menu.isShow)
			{	item.style.border="1 solid buttonface"
				item.style.backgroundColor="buttonface";
			}
		}
		item.onmousedown=item.onmouseup=function(){event.cancelBubble=true;menu.show(item)}
		item.onclick=function(){event.cancelBubble=true;menu.show(item);}
		mb.item[mb.item.length]=item
		mb.menu[mb.menu.length]=menu
		menu.bar=item
		return item
	}
}
