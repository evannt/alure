import "../style/Weather.css";

function Weather({ suggestion }) {
    return (
        <div className="weather-container">
            <div className="region-container">
                <h3 className="location">{suggestion.name}</h3>
                <p className="region">{suggestion.region}{suggestion.region && ", "}{suggestion.country}</p>
            </div>
            <div className="weather-icon">
                <img className="icon" src={suggestion.conditionIcon} alt={suggestion.condition} />
            </div>
            <div className="temperature">
                <h3>{suggestion.temp}&deg;F</h3>
            </div>
            <div className="weather-details">
                <div className="weather-details-keys">
                    <p>Conditions</p>
                    <p>Pressure</p>
                </div>
                <div className="weather-details-values">
                    <p>{suggestion.condition}</p>
                    <p>{suggestion.pressure} in.</p>
                </div>
            </div>
        </div>
    );
}

export default Weather;
