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
    <div class="topnav">
        <Link className="nes-btn is-error" to="/">DigiPet</Link>
        {username ? <button className="nes-btn is-error" onClick={handleLogout}>Logout</button>
          : <Link to="/login" className="nes-btn is-primary">Login</Link>}
        <Link to="/register" className="nes-btn is-success">Register</Link>
        <Link className="nes-btn is-warning" to="/pets">See a pet!</Link>
    </div>
    </>
  );
}

export default Nav;