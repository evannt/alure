import Loading from "../components/Loading.jsx";
import LureCard from "../components/LureCard.jsx";
import { useEffect, useState } from "react";
import { getCatalog } from "../service/alure.js";
import "../style/Error.css"
import "../style/Catalog.css";

function Catalog() {
    const [lures, setLures] = useState([]);
    const [error, setError] = useState(null);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const loadCatalog = async () => {
            try {
                setLoading(true);
                setError(null);
                const lureCatalog = await getCatalog();
                setLures(lureCatalog);
            } catch (e) {
                console.error(e);
                setError(e.message);
            } finally {
                setLoading(false);
            }
        };
        loadCatalog();
    }, []);

    return (
        <div className="catalog-container">
            <h2>Catalog</h2>

            <div className="catalog-details">
                <p>View all the avalable lure suggestions.</p>
            </div>

            {error && <div className="error">{error}</div>}

            {loading ? (
                <Loading />
            ) : (
                <div className="catalog-items">
                    {!error && lures.map((lure) => (
                        <LureCard lure={lure} key={lure.name} />
                    ))}
                </div>
            )}
        </div>
    );
}

export default Catalog;
