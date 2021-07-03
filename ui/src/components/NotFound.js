import { useHistory } from "react-router-dom";

function NotFound() {

    const history = useHistory();

    return (
        <div>
            <section className="nes-container is-dark">
            <section className="message-list">
                <section className="message -left">
                    <i className="nes-ash"></i>
                    <div className="nes-balloon from-left is-dark">
                        <p>Oof it looks like you found a page that shouldn't be here</p>
                    </div>
                </section>
                <section className="message -right">
                    <div className="nes-balloon from-right is-dark">
                        <p>oh no what should I do?</p>
                    </div>
                    <i className="nes-kirby"></i>
                </section>
                <section className="message -left">
                    <i className="nes-ash"></i>
                    <div className="nes-balloon from-left is-dark">
                        <p>Should probably take a step back.</p>
                    </div>
                </section>
            </section>
            <badge className="nes-badge" onClick={() => {history.goBack();}}>
                <span className="is-error">back</span>
            </badge>
            </section>
        </div>
    );
}

export default NotFound;