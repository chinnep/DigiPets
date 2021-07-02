const url = "http://localhost:8080/pets";

export async function findAll() {
    const response = await fetch(url);
    if (response.status === 200) {
        return await response.json();
    }
    return Promise.reject("not 200 Ok");
}