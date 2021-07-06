import { useState, useEffect } from "react";
import {useHistory, useParams, Link} from 'react-router-dom';
import Card from 'react-bootstrap/Card';
import{findById} from '../services/pets.js';
import { findAll as findItems } from "../services/items";

function BattlePrep() {

    const [pet, setPet] = useState();
    const [items, setItems] = useState();
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

      useEffect(() => {
        findItems()
            .then(setItems)
            .catch(() => history.push("/error"))
    }, [history]);

    return (
        <>
        {pet?
            <Card className="nes-container with-title is-centered">
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
                    //select an item here to take with you into the battle
                    <div className="nes-select">
                        <select required id="default_select">
                            <option value="" disabled selected hidden>Select an item</option>
                            {items? items.map((i, index) => 
                                <option value={index} key={i.name}>{i.name}</option>):<></>}
                        </select>
                    </div>
                    <button type="button" className="nes-btn is-success">Enter the Queue</button>
                </div>
            </Card> : <>error</>}
        </>
    );
}

export default BattlePrep;