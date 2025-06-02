import { Link } from "react-router-dom";
import "../style/NavBar.css";

function NavBar() {
    return <div className="nav-container">
        <div className="title">
            <h1>Alure</h1>
        </div>
        <nav className="primary-navigation">
            <div className="navigation-links">
                <Link to="/" className="navigation-link">Search</Link>
                <Link to="/catalog" className="navigation-link">Catalog</Link>
            </div>
        </nav>
    </div>;
}

export default NavBar
