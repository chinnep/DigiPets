import { useState, useEffect } from "react";
import {useHistory, useParams} from 'react-router-dom';
import{findById} from '../services/pets.js';

function Pet() {

    const [pet, setPet] = useState();
    const {id} = useParams();
    const history = useHistory();

    useEffect(() => {
        if (id) {
            findById(id)
            .then(p => {
                setPet(p)
            })
            .catch(() => history.push("/error"))
        }
      }, [id]);

    return (
        <div className="row">
                <div className='container' id="egg-container" key={pet.petId}>
                    <style>
                        width:"80rem";
                        height:"80rem";
                        position:"absolute";
                        margin-top:"50%";
                        left:"50%";
                        transform:"translate(-50%, -50%)";
                    </style>
                    <div className='display-bars'>
                        <progress id="health-bar" className="nes-progress is-error" value={pet.healthLevel} max="1000"/>
                        <text id="health-text" className="text">health_lvl</text>
                        <progress id="care-bar" className="nes-progress is-warning" value={pet.careLevel} max="100" />
                        <text id="care-text" className="text">care_lvl</text>
                        <progress id="hunger-bar" className="nes-progress is-success" value={pet.hungerLevel} max="100" />
                        <text id="hunger-text" className="text">hunger_lvl</text>
                        <progress id="thirst-bar" className="nes-progress is-primary" value={pet.thirstLevel} max="100" />
                        <text id="thirst-text" className="text">thirst_lvl</text>
                    </div>
                    <div className='loop'></div>
                    <div className='egg'>
                        <text className="display-name">{pet.name}</text>
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
        </div>
    );
}

export default Pet;