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

    const init = {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Accept": "application/json"
        },
        body: JSON.stringify(battle)
    }

    const response = await fetch(url, init);
    if (response.status !== 201) {
        return Promise.reject("not 201 Created");
    }
    return response.json();
}

export async function round(round) {

    const init = {
        method: "PUT",
        headers: {
            "Content-Type": "application/json",
            "Accept": "application/json",
        },
        body: JSON.stringify(round)
    }

    const response = await fetch(`${url}/${round.battleId}`, init);
    if (response.status !== 204) {
        return Promise.reject("not 204 No Content");
    }
}

export async function requestBattle(battleReqest) {

    const init = {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Accept": "application/json"
        },
        body: JSON.stringify(battleReqest)
    }

    const response = await fetch(`${url}/request`, init);
    if (response.status !== 201) {
        return Promise.reject("not 201 Created");
    }
    return response.json();
}