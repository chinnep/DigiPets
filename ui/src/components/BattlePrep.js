import { useState, useEffect } from "react";
import {useHistory, useParams, Link} from 'react-router-dom';
import Card from 'react-bootstrap/Card';
import{findById} from '../services/pets.js';

function BattlePrep() {

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

      console.log(pet);

    return (
        <div className="row">
            <div className="col">
                <Card className="nes-conatiner is-rounded">hello</Card>
            </div>
            <div className="col">
            <Card className="nes-container is-rounded">
                    <div  id="battleprep-egg" className='egg'>
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
                <Card.Body>
                    <Card.Text id="bars">
                        <div className="nes-field is-inline">
                            <text className="text">health_lvl</text>
                            <progress className="nes-progress is-error" value={pet.healthLevel} max={pet.petType.health}/>
                        </div>
                        <div className="nes-field is-inline">
                            <text className="text">care_lvl </text>
                            <progress className="nes-progress is-warning" value={pet.petType.care} max="100" />
                        </div>
                        <div className="nes-field is-inline">
                            <text className="text">hunger_lvl</text>
                            <progress className="nes-progress is-success" value={pet.petType.appetite} max="100" />
                        </div>
                        <div className="nes-field is-inline">
                            <text className="text">thirst_lvl</text>
                            <progress className="nes-progress is-primary" value={pet.petType.thirst} max="100" />
                        </div>
                    </Card.Text>
                    //select an item here to take with you into the battle
                    <div class="nes-select">
                        <select required id="default_select">
                            <option value="" disabled selected hidden>Select...</option>
                            <option value="0">To be</option>
                            <option value="1">Not to be</option>
                        </select>
                    </div>
                    <Link to="/battle" type="button" class="nes-btn is-success">Battle!</Link>
                </Card.Body>
            </Card>
            </div>
        </div>        
    );
}

export default BattlePrep;