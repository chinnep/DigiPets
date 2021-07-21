import '../pet.scss';
import Card from 'react-bootstrap/Card';

import { useState, useEffect, useContext } from "react";
import { useHistory, Link } from "react-router-dom";
import LoginContext from "../contexts/LoginContext";
import { findByUsername } from '../services/users';

function Pets() {

    const { username } = useContext(LoginContext);
    const [user, setUser] = useState();
    const [pet, setPet] = useState();
    const history = useHistory();
    const element = document.getElementById("pet-select");

    const thirstMonitor = 1000 * 60 * 60 * 3;
    const currentDate = new Date();

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
    }

    return (
        <>
        <div className="pet-container">
            <h3 htmlFor="success_select">Select a Digipet:</h3>
            <div className="nes-select is-success" name="pet-select">
                <select required id="pet-select" onChange={selectPet}>
                    <option value="" disabled selected hidden>Select...</option>
                    {user && user.pets.map((p, index) =>
                    <option value={index}>{p.name}</option>)}
                </select>
            </div>
            <br></br>
            <Link className="nes-btn is-primary" to={`/pet/${pet && pet.petId}`}>Care for Pet</Link>
        </div>
            <div className="pets-wrapper">
            {user && user.pets.map(p =>
                <Card id="pets-card" className="nes-container with-title is-centered">
                    <text id="battleprep-display-name" className="title">{p.name}</text>
                    <div>
                        <div id="bars">
                            <div className="nes-field is-inline">
                                <text className="text">health_lvl</text>
                            <progress className="nes-progress is-error" value={p.healthLevel} max={100}/>
                            </div>
                            <div className="nes-field is-inline">
                                <text className="text">care_lvl </text>
                                <progress className="nes-progress is-warning" value={p.careLevel} max={p.petType.care}/>
                            </div>
                            <div className="nes-field is-inline">
                                <text className="text">hunger_lvl</text>
                                <progress className="nes-progress is-success" value={p.hungerLevel} max={100}/>
                            </div>
                            <div className="nes-field is-inline">
                                <text className="text">thirst_lvl</text>
                                <progress className="nes-progress is-primary" value={p.thirstLevel} max={100}/>
                            </div>
                        </div>
                            <div  id="battleprep-egg" className='egg'>
                            <div id="battleprep-crack" className='crack'>
                                <div id="battleprep-display" className='display'>
                                    <div className='grid'>
                                        <img id="battleprep-image" src={process.env.PUBLIC_URL + "/img/" + p.petType.name + '/default.gif'} alt=""/>
                                    </div>
                                </div>
                            </div>
                            <div id="battleprep-buttons" className='buttons'>
                                <div id="battleprep-button" className='button'></div>
                                <div id="battleprep-button" className='button'></div>
                                <div id="battleprep-button" className='button'></div>
                            </div>
                        </div>
                    </div>
                </Card>
            )}
            </div>
        </>
    );
}

export default Pets;