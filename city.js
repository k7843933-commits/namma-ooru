// 🔐 Check token
const token = localStorage.getItem("token");

if (!token) {
    alert("Please login first ❌");
    window.location.href = "login.html";
}

// 🔥 Fetch cities
fetch("http://localhost:8082/city/all", {
    method: "GET",
    headers: {
        "Authorization": "Bearer " + token
    }
})
.then(res => {
    console.log("STATUS:", res.status);

    if (!res.ok) {
        throw new Error("Failed to fetch cities");
    }

    return res.json();
})
.then(data => {
    console.log("CITIES:", data);

    const container = document.getElementById("cities");
    container.innerHTML = ""; // clear old

    data.forEach(city => {
        const div = document.createElement("div");

        div.style.border = "1px solid black";
        div.style.padding = "10px";
        div.style.margin = "10px";
        div.style.cursor = "pointer";

        div.innerHTML = `
            <h3>${city.cityName}</h3>
            <p>ID: ${city.id}</p>
        `;

        // 👉 click → go to guides page
        div.onclick = () => {
            localStorage.setItem("cityId", city.id);
            window.location.href = "places.html";
        };

        container.appendChild(div);
    });
})
.catch(err => {
    console.error(err);
    alert("Error loading cities ❌");
});