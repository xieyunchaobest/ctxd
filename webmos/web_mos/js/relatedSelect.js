
  	function synRefresh(key, value, targetKey, changeSelect, allowFlag) {
		var url = "../AjaxServlet?method=ajaxGetRelateList";
		var params = "key=" + key + "&value=" + value + "&targetKey=" + targetKey + "&relationSelect=" + changeSelect + "&allowFlag="+allowFlag;
		var myAjax = new Ajax.Request(url, {method:"get", parameters:params, onComplete:_$_showResponse, asynchronous:false});
	}
	function synRefreshFaultType(key, value, targetKey, changeSelect, allowFlag) {
		var url = "../AjaxServlet?method=ajaxFaultTypeQuery";
		var params = "key=" + key + "&value=" + value + "&targetKey=" + targetKey + "&relationSelect=" + changeSelect + "&allowFlag="+allowFlag;
		var myAjax = new Ajax.Request(url, {method:"get", parameters:params, onComplete:_$_faultTypeResponse, asynchronous:false});
	}
    function _$_faultTypeResponse(originalRequest){
        $("faultTypeName").value=originalRequest.responseText;
    }
  	function synchRelated(url,value,changeSelect,requestMode){
  	
  		//alert(requestMode);
		var params = "value="+value+"&relationSelect="+changeSelect;
  		var myAjax = new Ajax.Request(
                    url,
                    {method: 'get', parameters: params, onComplete: _$_showResponse,asynchronous:requestMode}
                    );
                  
  	}
  	function related(url,value,changeSelect){
 	
 		var params = "value="+value+"&relationSelect="+changeSelect;
  		//alert(params);
  		var myAjax = new Ajax.Request(
                    url,
                    {method: 'get', parameters: params, onComplete: _$_showResponse}
                    );
                   
                    
  	}
  	
  	//��ݷ��ؽ�����ɼ�j��-�е�������
  	function _$_showResponse(originalRequest){
  		var xmlDoc=new ActiveXObject("Msxml2.DOMDocument");
  		var resXML = originalRequest.responseText;
  		
  		var relationSelect;
  		var labels=new Array();
  		var values=new Array();
  		xmlDoc.loadXML(resXML);
		if(xmlDoc.parseError.line>0){
			throw xmlDoc.parseError.reason;
		}
		//��ü�j��-�Ķ���
		var nodes= xmlDoc.selectNodes("/response/relationSelect");
		if(nodes.length == 1){
			relationSelect = nodes.item(i).text;
			//alert(relationSelect);
		}
		
		//��ü�j��-�����������
  		nodes= xmlDoc.selectNodes("/response/option/label");
		for(var i=0;i<nodes.length;i++){
			labels[i]= nodes.item(i).text;
			//alert(labels[i]);
		}
		nodes= xmlDoc.selectNodes("/response/option/value");
		for(var i=0;i<nodes.length;i++){
			values[i] = nodes.item(i).text;
			//alert(values[i]);
		}
  		

  		var select_relation = $(relationSelect);
  		var options_relation = select_relation.getElementsByTagName('OPTION');
  		
  		var optionLen = options_relation.length;
  		//alert(optionLen);
  		for(var i=0 ;i<optionLen; i++){
  			Element.remove(options_relation[0]);//ɾ���Ժ�index��lenght������!!
  		}
  			
  
  		for(var i=0;i<labels.length;i++){
  			var optionElement = document.createElement('OPTION');
	  		optionElement.text=labels[i];
	  		optionElement.value=values[i];
  			select_relation.options.add(optionElement);
  			//alert(labels[i]);
  		}
  		
  	}
  	//add by yangkai for xunjian
  	function synRefreshXJ(key, value, targetKey, changeSelect, allowFlag) {
  	  //alert(targetKey);
		var url = "../AjaxServlet?method=ajaxGetRelateList";
		var params = "key=" + key + "&value=" + value + "&targetKey=" + targetKey+ "&relationSelect=" + changeSelect+ "&allowFlag="+allowFlag;
		var myAjax = new Ajax.Request(url, {method:"get", parameters:params, onComplete:_$_showResponseXJ, asynchronous:false});
	}
		//the next diffrent
  	function _$_showResponseXJ(originalRequest){
  		var xmlDoc=new ActiveXObject("Msxml2.DOMDocument");
  		var resXML = originalRequest.responseText;
  		//alert(resXML);
  		var relationSelect;
  		var labels=new Array();
  		var values=new Array();
  		xmlDoc.loadXML(resXML);
		if(xmlDoc.parseError.line>0){
			throw xmlDoc.parseError.reason;
		}		
		var nodes= xmlDoc.selectNodes("/response/relationSelect");
		if(nodes.length == 1){
			relationSelect = nodes.item(i).text;			
		}
		
		
  		nodes= xmlDoc.selectNodes("/response/option/label");
		for(var i=0;i<nodes.length;i++){
			labels[i]= nodes.item(i).text;			
		}
		nodes= xmlDoc.selectNodes("/response/option/value");
		for(var i=0;i<nodes.length;i++){
			values[i] = nodes.item(i).text;			
		}	

  		var select_span = $(relationSelect); 
  		while(select_span.hasChildNodes()){//first delete exsits nodes
            select_span.removeChild(select_span.childNodes[0]);
         }	 
  		if('queryDiv2'==relationSelect){
  		  for(var i=0;i<labels.length;i++){
  		    if(values[i]!=0){//no display "all area"
  		    var newSpan=document.createElement("span");
  		    newSpan.setAttribute('id','displayText');
  		    newSpan.innerHTML="<a href='javascript:queryStaff("+values[i]+",&quot;"+labels[i]+"&quot;)'>"+labels[i]+"</a>&nbsp;&nbsp;";  		   
  		    select_span.appendChild(newSpan);
  		    }
  		    
  		    }	
  		}else if('queryDiv3'==relationSelect){
  		 for(var i=0;i<labels.length;i++){
  		    var newSpan=document.createElement("span");
  		    newSpan.setAttribute('id','displayText');
  		    newSpan.innerHTML="<a href='javascript:queryMap(null,null,"+values[i]+",&quot;"+labels[i]+"&quot;)'>"+labels[i]+"</a>&nbsp;&nbsp;";  		   
  		    select_span.appendChild(newSpan);
  		    }	
  		}else if('queryDiv4'==relationSelect){
  		 for(var i=0;i<labels.length;i++){
  		    var newSpan=document.createElement("span");
  		    newSpan.setAttribute('id','displayText');
  		    newSpan.innerHTML="<a href='javascript:queryTrack("+values[i]+",&quot;"+labels[i]+"&quot;)'>"+labels[i]+"</a>&nbsp;&nbsp;";  		   
  		    select_span.appendChild(newSpan);
  		    }	
  		}else if('queryDiv13'==relationSelect){
  		 for(var i=0;i<labels.length;i++){
  		    var newSpan=document.createElement("span");
  		    newSpan.setAttribute('id','displayText');
  		    newSpan.innerHTML="<a href='javascript:queryByPara(&quot;staff&quot;,"+values[i]+",&quot;"+labels[i]+"&quot;)'>"+labels[i]+"</a>&nbsp;&nbsp;";  		   
  		    select_span.appendChild(newSpan);
  		    }  		    
  		}
  			
  		
  	}
	function canOperate(allowFlag, confIds, localNetId) {
  		var localNetIds="";
  		if(localNetId == "0")
  			localNetIds = localNetId;
  		else {
	  		if (allowFlag != "A") {
				localNetIds = getAllSelectedLocalNetId(confIds);
				if (localNetIds == "false") {
					return false;
				}
			}
		}
		var url = "../AjaxServlet?method=ajaxCheckRight";
		var params = "allowFlag=" + allowFlag + "&localNetIds=" + localNetIds;
		var myAjax = new Ajax.Request(url, {method:"get", parameters:params, onComplete:processResponse, asynchronous:false});

		function processResponse(originalRequest) {
			if ("true"==originalRequest.responseText) {
				operate(allowFlag, confIds);
			} else {
				if(allowFlag=='A'){
					alert("\u5f53\u524d\u7528\u6237\u5bf9\u672c\u5730\u7f51\u6ca1\u6709\u64cd\u4f5c\u6743\u9650\uff01");
				}else{
					alert("\u5f53\u524d\u7528\u6237\u5bf9\u672c\u5730\u7f51[" + originalRequest.responseText + "]\u6ca1\u6709\u64cd\u4f5c\u6743\u9650\uff01");
				}
		}
	}
}
function canMaterialSpecPrptyAdd(localNetId, areaId, materialSpecId) {
	var url = "../AjaxServlet?method=ajaxCheckAdd";
	var params = "localNetId=" + localNetId + "&areaId=" + areaId + "&materialSpecId=" + materialSpecId;
	var myAjax = new Ajax.Request(url, {method:"get", parameters:params, onComplete:processResponse, asynchronous:false});
	function processResponse(originalRequest) {
		alert(originalRequest.responseText);
		if ("true" == originalRequest.responseText) {
			alert("\u8be5\u6750\u6599\u5df2\u5b58\u5728");
		} else {
			materialStockAdd(localNetId, areaId, materialSpecId);
		}
	}
}
function canMaterialSpecPrptyAdd2(localNetId, areaId, materialSpecId) {
	
	var url = "../mm/MaterialStockAction.do?method=ajaxCheckAdd";
	var params = "localNetId=" + localNetId + "&areaId=" + areaId + "&materialSpecId=" + materialSpecId;
	var myAjax = new Ajax.Request(url, {method:"get", parameters:params, onComplete:processResponse1});
	function processResponse1(originalRequest) {
		var xmlDoc = new ActiveXObject("Msxml2.DOMDocument");
		var a=originalRequest.responseText
		
		if ("true" == originalRequest.responseText) {
			alert("\u8be5\u6750\u6599\u5df2\u5b58\u5728");
		} else {
			materialStockAdd(localNetId, areaId, materialSpecId);
		}
	}
}
function canMaterialSpecPrptyDelete(materialSpecPrptyArray) {
	var url = "../AjaxServlet?method=ajaxCheckDelete";
	var params = "materialSpecPrptyIds=" + materialSpecPrptyArray;
	var myAjax = new Ajax.Request(url, {method:"get", parameters:params, onComplete:processResponse1});
	function processResponse1(originalRequest) {
		if ("true" == originalRequest.responseText) {
			alert("\u6709\u6548\u72b6\u6001\u7684\u6750\u6599\u89c4\u683c\u5c5e\u6027\u4e0d\u80fd\u5220\u9664\uff0c\u8bf7\u91cd\u65b0\u9009\u62e9\u65e0\u6548\u7684\u89c4\u683c\u5c5e\u6027\u3002");
		} else {
			deleteMaterialSpecPrpty(materialSpecPrptyArray);
		}
	}
}
function canDrawMaterial(materialArray) {
	var url = "../mm/MaterialManagerAction.do?method=ajaxCheckDrawMaterial";
	materialIds = materialArray.substring(0, materialArray.length - 1);
	var params = "materialIds=" + materialIds;
	var myAjax = new Ajax.Request(url, {method:"get", parameters:params, onComplete:processResponse, asynchronous:false});
	function processResponse(originalRequest) {
		if ("true" == originalRequest.responseText) {
			drawMaterial(materialArray);
		} else {
			alert("\u9009\u62e9\u9886\u7528\u7684\u6750\u6599\u4e2d\u5b58\u5728\u975e\u7a7a\u95f2\u72b6\u6001\u7684\u6750\u6599,\u8bf7\u91cd\u65b0\u9009\u62e9\u7a7a\u95f2\u6750\u6599\uff01");
		}
	}
}
function canRestoreMaterial(materialArray) {
	var url = "../AjaxServlet?method=ajaxCheckRestoreMaterial";
	materialIds = materialArray.substring(0, materialArray.length - 1);
	var params = "materialIds=" + materialIds;
	var myAjax = new Ajax.Request(url, {method:"get", parameters:params, onComplete:processResponse, asynchronous:false});
	function processResponse(originalRequest) {
		if ("true" == originalRequest.responseText) {
			restoreMaterial(materialArray);
		} else {
			alert("\u9009\u62e9\u5f52\u8fd8\u7684\u6750\u6599\u4e2d\u5b58\u5728\u7a7a\u95f2\u72b6\u6001\u7684\u6750\u6599\uff0c\u8bf7\u91cd\u65b0\u9009\u62e9\u975e\u7a7a\u95f2\u72b6\u6001\u7684\u6750\u6599\u8fdb\u884c\u5f52\u8fd8\uff01");
		}
	}
}
function canDrawMaterialStock(materialStockId) {
	var url = "../AjaxServlet?method=ajaxCheckDrawMaterialStock";
	var params = "materialStockId=" + materialStockId;
	var myAjax = new Ajax.Request(url, {method:"get", parameters:params, onComplete:processResponse, asynchronous:false});
	function processResponse(originalRequest) {
		if ("true" == originalRequest.responseText) {
			drawMaterialStock(materialStockId);
		} else {
			alert("\u9009\u62e9\u9886\u7528\u7684\u6750\u6599\u4e2d\u5b58\u5728\u975e\u7a7a\u95f2\u72b6\u6001\u7684\u6750\u6599,\u8bf7\u91cd\u65b0\u9009\u62e9\u7a7a\u95f2\u6750\u6599\uff01");
		}
	}
}
	
	//testWoAjax
