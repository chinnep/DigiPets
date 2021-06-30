import { useHistory } from "react-router-dom";

function NotFound() {

    const history = useHistory();

    return (
        <div>
            <section class="nes-container is-dark">
            <section class="message-list">
                <section class="message -left">
                    <i class="nes-ash"></i>
                    <div class="nes-balloon from-left is-dark">
                        <p>Oof it looks like you found a page that shouldn't be here</p>
                    </div>
                </section>
                <section class="message -right">
                    <div class="nes-balloon from-right is-dark">
                        <p>oh no what should I do?</p>
                    </div>
                    <i class="nes-kirby"></i>
                </section>
                <section class="message -left">
                    <i class="nes-ash"></i>
                    <div class="nes-balloon from-left is-dark">
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