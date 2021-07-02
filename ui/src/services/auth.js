const url = "http://localhost:8080";

export async function authenticate(user) {
    const init = {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Accept": "application/json"
        },
        body: JSON.stringify(user)
    }
    const response = await fetch(`${url}/authenticate`, init);
    if(response.status === 200) {
        return response.json();
    } 
    return Promise.reject("no dice...");
}

export async function refresh() {

    const jwt = localStorage.getItem("jwt");
    if (!jwt) {
        return Promise.reject("forbidden");
    }

    const init = {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Accept": "application/json",
            "Authorization": `Bearer ${jwt}`
        },
        body: JSON.stringify({ jwt_token: jwt })
    }

    const response = await fetch(`${url}/refresh_token`, init);

    if (response.status === 200) {
        const json = await response.json();
        return json["jwt_token"];
    }

    return Promise.reject("Token failed to refresh");
}