import '../pet.scss';
import { useState, useEffect, useContext } from "react";
import { useHistory, useParams } from 'react-router-dom';
import { findById, update } from '../services/pets.js';
import { findByUsername } from '../services/users';
import LoginContext from "../contexts/LoginContext";


function Pet() {

    const [pet, setPet] = useState();
    const[itemList, setItemList] = useState();
    const { username } = useContext(LoginContext);
    const [user, setUser] = useState();
    const { id } = useParams();
    const history = useHistory();

    const thirstMonitor = 1000 * 60 * 60 * 3;
    const currentDate = new Date();

    useEffect(() => {
        if (username) {
            findByUsername(username)
            .then((result) => {
                setUser(result);
            })
        }
        if(id) {
            findById(id)
            .then(setPet)
            .catch(() => history.push("/error"))
        }
    }, [history]);

    const updatePetHunger = evt => {
        pet.hungerLevel = pet.hungerLevel + 10;
        update(pet)
            .catch(() => history.push(`/pet/${pet && pet.petId}`));
    }

    const updatePetCare = evt => {
        pet.careLevel = pet.careLevel + 10;
        update(pet)
            .catch(() => history.push(`/pet/${pet && pet.petId}`));
    }

    const updatePetThirst = evt => {
        pet.thirstLevel = pet.thirstLevel + 10;
        update(pet)
            .catch(() => history.push(`/pet/${pet && pet.petId}`));

    }

    console.log(pet);

    return (
        <>
        <br></br>
        <label for="success_select">Use an Item?</label>
            <div class="nes-select is-success" name="pet-select">
            <select required id="pet-select">
                <option value="" disabled selected hidden>Select...</option>
                {user && user.items.map((i, index) =>
                <option value={index}>{i.name}</option>)}
            </select>
            </div>
        <div className='container' id="egg-container">
            <div className='display-bars'>
                <progress id="health-bar" className="nes-progress is-error" value={pet && pet.healthLevel} max={pet && pet.petType.health} />
                <text id="health-text" className="text">health_lvl</text>
                <progress id="care-bar" className="nes-progress is-warning" value={pet && pet.careLevel} max={pet && pet.petType.care} />
                <text id="care-text" className="text">care_lvl</text>
                <progress id="hunger-bar" className="nes-progress is-success" value={pet && pet.hungerLevel} max={pet && pet.petType.appetite} />
                <text id="hunger-text" className="text">hunger_lvl</text>
                <progress id="thirst-bar" className="nes-progress is-primary" value={pet && pet.thirstLevel} max={pet && pet.petType.thirst} />
                <text id="thirst-text" className="text">thirst_lvl</text>
            </div>
            <button onClick={() => setItemList(true)}className='loops'></button>
            <div className='eggs'>
                <text className="display-name">{pet && pet.name}</text>
                <div className='crack'>
                    <div className='display'>
                        <div className='grid'>
                            <img id="active-image" src="https://2.bp.blogspot.com/-BwqYts1IQQ8/Txl9ZXaXwFI/AAAAAAAACbg/2b9IMKJ8_H0/s1600/6.gif" alt="" />
                        </div>
                    </div>
                </div>
            </div>
            <div className='buttons-pet'>
                <button id="pet-buttons" class={`buttons-pet ${(pet && pet.hungerLevel >= 100 ? "is-warning" : "is-disabled")}`} onClick={updatePetHunger} />
                <button id="pet-buttons" class={`buttons-pet ${(pet && pet.careLevel >= 100 ? "is-warning" : "is-disabled")}`} onClick={updatePetCare} />
                <button id="pet-buttons" class={`buttons-pet ${(pet && pet.thirstLevel >= 100 ? "is-warning" : "is-disabled")}`} onClick={updatePetThirst} />
            </div>
            <button id='item-button' className='nes-btn is-normal' >item</button>
        </div>
        </>
    );
}

export default Pet;