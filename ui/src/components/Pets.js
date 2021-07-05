import '../pet.scss';
import { useState, useEffect } from "react";
import { useHistory } from "react-router";
import { findAll } from '../services/pets';

function Pets() {

    const [pets, setPets] = useState();
    const history = useHistory();

    const thirstMonitor = 1000 * 60 * 60 * 3;
    const currentDate = new Date();

    useEffect(() => {
        findAll()
            .then(setPets)
            .catch(() => history.push("/error"))
    }, [history]);

    return (
        <div className="row row-cols-4 g-2">
            {pets && pets.map(p =>
                <div className='container' id="egg-container" key={p.petId}>
                    <div className='display-bars'>
                        <progress id="health-bar" className="nes-progress is-error" value={p.healthLevel} max="1000"/>
                        <text id="health-text" className="text">health_lvl</text>
                        <progress id="care-bar" className="nes-progress is-warning" value={p.careLevel} max="100" />
                        <text id="care-text" className="text">care_lvl</text>
                        <progress id="hunger-bar" className="nes-progress is-success" value={p.hungerLevel} max="100" />
                        <text id="hunger-text" className="text">hunger_lvl</text>
                        <progress id="thirst-bar" className="nes-progress is-primary" value={p.thirstLevel} max="100" />
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
                                    <img id="active-image" src="https://2.bp.blogspot.com/-BwqYts1IQQ8/Txl9ZXaXwFI/AAAAAAAACbg/2b9IMKJ8_H0/s1600/6.gif" alt=""/>
                                </div>
                            </div>
                        </div>
                        <div className='buttons'>
                            <div id="pet-button" className='button'></div>
                            <div id="pet-button" className='button'></div>
                            <div id="pet-button" className='button'></div>
                        </div>
                    </div>
                </div>
            )}
        </div>
    );
}

export default Pets;