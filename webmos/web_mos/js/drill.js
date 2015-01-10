var subWindow;
function createWindow(url,listcode)
{
    var url1 = '../DetailListServlet/main?command=listresult&listcode='+listcode+'&change=1&'+url;
	var h = screen.height - 60;
	var w = screen.width - 10;		
	var status = "height="+h+",width="+w+",left=0,top=0,toolbar=no,titlebar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=yes";			
	if (null != subWindow && !subWindow.closed)
	{
		if (confirm("已打开窗口展现清单,是否载入新的内容?"))
		{
			subWindow.close();
			subWindow = window.open(url1,"",status);			
		}	
	}
	else
	{
		subWindow = window.open(url1,"",status);		
	}
	return;
}
 