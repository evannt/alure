import Loading from "../components/Loading.jsx";
import Weather from "../components/Weather.jsx";
import LureCard from "../components/LureCard.jsx";
import { useState } from "react";
import { getSuggestion } from "../service/alure.js";
import "../style/Error.css";
import "../style/Suggestion.css";

function Suggestion() {
    const [searchLocation, setSearchLocation] = useState("");
    const [suggestion, setSuggestion] = useState({ lures: [] });
    const [error, setError] = useState(null);
    const [loading, setLoading] = useState(false);

    async function fetchSuggestions(event) {
        event.preventDefault();
        if (!searchLocation.trim()) return;
        if (loading) return;

        try {
            setLoading(true);
            setError(null);
            const suggestion = await getSuggestion(searchLocation);
            setSuggestion(suggestion);
        } catch (e) {
            console.error(e);
            setError(e.message);
        } finally {
            setLoading(false);
        }
    }

    return (
        <div className="suggest-container">
            <h2>Search</h2>

            <div className="form-container">
                <div className="suggest-instructions">
                    <p>Enter a location to get lure suggestions based on the weather</p>
                </div>

                <form onSubmit={fetchSuggestions} className="suggest-form">
                    <input type="text" className="suggest-input" placeholder="Enter a location..." value={searchLocation} onChange={(e) => setSearchLocation(e.target.value)}/>
                    <button type="submit" className="suggest-btn">Suggest</button>
                    <button className="suggest-btn" onClick={() => setSearchLocation("")}>Reset</button>
                </form>
            </div>

            {error && <div className="error">{error}</div>}

            { loading ? (
                <Loading />
            ) : 
                !error && suggestion?.lures?.length > 0 && (
                <>
                    <Weather suggestion={suggestion} />

                    <h2>Suggested Lures</h2>

                    <div className="lure-container">
                        {suggestion.lures.map((lure) => (
                            <LureCard lure={lure} key={lure.name} />
                        ))}
                    </div>
                </>
            )}
        </div>
    );
}

export default Suggestion;
