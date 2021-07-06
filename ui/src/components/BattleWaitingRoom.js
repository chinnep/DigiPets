function BattleWaitingRoom() {

    return(
        <div className="cat-container">
            <img id="loading" src={process.env.PUBLIC_URL + '/img/loading_cat.gif'} />
            <div className="nes-container is-rounded is-dark">
                <p>Please wait while we pair you with your opponent...</p>
            </div>
        </div>
    )
}

export default BattleWaitingRoom;