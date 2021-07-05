import { useState, useEffect, useContext } from "react";
import { useHistory } from "react-router";
import LoginContext from "../contexts/LoginContext";
import { findAll as findItems } from "../services/items";

function Shop() {

    const [items, setItems] = useState();
    const history = useHistory();
    const { username } = useContext(LoginContext);

    useEffect(() => {
        findItems()
            .then(setItems)
            .catch(() => history.push("/error"))
    }, [history]);

    return (
        <>
            <div className="shop nes-container is-rounded">
                <img className="ghost-shop"
                    src={process.env.PUBLIC_URL + '/img/Shop.gif'} alt="Shop" />
            </div>

            <section class="nes-container is-rounded is-centered">
                <div className="nes-container" key='0'>
                    <img src={process.env.PUBLIC_URL + '/img/Egg.gif'} alt="A new egg!" />
                    <div className="card-body">
                        <h2 className="card-title nes-text is-primary">DigiPet Egg</h2>
                        <p className="card-text">This excitable little egg is hopping all about! What pet waits inside??!</p>
                    </div>
                    <button type="button" class="nes-icon coin nes-text is-warning">100</button>
                </div>
                {items && items.map(i =>
                    <div className="nes-container is-rounded" key={i.id}>
                        {i.name && <img src={process.env.PUBLIC_URL + '/img/items' + i.imgUrl} />}
                        <div className="card-body">
                            <h2 className="card-title nes-text is-primary">{i.name}</h2>
                            <p className="card-text">{i.description}</p>
                            <button type="button" class="nes-icon coin nes-text is-warning">{i.price}</button>
                        </div>
                    </div>
                )}
            </section>
        </>
    );
}

export default Shop;