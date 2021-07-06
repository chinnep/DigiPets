  import { BrowserRouter as Router, Route, Switch, Redirect } from "react-router-dom";
import { useState } from "react";

import Home from './components/Home';
import Login from './components/Login';
import LoginContext from "./contexts/LoginContext";
import MockPet from "./components/MockPet";
import Nav from './components/Nav';
import NotFound from "./components/NotFound";
import Pets from "./components/Pets";
import Pet from './components/Pet';
import { refresh } from "./services/auth";
import Register from "./components/Register";
import Shop from "./components/Shop";
import BattlePrep from "./components/BattlePrep";
import Battle from "./components/Battle";
import BattleWaitingRoom from "./components/BattleWaitingRoom";
import './style.css';
// import 'bootstrap/dist/css/bootstrap.min.css';
import "nes.css/css/nes.min.css";

const wait = 1000 * 60 * 55;

const parseToken = token => {
  const firstDot = token.indexOf(".");
  const secondDot = token.indexOf(".", firstDot + 1);
  const jwtBody = token.substring(firstDot + 1, secondDot);
  try {
    return JSON.parse(atob(jwtBody));
  } catch (err) { }
};

function App() {

  const [username, setUsername] = useState();

  const refreshToken = () => {
    refresh()
      .then(token => {
        localStorage.setItem("jwt", token);
        setTimeout(refreshToken, wait);
      })
      .catch(() => localStorage.removeItem("jwt"));
  };

  const afterAuth = token => {
    const payload = parseToken(token) || { sub: null };
    setUsername(payload.sub);
    localStorage.setItem("jwt", token);
    setTimeout(refreshToken, wait);
  };

  const logout = () => {
    setUsername();
    localStorage.removeItem("jwt");
  };

  return (
    <LoginContext.Provider value={{ username, afterAuth, logout }}>
      <Router>
        <Nav />
        <Switch>

          <Route path="/register">
            <Register />
          </Route>

          <Route path="/login">
            <Login />
          </Route>
          
          <Route path="/battle/:petAId/:petBId">
            <Battle/>
          </Route>

          <Route path="/battleprep/:id">
            <BattlePrep/>
          </Route>

          <Route path="/waitingroom/:id">
            <BattleWaitingRoom />
          </Route>

          <Route path="/shop">
            {username ? <Shop /> : <Redirect to="/"/>}
          </Route>

          <Route path="/mockpet">
            <MockPet />
          </Route>

          <Route path="/pet/:id">
            <Pet />
          </Route>

          <Route path="/error">
            <NotFound />
          </Route>

          <Route path="/">
            {username ? <Pets /> : <Home />}
          </Route>

        </Switch>
      </Router>
    </LoginContext.Provider>
  );
}

export default App;