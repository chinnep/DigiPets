  
import { useState, useEffect, useContext } from "react";
import { useHistory, useParams } from 'react-router-dom';
import LoginContext from "../contexts/LoginContext";
import Card from 'react-bootstrap/Card';
import { findById, round, deleteById } from '../services/battle.js';
import { update } from '../services/pets.js';

function Battle() {

    const [battle, setBattle] = useState();
    const { battleId } = useParams();
    const { username } = useContext(LoginContext);
    const history = useHistory();
    var time = 250;

    useEffect(() => {

        const interval = setInterval(() => {
            if (battleId) {
                findById(battleId)
                    .then(b => setBattle(b))
                    .catch(() => history.push("/error"));
            }

        }, time);
        return () => clearInterval(interval);
    }, [battleId]);



    const makeMove = (move, isPlayerA) => {

        round({ battleId, move, isPlayerA })
            .then(result => {
                if (result) {
                    //update petA and petB
                    update(battle.petA)
                    .catch((err) => console.log(err));

                    update(battle.petB)
                    .catch((err) => console.log(err));

                    deleteById(battle.battleId)
                    .catch((err) => console.log(err));
                    
                    time = 3000;
                    if (result.petB.healthLevel <= 0) {
                        if (result.petB.username === username) {
                            history.push(`/loss/${result.petB.petId}`);
                        } else {
                            history.push(`/victory/${result.petA.petId}`);
                        }
                    } else if (result.petA.healthLevel <= 0) {
                        if (result.petB.username === username) {
                            history.push(`/victory/${result.petB.petId}`);
                        } else {
                            history.push(`/loss/${result.petA.petId}`);
                        }
                    }
                else {} 
                }
            })
            //.catch(() => history.push("/error"));
    }
    const chatWindow = document.getElementById('msg-container'); 
    if (chatWindow) {
        var xH = chatWindow.scrollHeight; 
        chatWindow.scrollTo(0, xH);
    };

    const backgroundContainer = document.getElementById('battle-background'); 
    if (backgroundContainer && battle) {
        const bgLink = '../img/backgrounds/'+battle.background+'.gif';
        backgroundContainer.style.backgroundImage = `url(${bgLink})`;
    };

    return (
        <>
            {battle && battle.petB ?
                <>
                    <div className="container">
                        <div id="title-container">
                            <p>Battle</p>
                            <p className="caption">
                                <div className="row">
                                    <div className="column">
                                   {battle.petA.name}
                                    </div>
                                    <div className="column">
                                    <button id="music-button"><i class="nes-icon close is-small"></i></button>
                                    </div>
                                    <div className="column">
                                    {battle.petB.name}
                                    </div>
                                </div>
                                
                            </p>
                        </div>
                    </div>
                    <div id="battle-background">
                        {battle.petA ?
                            <Card id="battle-card-left" className="nes-container is-dark is-centered">
                                <div id="battleprep-egg" className='egg'>
                                    <div id="battleprep-crack" className='crack'>
                                        <div id="battleprep-display" className='display'>
                                            <div className='grid'>
                                                <img id="battleprep-image" src={process.env.PUBLIC_URL + "/img/" + `${battle.petA.petType.name}` + '/default.gif'} alt="" />
                                            </div>
                                        </div>
                                    </div>
                                    <div id="battleprep-buttons" className='buttons'>
                                        <button id="battleprep-button" value={0} onClick={() => makeMove(battle.petA.petType.moves[0], true)} className={username !== battle.petA.username ? 'is-disabled' : 'is-warning'} />
                                        <button id="battleprep-button" value={1} onClick={() => makeMove(battle.petA.petType.moves[1], true)} className={username !== battle.petA.username ? 'is-disabled' : 'is-warning'} />
                                        <button id="battleprep-button" className='button' />
                                    </div>
                                </div>
                                <div>
                                    <div id="bars">
                                        <div className="nes-field is-inline">
                                            <text className="text">health_lvl</text>
                                            <progress className="nes-progress is-error" value={battle.petA.healthLevel} max={100} />
                                        </div>
                                    </div>
                                </div>
                            </Card> : <></>}
                        {battle.petB ?
                            <Card id="battle-card-right" className="nes-container is-dark is-centered">
                                <div id="battleprep-egg" className='egg'>
                                    <div id="battleprep-crack" className='crack'>
                                        <div id="battleprep-display" className='display'>
                                            <div className='grid'>
                                                <img id="battleprep-image" src={process.env.PUBLIC_URL + "/img/" + `${battle.petB.petType.name}` + '/default.gif'} alt="" />
                                            </div>
                                        </div>
                                    </div>
                                    <div id="battleprep-buttons" className='buttons'>
                                        <button id="battleprep-button" value={0} onClick={() => makeMove(battle.petB.petType.moves[0], false)} className={username !== battle.petB.username ? 'is-disabled' : 'is-warning'} />
                                        <button id="battleprep-button" value={1} onClick={() => makeMove(battle.petB.petType.moves[1], false)} className={username !== battle.petB.username ? 'is-disabled' : 'is-warning'} />
                                        <button id="battleprep-button" className='button' />
                                    </div>
                                </div>
                                <div>
                                    <div id="bars">
                                        <div className="nes-field is-inline">
                                            <text className="text">health_lvl</text>
                                            <progress className="nes-progress is-error" value={battle.petB.healthLevel} max={100} />
                                        </div>
                                    </div>
                                </div>
                            </Card> : <></>}
                        <div>.</div>
                    </div>
                    <br></br>
                    <div className="nes-container is-rounded" id="msg-container">
                            {battle.battleLog.map(msg => 
                                <p>{msg}</p>)}
                    </div>
                    <audio src={process.env.PUBLIC_URL+'/battle_tunes/'+battle.music+'.mp3'} controls loop></audio>
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