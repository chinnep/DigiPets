import { useState, useEffect, useContext } from "react";
import { useHistory, useParams } from 'react-router-dom';
import { findById, update } from '../services/pets.js';

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
            <h3 id="title-container">{pet.name} wins!</h3>

            : <></>}
        </>
    );
}

export default Victory;