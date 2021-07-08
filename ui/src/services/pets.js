const url = "http://localhost:8080/pet";

export async function findAll() {
    const response = await fetch(url);
    if (response.status === 200) {
        return await response.json();
    }
    return Promise.reject("not 200 Ok");
}

export async function findById(petId) {

    const response = await fetch(`${url}/${petId}`);
    if (response.status !== 200) {
        return Promise.reject("not 200 ok");
    }
    return response.json();
}

export async function add(pet) {

    const init = {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Accept": "application/json"
        },
        body: JSON.stringify(pet)
    }

    const response = await fetch(url, init);
    if (response.status !== 201) {
        return Promise.reject("not 201 Created");
    }
    return response.json();
}

export async function update(pet) {

    const jwt = localStorage.getItem("jwt");

    if (!jwt) {
        return Promise.reject("forbidden");
    }

    const init = {
        method: "PUT",
        headers: {
            "Content-Type": "application/json",
            "Accept": "application/json",
            "Authorization": `bearer ${jwt}`
        },
        body: JSON.stringify(pet)
    }

    const response = await fetch(`${url}/${pet.petId}`, init);

    if (response.status !== 204) {
        return Promise.reject("not 204 ok");
    }
    return response.json();
}

export async function deleteById(id) {
    const response = await fetch(`${url}/${id}`, { method: "DELETE" });
    if (response.status !== 204) {
        return Promise.reject("wasn't 204");
    }
}
