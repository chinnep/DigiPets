import '../pet.scss';

function MockPet() {

    return (
        <div className='container' id="egg-container">
            <div className='loop'></div>
            <div className='egg'>
                <div className='crack'>
                    <div className='display'>
                        <div className='grid'>
                            <img id="active-image" src="https://2.bp.blogspot.com/-BwqYts1IQQ8/Txl9ZXaXwFI/AAAAAAAACbg/2b9IMKJ8_H0/s1600/6.gif" alt=""/>
                        </div>
                    </div>
                </div>
                <div className='buttons'>
                    <button id="pet-button" className='button' />
                    <button id="pet-button" className='button' />
                    <button id="pet-button" className='button' />
                </div>
            </div>
        </div>
    );
}

export default MockPet;