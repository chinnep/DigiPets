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
        }, 400000);
    }, [history, battleId])

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
        <div>
            {battle.petA?
                <Card id="battle-card-left" className="nes-container with-title is-centered">
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
                                <div id="battleprep-button" className='button'></div>
                                <div id="battleprep-button" className='button'></div>
                                <div id="battleprep-button" className='button'></div>
                            </div>
                        </div>
                    <div>
                        <div id="bars">
                            <div className="nes-field is-inline">
                                <text className="text">health_lvl</text>
                            <progress className="nes-progress is-error" value={battle.petA.healthLevel} max={battle.petA.petType.health}/>
                            </div>
                            <div className="nes-field is-inline">
                                <text className="text">care_lvl </text>
                                <progress className="nes-progress is-warning" value={battle.petA.careLevel} max={battle.petA.petType.care}/>
                            </div>
                            <div className="nes-field is-inline">
                                <text className="text">hunger_lvl</text>
                                <progress className="nes-progress is-success" value={battle.petA.hungerLevel} max={battle.petA.petType.appetite}/>
                            </div>
                            <div className="nes-field is-inline">
                                <text className="text">thirst_lvl</text>
                                <progress className="nes-progress is-primary" value={battle.petA.thirstLevel} max={battle.petA.petType.thirst}/>
                            </div>
                        </div>
                        <button type="button" className="nes-btn is-success">Use the item?</button>
                    </div>
                </Card> :<></>}
            {battle.petB?
                <Card id="battle-card-right" className="nes-container with-title is-centered">
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
                                <div id="battleprep-button" className='button'></div>
                                <div id="battleprep-button" className='button'></div>
                                <div id="battleprep-button" className='button'></div>
                            </div>
                        </div>
                    <div>
                        <div id="bars">
                            <div className="nes-field is-inline">
                                <text className="text">health_lvl</text>
                            <progress className="nes-progress is-error" value={battle.petB.healthLevel} max={battle.petB.petType.health}/>
                            </div>
                            <div className="nes-field is-inline">
                                <text className="text">care_lvl </text>
                                <progress className="nes-progress is-warning" value={battle.petB.careLevel} max={battle.petB.petType.care}/>
                            </div>
                            <div className="nes-field is-inline">
                                <text className="text">hunger_lvl</text>
                                <progress className="nes-progress is-success" value={battle.petB.hungerLevel} max={battle.petB.petType.appetite}/>
                            </div>
                            <div className="nes-field is-inline">
                                <text className="text">thirst_lvl</text>
                                <progress className="nes-progress is-primary" value={battle.petB.thirstLevel} max={battle.petB.petType.thirst}/>
                            </div>
                        </div>
                        <button type="button" className="nes-btn is-success">Use the item?</button>
                    </div>
                </Card> :<></>}
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