const guideId = parseInt(localStorage.getItem("selectedGuideId"));
let pricePerHour = parseInt(localStorage.getItem("pricePerHour"));

const token = localStorage.getItem("token");
const touristId = localStorage.getItem("touristId");

const hoursInput = document.getElementById("hours");
const totalText = document.getElementById("totalAmount");


// ======================
// 🚨 SAFE FALLBACK FIX
// ======================
if (!pricePerHour || isNaN(pricePerHour)) {

    console.warn("⚠️ Price missing → setting default 100");

    // 👉 fallback (temporary fix)
    pricePerHour = 100;

    // 👉 or redirect (strict method)
    // alert("Please select guide again ❌");
    // window.location.href = "places.html";
}


// ======================
// 🔥 AUTO CALCULATE
// ======================
hoursInput.addEventListener("input", () => {

    const hours = parseInt(hoursInput.value);

    if (!hours || hours <= 0) {
        totalText.innerText = "Total: ₹0";
        return;
    }

    const total = hours * pricePerHour;

    totalText.innerText = "Total: ₹" + total;
});


// ======================
// 🔥 CONFIRM BOOKING
// ======================
function confirmBooking() {

    const hours = parseInt(hoursInput.value);
    const startDate = document.getElementById("startDate").value;
    const endDate = document.getElementById("endDate").value;

    if (!hours || !startDate || !endDate) {
        alert("Fill all fields ❌");
        return;
    }

    if (!touristId) {
        alert("Login again ❌");
        return;
    }

    // 🔥 DATE VALIDATION
    const today = new Date().toISOString().split("T")[0];

    if (startDate < today) {
        alert("Past date not allowed ❌");
        return;
    }

    if (endDate < startDate) {
        alert("End date must be after start date ❌");
        return;
    }

    fetch("http://localhost:8082/booking/add", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Authorization": "Bearer " + token
        },
        body: JSON.stringify({
            guide: { id: guideId },
            tourist: { id: touristId },
            hours: hours,
            startDate: startDate,
            endDate: endDate
        })
    })
    .then(res => {
        if (!res.ok) {
            return res.text().then(text => { throw new Error(text); });
        }
        return res.json();
    })
    .then(() => {
        alert("Booking Success ✅");

        // 👉 clean
        localStorage.removeItem("selectedGuideId");
        localStorage.removeItem("pricePerHour");

        window.location.href = "my-bookings.html";
    })
    .catch(err => {
        console.error(err);
        alert(err.message);
    });
}