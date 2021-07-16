import '../pet.scss';
import Card from 'react-bootstrap/Card';

function MockPet() {

    return (
        <Card id="battleprep-card" className="nes-container with-title is-centered">
                    <text id="battleprep-display-name" className="title">Ducky</text>
                    <div>
                        <div id="bars">
                            <div className="nes-field is-inline">
                                <text className="text">health_lvl</text>
                            <progress className="nes-progress is-error" value={100} max={100}/>
                            </div>
                            <div className="nes-field is-inline">
                                <text className="text">care_lvl </text>
                                <progress className="nes-progress is-warning" value={100} max={100}/>
                            </div>
                            <div className="nes-field is-inline">
                                <text className="text">hunger_lvl</text>
                                <progress className="nes-progress is-success" value={80} max={100}/>
                            </div>
                            <div className="nes-field is-inline">
                                <text className="text">thirst_lvl</text>
                                <progress className="nes-progress is-primary" value={100} max={100}/>
                            </div>
                        </div>
                         <div  id="battleprep-egg" className='egg'>
                            <div id="battleprep-crack" className='crack'>
                                <div id="battleprep-display" className='display'>
                                    <div className='grid'>
                                        <img id="battleprep-image" src={process.env.PUBLIC_URL + '/img/' + '/Duck.gif'} alt=""/>
                                    </div>
                                </div>
                            </div>
                            <div id="battleprep-buttons" className='buttons'>
                                <div id="battleprep-button" className='button'></div>
                                <div id="battleprep-button" className='button'></div>
                                <div id="battleprep-button" className='button'></div>
                            </div>
                        </div>
                    </div>
                </Card>
    );
}

export default MockPet;