const url = "http://localhost:8080/users";

export async function findByIds(username) {
    const response = await fetch(`${url}/${username}`);
    if (response.status === 200) {
        return await response.json();
    }
    return Promise.reject("not 200 OK");
}

export async function add(user) {
    const init = {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Accept": "application/json",
        },
        body: JSON.stringify(user)
    }

    const response = await fetch(`${url}/register`, init);

    if (response.status === 201) {
        // return await response.json();
        return;
    }

    const { messages } = await response.json();

    return Promise.reject(messages);
}
