import Suggestion from "./pages/Suggestion.jsx";
import Catalog from "./pages/Catalog.jsx";
import { Routes, Route } from "react-router-dom";
import NavBar from "./components/NavBar.jsx";
import "./style/App.css"

function App() {
  return (
    <>
      <NavBar />
      <main>
        <Routes>
            <Route path="/" element={<Suggestion />}/>
            <Route path="/catalog" element={<Catalog />}/>
        </Routes>
      </main>
    </>
  );
}

export default App
