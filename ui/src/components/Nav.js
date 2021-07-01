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
        <a class="active" href="/">DigiPet</a>
        <a href="/login">Login</a>
        <a href="/register">Register</a>
        <a href="/pets">See a pet!</a>
    </div>
    </>
  );
}

export default Nav;