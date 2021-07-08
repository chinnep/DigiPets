import '../pet.scss';
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
        <div className="row" >
            <label for="success_select">Select a Digipet:</label>
            <div class="nes-select is-success" name="pet-select">
            <select required id="pet-select" onChange={selectPet}>
                <option value="" disabled selected hidden>Select...</option>
                {user && user.pets.map((p, index) =>
                <option value={index}>{p.name}</option>)}
            </select>
            <Link className="nes-btn is-primary" to={`/pet/${pet && pet.petId}`}>Care for Pet</Link>
            </div>
            {user && user.pets.map(p =>
                <div className='container' id="egg-container" key={p.petId}>
                    <div className='display-bars'>
                    <progress id="health-bar" className="nes-progress is-error" value={p.healthLevel} max={100} />
                    <text id="health-text" className="text">health_lvl</text>
                    <progress id="care-bar" className="nes-progress is-warning" value={p.careLevel} max={100} />
                    <text id="care-text" className="text">care_lvl</text>
                    <progress id="hunger-bar" className="nes-progress is-success" value={p.hungerLevel} max={100} />
                    <text id="hunger-text" className="text">hunger_lvl</text>
                    <progress id="thirst-bar" className="nes-progress is-primary" value={p.thirstLevel} max={100} />
                            {//p.thirstLevel - (Math.abs(currentDate.toLocaleString() - p.timeAtLastLogin) / thirstMonitor)
                            // ^^ trying to get thirst to deplete over time... 1 point every 3 hours is what it should equate to
                            }
                        <text id="thirst-text" className="text">thirst_lvl</text>
                    </div>
                    <div className='loops'></div>
                    <div className='eggs'>
                        <text className="display-name">{p.name}</text>
                        <div className='crack'>
                            <div className='display'>
                                <div className='grid'>
                                    <img id="active-image" src={process.env.PUBLIC_URL + "/img/" + p.petType.name + '/default.gif'} alt=""/>
                                </div>
                            </div>
                        </div>
                        <div className='buttons'>
                            <div id="pet-button" class='button' />
                            <div id="pet-button" class='button' />
                            <div id="pet-button" class='button' />
                        </div>
                    </div>
                </div>
            )}
        </div>
    );
}

export default Pets;