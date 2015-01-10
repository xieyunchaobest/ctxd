function updateMenu(obj) {
	var menu = new tree_menu(120);
	if (obj != null) {
		if (foldAllItem.isShow == null) {
			menu.add("修改", "..\\images\\sla_modi.gif", 'js', "modiLeaf('"
					+ obj.id + "');");
			menu.add("删除", "..\\images\\sla_del.gif", 'js', "delLeaf('"
					+ obj.id + "');");
		}
		obj.oncontextmenu = function() {
			event.returnValue = false;
		}
		if (event.button == 2) {
			menu.hideAll();
			menu.show();
		}
	}
}
//点击树根节点事件
function updateRootMenu(obj) {
	var menu = new tree_menu(120);
	if (obj != null) {
		if (foldAllItem.isShow == null) {
			menu.add("增加", "..\\images\\sla_add.gif", 'js', "addLeaf('"
					+ obj.id + "');");
		}
		obj.oncontextmenu = function() {
			event.returnValue = false;
		}
		if (event.button == 2) {
			menu.hideAll();
			menu.show();
		}

	}
}
function toClickBox(name) {
	var searchValue = document.getElementById(name);
	searchValue.value = "";
	searchValue.style.color = "black";
	searchValue.style.fontStyle = "normal";
}
function toLeaveBox(name) {
	var search = document.getElementById(name);
	if (search.value == "") {
		search.value = "请输入名称";
		search.style.color = "gray";
		search.style.fontStyle = "italic";
	}
}
//字符串两边去处空格
function trim(inStr) {
	var str = inStr.replace(/^\s+/, '');
	for ( var i = str.length - 1; i >= 0; i--) {
		if (/\S/.test(str.charAt(i))) {
			str = str.substring(0, i + 1);
			break;
		}
	}
	return str;
}
function initSearchName(eName){
		//初始化内容
	var search = document.getElementById(eName);
	search.style.color="gray";
	search.style.fontStyle="italic";
	
}
//执行模糊查询方法
function searchTreeName(url,eName,frame) {
	var name = document.getElementById(eName).value.toString() + "";
	var searchName = trim(name); //查询时去除两边空格
	var f1 = document.getElementById(frame);
	if (searchName != null && searchName != "请输入名称") {
		f1.src = url + "&" + eName + "=" + name;
	} else {
		f1.src = url;
	}
	function(){tree.openItem("root")};
	　//frame1.submit();		
}
var sp_tree = {
	modiLeaf : function(id){
		alert();
	},
	addLeaf : function(id){
		
	},
	delLeaf : function(id){
		
	}
}