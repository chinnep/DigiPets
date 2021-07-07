const url = "http://localhost:8080/shop";

export async function purchaseEgg(username, petName) {
    const init = {
        method: "PUT",
        headers: {
            "Content-Type": "application/json",
            "Accept": "application/json",
        }
    }

    const response = await fetch(`${url}/egg/${username}/${petName}`, init);

    if (response.status !== 201) {
        return Promise.reject("not 200 ok");
    }

    return response.json();
}

export async function purchaseItem(username, itemId) {

    console.log("in purchase item");

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

    console.log(init);

    const response = await fetch(`${url}/item/${username}/${itemId}`, init);

    console.log('after fetch');

    if (response.status !== 200) {
        return Promise.reject("not 200 ok");
    }

    return response.json();
}