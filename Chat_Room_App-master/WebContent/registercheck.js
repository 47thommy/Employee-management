function check() {

    var name = document.getElementById('user_name').value;
    var email=document.getElementById('user_email').value;
    var password = document.getElementById('user_password').value;
    if (name == "" || password == "" || email=="") {
        alert("All fields are required")
        return error;
    }
}