import { useState, useEffect } from "react";
import {useHistory, useParams, Link} from 'react-router-dom';
import Card from 'react-bootstrap/Card';
import{findById} from '../services/pets.js';

function Battle() {

    const [petA, setPetA] = useState();
    const [petB, setPetB] = useState();
    const {petAId, petBId} = useParams();
    const history = useHistory();

    useEffect(() => {
        if (petAId) {
            findById(petAId)
            .then(p => {
                setPetA(p)
            })
            .catch(() => history.push("/error"))
        }
        if (petBId) {
            findById(petBId)
            .then(p => {
                setPetB(p)
            })
            .catch(() => history.push("/error"))
        }
      }, [petAId, petBId]);

    return (
        <>
        <div className="container">
                <div id="title-container" className="nes-container">
                    <p>Battle</p>
                    <p className="caption">petA versus petB</p>
                </div>
            </div>
        <div id="battle-container" className="container">
            <div className="col-left">
            <Card className="nes-container with-title is-centered">
                <text id="battleprep-display-name" className="title">petA.name</text>
                    <div  id="battleprep-egg" className='egg'>
                        <div id="battleprep-crack" className='crack'>
                            <div id="battleprep-display" className='display'>
                                <div className='grid'>
                                    <img id="battleprep-image" src="https://2.bp.blogspot.com/-BwqYts1IQQ8/Txl9ZXaXwFI/AAAAAAAACbg/2b9IMKJ8_H0/s1600/6.gif" alt=""/>
                                </div>
                            </div>
                        </div>
                        <div id="battleprep-buttons" className='buttons'>
                            <div id="pet-button" className='button'></div>
                            <div id="pet-button" className='button'></div>
                            <div id="pet-button" className='button'></div>
                        </div>
                    </div>
                <div>
                    <div id="bars">
                        <div className="nes-field is-inline">
                            <text className="text">health_lvl</text>
                            <progress className="nes-progress is-error" value={petA.healthLevel} max={petA.petType.health}/>
                        </div>
                        <div className="nes-field is-inline">
                            <text className="text">care_lvl </text>
                            <progress className="nes-progress is-warning" value={petA.careLevel} max={petA.petType.care}/>
                        </div>
                        <div className="nes-field is-inline">
                            <text className="text">hunger_lvl</text>
                            <progress className="nes-progress is-success" value={petA.hungerLevel} max={petA.petType.appetite}/>
                        </div>
                        <div className="nes-field is-inline">
                            <text className="text">thirst_lvl</text>
                            <progress className="nes-progress is-primary" value={petA.thirstLevel} max={petA.petType.thirst}/>
                        </div>
                    </div>
                    <Link to="/battle" type="button" className="nes-btn is-success">Battle!</Link>
                </div>
            </Card>
            </div>
            <div className="col-right">
            <Card className="nes-container with-title is-centered">
                <text id="battleprep-display-name" className="title">{petB.name}</text>
                    <div  id="battleprep-egg" className='egg'>
                        <div id="battleprep-crack" className='crack'>
                            <div id="battleprep-display" className='display'>
                                <div className='grid'>
                                    <img id="battleprep-image" src="https://2.bp.blogspot.com/-BwqYts1IQQ8/Txl9ZXaXwFI/AAAAAAAACbg/2b9IMKJ8_H0/s1600/6.gif" alt=""/>
                                </div>
                            </div>
                        </div>
                        <div id="battleprep-buttons" className='buttons'>
                            <div id="pet-button" className='button'></div>
                            <div id="pet-button" className='button'></div>
                            <div id="pet-button" className='button'></div>
                        </div>
                    </div>
                <div>
                    <div id="bars">
                        <div className="nes-field is-inline">
                            <text className="text">health_lvl</text>
                            <progress className="nes-progress is-error" value={petB.healthLevel} max={petB.petType.health}/>
                        </div>
                        <div className="nes-field is-inline">
                            <text className="text">care_lvl </text>
                            <progress className="nes-progress is-warning" value={petB.careLevel} max={petB.petType.care}/>
                        </div>
                        <div className="nes-field is-inline">
                            <text className="text">hunger_lvl</text>
                            <progress className="nes-progress is-success" value={petB.hungerLevel} max={petB.petType.appetite}/>
                        </div>
                        <div className="nes-field is-inline">
                            <text className="text">thirst_lvl</text>
                            <progress className="nes-progress is-primary" value={petB.thirstLevel} max={petB.petType.thirst}/>
                        </div>
                    </div>
                    <Link to="/battle" type="button" className="nes-btn is-success">Battle!</Link>
                </div>
            </Card>
            </div>
        </div>
        </> 
        
    )
}

export default Battle;