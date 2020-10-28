window.onload = function () {
    document.getElementById("password").onchange = validatePassword;
    document.getElementById("confirmPassword").onchange = validatePassword;
}
function validatePassword(){
    var pass2=document.getElementById("confirmPassword").value;
    var pass1=document.getElementById("password").value;
    if(pass1!=pass2)
        document.getElementById("confirmPassword").setCustomValidity("Passwords don't match");
    else
        document.getElementById("confirmPassword").setCustomValidity('');
//empty string means no validation error
}