const token = localStorage.getItem("token");

if (!token) {
    alert("Please login first ❌");
    window.location.href = "login.html";
}

// 🌆 Get selected cityId
const cityId = localStorage.getItem("cityId");

if (!cityId) {
    alert("Please select a city first ❌");
    window.location.href = "city.html";
}

// 📡 Fetch guides by city
fetch(`http://localhost:8082/guide/city/${cityId}`, {
    method: "GET",
    headers: {
        "Authorization": "Bearer " + token
    }
})
.then(res => {
    console.log("STATUS:", res.status);

    if (!res.ok) {
        throw new Error("Failed to fetch guides");
    }

    return res.json();
})
.then(data => {
    console.log("GUIDES:", data);

    const container = document.getElementById("guides");
    container.innerHTML = "";

    data.forEach(guide => {

        const div = document.createElement("div");

        div.style.border = "1px solid black";
        div.style.padding = "10px";
        div.style.margin = "10px";

        div.innerHTML = `
            <h3>${guide.name}</h3>
            <p>ID: ${guide.id}</p>
            <p>Price: ₹${guide.perHourAmount}/hr</p>
            <button onclick="selectGuide(${guide.id}, ${guide.perHourAmount})">
                Select Guide
            </button>
        `;

        container.appendChild(div);
    });
})
.catch(err => {
    console.error(err);
    alert("Error loading guides ❌");
});


// ======================
// 🔥 SELECT GUIDE FUNCTION (FINAL FIX)
// ======================
function selectGuide(guideId, price) {

    console.log("Selected Guide 👉", guideId);
    console.log("Price 👉", price);

    // 🚨 safety check
    if (!price || isNaN(price)) {
        alert("Invalid price ❌");
        return;
    }

    // ✅ store properly
    localStorage.setItem("selectedGuideId", guideId.toString());
    localStorage.setItem("pricePerHour", price.toString());

    // 🔥 confirm saved
    console.log("Saved Price 👉", localStorage.getItem("pricePerHour"));

    // 👉 go to booking page
    window.location.href = "booking.html";
}