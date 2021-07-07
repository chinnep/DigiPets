const url = "http://localhost:8080/shop";

export async function purchaseEgg(username) {

    const jwt = localStorage.getItem("jwt");

    if (!jwt) {
        return Promise.reject("forbidden");
    }

    const init = {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Accept": "application/json",
            "Authorization": `bearer ${jwt}`
        }
    }

    const response = await fetch(`${url}/egg/${username}/`, init);

    if (response.status !== 200) {
        return Promise.reject("not 200 ok");
    }

    return response.json();
}

export async function purchaseItem(username, itemId) {

    const jwt = localStorage.getItem("jwt");

    if (!jwt) {
        return Promise.reject("forbidden");
    }

    const init = {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Accept": "application/json",
            "Authorization": `bearer ${jwt}`
        }
    }

    const response = await fetch(`${url}/item/${username}/${itemId}`, init);

    if (response.status !== 200) {
        return Promise.reject("not 200 ok");
    }

    return response.json();
}