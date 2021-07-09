const url = "http://localhost:8080/battle";

export async function findAll() {
    const response = await fetch(url);
    if (response.status === 200) {
        return await response.json();
    }
    return Promise.reject("not 200 Ok");
}

export async function findById(battleId) {
    const response = await fetch(`${url}/${battleId}`);
    if (response.status !== 200) {
        return Promise.reject("not 200 ok");
    }
    return response.json();
}

export async function add(battle) {
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
        },
        body: JSON.stringify(battle)
    }

    const response = await fetch(url, init);
    if (response.status !== 201) {
        return Promise.reject("not 201 Created");
    }
    return response.json();
}

export async function round(roundMove) {
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
        },
        body: JSON.stringify(roundMove)
    }

    const response = await fetch(`${url}/${roundMove.battleId}`, init);
    if (response.status !== 200) {
        return Promise.reject("not 200 No Content");
    }
    return response.json();
}

export async function requestBattle(battleReqest) {
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
        },
        body: JSON.stringify(battleReqest)
    }

    const response = await fetch(`${url}/request`, init);
    if (response.status === 201) {
        return response.json();
    } else if(response.status === 200) {
        return;
    }
    return Promise.reject("not 201 Created");
}