function openWoAjax(woNbr) {
	var url = "../AjaxServlet?method=ajaxCheckOpenWo";
	var params = "woNbr=" + woNbr;
	var myAjax = new Ajax.Request(url, {method:"get", parameters:params, onComplete:processResponse, asynchronous:false});
	function processResponse(originalRequest) {
		var returnText = originalRequest.responseText;
		var tdo = document.getElementById("td" + woNbr);
		var tro = document.getElementById("tr" + woNbr);
		if (tdo.style.display == "none") {
			tdo.style.display = "";
			tro.style.display = "";
			tdo.innerHTML = returnText;
			document.getElementById("openImg" + woNbr).src = "../images/subtraction.gif";
		} else {
			tdo.style.display = "none";
			tro.style.display = "none";
			document.getElementById("openImg" + woNbr).src = "../images/plus.gif";
		}
	}
}
function openToAjax(toNbr) {
	var url = "../AjaxServlet?method=ajaxCheckOpenTo";
	var params = "toNbr=" + toNbr;
	var myAjax = new Ajax.Request(url, {method:"get", parameters:params, onComplete:processResponse, asynchronous:false});
	function processResponse(originalRequest) {
		var returnText = originalRequest.responseText;
		var tdo = document.getElementById("td" + toNbr);
		var tro = document.getElementById("tr" + toNbr);
		if (tdo.style.display == "none") {
			tdo.style.display = "";
			tro.style.display = "";
			tdo.innerHTML = returnText;
			document.getElementById("openImg" + toNbr).src = "../images/subtraction.gif";
		} else {
			tdo.style.display = "none";
			tro.style.display = "none";
			document.getElementById("openImg" + toNbr).src = "../images/plus.gif";
			}
		}
	}
	function getAllSelectedLocalNetId(confIds) {
		var _confIds = confIds;
		if (_confIds.lastIndexOf(",") == (_confIds.length - 1)) {
			_confIds = _confIds.substring(0, _confIds.length - 1);
		}
		var ArryIds = _confIds.split(",");
		var returnVal = "";
		for (var i = 0; i < ArryIds.length; i++) {
			var localNetObj = document.getElementById("localNetId" + ArryIds[i]);
			if (localNetObj) {
				if ((returnVal + ",").indexOf("," + localNetObj.value + ",") == -1) {
					returnVal += "," + localNetObj.value;
				}
			} else {
				return false;
			}
		}
		if (returnVal.indexOf(",") == 0) {
			returnVal = returnVal.substring(1, returnVal.length);
		}
		return returnVal;
	}
