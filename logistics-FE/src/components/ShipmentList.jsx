import React from "react";
import RiskBadge from "./RiskBadge";

const ShipmentList = ({ shipments }) => {
  return (
    <div
      className="container border rounded bg-white p-3 p-md-4 d-flex flex-column h-100"
      style={{ maxHeight: "90%" }}
    >
      <h3 className="mb-3">Shipment List</h3>
      <div
        className="table-responsive flex-grow-1"
        style={{
          maxHeight: "calc(100vh - 220px)",
          overflow: "auto",
          scrollbarWidth: "none",
          msOverflowStyle: "none",
        }}
      >
        <table className="table table-striped table-bordered table-hover mb-0">
          <thead className="thead-light position-sticky top-0">
            <tr>
              <th>Product Code</th>
              <th>Quantity</th>
              <th>Destination City</th>
              <th>Dispatch Date</th>
              <th>Shipment Status</th>
              <th>Risk Level</th>
            </tr>
          </thead>
          <tbody>
            {shipments.map((shipment) => (
              <tr key={shipment.id}>
                <td>{shipment.productCode}</td>
                <td>{shipment.quantity}</td>
                <td>{shipment.destinationCity}</td>
                <td>{shipment.dispatchDate}</td>
                <td>{shipment.shipmentStatus}</td>
                <td>
                  <RiskBadge riskLevel={shipment.riskLevel} />
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default ShipmentList;
