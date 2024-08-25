function routepages() {
    var role = document.getElementById('username').value; // Get the value of the input field with id 'username'

    if (role === "user") {
        window.location.href = "../user/BookAppointment.html"; // Redirect to user.html if role is 'user'
    } else if (role === "admin") {
        window.location.href = "../admin/ShowAllPatients.html"; // Redirect to admin.html if role is 'admin'
    } else if (role === "doctor") {
        window.location.href = "../doctor/dr.html"; // Redirect to admin.html if role is 'admin'
    } 
    
    else {
        alert("Invalid username!"); // Alert if the username doesn't match 'user' or 'admin'
    }
}