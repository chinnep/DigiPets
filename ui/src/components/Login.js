import { Link, useHistory } from 'react-router-dom';
import LoginContext from '../contexts/LoginContext';
import { useState, useContext } from 'react';
import { authenticate } from '../services/auth';
import ErrorSummary from "./ErrorSummary";

function Login() {

    const [name, setName] = useState("");
    const [password, setPassword] = useState("");
    const [hasError, setHasError] = useState(false);

    const { afterAuth } = useContext(LoginContext);
    const history = useHistory();

    function onSubmit(evt) {
        evt.preventDefault();
        authenticate({ username: name, password })
            .then(body => {
                const { jwt_token } = body;
                afterAuth(jwt_token);
                history.push("/");
            }).catch(() => setHasError(true));
    }

    return (
        <form className="nes-container with-title is-centered form" onSubmit={onSubmit}>
            <h1>Login</h1>
            <div className="nes-field is-inline">
                <label htmlFor="inline_field">Username</label>
                <input type="text" id="username" className="nes-input" placeholder="Username"
                    value={name} onChange={evt => setName(evt.target.value)} />
            </div>
            <div className="nes-field is-inline">
                <label htmlFor="inline_field">Password</label>
                <input type="password" id="password" className="nes-input" placeholder="Password"
                    required="" value={password} onChange={evt => setPassword(evt.target.value)} />
            </div>
            <div>
                <button type="submit" className="nes-btn is-success">Submit</button>
                <Link to="/" className="nes-btn is-warning">Cancel</Link>
            </div>

            {hasError && <ErrorSummary errors={["Login failed."]} />}
        </form>
    );
}

export default Login;