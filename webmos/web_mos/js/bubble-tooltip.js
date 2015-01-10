/************************************************************************************************************
Ajax dynamic list
Copyright (C) September 2005  DTHMLGoodies.com, Alf Magne Kalleland

This library is free software; you can redistribute it and/or
modify it under the terms of the GNU Lesser General Public
License as published by the Free Software Foundation; either
version 2.1 of the License, or (at your option) any later version.

This library is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public
License along with this library; if not, write to the Free Software
Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA

Dhtmlgoodies.com., hereby disclaims all copyright interest in this script
written by Alf Magne Kalleland.

Alf Magne Kalleland, 2006
Owner of DHTMLgoodies.com
	
************************************************************************************************************/	
function showToolTip(x,y,text){
	var obj;
	var obj2;
	var st;
	var leftPos;
	if(y<200){
	   
		 obj = document.getElementById('bubble_tooltip_up');
		 obj2 = document.getElementById('bubble_tooltip_content_tranc');
		 obj2.innerHTML = text;
		 obj.style.display = 'block';
		 st = Math.max(document.body.scrollTop,document.documentElement.scrollTop);
		if(navigator.userAgent.toLowerCase().indexOf('safari')>=0)st=0; 
		 leftPos = x ;
		
			if(leftPos<0){leftPos = 0;}
		    obj.style.left = leftPos + 'px';
			obj.style.top = y +10+ 'px';
		
	}else{
	
		 obj = document.getElementById('bubble_tooltip');
		 obj2 = document.getElementById('bubble_tooltip_content');
		 obj2.innerHTML = text;
		 obj.style.display = 'block';
		 st = Math.max(document.body.scrollTop,document.documentElement.scrollTop);
		if(navigator.userAgent.toLowerCase().indexOf('safari')>=0)st=0; 
		 leftPos = x - 100;
		 if(leftPos<0){leftPos = 0;}
		 obj.style.left = leftPos + 'px';
		 obj.style.top = y - obj.offsetHeight + st + 'px';
	
	}
	
	
}	

function hideToolTip()
{
	document.getElementById('bubble_tooltip').style.display = 'none';
	document.getElementById('bubble_tooltip_up').style.display = 'none';
	
}