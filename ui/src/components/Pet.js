import '../pet.scss';
import { useState, useEffect, useContext } from "react";
import { useHistory, useParams } from 'react-router-dom';
import { update } from '../services/pets.js';
import Card from 'react-bootstrap/Card';

import { findByUsername } from '../services/users';
import LoginContext from "../contexts/LoginContext";


function Pet() {

    const [pet, setPet] = useState();
    const [itemList, setItemList] = useState();
    const [item, setItem] = useState();
    const { username } = useContext(LoginContext);
    const [user, setUser] = useState();
    const [msg, setMsg] = useState();
    const { id } = useParams();
    const history = useHistory();
    const {LocalDateTime, Duration, DateTimeFormatter} = require("@js-joda/core");
    var lastLogin = useState();
    var timePassed = useState();

    useEffect(() => {
        if (username) {
            findByUsername(username)
                .then((result) => {
                    setUser(result);

                    for (let i = 0; i < result.pets.length; i++) {
                        if (result.pets[i].petId == id) {

                            //resetting health params based on last login:
                            lastLogin = LocalDateTime.parse(result.pets[i].timeAtLastLogin);
                            timePassed = Duration.between(lastLogin, LocalDateTime.now());

                            result.pets[i].careLevel -= updateStats(result.pets[i].careLevel, Math.floor(result.pets[i].petType.care*timePassed._seconds/3600));
                            result.pets[i].hungerLevel -= updateStats(result.pets[i].hungerLevel, Math.floor(result.pets[i].petType.care*timePassed._seconds/3600));
                            result.pets[i].thirstLevel -= updateStats(result.pets[i].thirstLevel, Math.floor(result.pets[i].petType.care*timePassed._seconds/3600));
                            result.pets[i].healthLevel += result.pets[i].healthLevel < 100 ? Math.floor(result.pets[i].petType.care*timePassed._seconds/60) : 0;  
                            result.pets[i].timeAtLastLogin = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME).toString();
                            update(result.pets[i]);

                            setPet(result.pets[i]);
                            
                        }
                    }
                })
                .catch(() => history.push("/error"))
            }
    }, [id, history]);
    
    const updatePetHunger = evt => {
        pet.hungerLevel += 10;
        update(pet)
            .then(history.push(`/pet/${pet.petId}`))
            .catch((err) => console.log(err));
    }

    const updatePetCare = evt => {
        pet.careLevel += 10;
        update(pet)
            .then(() => history.push(`/pet/${pet.petId}`))
            .catch(() => history.push("/error"));
    }

    const updatePetThirst = evt => {
        pet.thirstLevel += 10;
        update(pet)
            .then(() => history.push(`/pet/${pet.petId}`))
            .catch(() => history.push("/error"));
    }

    const selectItem = (e) => {
        const index = e.target.value;

        if (index && user) {
            setItem(user.items[index]);
            console.log(item);
        }
    };

    const useItem = () => {

        if (item) {
            const petClone = { ...pet };
            if(item.forBattle) {
                //send an error
            }
            switch (item.itemId) {
                case 1:
                    petClone.hungerLevel += 700;
                    petClone.thirstLevel += 700;
                    break;
                case 2:
                    petClone.hungerLevel += 700;
                    break;
                case 3:
                    petClone.thirstLevel += 700;
                    break;
                case 4:
                    petClone.careLevel += 700;
                    break;
            }
            update(petClone)
                .then(() => setPet(petClone))
                .catch(() => history.push("/error"));
        }
    }

    return (
    <>
    <br></br>
    {itemList ?
        <div className="pet-container">
            <h3 htmlFor="success_select">Use an Item?</h3>
            <div className="nes-select is-success" name="pet-select">
                <select required id="pet-select" onChange={selectItem}>
                    <option defaultValue={null} key="default">Select...</option>
                    {user && user.items.map((i, index) =>
                    
                        <option value={index} key={i.itemId}>{i.name}</option>)}
                </select>
            </div>
            <br></br>
            <button onClick={useItem} className="nes-btn is-success">use on {pet.name}</button>
        </div> : <></>}
        {pet?
        <Card id="battleprep-card" className="nes-container with-title is-centered">
            <text id="battleprep-display-name" className="title">{pet.name}</text>
            <div>
                <div id="bars">
                    <div className="nes-field is-inline">
                        <text className="text">health_lvl</text>
                    <progress className="nes-progress is-error" value={pet.healthLevel} max={100}/>
                    </div>
                    <div className="nes-field is-inline">
                        <text className="text">care_lvl </text>
                        <progress className="nes-progress is-warning" value={pet.careLevel} max={100}/>
                    </div>
                    <div className="nes-field is-inline">
                        <text className="text">hunger_lvl</text>
                        <progress className="nes-progress is-success" value={pet.hungerLevel} max={100}/>
                    </div>
                    <div className="nes-field is-inline">
                        <text className="text">thirst_lvl</text>
                        <progress className="nes-progress is-primary" value={pet.thirstLevel} max={100}/>
                    </div>
                </div>
                <button onClick={() => setItemList(true)} className='loops'></button>
                    <div  id="battleprep-egg" className='egg'>
                    <div id="battleprep-crack" className='crack'>
                        <div id="battleprep-display" className='display'>
                            <div className='grid'>
                                <img id="battleprep-image" src={process.env.PUBLIC_URL + "/img/" + pet.petType.name + '/default.gif'} alt=""/>
                            </div>
                        </div>
                    </div>
                    <div id="battleprep-buttons" className='buttons'>
                        <button id="battleprep-button" className={`buttons-pet ${(pet.hungerLevel >= 100 ? "is-warning" : "is-disabled")}`} onClick={updatePetHunger}></button>
                        <button id="battleprep-button" className={`buttons-pet ${(pet.careLevel >= 100 ? "is-warning" : "is-disabled")}`} onClick={updatePetCare}></button>
                        <button id="battleprep-button" className={`buttons-pet ${(pet.thirstLevel >= 100 ? "is-warning" : "is-disabled")}`} onClick={updatePetThirst}></button>
                    </div>
                </div>
            </div>
        </Card> :<></>}
        </>
    );
}

function updateStats(old, decrement) {

    return decrement > old ? old : decrement;
}

export default Pet;