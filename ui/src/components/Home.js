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
                <p className="caption">Online DigiPet: take care of an 8 bit pet and then battle them against friends for fun prizes or new pets! If your pet isn’t taken care of well enough it will lose stamina, become dissatisfied, or even run away. Conversely, tending to all their needs will allow them to become a true battle master and climb the ranks of the battle board.
                </p>
            </div>
            <div id="devs-section">
                <h1 id="title">Developers</h1>
                <div id="profiles" className="row">
                    <div className="column">
                        <div className="row">
                            <img class="nes-avatar is-rounded is-large" alt="EC Avatar" src="logo.png" />
                        </div>
                        Eleanor Chinn
                        <div>
                            _
                        </div>
                        <p>Hello! I'm from Northern Virginia and I love hiking, travelling, and learning new things.
                            My favorite games are Settlers of Catan, Clash of Clans, and almost all Nintendo games.
                        </p>
                    </div>
                    <div className="column">
                        <div className="row">
                            <img class="nes-avatar is-rounded is-large" alt="EC Avatar" src="logo.png" />
                        </div>
                        Tim Warren
                        <div>
                            _
                        </div>
                        <p>Hey there. I'm a software engineer living in Arlington, VA. I like to travel, ski,
                            play volleyball, and my all-time favorite games are Dragon Age: Origins, Final Fantasy
                            Tactics, and Chrono Trigger.
                        </p>
                    </div>
                    <div className="column">
                        <div className="row">
                            <img class="nes-avatar is-rounded is-large" alt="EC Avatar" src="logo.png" />
                        </div>
                        Josh Frey
                        <div>
                            _
                        </div>
                        <p>Hey all- I'm a software developer trainee from Prescott, WI. I have a degree in health,
                            wellness & fitness. I enjoy staying active, but also love playing video games (mainly older
                            Nintendo games).</p>
                    </div>
                </div>
            </div>
        </>
    );
}

export default Home;