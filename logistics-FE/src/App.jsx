import { useState } from "react";
import heroImg from "./assets/hero.png";
import reactLogo from "./assets/react.svg";
import viteLogo from "./assets/vite.svg";
import "./App.css";
import ShipmentForm from "./components/ShipmentForm";
import ShipmentList from "./components/ShipmentList";
import { MOCK_SHIPMENTS } from "./MockData.js";

function App() {
  const [shipments, setShipments] = useState(MOCK_SHIPMENTS);

  const handleAddShipment = (newShipment) => {
    setShipments((prevShipments) => [...prevShipments, newShipment]);
  };

  return (
    <div className="container-fluid vh-100 overflow-auto bg-light text-dark px-3 px-md-4 py-3 d-flex flex-column">
      <h1 className="mb-4 text-center">Shipment Management</h1>
      <div className="row g-4 align-items-start h-100">
        <div className="col-12 col-lg-5 h-100">
          <ShipmentForm onAddShipment={handleAddShipment} />
        </div>
        <div className="col-12 col-lg-7 h-100">
          <ShipmentList shipments={shipments} />
        </div>
      </div>
    </div>
  );
}

export default App;
