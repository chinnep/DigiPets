import { useState, useEffect, useContext } from "react";
import { useHistory } from "react-router";
import LoginContext from "../contexts/LoginContext";
import { findByUsername } from "../services/users";


function Shop() {

    const history = useHistory();
    const { username } = useContext(LoginContext);
    const [user, setUser] = useState(findByUsername(username));

    useEffect(() => {
        if (username) {
            findByUsername(username)
                .then(setUser)
                .catch(() => history.push("/error"))
        }
    }, [history])

    console.log(user);
    console.log(user.items);
    console.log(Object.keys(user));


    return (
        <>
            <div className="shop nes-container">
                <img className="ghost-shop"
                    src={process.env.PUBLIC_URL + '/img/Shop.gif'} alt="Shop" />
            </div>

            <section className="user-coin icon-list">
                <i class="nes-icon coin is-large"></i>
                <i class="nes-icon coin is-large"></i>
                <i class="nes-icon coin is-large"></i>
                <i class="nes-icon coin is-large"></i>
                <h1 class="nes-text is-warning">{username}</h1>

                <h2 className="nes-text is-warning">
                    Current Gold: {user.gold}
                </h2>
                <i class="nes-icon coin is-large"></i>
                <i class="nes-icon coin is-large"></i>
                <i class="nes-icon coin is-large"></i>
                <i class="nes-icon coin is-large"></i>
            </section>

            <div class="nes-container is-centered">
                <div className="nes-container is-rounded">
                    <img class="shop-egg" src={process.env.PUBLIC_URL + '/img/Egg.gif'} alt="A new egg!" />
                    <div className="card-body">
                        <h2 className="card-title nes-text is-warning">DigiPet Egg</h2>
                        <p className="card-text">This excitable little egg is hopping all about! What pet awaits inside??!</p>
                    </div>
                    <button type="button" class="nes-btn is-warning">
                            <i class="nes-icon coin" />
                            <p>100</p>
                        </button>
                </div>
                {user && user.items.map(i =>
                    <div className="nes-container is-rounded" key={i.itemId}>
                        {i.name && <img class="shop-item" src={process.env.PUBLIC_URL + '/img/items' + i.imgUrl} alt="" />}
                        <div className="card-body">
                            <h2 className="card-title nes-text is-warning">{i.name}</h2>
                            <p className="card-text">{i.description}</p>
                        </div>
                        <button type="button" class="nes-btn is-warning">
                            <i class="nes-icon coin" />
                            <p>{i.price}</p>
                        </button>
                        <div class="card-body">
                            <p class="nes-text is-warning">Owned: {i.quantity}</p>
                        </div>
                    </div>
                )}
            </div>
        </>
    );
}

export default Shop;