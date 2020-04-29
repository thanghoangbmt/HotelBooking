/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function Validate() {
    var password = document.getElementById("txtPassword").value;
    var confirmPassword = document.getElementById("txtConfirmPassword").value;
    if (password !== confirmPassword) {
        alert("Passwords do not match!");
        return false;
    }

    var phone = document.getElementById("phone").value;
    var phoneNum = /^\d{10}$/;
    if (!phone.value.match(phoneNum)) {
        alert("Phone number is invalid!");
        return false;
    }
    
    return true;
}

function checkPhone() {
    var phone = document.getElementById("txtPhone").value;
    var phoneNum = /^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$/;

    if (phone.value.match(phoneNum)) {
        return true;
    } else {
        alert("Phone number is invalid!");
        return false;
    }
}