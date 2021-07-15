import { useState, useEffect, useContext } from "react";
import { useHistory } from "react-router";
import LoginContext from "../contexts/LoginContext";
import { findByUsername } from "../services/users";
import { purchaseItem, purchaseEgg } from "../services/shop";


function Shop() {

    const history = useHistory();
    const { username } = useContext(LoginContext);
    const [user, setUser] = useState();

    useEffect(() => {
        if (username) {
            findByUsername(username)
                .then(setUser)
                .catch(() => history.push("/error"))
        }
    }, [history])

    const buyItem = i => {
        purchaseItem(username, i.itemId)
            .then(setUser)
            .catch(() => history.push("/error"))
    }

    const buyEgg = evt => {
        purchaseEgg(username)
            .then(setUser)
            .catch(() => history.push("/error"))
    }

    return (
        <>
            <section class="shop nes-container">
                <section class="message-list">
                    <section class="message -left">
                    <img className="ghost-shop" 
                    src={process.env.PUBLIC_URL + '/img/Shop.gif'} alt="Shop" />
                    <div class="nes-balloon from-left">
                        <p>Welcome to my shop!</p>
                        <p>You have {" "}
                        <i class="nes-icon coin is-small"></i>
                         {user && user.gold}</p>
                    </div>
                    </section>
                </section>
            </section>
            <br></br>
            <div class="wrapper is-centered">
                <div className="nes-container is-rounded is-centered">
                    <img class="shop-egg" src={process.env.PUBLIC_URL + '/img/Egg.gif'} alt="A new egg!" />
                    <div className="card-body">
                        <h2 className="card-title nes-text is-warning">DigiPet Egg</h2>
                        <p className="card-text">This excitable little egg is hopping all about! What pet awaits inside??!</p>
                        <button type="button" class={`nes-btn ${(user && user.gold >= 100 ? "is-warning" : "is-disabled")}`} onClick={() => buyEgg()}>
                            <i class="nes-icon coin" />
                            <p>100</p>
                        </button>
                    </div>
                </div>
                {user && user.items.map((i, index) =>
                    <div id="item-container" className="nes-container is-rounded is-centered" key={i.itemId}>
                        {i.name && <img class="shop-item" src={process.env.PUBLIC_URL + '/img/items' + i.imgUrl} alt="" />}
                        <div className="card-body">
                            <h2 className="card-title nes-text is-warning">{i.name}</h2>
                            <p className="card-text">{i.description}</p>
                            <button type="button" class={`nes-btn ${(user.gold >= i.price ? "is-warning" : "is-disabled")}`} onClick={() => buyItem(i)}>
                                <i class="nes-icon coin" />
                                <p>{i.price}</p>
                            </button>
                            <p class="nes-text is-warning">Owned: {i.quantity}</p>
                        </div>
                    </div>)}
            </div>
        </>
    );
}

export default Shop;