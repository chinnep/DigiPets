import { BrowserRouter as Router, Route, Switch, Redirect } from "react-router-dom";
import { useState } from "react";
import LoginContext from "./contexts/LoginContext";
import Nav from './components/Nav';
import Home from './components/Home';
import Login from './components/Login';
import Register from "./components/Register";
import NotFound from "./components/NotFound";
import Pets from "./components/Pets";
import './style.css';
import "nes.css/css/nes.min.css";

function App() {

  const [credentials, setCredentials] = useState({
    username: null,
    jwt: null
  });

  const afterAuth = token => {
    const firstDot = token.indexOf(".");
    const secondDot = token.indexOf(".", firstDot + 1);
    const jwtBody = token.substring(firstDot + 1, secondDot);
    const body = JSON.parse(atob(jwtBody));
    setCredentials({
      username: body.sub,
      jwt: token
    });
  };

  const logout = () => setCredentials({username:null, jwt:null});

  return (
    <LoginContext.Provider value={{...credentials, afterAuth, logout}}>
          <Router>
            <Nav />
            <Switch>

              <Route path="/register">
                <Register/>
              </Route>

            <Route path="/login">
                <Login/>
              </Route>

              <Route path="/pets">
                {/* {credentials.username ? <Pets/> : <Redirect to="/" />} */}
                <Pets />
              </Route>

              <Route path="/error">
                <NotFound/>
              </Route>

              <Route path="/">
                <Home/>
              </Route>
    
            </Switch>
          </Router>
        </LoginContext.Provider>
      );
}

export default App;