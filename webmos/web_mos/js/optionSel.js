
var spellList_sel="",spellList_timer=null;
function spellList(){
       with(window.event){
          with(srcElement){
              if(keyCode<48)return;
              if(keyCode>95)keyCode-=48
              spellList_sel+=String.fromCharCode(keyCode)
              window.status=spellList_sel
              for(i=0;i<length;i++){
              	if(!options[i].sp){
              		var tmp="",arr=getSpell(options[i].text,"'").split("'")
              		for(var j=0;j<arr.length;j++)tmp+=arr[j].substr(0,1).toUpperCase();
              		options[i].sp=tmp;
              	}
              	if(options[i].sp.indexOf(spellList_sel)==0){selectedIndex=i;break;}
              }
          }
          returnValue=false;
          clearTimeout(spellList_timer)
          spellList_timer=setTimeout("spellList_sel=''",600);
     }
}
