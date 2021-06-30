import '../homeStyle.css';

function Home() {

    return (
        <>
        <div className="container">
            <div id="title-container" class="nes-container">
                <p>DigiPets</p>
                <p className="caption">a dev10 production</p>
            </div>
        </div>
        <div id="about-section">
            <h1 id="title">About</h1>
            <p className="caption">Online Tamagotchi app: take care of an 8 bit pet and then battle them against friends for fun prizes or other Tamagotchi! If your pet isn’t taken care of well enough it will lose stamina, become dissatisfied, or even run away. Conversely, tending to all their needs will allow them to become a true battle master and climb the ranks of the battle board.
            </p>
        </div>
        <div id="devs-section">
            <h1 id="title">Developers</h1>
            <div id="profiles" className="row">
                <div className="column">
                    <div className="row">
                        <img class="nes-avatar is-rounded is-large" alt="EC Avatar" src="logo.png"/>
                    </div>
                    Eleanor Chinn
                    <p>about ec</p>
                </div>
                <div className="column">
                <div className="row">
                        <img class="nes-avatar is-rounded is-large" alt="EC Avatar" src="logo.png"/>
                    </div>
                Tim Warren
                </div>
                <div className="column">
                <div className="row">
                        <img class="nes-avatar is-rounded is-large" alt="EC Avatar" src="logo.png"/>
                    </div>
                Josh Frey
                </div>
            </div>
        </div>
        </>
    );
}

export default Home;