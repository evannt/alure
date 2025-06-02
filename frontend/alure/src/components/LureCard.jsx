import "../style/LureCard.css";

function LureCard({lure}) {
    return (
        <div className="lure-card">
            <div className="lure-card-top">
                <div className="lure-icon">
                    <img src={lure.image} alt={lure.name}/>
                </div>
            </div>
            <div className="lure-details">
                <h2 className="lure-name">{lure.name}</h2>
                <h3 className="lure-condition">{lure.condition} | {lure.score}</h3>
                <p className="lure-description">{lure.description}</p>
            </div>
            <div className="lure-footer">
                <p className="lure-reason">{lure.reason}</p>
            </div>
        </div>
    );
}

export default LureCard;
