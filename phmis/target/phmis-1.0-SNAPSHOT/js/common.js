/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function validateLogin() {

    var errorCount = 1;
    var error = "";


    if ($("#username").val() === "") {
        error += errorCount++ + ". Please enter the user name.<br/>";
    }

    if ($("#password").val() === "") {
        error += errorCount++ + ". Please enter the password.<br/>.";
    }

    if (errorCount > 1) {
        $("#error").html(error);
        $("#error-div").show();
        return false;
    }

}

function validateEnterprise() {

    var errorCount = 1;
    var error = "";


    if ($("#name").val() === "") {
        error += errorCount++ + ". Hospital Name cannot be blank.<br/>";
    }
    if ($("#street").val() === "") {
        error += errorCount++ + ". Street Name cannot be blank.<br/>";
    }
    if ($("#city").val() === "") {
        error += errorCount++ + ". City cannot be blank.<br/>";
    }
    if ($("#state").val() === "") {
        error += errorCount++ + ". Street Name cannot be blank.<br/>";
    }
    if ($("#zipCode").val() === "") {
        error += errorCount++ + ". Zip Code cannot be blank.<br/>";
    }
    if ($("#contact").val() === "") {
        error += errorCount++ + ". Contact Number cannot be blank.<br/>";
    }

    if (errorCount > 1) {
        $("#error").html(error);
        $("#error-div").show();
        return false;
    }

}

function validateActivation() {

    var errorCount = 1;
    var error = "";


    if ($("#password").val() === "") {
        error += errorCount++ + ". Please enter the password.<br/>";
    } else if ($("#confirm").val() !== $("#password").val()) {
        error += errorCount++ + ". Passwords entered does not match.<br/>";
    }

    if (errorCount > 1) {
        $("#error").html(error);
        $("#error-div").show();
        return false;
    }

}

function validateProduct() {

    var errorCount = 1;
    var error = "";


    if ($("#name").val() === "") {
        error += errorCount++ + ". Please enter the product name.<br/>";
    }
    if ($("#description").val() === "") {
        error += errorCount++ + ". Please enter product description.<br/>";
    }
    if ($("#cost").val() === "") {
        error += errorCount++ + ". Please enter product cost.<br/>";
    } else if (isNaN($("#cost").val())) {
        error += errorCount++ + ". Please enter valid product product.<br/>";
    }

    if (errorCount > 1) {
        $("#error").html(error);
        $("#error-div").show();
        return false;
    }

}

function toggleDialog(div) {

    if (div === "room-div") {
        $("#room-div").show();
        $("#staff-div").hide();
    } else {
        $("#staff-div").show();
        $("#room-div").hide();
    }

}

function validateRoom() {

    var errorCount = 1;
    var error = "";


    if ($("#location").val() === "") {
        error += errorCount++ + ". Please enter the room location.<br/>";
    } 

    if (errorCount > 1) {
        $("#error-room").html(error);
        $("#error-div-room").show();
        return false;
    }

}

function validateStaff() {

    var errorCount = 1;
    var error = "";


    if ($("#name").val() === "") {
        error += errorCount++ + ". Please enter the staff name.<br/>";
    } 

    if (errorCount > 1) {
        $("#error-staff").html(error);
        $("#error-div-staff").show();
        return false;
    }

}

