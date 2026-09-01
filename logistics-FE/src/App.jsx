import { useState, useEffect } from "react";
import "./App.css";
import ShipmentForm from "./components/ShipmentForm";
import ShipmentList from "./components/ShipmentList";
import { fetchShipments, createShipment } from "./services/api";

function App() {
  const [shipments, setShipments] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const loadShipments = async () => {
    try {
      setLoading(true);
      const data = await fetchShipments();
      setShipments(data);
      setError(null);
    } catch (error) {
      setError("Failed to load shipments. Please try again later.");
      console.error("Error fetching shipments:", error);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    loadShipments();
  }, []);

  const handleAddShipment = async (newShipment) => {
    try {
      const payload = {
        productCode: newShipment.productCode,
        quantity: newShipment.quantity,
        destinationCity: newShipment.destinationCity,
        destinationLatitude: newShipment.destinationLatitude,
        destinationLongitude: newShipment.destinationLongitude,
        dispatchDate: newShipment.dispatchDate,
      };

      const createdShipment = await createShipment(payload);
      setShipments((prevShipments) => [...prevShipments, createdShipment]);
      setError(null);
    } catch (error) {
      setError("Failed to add shipment. Please try again later.");
      console.error("Error creating shipment:", error);
    }
  };

  return (
    <div className="container-fluid vh-100 overflow-auto bg-light text-dark px-3 px-md-4 py-3 d-flex flex-column">
      <div className="w-100">
        <h1 className="mb-4 text-center">Shipment Management</h1>
        {error && (
          <div
            className="alert alert-warning text-center py-2 mb-3"
            role="alert"
          >
            {error}
          </div>
        )}

        {loading ? (
          <div className="text-center my-5">
            <div className="spinner-border text-primary" role="status">
              <span className="visually-hidden">Loading...</span>
            </div>
          </div>
        ) : (
          <div className="row g-4 align-items-start h-100 justify-content-center">
            <div className="col-12 col-lg-5 d-flex justify-content-center">
              <div className="w-100">
                <ShipmentForm onAddShipment={handleAddShipment} />
              </div>
            </div>
            <div className="col-12 col-lg-7 d-flex justify-content-center">
              <div className="w-100">
                <ShipmentList shipments={shipments} />
              </div>
            </div>
          </div>
        )}
      </div>
    </div>
  );
}

export default App;
