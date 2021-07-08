import '../pet.scss';
import { useState, useEffect } from "react";
import { useHistory, useParams } from 'react-router-dom';
import { findById, update } from '../services/pets.js';

function Pet() {

    const [pet, setPet] = useState();
    const { id } = useParams();
    const history = useHistory();

    const thirstMonitor = 1000 * 60 * 60 * 3;
    const currentDate = new Date();

    useEffect(() => {
        if (id) {
            findById(id)
                .then(p => {
                    setPet(p)
                    console.log(p);
                })
                .catch(() => history.push("/error"))
        }
    }, [id]);

    const updatePetHunger = evt => {
        pet.hungerLevel = pet.hungerLevel + 10;
        update(pet)
            .then(() => history.push(`/pet/${pet && pet.petId}`))
            .catch(() => history.push("/error"));
    }

    const updatePetCare = evt => {
        pet.careLevel = pet.careLevel + 10;
        update(pet)
            .then(() => history.push(`/pet/${pet && pet.petId}`))
            .catch(() => history.push("/error"));
    }

    const updatePetThirst = evt => {
        pet.thirstLevel = pet.thirstLevel + 10;
        update(pet)
            .then(() => history.push(`/pet/${pet && pet.petId}`))
            .catch(() => history.push("/error"));
    }

    return (
        <div className='container' id="egg-container">
            <div className='display-bars'>
                <progress id="health-bar" className="nes-progress is-error" value={pet && pet.healthLevel} max="100" />
                <text id="health-text" className="text">health_lvl</text>
                <progress id="care-bar" className="nes-progress is-warning" value={pet && pet.careLevel} max="100" />
                <text id="care-text" className="text">care_lvl</text>
                <progress id="hunger-bar" className="nes-progress is-success" value={pet && pet.hungerLevel} max="100" />
                <text id="hunger-text" className="text">hunger_lvl</text>
                <progress id="thirst-bar" className="nes-progress is-primary" value={pet && pet.thirstLevel} max="100" />
                <text id="thirst-text" className="text">thirst_lvl</text>
            </div>
            <div className='loops'></div>
            <div className='eggs'>
                <text className="display-name">{pet && pet.name}</text>
                <div className='crack'>
                    <div className='display'>
                        <div className='grid'>
                            {pet && <img id="active-image" src={process.env.PUBLIC_URL + "/img/" + pet.petType.name + '/default.gif'} alt="" />}
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
    );
}

export default Pet;