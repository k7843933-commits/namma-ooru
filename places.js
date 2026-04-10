const token = localStorage.getItem("token");
const cityId = localStorage.getItem("cityId");

// ======================
// 🔥 LOAD PLACES
// ======================
fetch(`https://namma-ooru-3.onrender.com/place/city/${cityId}`, {
    headers: {
        "Authorization": "Bearer " + token
    }
})
.then(res => res.json())
.then(data => {

    const container = document.getElementById("places");
    container.innerHTML = "";

    data.forEach(place => {

        const div = document.createElement("div");
        div.className = "card";

        const name = document.createElement("h3");
        name.innerText = place.placeName;

        div.appendChild(name);

        container.appendChild(div);
    });

})
.catch(err => {
    console.error(err);
    alert("Failed to load places ❌");
});


// ======================
// 🔥 LOAD GUIDES
// ======================
fetch(`https://namma-ooru-3.onrender.com/guide/city/${cityId}`, {
    headers: {
        "Authorization": "Bearer " + token
    }
})
.then(res => res.json())
.then(data => {

    const container = document.getElementById("guides");
    container.innerHTML = "";

    data.forEach(guide => {

        const div = document.createElement("div");
        div.className = "guide-card";

        const name = document.createElement("h3");
        name.innerText = guide.name;

        const price = document.createElement("p");
        price.innerText = "₹" + guide.perHourAmount + "/hr";

        const btn = document.createElement("button");
        btn.innerText = "Book";

        // 🔥 BOOK BUTTON FIX
        btn.onclick = function () {

            console.log("Guide selected:", guide.id);
            console.log("Price:", guide.perHourAmount);

            localStorage.setItem("selectedGuideId", guide.id);
            localStorage.setItem("pricePerHour", guide.perHourAmount);

            window.location.href = "booking.html";
        };

        div.appendChild(name);
        div.appendChild(price);
        div.appendChild(btn);

        container.appendChild(div);
    });

})
.catch(err => {
    console.error(err);
    alert("Failed to load guides ❌");
});