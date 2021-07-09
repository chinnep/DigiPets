import { useContext } from "react";
import { useHistory, Link } from "react-router-dom";
import LoginContext from "../contexts/LoginContext";

function Nav() {
  const { username, logout } = useContext(LoginContext);
  const history = useHistory();

  const handleLogout = () => {
    logout();
    history.push("/");
  }

  return (
    <>
      <div className="topnav">
        <div className="col-left">
          <Link className="nes-btn is-primary" to="/">DigiPet</Link>
          {username ? <Link className="nes-btn is-warning" to="/shop">Shop!</Link>
          : <Link className="nes-btn is-warning" to="/mockpet">See a pet!</Link>}
          {username && <Link className="nes-btn is-success" to="/battleprep">Battle!</Link>}
          <Link to="/rankings"className="nes-btn is-error">Rankings</Link>
          <Link to="/Victory"className="nes-btn is-error">Victory</Link>
        </div>
        <div className="col-right">
          {username ?<button className="nes-btn is-error" onClick={handleLogout}>Logout</button>
            : <Link to="/login" className="nes-btn is-primary">Login</Link>}
          {!username && <Link to="/register" className="nes-btn is-success">Register</Link>}
        </div>
      </div>
    </>
  );
}

export default Nav;