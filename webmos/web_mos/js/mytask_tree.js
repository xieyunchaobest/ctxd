
var selectedNode = null;  //当前选定的节点
var overNode = null; //当前经过的节点
function smfolder(span, id) {
	var div = span.parentNode.parentNode;
	var divs = div.children;
	
	var chld = span.children;
	
	if (divs.length > 1 && divs[1].style.display == "none") {  //加divs.length>1是要保证有两个以上节点是才执行
		for (var i = 0; i < divs.length; i++) {
			if (divs[i].style.display != "block") {
				divs[i].style.display = "block";
			} else {
				if (divs[i].nodeName != "SPAN" && (bool != null)) {
					divs[i].style.display = "none";
				}
			}
		}
		chld[0].src = "../images/catalogOpen.gif";
	} else {
		for (var i = 1; i < divs.length; i++) {
			if (divs[i].style.display != "none") {
				divs[i].style.display = "none";
			} else {
				if (divs[i].nodeName != "SPAN" && (bool != null)) {
					divs[i].style.display = "block";
				}
			}
		}
		chld[0].src = "../images/catalog.gif";
	}
}
function smfile(span, href) {
	
	var string2 = getName(span);
	var div = span.parentNode.parentNode.parentNode;
	var pageCode = div.serial;
	while (div.getAttribute("id") != "root") {
		var name = getName(div.children[0].children[0]);
		string2 = name + "&gt;&gt;" + string2;
		div = div.parentNode;
	}
	var id = getId(span);
	//self.parent.parent.frame00.now.innerHTML = "&nbsp;&nbsp;\u5f53\u524d\u4f4d\u7f6e\uff1a" + string2;
	//self.parent.parent.frame00.shortcut.innerHTML = "<a href='#'' onclick='cor(" + id + ")'>\u52a0\u5165\u6211\u7684\u5feb\u6377\u7ec4</a>";
	if (leafType == "url") {
		if (id != null && lUrl.indexOf("#") < 0) {
			
			//alert(lUrl +href + "&funcNodeId=" + id+"&funcNodeName="+string2);
			//alert(self.parent.parent.targetFrame.name);
			//self.parent.parent.targetFrame.src = lUrl +href + "&funcNodeId=" + id+"&funcNodeName="+string2;
			//self.parent.parent.targetFrame.refresh;
			open(lUrl+"&pageCode="+pageCode,tar);
			this.parent.parent.location.reaload();
			return;
		}
	}
	if (leafType == "js") {
		
		eval(lUrl);
		return;
	}
}
function smexpand(div, bool) {
	var divs = div.children;
	if (divs.length > 1) {
		for (var i = 0; i < divs.length; i++) {
			if (bool==null&&divs[i].style.display != "block") {
				divs[i].style.display = "block";
			} else {
				if (divs[i].nodeName != "SPAN" && (bool != null)) {
					divs[i].style.display = "none";
				}
			}
		}
		divSpanSpans = divs[0].children;
		if (divs[1].style.display == ("none")) {
			(divSpanSpans[0].children)[0].src = "../images/catalog.gif";
		} else {
			(divSpanSpans[0].children)[0].src = "../images/catalogOpen.gif";
		}
	} else {
		if (divs[0].style.display != "block") {
			divs[0].style.display = "block";
		}
	}
}
function mouseOver(span) {
	overNode = span;
	if (span != selectedNode) {
		span.style.background = "#cccccc";
  //span.style.borderBottom="#999999 1px solid";
  //span.style.borderLeft="#999999 1px solid";
  //span.style.borderRight="#999999 1px solid";
  //span.style.borderTop="#999999 1px solid";
		span.style.borderBottom = "";
		span.style.borderLeft = "";
		span.style.borderRight = "";
		span.style.borderTop = "";
	}
  //status = getSelectedId()+"  "+getSelectedIsNode()+" "+getOverId()+" "+getOverIsNode();
}
function mouseOut(span) {
	overNode = null;
	if (span != selectedNode) {
		span.style.background = "";
		span.style.borderBottom = "";
		span.style.borderLeft = "";
		span.style.borderRight = "";
		span.style.borderTop = "";
	}
  //status = getSelectedId()+"  "+getSelectedIsNode()+" "+getOverId()+" "+getOverIsNode();
}
function mouseDown(span) {
	span.style.background = "#999999";
  //span.style.borderBottom="#999999 1px solid";
  //span.style.borderLeft="#999999 1px solid";
  //span.style.borderRight="#999999 1px solid";
  //span.style.borderTop="#999999 1px solid";
	span.style.borderBottom = "";
	span.style.borderLeft = "";
	span.style.borderRight = "";
	span.style.borderTop = "";
	if (selectedNode != null && selectedNode != span) {
		selectedNode.style.background = "";
		selectedNode.style.borderBottom = "";
		selectedNode.style.borderLeft = "";
		selectedNode.style.borderRight = "";
		selectedNode.style.borderTop = "";
	}
	selectedNode = span;
}
function mouseUp(span) {
	mouseOver(span);
}
/**
 * 将一个节点展开n层
 */
function smunfold(div, bool, n) {
	if (n != 0) {
		var divs = div.children;
		if (divs != null && divs.length > 1) {
			for (var i = 0; i < divs.length; i++) {
			    if (divs[i].style.display != "block") {
				divs[i].style.display = "block";}
				anunfold(divs[i], bool, n-1);
			}
		}
	}
}
function anunfold(div,bool,n){
    smexpand(div,bool);
  if(n!=0){
    var divs = div.children;
    if(divs!=null && divs.length > 1){
      for(var i=1; i<divs.length; i++){
        anunfold(divs[i],bool,n-1);
      }
    }
  }
}
/*得到被选中节点的信息*/
function getSelectedId() {
	return getId(selectedNode);
}
function getSelectedName() {
	return getName(selectedNode);
}
function getSelectedIsNode() {
	return isNode(selectedNode);
}
/*得到当前经过节点的信息*/
function getOverId() {
	return getId(overNode);
}
function getOverIsNode() {
	return isNode(overNode);
}
/*根据span取得节点信息*/
function getId(span) {
	if (span == null) {
		return;
	}
	var div = span.parentNode.parentNode;
	return div.getAttribute("serial");
}
function getName(span) {
	if (span == null) {
		return null;
	}
	var children = span.children;
	if (children.length < 2) {
		return null;
	}
	var label = children[1];
	return label.innerText;
}
function isNode(span) {
	if (span == null) {
		return null;
	}
	var div = span.parentNode.parentNode;
	var isNode = div.getAttribute("isNode");
	if (isNode == null) {
		return null;
	}
	if (isNode == "Y" || isNode == "y") {
		return true;
	} else {
		return false;
	}
}

/*根据span取得节点信息*/
function getWorkAreaId(span) {
	if (span == null) {
		return;
	}
	var div = span.parentNode.parentNode;
	return div.getAttribute("workAreaId");
}

/*根据span取得pageCode*/
function getPageCode(span) {
	if (span == null) {
		return;
	}
	var div = span.parentNode.parentNode;
	return div.getAttribute("pageCode");
}

