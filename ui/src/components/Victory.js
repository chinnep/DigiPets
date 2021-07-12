import { useState, useEffect } from "react";
import { useHistory, useParams, Link } from 'react-router-dom';
import { findById } from '../services/pets.js';

function Victory() {

    const { petId } = useParams();
    const [pet, setPet] = useState();
    const history = useHistory();


    useEffect(() => {
        if (petId) {
            findById(petId)
                .then((result) => {
                    setPet(result);
                })
                .catch(() => history.push("/error"))
        }
    }, [petId, history]);

    return (
        <>{pet ?
            <div className="cat-container">
                <img alt="jumping cat" id="loading" src={process.env.PUBLIC_URL + '/img/victory.gif'} />
                <div className="nes-container is-rounded is-dark">
                    <p>{pet.name} wins!</p> 
                </div>
                <div>
                    <br></br>
                    <Link to="/" className="nes-btn is-success">Home</Link>
                </div>
            </div>
            : <></>}
        </>
    );
}

export default Victory;