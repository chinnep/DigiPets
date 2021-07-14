import { useEffect, useState } from 'react';
import { useHistory } from 'react-router-dom';
import {findAll} from '../services/pets';
function Rankings() {

    const [pets, setPets] = useState();
    const history = useHistory();


    useEffect(() => {
        findAll()
            .then(setPets)
            .catch(() => history.push("/error"))
    }, [history])


    const sortByTrophies = (pets) => {
        if (pets == null) return null;
        let newArray = [...pets];
        // no need to track max since we're bubbling
        for (let i = 0; i < newArray.length; i++) {
            // we can start at 1 because we assume the first element is already the max
            for (let j = 1; j < newArray.length - i; j++) {
                if (newArray[j].trophies < newArray[j - 1].trophies) {
                    // if the item on the left is larger than the current item,
                    // swap them to "bubble".
                    let swapTemp = newArray[j];
                    newArray[j] = newArray[j-1];
                    newArray[j - 1] = swapTemp;
                }
            }
        }
    pets = newArray.reverse();
    return newArray;
    }
    return (
        <>
        <div className="container">
            <div id="title-container" className="nes-container">
                <p>RANKINGS</p>
                <p className="caption">champions board</p>
            </div>
        </div>
        <div id="rankings-about-section">
            <p className="caption">Battle to gain trophies and reach the top of the rankings!</p>
        </div>
        <div id="rankings-section">
        <div className="row"> 
                <div className="column">Trophies</div>
                <div className="column">DigiPet</div>
                <div className="column">User</div>
            </div>
        {pets && sortByTrophies(pets).map(p =>
            <div className="row"> 
                <div className="column">
                    <i className="nes-icon trophy is-small"></i>
                    {p.trophies}
                    </div>
                <div className="column">{p.name}</div>
                <div className="column">{p.username}</div>
            </div>)}
        </div>
        </>
    )
}

export default Rankings;