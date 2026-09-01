const BASE_URL =
  import.meta.env.VITE_API_BASE_URL || "http://localhost:8082/api";

export const fetchShipments = async () => {
  try {
    const response = await fetch(`${BASE_URL}/shipments`);
    if (!response.ok) {
      throw new Error(`HTTP error! status: ${response.status}`);
    }
    return await response.json();
  } catch (error) {
    console.error("Failed to fetch shipments:", error);
    throw error;
  }
};

export const createShipment = async (shipmentData) => {
  try {
    const response = await fetch(`${BASE_URL}/shipments`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(shipmentData),
    });
    if (!response.ok) {
      throw new Error(`HTTP error! status: ${response.status}`);
    }
    return await response.json();
  } catch (error) {
    console.error("Failed to create shipment:", error);
    throw error;
  }
};
