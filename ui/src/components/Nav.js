import { Link } from "react-router-dom";

function Nav() {

  return (
      <div className="topnav">
        <Link to="/">
            <img src="digipetHeader.png" alt="img not found"/>
        </Link>
        <Link class="nes-badge from-right">
            <span class="is-primary">To</span>
        </Link>
      </div>
  );
}

export default Nav;