import '../pet.scss';
import { useState, useEffect } from "react";
import { useHistory } from "react-router";
import { findAll } from '../services/pets';

function Pets() {

    const [pets, setPets] = useState();
    const history = useHistory();

    useEffect(() => {
        findAll()
            .then(setPets)
            .catch(() => history.push("/error"))
    }, [history]);

    return (
        <div className="row row-cols-4 g-2">
            {pets && pets.map(p =>
                <div class='container' id="egg-container" key={p.petId}>
                    <div class='display-bars'>
                        <progress id="health-bar" class="nes-progress is-error" value={`${p.healthLevel}`} max="1000"/>
                        <text id="health-text" class="text">health_lvl</text>
                        <progress id="care-bar" class="nes-progress is-warning" value="20" max="100" />
                        <text id="care-text" class="text">care_lvl</text>
                        <progress id="hunger-bar" class="nes-progress is-success" value="50" max="100" />
                        <text id="hunger-text" class="text">hunger_lvl</text>
                        <progress id="thirst-bar" class="nes-progress is-primary" value="50" max="100" />
                        <text id="thirst-text" class="text">thirst_lvl</text>
                    </div>
                    <div class='loop'></div>
                    <div class='egg'>
                        <text class="display-name">{p.name}</text>
                        <div class='crack'>
                            <div class='display'>
                                <div class='grid'>
                                    <img id="active-image" src="https://2.bp.blogspot.com/-BwqYts1IQQ8/Txl9ZXaXwFI/AAAAAAAACbg/2b9IMKJ8_H0/s1600/6.gif" alt=""/>
                                </div>
                            </div>
                        </div>
                        <div class='buttons'>
                            <div id="pet-button" class='button'></div>
                            <div id="pet-button" class='button'></div>
                            <div id="pet-button" class='button'></div>
                        </div>
                    </div>
                </div>
            )}
        </div>
    );
}

export default Pets;