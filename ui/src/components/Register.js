import { Link, useHistory } from 'react-router-dom';
import { useState } from 'react';
import { add } from '../services/users';
import ErrorSummary from "./ErrorSummary";

function Register() {

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [confirmPassword, setConfirmPassword] = useState("");
    const [errors, setErrors] = useState();

    const history = useHistory();

    async function onSubmit(evt) {
        evt.preventDefault();

        password === confirmPassword ?
            add({ username, password })
                .then(() => history.push("/login"))
                .catch(setErrors(["There was a problem registering."]))
            : setErrors(["Passwords don't match"]);
    }

    return (
        <form className="nes-container with-title is-centered form" onSubmit={onSubmit}>
            <h1>Register</h1>
            <div className="nes-field is-inline">
                <label htmlFor="inline_field">Username</label>
                <input type="text" id="username" className="nes-input" placeholder="Username"
                    value={username} onChange={evt => setUsername(evt.target.value)}></input>
            </div>
            <div className="nes-field is-inline">
                <label htmlFor="inline_field">Password</label>
                <input type="password" id="password" className="nes-input" placeholder="Password" required
                    value={password} onChange={evt => setPassword(evt.target.value)}></input>
            </div>
            <div className="nes-field is-inline">
                <label htmlFor="inline_field">Confirm Password</label>
                <input type="password" id="password-confirm" className="nes-input" placeholder="Confirm Password" required
                    value={confirmPassword} onChange={evt => setConfirmPassword(evt.target.value)}></input>
            </div>
            <div>
                <button type="submit" className="nes-btn is-success">Submit</button>
                <Link to="/" className="nes-btn is-warning">Cancel</Link>
            </div>
            <br></br>
            <ErrorSummary errors={errors} />
        </form>
    );
}

export default Register;