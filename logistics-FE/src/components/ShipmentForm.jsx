import React, { useState } from "react";

const ShipmentForm = ({ onAddShipment }) => {
  const [formData, setFormData] = useState({
    productCode: "",
    quantity: "",
    destinationCity: "",
    destinationLatitude: "",
    destinationLongitude: "",
    dispatchDate: "",
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (
      !formData.productCode ||
      !formData.quantity ||
      !formData.destinationCity ||
      !formData.destinationLatitude ||
      !formData.destinationLongitude ||
      !formData.dispatchDate
    ) {
      return;
    }

    const latitude = Number(formData.destinationLatitude);
    const longitude = Number(formData.destinationLongitude);

    if (!Number.isFinite(latitude) || !Number.isFinite(longitude)) {
      return;
    }

    const newShipment = {
      id: crypto.randomUUID(),
      productCode: formData.productCode,
      quantity: Number.parseInt(formData.quantity, 10),
      destinationCity: formData.destinationCity,
      destinationLatitude: latitude,
      destinationLongitude: longitude,
      dispatchDate: formData.dispatchDate,
    };

    onAddShipment(newShipment);

    setFormData({
      productCode: "",
      quantity: "",
      destinationCity: "",
      destinationLatitude: "",
      destinationLongitude: "",
      dispatchDate: "",
    });
  };

  return (
    <div className="container border rounded p-3 p-md-4 text-start bg-white">
      <h3 className="mb-3 text-center">Add new Shipment</h3>
      <hr />
      <form onSubmit={handleSubmit}>
        <div className="form-group mb-3 text-start">
          <label htmlFor="productCode" className="d-block text-start">
            Product Code
          </label>
          <input
            type="text"
            className="form-control"
            id="productCode"
            name="productCode"
            value={formData.productCode}
            onChange={handleChange}
            placeholder="Enter product code"
            required
          />
        </div>

        <div className="form-group mb-3 text-start">
          <label htmlFor="quantity" className="d-block text-start">
            Quantity
          </label>
          <input
            type="number"
            className="form-control"
            id="quantity"
            name="quantity"
            value={formData.quantity}
            onChange={handleChange}
            placeholder="Enter quantity"
            required
          />
        </div>

        <div className="form-group mb-3 text-start">
          <label htmlFor="destinationCity" className="d-block text-start">
            Destination City
          </label>
          <input
            type="text"
            className="form-control"
            id="destinationCity"
            name="destinationCity"
            value={formData.destinationCity}
            onChange={handleChange}
            placeholder="Enter destination city"
            required
          />
        </div>

        <div className="row g-3">
          <div className="col-12 col-md-6">
            <div className="form-group text-start">
              <label
                htmlFor="destinationLatitude"
                className="d-block text-start"
              >
                Destination Latitude
              </label>
              <input
                type="number"
                step="any"
                className="form-control"
                id="destinationLatitude"
                name="destinationLatitude"
                value={formData.destinationLatitude}
                onChange={handleChange}
                placeholder="Latitude"
                required
              />
            </div>
          </div>

          <div className="col-12 col-md-6">
            <div className="form-group text-start">
              <label
                htmlFor="destinationLongitude"
                className="d-block text-start"
              >
                Destination Longitude
              </label>
              <input
                type="number"
                step="any"
                className="form-control"
                id="destinationLongitude"
                name="destinationLongitude"
                value={formData.destinationLongitude}
                onChange={handleChange}
                placeholder="Longitude"
                required
              />
            </div>
          </div>
        </div>

        <div className="form-group mt-3 mb-3 text-start">
          <label htmlFor="dispatchDate" className="d-block text-start">
            Dispatch Date
          </label>
          <input
            type="date"
            className="form-control"
            id="dispatchDate"
            name="dispatchDate"
            value={formData.dispatchDate}
            onChange={handleChange}
            required
          />
        </div>

        <button type="submit" className="btn btn-primary w-100 mt-2">
          Add Shipment
        </button>
      </form>
    </div>
  );
};

export default ShipmentForm;
