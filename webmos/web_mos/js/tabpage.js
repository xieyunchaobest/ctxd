var TabPageFramework = {

	/**
	*定义数据结构  
	*
	*tabPageName:tab页标签名称
	*viewDivId:展现div ID
	*callBackFunc:回调函数名称（目前不支持参数）
	*isRefresh:是否每次刷新页面 true or false default true;
	*isInit：是否首次加载 true or false default false;
	*loaded: 是否已经加载 
	*initDisplay :是否初始显示
	**/
	createTabPageInfo:function(tabPageName,viewDivId,callBackFunc,isRefresh,isInit,initDisplay){
		var tabPageInfo = new Object();
		
		tabPageInfo.tabPageName = tabPageName;
		tabPageInfo.viewDiv = viewDivId;
		tabPageInfo.callBackFunc = callBackFunc;
		tabPageInfo.refresh = isRefresh;
		tabPageInfo.init = isInit;
		tabPageInfo.loaded = false;
		tabPageInfo.initDisplay = initDisplay;
		
		//刷新默认值为true
		if((isRefresh !== true) && (isRefresh !== false) ){
			tabPageInfo.refresh = true;
		}
		//是否初始加载默认值为false
		if((isInit !== true) && (isInit !== false) ){
			tabPageInfo.init = false;
		}
		//是否初始显示
		if((initDisplay !== true) && (initDisplay !== false) ){
			tabPageInfo.initDisplay = false;
		}
		//标签处理方法的默认方法
		if((null === callBackFunc) || (undefined === callBackFunc) || ("" === callBackFunc)){
			tabPageInfo.callBackFunc = TabPageFramework._doNothing;
		}
		
		return tabPageInfo;
	},

	/**
	*创建Tab页的方法 
	*/
	createTabPage:function(outerDivId,tabPageInfoArr,ctlLayout){

		var outerDiv = $(outerDivId);
	    //清空上次绘制
		outerDiv.innerHTML="";       
		var tabTableId = outerDivId+'tabPageTable';
		var _viewId = outerDivId+'_viewId';
		var tableHtml = '<table style="width:100%;border:0px" cellpadding="0" cellspacing="0" > ';
		tableHtml +=		'<tr>';
		tableHtml +=			'<td align="left">';
		tableHtml +=				'<table id="'+ tabTableId+'" cellpadding="0" cellspacing="0">';
		tableHtml +=				'</table>';
		tableHtml +=			'</td>';
		tableHtml +=		'</tr>';
		tableHtml +=        '<tr>';
		tableHtml +=			'<td align="left">';
		tableHtml +=				'<div id="'+_viewId+'"/>';
		tableHtml +=			'</td>';
		tableHtml +=        '</tr>';
		tableHtml +=	'</table>';
		var view_DIV = document.createElement("div");
		view_DIV.innerHTML = tableHtml;
		outerDiv.appendChild(view_DIV);
		var tabPageTable = $(tabTableId);
		//需要创建几个tab页
		var len = tabPageInfoArr.length;
		var tr = document.createElement("tr");
		
		for(i = 0 ; i < len ; i++){
			td = TabPageFramework.createTd('L',false);
			tr.appendChild(td);
			td = TabPageFramework.createTd("M",false,tabPageInfoArr[i].tabPageName);
			Event.observe(td, 'click', TabPageFramework.tabChangeHandler.bindAsEventListener("replaceObj",view_DIV,tabPageTable,i,tabPageInfoArr));
			tr.appendChild(td); 
			td = TabPageFramework.createTd('R',false);
			tr.appendChild(td);
		}
		tabPageTable.tBodies[0].appendChild(tr);
		
		//是否交由框架控制布局
		if(ctlLayout !== true && ctlLayout !== false){
			ctlLayout = true;
		}
		//将展现div apppend进来
		if(ctlLayout){
			for(var i = 0 ; i < tabPageInfoArr.length ; i++){
				var viewDiv = $(tabPageInfoArr[i].viewDiv);
				$(_viewId).appendChild(viewDiv);
			}
		}
		TabPageFramework.initTabPage(tabPageTable,tabPageInfoArr);
	},

	/**
	*tab页click事件监听方法
	*用途是控制显示格式，以及刷新和不刷新的等控制问题，实际的操作交给具体的处理方法，相当于一个action
	*起控制作用
	*/
	tabChangeHandler:function (e){
		var paras = $A(arguments);	
		var outerDiv = paras[1];
		var table = paras[2];
		var num = paras[3];
		var tagPageInfoArr = paras[4];
		//处理查询，(第num个tab页的查询)
		var tabPageInfo = tagPageInfoArr[num];
		var tabPageDiv = $(tabPageInfo.viewDiv);
		var refresh = tabPageInfo.refresh;
		var loaded = tabPageInfo.loaded;
		var innerHTML = tabPageDiv.innerHTML;
		var retFlag = true;
		//如果刷新，调查询方法
		if(refresh){
			retFlag = tabPageInfo.callBackFunc.apply("replaceObj",$A(arguments));
		}else{
				//如果不是每次刷新，则只查询一次 
				if(!loaded){
					retFlag = tabPageInfo.callBackFunc.apply("replaceObj",$A(arguments));
					tabPageInfo.loaded=true;
				}
		}
		//回调函数不明确返回false，则默认为成功执行，调用格式控制函数
		if(retFlag != false){
			retFlag = true;
		}
		if(retFlag){
			//处理显示格式
			TabPageFramework.changeStyle(outerDiv,table,num,tagPageInfoArr);
		}
		
		retFlag = true; //返回值置初值
	},

	/*
	*创建td
	*
	*postion : L,M,R
	*curr:当前是否选中 true,false 
	*/
	createTd:function(position,curr,text){
		var td = null;
		if(position == 'L'){
			td = document.createElement("td");
			if(curr){
				td.className = "left1";
			}else{
				td.className = "left2";
			}
		}
		if(position == 'M'){
			td = document.createElement("td");
			var innerText = document.createTextNode(text);
			td.appendChild(innerText);
			if(curr){
				td.className="middle1";
			}else{
				td.className="middle2";
			}
		}
		if(position == 'R'){
			td = document.createElement("td");
			if(curr){
				td.className = "right1";
			}else{
				td.className = "right2";
			}
		}
		return td;
	},

	/*
	*
	*控制显示格式
	*outerDiv参数暂时未使用
	*
	*/
	changeStyle:function(outerDiv,table,num,tagPageInfoArr){
		//改变展现div的显示与否
		for(var i = 0 ; i < tagPageInfoArr.length ; i++){
			var tabPageInfo = tagPageInfoArr[i];
			var viewDiv = $(tabPageInfo.viewDiv);
			var displayDiv;
			if(i == num){
				displayDiv = viewDiv;
			}else{
				viewDiv.style.display = "none";
			}
		}
		displayDiv.style.display = "block";
		//改变tab页的样式（明，暗）
		 var currPos = eval((num+1)*3-2);
		 var c=table.cells;
		  for(var i=1 ; i<c.length ; i+=3){
				 if(c[i].className=="middle1"){
					if(currPos != i ){
						c[i].className="middle2";
						c[i-1].className="left2";
						c[i+1].className="right2";
						break;
					}
				  }
			}
			c[currPos].className = "middle1";
			c[currPos-1].className = "left1";
			c[currPos+1].className = "right1";
	 },

	/**
	*init方法：1.默认第一个tab页要查询并且显示，其余的按init即initDiplay配置是否查询即显示
	*
	*/
	initTabPage:function(tabPageTable,tagPageInfoArr){
		var displayNum = 0;
		for(var i = 0 ; i < tagPageInfoArr.length ; i++){
			var tabpageInfo = tagPageInfoArr[i];
			var init = tabpageInfo.init;
			var initDisplay = tabpageInfo.initDisplay;
			
			//初始加载(初始显示的默认为初始加载)
			if(init || initDisplay){
				tagPageInfoArr[i].callBackFunc.apply("replaceObj",$A(arguments));
				tagPageInfoArr[i].loaded=true;
			}
			//初始显示
			if(initDisplay){
				displayNum = i;
			}
		}
		var loaded = tagPageInfoArr[displayNum].loaded;
		if(!loaded){
			var func = tagPageInfoArr[displayNum].callBackFunc;
			func.apply("replaceObj",$A(arguments));
			tagPageInfoArr[displayNum].loaded = true;
		}
		TabPageFramework.changeStyle('',tabPageTable,displayNum,tagPageInfoArr);
	},
	
	/**
	*默认的回调函数，什么也不做，处理tab页点击事件不需任何处理的情况
	*一般用于各tab页的加载同时初始化时完成，且点击tab页时不需重新刷新，
	*只是控制显示就可以了。
	*/
	_doNothing:function(){
		return true;
	}
}

 