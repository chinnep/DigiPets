import { Link, useHistory}  from 'react-router-dom';
import LoginContext from '../contexts/LoginContext';
import { useState, useContext } from 'react';
import { authenticate } from '../services/auth';

function Login() {

    const[name, setName] = useState("");
    const[password, setPassword] = useState("");
    const { afterAuth } = useContext(LoginContext);
    const history = useHistory();

    function onSubmit(evt) {
        evt.preventDefault();
        authenticate({username: name, password})
            .then(body => {
                const {jwt_token} = body;
                console.log(jwt_token);
                afterAuth(jwt_token);
                history.push("/");
            }).catch(err => alert("err"));
    }

    return (
            <form className="form" onSubmit={onSubmit}>
                <h1>Login</h1>
                <div class="nes-field is-inline">
                    <label for="inline_field">Username</label>
                    <input type="text" id="username" className="nes-input" placeholder="Username" autofocus=""value={name} onChange={evt => setName(evt.target.value)} />
                </div>
                <div class="nes-field is-inline">
                    <label for="inline_field">Password</label>
                    <input type="password" id="password" className="nes-input" placeholder="Password" required="" value={password} onChange={evt => setPassword(evt.target.value)} />
                </div>
                <div class="nes-field is-inline">
                    <button type="submit" className="nes-btn is-success">Submit</button>
                    <Link to="/" className="new-btn is-warning">Cancel</Link>
                </div>
            </form>
    );
}

export default Login;