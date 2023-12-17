function check()
{
    
    var username=document.getElementById('user_name').value;
    var password=document.getElementById('user_password').value;
    if(username=="" || password=="")
    {
        alert("All fields are required")
        return error;
    }
}