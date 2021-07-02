import '../pet.scss';

function MockPet() {

    return (
        <div class='container' id="egg-container">
            <div class='loop'></div>
            <div class='egg'>
                <div class='crack'>
                    <div class='display'>
                        <div class='grid'>
                            <img id="active-image" src="https://2.bp.blogspot.com/-BwqYts1IQQ8/Txl9ZXaXwFI/AAAAAAAACbg/2b9IMKJ8_H0/s1600/6.gif" alt=""/>
                        </div>
                    </div>
                </div>
                <div class='buttons'>
                    <div id="pet-button" class='button'></div>
                    <div id="pet-button" class='button'></div>
                    <div id="pet-button" class='button'></div>
                </div>
            </div>
        </div>
    );
}

export default MockPet;