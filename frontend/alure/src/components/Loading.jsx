import "../style/Loading.css";

function Loading() {
    return (
        <div id="loader" className="loader">
            <div className="fish fish1">
                <div className="tail tail1"></div>
                <div className="eye"></div>
            </div>
            <div className="fish fish2">
                <div className="tail tail2"></div>
                <div className="eye"></div>
            </div>
        </div>
    );
}

export default Loading;
