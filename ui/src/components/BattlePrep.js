import { useState, useEffect } from "react";
import {useHistory, useParams} from 'react-router-dom';
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

    return (
        <>
            <Card className="nes-container is-rounded">
                <style>
                    width:"80rem";
                    height:"80rem";
                    position:"relative";
                    margin-top:"50%";
                    left:"50%";
                    transform: "translate(50%, 200px)";
                </style>
                    <div className='egg'>
                        <text className="display-name">test name</text>
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
                    <Card.Title>Pet name</Card.Title>
                    <Card.Text>
                        <progress className="nes-progress is-error" value={800} max="1000"/>
                        <text className="text">health_lvl</text>
                        <progress className="nes-progress is-warning" value={65} max="100" />
                        <text className="text">care_lvl</text>
                        <progress className="nes-progress is-success" value={35} max="100" />
                        <text className="text">hunger_lvl</text>
                        <progress className="nes-progress is-primary" value={10} max="100" />
                        <text className="text">thirst_lvl</text>
                    </Card.Text>
                    <button className="nes-btn is-primary">Go somewhere</button>
                </Card.Body>
            </Card>
        </>
    );
}

export default BattlePrep;