function login() {

    const phone = document.getElementById("phone").value.trim();
    const password = document.getElementById("password").value.trim();

    fetch("https://namma-ooru-3.onrender.com/auth/tourist/login", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({ phone, password })
    })
    .then(res => {
        if (!res.ok) {
            return res.text().then(text => {
                throw new Error(text || "Login failed ❌");
            });
        }
        return res.text(); // token
    })
    .then(token => {

        console.log("TOKEN 👉", token);
        localStorage.setItem("token", token);

        // ✅ GET USER BY PHONE
        return fetch("https://namma-ooru-3.onrender.com/tourist/phone/" + phone)
            .then(res => {
                if (!res.ok) {
                    return res.text().then(text => {
                        throw new Error(text || "User fetch failed ❌");
                    });
                }
                return res.json();
            })
            .then(user => {

                console.log("USER 👉", user);

                if (!user || !user.id) {
                    throw new Error("Invalid user data ❌");
                }

                localStorage.setItem("touristId", user.id);

                alert("Login Success ✅");
                window.location.href = "dashboard.html";
            });

    })
    .catch(err => {
        console.error("LOGIN ERROR 👉", err);
        alert(err.message);
    });
}



// ======================
// 📝 REGISTER
// ======================
function register() {

    const name = document.getElementById("regName").value.trim();
    const phone = document.getElementById("regPhone").value.trim();
    const password = document.getElementById("regPassword").value.trim();

    if (!validatePhone(phone)) {
        alert("Enter valid Indian number (+91XXXXXXXXXX) ❌");
        return;
    }

    if (!validatePassword(password)) {
        alert("Password must be at least 6 characters ❌");
        return;
    }

    fetch("https://namma-ooru-3.onrender.com/tourist/add", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({ name, phone, password })
    })
    .then(res => {
        if (!res.ok) {
            return res.text().then(text => {
                throw new Error(text || "Registration failed ❌");
            });
        }
        return res.json();
    })
    .then(() => {

        // ✅ AUTO LOGIN
        return fetch("https://namma-ooru-3.onrender.com/auth/tourist/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({ phone, password })
        })
        .then(res => {
            if (!res.ok) {
                return res.text().then(text => {
                    throw new Error(text || "Auto login failed ❌");
                });
            }
            return res.text();
        })
        .then(token => {

            console.log("TOKEN 👉", token);
            localStorage.setItem("token", token);

            // ✅ GET USER ID
            return fetch("https://namma-ooru-3.onrender.com/tourist/phone/" + phone)
                .then(res => {
                    if (!res.ok) {
                        return res.text().then(text => {
                            throw new Error(text || "User fetch failed ❌");
                        });
                    }
                    return res.json();
                })
                .then(user => {

                    console.log("USER 👉", user);

                    if (!user || !user.id) {
                        throw new Error("Invalid user data ❌");
                    }

                    localStorage.setItem("touristId", user.id);

                    alert("Registered & Logged in ✅");
                    window.location.href = "dashboard.html";
                });
        });

    })
    .catch(err => {
        console.error("REGISTER ERROR 👉", err);
        alert(err.message);
    });
}



// ======================
// 🔍 VALIDATION
// ======================
function validatePhone(phone) {
    return /^\+91[6-9]\d{9}$/.test(phone);
}

function validatePassword(password) {
    return password.length >= 6;
}