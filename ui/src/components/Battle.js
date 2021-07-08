import { useState, useEffect, useContext } from "react";
import {useHistory, useParams, Link} from 'react-router-dom';
import LoginContext from "../contexts/LoginContext";
import Card from 'react-bootstrap/Card';
import{findById} from '../services/battle.js';
import {findByUsername} from '../services/users';

function Battle() {

    const [battle, setBattle] = useState();
    const [user, setUser] = useState();
    const {battleId} = useParams();
    const { username } = useContext(LoginContext);
    const history = useHistory();

    useEffect(() => {
        if (username) {
            findByUsername(username)
            .then(setUser)
            .catch(() => history.push("/error"))
        };

        const interval = setInterval(() => {
            if(battleId) {
                findById(battleId)
                .then(b => setBattle(b))
                .catch(() => history.push("/error"));
            }
        }, 400);
    }, [history, battleId])

    return (
        <>
        {battle && battle.petB?
        <>
        <div className="container">
            <div id="title-container">
                <p>Battle</p>
                <p className="caption">{battle.petA.name} versus {battle.petB.name}</p>
            </div>
        </div>
        <div id="battle-background">
            <style>
                body background-image: url("battle_background_stars.gif");
            </style>
            {battle.petA?
                <Card id="battle-card-left" className="nes-container is-dark with-title is-centered">
                    <text id="battleprep-display-name" className="title">{battle.petA.name}</text>
                        <div  id="battleprep-egg" className='egg'>
                            <div id="battleprep-crack" className='crack'>
                                <div id="battleprep-display" className='display'>
                                    <div className='grid'>
                                        <img id="battleprep-image" src="https://2.bp.blogspot.com/-BwqYts1IQQ8/Txl9ZXaXwFI/AAAAAAAACbg/2b9IMKJ8_H0/s1600/6.gif" alt=""/>
                                    </div>
                                </div>
                            </div>
                            <div id="battleprep-buttons" className='buttons'>
                                <button id="battleprep-button" value={0} className='button'/>
                                <button id="battleprep-button" value={1} className='button'/>
                                <button id="battleprep-button" className='button'/>
                            </div>
                        </div>
                    <div>
                        <div id="bars">
                            <div className="nes-field is-inline">
                                <text className="text">health_lvl</text>
                            <progress className="nes-progress is-error" value={battle.petA.healthLevel} max={battle.petA.petType.health}/>
                            </div>
                        </div>
                    </div>
                </Card> :<></>}
            {battle.petB?
                <Card id="battle-card-right" className="nes-container is-dark with-title is-centered">
                    <text id="battleprep-display-name" className="title">{battle.petB.name}</text>
                        <div  id="battleprep-egg" className='egg'>
                            <div id="battleprep-crack" className='crack'>
                                <div id="battleprep-display" className='display'>
                                    <div className='grid'>
                                        <img id="battleprep-image" src="https://2.bp.blogspot.com/-BwqYts1IQQ8/Txl9ZXaXwFI/AAAAAAAACbg/2b9IMKJ8_H0/s1600/6.gif" alt=""/>
                                    </div>
                                </div>
                            </div>
                            <div id="battleprep-buttons" className='buttons'>
                                <button id="battleprep-button" className='button'/>
                                <button id="battleprep-button" className='button'/>
                                <button id="battleprep-button" className='button'/>
                            </div>
                        </div>
                    <div>
                        <div id="bars">
                            <div className="nes-field is-inline">
                                <text className="text">health_lvl</text>
                            <progress className="nes-progress is-error" value={battle.petB.healthLevel} max={battle.petB.petType.health}/>
                            </div>
                        </div>
                    </div>
                </Card> :<></>}
                <div>
                    log
                </div>
        </div>
        </>
        :
        <div className="cat-container">
            <img alt="jumping cat" id="loading" src={process.env.PUBLIC_URL + '/img/loading_cat.gif'} />
            <div className="nes-container is-rounded is-dark">
                <p>Please wait while we pair you with your opponent...</p>
            </div>
        </div>}
        </> 
        
    )
}

export default Battle;