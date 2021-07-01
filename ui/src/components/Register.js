import {Link, useHistory} from 'react-router-dom';
import LoginContext from '../contexts/LoginContext';
import {useState, useContext} from 'react';
//import {register} from '../services/auth';

function Register() {

    const[username, setUsername] = useState("");
    const[password, setPassword] = useState("");

    let [confirmPassword, setConfirmPassword] = useState("");
    let passwordsMatch = false;
    const history = useHistory();
    
    async function onSubmit(evt) {
        evt.preventDefault();
        
        // register({username, password})
        // .then(() => history.push("/login"))
        // .catch(() => history.push("/error"));
    }

    async function confirmPasswordsMatch(evt) {
        evt.preventDefault();
        setConfirmPassword(evt.target.value);
        passwordsMatch = (password == confirmPassword);
        console.log(passwordsMatch);
    }

    return(
            <form className="form" onSubmit={onSubmit}>
                <h1>Register</h1>
                <div class="nes-field is-inline">
                    <label for="inline_field">Username</label>
                    <input type="text" id="username" className="nes-input" placeholder="Username" autofocus=""value={username} onChange={evt => setUsername(evt.target.value)}></input>
                </div>
                <div class="nes-field is-inline">
                    <label for="inline_field">Password</label>
                    <input type="password" id="password" className="nes-input" placeholder="Password" required="" value={password} onChange={evt => setPassword(evt.target.value)}></input>
                </div>
                <div class="nes-field is-inline">
                    <label for="inline_field">Confirm Password</label>
                    <input type="password" id="password" className="nes-input" placeholder="Confirm Password" required="" value={confirmPassword} onChange={evt => setConfirmPassword(evt.target.value)}></input>
                </div>
                <button type="button" className="nes-btn is-success">Success</button>
            </form>
    );
}

export default Register;