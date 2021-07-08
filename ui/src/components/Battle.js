import { useState, useEffect, useContext } from "react";
import {useHistory, useParams, Link} from 'react-router-dom';
import LoginContext from "../contexts/LoginContext";
import Card from 'react-bootstrap/Card';
import{findById, round} from '../services/battle.js';
import {findByUsername} from '../services/users';

function Battle() {

    const [battle, setBattle] = useState();
    const [user, setUser] = useState();
    const[moveA, setMoveA] = useState();
    const[moveB, setMoveB] = useState();
    const[readyA, setReadyA] = useState();
    const[readyB, setReadyB] = useState();
    const {battleId} = useParams();
    const { username } = useContext(LoginContext);
    const history = useHistory();

    useEffect(() => {

        const interval = setInterval(() => {
            if(battleId) {
                findById(battleId)
                .then(b => setBattle(b))
                .catch(() => history.push("/error"));
            }
        }, 4000000);
    }, [history, battleId]);

    console.log("readyA: " + readyA + ", moveA: "+ moveA.name);
    console.log("readyB: " + readyB + ", moveB: "+ moveB.name);

    const checkAndSendRoundRequest = () => {
        if(readyA && readyB && moveA && moveB) {
            round(battleId, moveA, moveB)
            .then(result => {
                if(result) {
                    //isover
                } else {
                    setReadyA(false);
                    setReadyB(false);
                }
            })
            .catch(() => history.push("/error"));
        }
    }

    
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
                                        <img id="battleprep-image" src={process.env.PUBLIC_URL + "/img/" + `${battle.petA.petType.name}` + '/default.gif'} alt=""/>
                                    </div>
                                </div>
                            </div>
                            <div id="battleprep-buttons" className='buttons'>
                                <input type="radio" name="petA-move" id="battleprep-button" value={0} onClick={() => setMoveA(battle.petA.petType.moves[0])} className={username !== battle.petA.username ? 'is-disabled' : 'is-warning'}/>
                                <input type="radio" name="petA-move" id="battleprep-button" value={1} onClick={() => setMoveA(battle.petA.petType.moves[1])} className={username !== battle.petA.username ? 'is-disabled' : 'is-warning'}/>
                                <input type="radio" name="petA-move" id="battleprep-button" className='button'/>
                            </div>
                        </div>
                    <div>
                        <div id="bars">
                            <div className="nes-field is-inline">
                                <text className="text">health_lvl</text>
                            <progress className="nes-progress is-error" value={battle.petA.healthLevel} max={battle.petA.petType.health}/>
                            </div>
                        </div>
                        {/* {username === battle.petA.username ?
                        <button type="button" className="nes-btn is-success">ready</button>
                        :<></>} */}
                        <button type="button" onClick={() => setReadyA(true)} className={!readyA?"nes-btn is-success": "nes-btn is-disabled"}>ready</button>
                    </div>
                </Card> :<></>}
            {battle.petB?
                <Card id="battle-card-right" className="nes-container is-dark with-title is-centered">
                    <text id="battleprep-display-name" className="title">{battle.petB.name}</text>
                        <div  id="battleprep-egg" className='egg'>
                            <div id="battleprep-crack" className='crack'>
                                <div id="battleprep-display" className='display'>
                                    <div className='grid'>
                                        <img id="battleprep-image" src={process.env.PUBLIC_URL + "/img/" + `${battle.petB.petType.name}` + '/default.gif'} alt=""/>
                                    </div>
                                </div>
                            </div>
                            <div id="battleprep-buttons" className='buttons'>
                                <input type="radio" name="petB-move" id="battleprep-button" value={0} onClick={() => setMoveB(battle.petB.petType.moves[0])} className={username !== battle.petB.username ? 'is-disabled' : 'is-warning'}/>
                                <input type="radio" name="petB-move" id="battleprep-button" value={1} onClick={() => setMoveB(battle.petB.petType.moves[1])} className={username !== battle.petB.username ? 'is-disabled' : 'is-warning'}/>
                                <input type="radio" name="petB-move" id="battleprep-button" className='button'/>
                            </div>
                        </div>
                    <div>
                        <div id="bars">
                            <div className="nes-field is-inline">
                                <text className="text">health_lvl</text>
                            <progress className="nes-progress is-error" value={battle.petB.healthLevel} max={battle.petB.petType.health}/>
                            </div>
                        </div>
                        {/* {username === battle.petB.username ?
                        <button type="button" onClick={readyB=true} className={readyB?"nes-btn is-success": "nes-btn is-disabled"}>ready</button>
                        :<></>} */}
                        <button type="button" onClick={() => (setReadyB(true))} className={!readyB?"nes-btn is-success": "nes-btn is-disabled"}>ready</button>
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