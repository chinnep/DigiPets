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

  const { username, logout } = useContext(LoginContext);
  const history = useHistory();

  const handleLogout = () => {
    logout();
    history.push("/");
  }

  return (
    <>
    <div class="topnav">
        <a class="active" href="/">DigiPet</a>
        {username ? <button className="nes-btn is-error" onClick={handleLogout}>Logout</button>
          : <Link to="/login" className="nes-btn is-primary">Login</Link>}
        <Link to="/register" className="new-btn is-success">Register</Link>
        <a href="/pets">See a pet!</a>
    </div>
    </>
  );
}

export default Nav;