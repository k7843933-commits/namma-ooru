const token = localStorage.getItem("token");
const touristId = localStorage.getItem("touristId");

if (!token || !touristId) {
    alert("Login first ❌");
    window.location.href = "login.html";
}

// ======================
// 🔥 LOAD BOOKINGS
// ======================
fetch(`https://namma-ooru-3.onrender.com/booking/tourist/${touristId}`, {
    headers: {
        "Authorization": "Bearer " + token
    }
})
.then(res => {
    if (!res.ok) throw new Error("Failed to load bookings");
    return res.json();
})
.then(data => {
    const container = document.getElementById("bookings");
    container.innerHTML = "";

    if (data.length === 0) {
        container.innerHTML = "<p>No bookings yet 😔</p>";
        return;
    }

    data.forEach(b => {

        const div = document.createElement("div");
        div.style.border = "1px solid black";
        div.style.margin = "10px";
        div.style.padding = "10px";

        let color = "black";

        if (b.status === "BOOKED") color = "orange";
        if (b.status === "CANCELLED") color = "red";
        if (b.status === "COMPLETED") color = "green";

        div.innerHTML = `
            <h3>Guide ID: ${b.guide.id}</h3>
            <p>Name: ${b.guide.name}</p>
            
            <p>Hours: ${b.hours}/hrs</p>
            <p>From: ${b.startDate}</p>
            <p>To: ${b.endDate}</p>
            <p><b>Total Charge:</b> ₹${b.totalAmount}</p>
            <p style="color:${color}"><b>Status: ${b.status}</b></p>
        `;

        // 🔥 CANCEL BUTTON
        if (b.status === "BOOKED") {
            const cancelBtn = document.createElement("button");
            cancelBtn.innerText = "Cancel Booking ❌";
            cancelBtn.onclick = () => cancelBooking(b.id);
            div.appendChild(cancelBtn);
        }

        // ⭐ REVIEW BUTTON
        if (b.status === "COMPLETED") {
            const reviewBtn = document.createElement("button");
            reviewBtn.innerText = "Give Review ⭐";
            reviewBtn.onclick = () => giveReview(b.id, b.guide.id);
            div.appendChild(reviewBtn);
        }

        container.appendChild(div);
    });
})
.catch(err => {
    console.error(err);
    alert("Failed to load bookings ❌");
});


// ======================
// 🔥 CANCEL BOOKING
// ======================
function cancelBooking(id) {

    const reason = prompt("Enter cancel reason:");

    if (!reason) {
        alert("Reason required ❌");
        return;
    }

    fetch(`https://namma-ooru-3.onrender.com/booking/cancel/${id}`, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json",
            "Authorization": "Bearer " + token
        },
        body: JSON.stringify({
            cancelReason: reason
        })
    })
    .then(res => {
        if (!res.ok) {
            return res.text().then(text => {
                throw new Error(text);
            });
        }
        return res.json();
    })
    .then(() => {
        alert("Booking cancelled ✅");
        location.reload();
    })
    .catch(err => {
        console.error(err);
        alert(err.message);
    });
}


// ======================
// ⭐ ADD REVIEW
// ======================
function giveReview(bookingId, guideId) {

    const rating = prompt("Enter rating (1-5):");
    const comment = prompt("Enter comment:");

    if (!rating || rating < 1 || rating > 5) {
        alert("Invalid rating ❌");
        return;
    }

    fetch(`https://namma-ooru-3.onrender.com/review/add/${bookingId}`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Authorization": "Bearer " + token
        },
        body: JSON.stringify({
            rating: rating,
            comment: comment,
            guide: { id: guideId },
            tourist: { id: touristId }
        })
    })
    .then(res => {
        if (!res.ok) {
            return res.text().then(text => {
                throw new Error(text);
            });
        }
        return res.text();
    })
    .then(() => {
        alert("Review added successfully ⭐");
    })
    .catch(err => {
        console.error(err);
        alert(err.message);
    });
}