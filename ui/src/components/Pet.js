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
                })
                .catch(() => history.push("/error"))
        }
    }, [id]);

    const onSubmit = evt => {
        evt.preventDefault();
        update(pet)
            .then(() => history.push("/"))
            .catch(() => history.push("/failure"));
    };

    return (
        <form onSubmit={onSubmit}>
            {}
            <div className='container' id="egg-container">
                <div className='display-bars'>
                    <progress id="health-bar" className="nes-progress is-error" value={pet && pet.healthLevel} max="1000" />
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
                                <img id="active-image" src="https://2.bp.blogspot.com/-BwqYts1IQQ8/Txl9ZXaXwFI/AAAAAAAACbg/2b9IMKJ8_H0/s1600/6.gif" alt="" />
                            </div>
                        </div>
                    </div>
                </div>
                <div className='buttons-pet'>
                    <button id="pet-buttons" className='button' onClick={pet && pet.hungerLevel + 10} />
                    <button id="pet-buttons" className='button' onClick={pet && pet.careLevel + 10} />
                    <button id="pet-buttons" className='button' onclick={pet && pet.thirstLevel + 10} />
                </div>
            </div>
        </form>
    );
}

export default Pet;