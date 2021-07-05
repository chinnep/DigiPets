import { useState, useEffect, useContext } from "react";
import { useHistory } from "react-router";
import LoginContext from "../contexts/LoginContext";
import { findAll as findItems } from "../services/items";
import { findByUsername } from "../services/users";

function Shop() {

    const [items, setItems] = useState();
    const history = useHistory();
    const { username } = useContext(LoginContext);
    const [user] = useState(findByUsername(username));

    useEffect(() => {
        findItems()
            .then(setItems)
            .catch(() => history.push("/error"))
    }, [history]);

    return (
        <>
            <div className="shop nes-container">
                <img className="ghost-shop"
                    src={process.env.PUBLIC_URL + '/img/Shop.gif'} alt="Shop" />
            </div>

            <div class="nes-container is-centered">
                <div className="nes-container is-rounded" key='0'>
                    <img class="shop-egg" src={process.env.PUBLIC_URL + '/img/Egg.gif'} alt="A new egg!" />
                    <div className="card-body">
                        <h2 className="card-title nes-text is-primary">DigiPet Egg</h2>
                        <p className="card-text">This excitable little egg is hopping all about! What pet awaits inside??!</p>
                    </div>
                    <button type="nes-button is-warning">
                        <i class="nes-icon coin" />
                        <p class="nes-text is-warning">100</p>
                    </button>
                </div>
                {items && items.map(i =>
                    <div className="nes-container is-rounded" key={i.id}>
                        {i.name && <img class="shop-item" src={process.env.PUBLIC_URL + '/img/items' + i.imgUrl} alt="" />}
                        <div className="card-body">
                            <h2 className="card-title nes-text is-primary">{i.name}</h2>
                            <p className="card-text">{i.description}</p>
                        </div>
                        <button type="nes-button is-warning">
                            <i class="nes-icon coin" />
                            <p class="nes-text is-warning">{i.price}</p>
                        </button>
                        <p>
                            
                        </p>
                        <br></br>
                    </div>
                )}
            </div>
        </>
    );
}

export default Shop;