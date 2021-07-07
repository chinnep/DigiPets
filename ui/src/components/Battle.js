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
                .then(b => setBattle(b))
                .catch(() => history.push("/error"));
            }
        }, 4000);
    }, [history, battleId])

    console.log(battle);



    return (
        <>
        {battle && battle.petB?
        <>
        <div className="container">
            <div id="title-container" className="nes-container">
                <p>Battle</p>
                <p className="caption">petA versus petB</p>
            </div>
        </div>
        <div className="row">
            <div className="column">
                column 1
            </div>
            <div className="column">
                column 2
            </div>
        </div>
        </>
        :
        <div className="cat-container">
            <img alt="jumping cat" id="loading" src={process.env.PUBLIC_URL + '/img/loading_cat.gif'} />
            <div className="nes-container is-rounded is-dark">
                <p>Loading...</p>
            </div>
        </div>}
        </> 
        
    )
}

export default Battle;