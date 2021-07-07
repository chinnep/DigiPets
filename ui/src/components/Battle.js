import { useState, useEffect } from "react";
import {useHistory, useParams, Link} from 'react-router-dom';
import Card from 'react-bootstrap/Card';
import{findById} from '../services/battle.js';

function Battle() {

    const [battle, setBattle] = useState();
    const {battleId} = useParams();
    const history = useHistory();

    useEffect(() => {
        const interval = setInterval(() => {
            if(battleId) {
                findById(battleId)
                .then((battle) => setBattle(battle))
                .catch(() => history.pushState("/error"));
            }
        }, 77);
    }, [history, battleId])

    console.log(battle);



    return (
        <>
        {battle?
        <div className="container">
            <div id="title-container" className="nes-container">
                <p>Battle</p>
                <p className="caption">{battle.petA} versus {battle.petB}</p>
            </div>
            <div class="nes-field">
                <label for="name_field">please work</label>
                <input value={msg} type="text" id="msg" class="nes-input"/>
                <button type="button" className="nes-btn is-success" id="btnSend" onClick={send}>Send Message</button>
            </div>
            <div>
                <p id="messages">chat area here:</p>
            </div>
        </div>
        :
        <div className="cat-container">
            <img id="loading" src={process.env.PUBLIC_URL + '/img/loading_cat.gif'} />
            <div className="nes-container is-rounded is-dark">
                <p>Loading...</p>
            </div>
        </div>}
        </> 
        
    )
}

export default Battle;