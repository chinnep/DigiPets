import { useEffect } from "react";
import { useParams, useHistory } from "react-router-dom";
import {findAll} from "../services/battle";

function BattleWaitingRoom() {

    const {petId} = useParams();
    const history = useHistory();

    useEffect(() => {
        const interval = setInterval(() => {
            if(petId) {
                findAll()
                .then((battles) => {
                    for(let i = 0; i < battles.length; i++) {
                        //shouldn't need to check petB bc priority is given to the one in the waiting room
                        if(battles[i].petA.petId === petId) {
                            history.push(`/battle/${battles[i].battleId}`)
                        }
                    }
                })
                .catch(() => history.pushState("/error"));
            }
        }, 77);
    }, [history, petId])

    return(
        <div className="cat-container">
            <img alt="jumping cat" id="loading" src={process.env.PUBLIC_URL + '/img/loading_cat.gif'} />
            <div className="nes-container is-rounded is-dark">
                <p>Please wait while we pair you with your opponent...</p>
            </div>
        </div>
    )
}

export default BattleWaitingRoom;