function synMaterial(materialSpecId){
	var url = "../AjaxServlet?method=ajaxSynMaterial";
	var params = "materialSpecId=" + materialSpecId;
	var myAjax = new Ajax.Request(url, {method:"get", parameters:params, onComplete:processResponse, asynchronous:false});
	function processResponse(originalRequest) {
            var str = originalRequest.responseText;
            var unitId =str.split(",")[0];
            var usedUnitId =str.split(",")[1];
  		    var capacity =str.split(",")[2];
			syn(unitId, usedUnitId, capacity);
	}
}
	
	function canSpecPrptyAdd(standardCode){
    	var url = "../AjaxServlet?method=ajaxCheckSpecPrptyAdd";
    	var params = "standardCode="+standardCode;
   	 	var myAjax = new Ajax.Request(url, {method:"get", parameters:params, onComplete:processResponse, asynchronous:false});
    	function processResponse(originalRequest) {
			if ("false" == originalRequest.responseText) {
				alert("\u8be5\u6807\u51c6\u7f16\u7801\u5df2\u5b58\u5728");
			} else {
				specPrptyAdd();
			}
		}
	}
	
	function canChgServSpecAdd(standardCode){
	    var url = "../AjaxServlet?method=ajaxChgServSpecAdd";
	    var params = "standardCode="+standardCode;
	    var myAjax = new Ajax.Request(url, {method:"get", parameters:params, onComplete:processResponse, asynchronous:false});
	    function processResponse(originalRequest) {
			if ("false" == originalRequest.responseText) {
				alert("\u8be5\u6807\u51c6\u7f16\u7801\u5df2\u5b58\u5728");
			} else {
				chgServSpecAdd();
			}
		}
	}
	
	function canSpecPrptyValueAdd(paraValue,specPrptyId){
	    var url = "../AjaxServlet?method=ajaxSpecPrptyValueAdd";
	    var params = "paraValue="+paraValue+"&specPrptyId="+specPrptyId;
	    var myAjax = new Ajax.Request(url, {method:"get", parameters:params, onComplete:processResponse, asynchronous:false});
	    function processResponse(originalRequest) {
			if ("false" == originalRequest.responseText) {
				alert("\u8be5\u5c5e\u6027\u503c\u5df2\u5b58\u5728");
			} else {
				specPrptyValueAdd();
			}
		}
	}
	