// to get current year
function getYear() {
    var currentDate = new Date();
    var currentYear = currentDate.getFullYear();
    document.querySelector("#displayYear").innerHTML = currentYear;
}

getYear();

// nice select
$(document).ready(function () {
    $('select').niceSelect();
});

// date picker
$(function () {
    $("#inputDate").datepicker({
        autoclose: true,
        todayHighlight: true
    }).datepicker('update', new Date());
});

// owl carousel slider js
$('.team_carousel').owlCarousel({
    loop: true,
    margin: 15,
    dots: true,
    autoplay: true,
    navText: [
        '<i class="fa fa-angle-left" aria-hidden="true"></i>',
        '<i class="fa fa-angle-right" aria-hidden="true"></i>'
    ],
    autoplayHoverPause: true,
    responsive: {
        0: {
            items: 1,
            margin: 0
        },
        576: {
            items: 2,
        },
        992: {
            items: 3
        }
    }
})

// Side Bar
function w3_open() {
  document.getElementById("mySidebar").style.display = "block";
}

function w3_close() {
  document.getElementById("mySidebar").style.display = "none";
}

// Form

function showNewUserForm() {
    document.getElementById("newUserForm").style.display = "block";
    document.getElementById("oldUserForm").style.display = "none";
}

function showOldUserForm() {
    document.getElementById("newUserForm").style.display = "none";
    document.getElementById("oldUserForm").style.display = "block";
}

function validateNewUserForm() {
    const name = document.getElementById("name").value;
    const gender = document.getElementById("gender").value;
    const dob = document.getElementById("dob").value;
    const contact = document.getElementById("contact").value;

    if (name === "" || gender === "" || dob === "" || contact === "") {
        alert("Please fill in all the mandatory fields.");
        return false;
    }

    return true;
}

function validateOldUserForm() {
    const patientId = document.getElementById("patientId").value;
    const contactOld = document.getElementById("contactOld").value;

    if (patientId === "" || contactOld === "") {
        alert("Please fill in all the mandatory fields.");
        return false;
    }

    return true;
}

function toggleSidebar() {
    const sidebar = document.getElementById("sidebar");
    const mainContent = document.querySelector(".main-content");
    if (sidebar.style.width === "0px" || sidebar.style.width === "") {
        sidebar.style.width = "250px";
        mainContent.style.marginLeft = "250px";
    } else {
        sidebar.style.width = "0";
        mainContent.style.marginLeft = "0";
    }
}