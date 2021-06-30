import {useHistory} from 'react-router-dom';
import LoginContext from '../contexts/LoginContext';
import {useState, useContext} from 'react';
//import {authenticate} from '../services/auth';

function SignIn() {

    const[name, setName] = useState("");
    const[password, setPassword] = useState("");
    const { afterAuth } = useContext(LoginContext);
    const history = useHistory();

    function onSubmit(evt) {
        evt.preventDefault();
        // authenticate({username: name, password})
        //     .then(body => {
        //         const {jwt_token} = body;
        //         console.log(jwt_token);
        //         afterAuth(jwt_token);
        //         history.push("/");
        //     })
        //     .catch(() => history.push("/error"));
    }

    return (
        <div className="row" id="login-form">
            <form className="form" onSubmit={onSubmit}>
                <h1>Login</h1>
                <div class="nes-field is-inline">
                    <label for="inline_field">Username</label>
                    <input type="text" id="username" className="nes-input" placeholder="Username" autofocus=""value={name} onChange={evt => setName(evt.target.value)}></input>
                </div>
                <div class="nes-field is-inline">
                    <label for="inline_field">Password</label>
                    <input type="password" id="password" className="nes-input" placeholder="Password" required="" value={password} onChange={evt => setPassword(evt.target.value)}></input>
                </div>
                <button type="button" className="nes-btn is-success">Success</button>
            </form>
        </div>
    );
}

export default SignIn;