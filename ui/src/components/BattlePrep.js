import { useState, useEffect, useContext } from "react";
import {useHistory} from 'react-router-dom';
import Card from 'react-bootstrap/Card';
import LoginContext from "../contexts/LoginContext";
import { findByUsername } from "../services/users";
import {requestBattle} from '../services/battle';


function BattlePrep() {

    //should use the list of pets from the user
    const [pet, setPet] = useState();
    const[item, setItem] = useState();
    const { username } = useContext(LoginContext);
    const [user, setUser] = useState();
    const history = useHistory();
    

    useEffect(() => {
        if (username) {
            findByUsername(username)
            .then(setUser)
            .catch(() => history.push("/error"))
        }
      }, [history]);

      const selectPet = (e) => {
          const index = e.target.value;

          if (index && user) {
            setPet(user.pets[index]);
        }
      };

      const selectItem = (e) => {
        const selectedId = e.target.value;

        if (selectedId && user) {
            for(let i = 0; i < user.item.length; i++) {
                if(user.item[i].itemId == selectedId) {
                    setItem(user.item[i])
                }
            }
        }
    };

    const enterQueue = () => {
        const request = {pet,item}

        requestBattle(request)
        .then((result)=> {
            if(result) {
                history.push(`./battle/${result.battleId}`);
            } else {history.push(`/waitingroom/${pet.petId}`)}
        })
        .catch((err) => {
            console.error(err);
            history.push("/error")
        })
    };

    return (
        <>
        {!user? <h3>big error oof</h3>:
            <>
            <div className="battleprep-container">
                <h2 htmlFor="success_select">Choose a DigiPet to Battle:</h2>
                <div className="nes-select is-warning" name="pet-select">
                <select required id="pet-select" onChange={selectPet}>
                    <option defaultValue={null} key="default">Select...</option>
                    {user.pets? user.pets.map((p, index) =>
                    <option value={index} key={p.petId}>{p.name}</option>):<></>}
                </select>
                </div>
            </div>
            {pet?
                <Card id="battleprep-card" className="nes-container with-title is-centered">
                    <text id="battleprep-display-name" className="title">{pet.name}</text>
                        <div  id="battleprep-egg" className='egg'>
                            <div id="battleprep-crack" className='crack'>
                                <div id="battleprep-display" className='display'>
                                    <div className='grid'>
                                        <img id="battleprep-image" src="https://2.bp.blogspot.com/-BwqYts1IQQ8/Txl9ZXaXwFI/AAAAAAAACbg/2b9IMKJ8_H0/s1600/6.gif" alt=""/>
                                    </div>
                                </div>
                            </div>
                            <div id="battleprep-buttons" className='buttons'>
                                <div id="battleprep-button" className='button'></div>
                                <div id="battleprep-button" className='button'></div>
                                <div id="battleprep-button" className='button'></div>
                            </div>
                        </div>
                    <div>
                        <div id="bars">
                            <div className="nes-field is-inline">
                                <text className="text">health_lvl</text>
                            <progress className="nes-progress is-error" value={pet.healthLevel} max={pet.petType.health}/>
                            </div>
                            <div className="nes-field is-inline">
                                <text className="text">care_lvl </text>
                                <progress className="nes-progress is-warning" value={pet.careLevel} max={pet.petType.care}/>
                            </div>
                            <div className="nes-field is-inline">
                                <text className="text">hunger_lvl</text>
                                <progress className="nes-progress is-success" value={pet.hungerLevel} max={pet.petType.appetite}/>
                            </div>
                            <div className="nes-field is-inline">
                                <text className="text">thirst_lvl</text>
                                <progress className="nes-progress is-primary" value={pet.thirstLevel} max={pet.petType.thirst}/>
                            </div>
                        </div>
                        <div className="nes-select">
                            <select required id="default_select" onChange={selectItem}>
                                <option deafultvalue="" disabled selected hidden>Select an item</option>
                                {user.items? user.items.map(i => {(i.quantity > 0) ?
                                    <option value={i.itemId} key={i.name}>{console.log(i.name)}</option>:<></>}):<></>}
                            </select>
                        </div>
                        <button onClick={enterQueue} type="button" className="nes-btn is-success">Enter the Queue</button>
                    </div>
                </Card> :<></>}
            </>}
        </>
    );
}

export default BattlePrep;