 var x=document.getElementById("sendmessage")
 var name=document.getElementById("username").value
 x.addEventListener("click",()=>{
	 
	 
	 
     var msg=document.getElementById("msg").value
//     var msgbox=document.getElementById("mychat")
//     var para=document.createElement("p")
//     para.innerHTML=name+"-->"+msg
//     msgbox.append(para)
     
     var xhr=new XMLHttpRequest()
     xhr.onreadystatechange=()=>
     {
    	 if(xhr.readyState==4 && xhr.status==200)
    		 {
    		 //prform database opps
    		 }
     }
     console.log(msg)
     
     xhr.open("POST","mychatcontroller?name="+name+ "&message=" + msg,true)
     xhr.send()
     
     
     
     
     
     setTimeout(()=>
               {
         var x=document.getElementById("mychat")
         x.scrollTop=x.scrollHeight
         
                },1000
               )
     
     
     })

var addtext=()=>
{
	var xhr=new XMLHttpRequest();
	
	xhr.onreadystatechange=()=>{
		
		if(xhr.readyState==4 && xhr.status==200)
			{
			    res=xhr.responseText
			    var msgbox=document.getElementById("mychat").innerHTML=res;
//			     var para=document.createElement("p")
//			     para.innerHTML=res
//			     msgbox.append(para)
			}
	}
	xhr.open("POST","getmessages",true)
	xhr.send();
	
}

//setTimeout(addtext,1000);