
  	
  	
  	
  	function relate(url,value,changeSelect){
 		var params = "value="+value+"&relationSelect="+changeSelect;
  		//alert(params);
  		var myAjax = new Ajax.Request(
                    url,
                    {method: 'get', parameters: params, onComplete: showResponse,asynchronous:false}
                    );
                    
  	}
  	
  	function showResponse(originalRequest){
  		var xmlDoc=new ActiveXObject("Msxml2.DOMDocument");
  		var resXML = originalRequest.responseText;
  		
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
			//alert(relationSelect);
		}
		
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
  			Element.remove(options_relation[0]);
  		}
  			
  
  		for(var i=0;i<labels.length;i++){
  			var optionElement = document.createElement('OPTION');
	  		optionElement.text=labels[i];
	  		optionElement.value=values[i];
  			select_relation.options.add(optionElement);
  			//alert(labels[i]);
  		}
  		
  	}