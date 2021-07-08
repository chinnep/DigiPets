import '../pet.scss';
import { useState, useEffect, useContext } from "react";
import { useHistory, useParams } from 'react-router-dom';
import { findById, update } from '../services/pets.js';
import { findByUsername } from '../services/users';
import LoginContext from "../contexts/LoginContext";


function Pet() {

    const [pet, setPet] = useState();
    const[itemList, setItemList] = useState();
    const[item, setItem] = useState();
    const { username } = useContext(LoginContext);
    const [user, setUser] = useState();
    const [msg, setMsg] = useState();
    const { id } = useParams();
    const history = useHistory();

    const thirstMonitor = 1000 * 60 * 60 * 3;
    const currentDate = new Date();

    useEffect(() => {
        if (username) {
            findByUsername(username)
            .then((result) => {
                setUser(result);

                if((id && user) && !pet) {
                    for(let i = 0; i < user.pets.length; i++) {
                        if(user.pets[i].petId == id) {
                            setPet(user.pets[i]);
                        }
                    }
                }})
            .catch(() => history.push("/error"))
        }
      }, [id, user, history]);

    const updatePetHunger = evt => {
        pet.hungerLevel += 10;
        console.log(pet);
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

        console.log(item);
        console.log(pet);

         if (item) {
            switch (item.itemId) {
                case 1:
                  while(pet.hungerLevel < pet.petType.appetite 
                    && pet.thirstLevel < pet.petType.thirst) {
                      updatePetHunger();
                      updatePetThirst();
                      console.log("increasing");
                  }
                  break;
                case 2:
                    while(pet.hungerLevel < pet.petType.appetite) {
                          updatePetHunger();
                        }
                  break;
                case 3:
                    while(pet.thirstLevel < pet.petType.thirst) {
                          updatePetThirst();
                      }
                  break;
                case 4:
                    while(pet.careLevel < pet.petType.care) {
                        updatePetCare();
                    }
                  break;
            }
        }
    }

    return (
        <>
        <br></br>
        {itemList?
        <>
        <label for="success_select">Use an Item?</label>
            <div className="nes-select is-success" name="pet-select">
            <select required id="pet-select" onChange={selectItem}>
                <option defaultValue={null} key="default">Select...</option>
                {user && user.items.map((i, index) =>
                <option value={index} key={i.itemId}>{i.name}</option>)}
            </select>
            <br></br>
            </div>
            <button onClick={useItem} className="nes-btn is-success">use on {pet.name}</button>

            <br></br>
        </>:<></>}
        {pet ?
        <div className='container' id="egg-container">
            <div className='display-bars'>
                <progress id="health-bar" className="nes-progress is-error" value={pet.healthLevel} max={100} />
                <text id="health-text" className="text">health_lvl</text>
                <progress id="care-bar" className="nes-progress is-warning" value={pet.careLevel} max={100} />
                <text id="care-text" className="text">care_lvl</text>
                <progress id="hunger-bar" className="nes-progress is-success" value={pet.hungerLevel} max={100} />
                <text id="hunger-text" className="text">hunger_lvl</text>
                <progress id="thirst-bar" className="nes-progress is-primary" value={pet.thirstLevel} max={100} />
                <text id="thirst-text" className="text">thirst_lvl</text>
            </div>
            <button onClick={() => setItemList(true)}className='loops'></button>
            <div className='eggs'>
                <text className="display-name">{pet.name}</text>
                <div className='crack'>
                    <div className='display'>
                        <div className='grid'>
                            <img id="active-image" src={process.env.PUBLIC_URL + "/img/" + pet.petType.name + '/default.gif'} alt="" />
                        </div>
                    </div>
                </div>
            </div>
            <div className='buttons-pet'>
                <button id="pet-buttons" className={`buttons-pet ${(pet.hungerLevel >= 100 ? "is-warning" : "is-disabled")}`} onClick={updatePetHunger} />
                <button id="pet-buttons" className={`buttons-pet ${(pet.careLevel >= 100 ? "is-warning" : "is-disabled")}`} onClick={updatePetCare} />
                <button id="pet-buttons" className={`buttons-pet ${(pet.thirstLevel >= 100 ? "is-warning" : "is-disabled")}`} onClick={updatePetThirst} />
            </div>
        </div>:<></>}
        </>
    );
}

export default Pet;