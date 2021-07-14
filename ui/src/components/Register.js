import { Link, useHistory } from 'react-router-dom';
import { useState, useEffect } from 'react';
import { add } from '../services/users';
import {findByUsername} from '../services/users';

function Register() {

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [confirmPassword, setConfirmPassword] = useState("");
    const [errors, setErrors] = useState();
    const history = useHistory();

    useEffect(() => {
        let e = [];
        if (username) {
            findByUsername(username)
                .then(() => {
                    e.push("Username is already taken.");
                    setErrors(e);
                })
                .catch(() => {
                    if(/\W+/.test(username)) {
                        e.push("Invalid username, can only contain letters, numbers, and underscore(_).")
                        setErrors(e);
                    }
                })
        }
        if(password === confirmPassword) {
            if(password.length < 8) {
                e.push("Password must be at least 8 characters long.");
                setErrors(e);
            } if(!/\d+/.test(password)) {
                e.push("Password must contain a number.");
                setErrors(e);
            } if(!/[a-zA-Z]/.test(password)) {
                e.push("Password must contain a letter.");
                setErrors(e);
            }
            if(password.length >= 8 && /\d+/.test(password) && /[a-zA-Z]/.test(password) && !/\W+/.test(username)) {
               setErrors([]);
            }
        } else setErrors(["Passwords don't match"]);

    }, [username, password, confirmPassword, history]);

    async function onSubmit(evt) {
        evt.preventDefault();

        // if(password === confirmPassword) {
        //     if(password.length < 8) {
        //         e.push("Password must be at least 8 characters long.");
        //         setErrors(e);
        //     } if(!/\d+/.test(password)) {
        //         e.push("Password must contain a number.");
        //         setErrors(e);
        //     } if(!/[a-zA-Z]/.test(password)) {
        //         e.push("Password must contain a letter.");
        //         setErrors(e);
        //     }
            if(errors.length === 0) {
                add({ username, password })
                .then(() => history.push("/login"))
                .catch(setErrors(["There was a problem registering."]))
            }
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
                <input type="password" id="password" className={errors && errors.length > 0 ? "nes-input is-error":"nes-input"} placeholder="Password" required
                    value={password} onChange={evt => setPassword(evt.target.value)}></input>
            </div>
            <div className="nes-field is-inline">
                <label htmlFor="inline_field">Confirm Password</label>
                <input type="password" id="password-confirm" className={errors && errors.length > 0 ? "nes-input is-error":"nes-input"} placeholder="Confirm Password" required
                    value={confirmPassword} onChange={evt => setConfirmPassword(evt.target.value)}></input>
            </div>
            <div>
                <button type="submit" className="nes-btn is-success">Submit</button>
                <Link to="/" className="nes-btn is-warning">Cancel</Link>
            </div>
            <br></br>
            {errors && errors.length > 0 ?
                <ul className="nes-list is-disc">
                    {errors.map((e, index) => 
                    <li className="nes-text" key={index}>{e}</li>)}
                </ul>:<></>}
        </form>
    );
}

export default Register;