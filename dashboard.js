function goToCity() {
    window.location.href = "city.html";
}

function goToBookings() {
    window.location.href = "my-bookings.html";
}

function logout() {
    localStorage.clear();
    alert("Logged out ✅");
    window.location.href = "index.html";
}