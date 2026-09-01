import React from "react";

const RiskBadge = ({ riskLevel }) => {
  const getBadgeColor = () => {
    switch (riskLevel) {
      case "LOW":
        return "bg-success";
      case "MEDIUM":
        return "bg-warning";
      case "HIGH":
        return "bg-danger";
      case "UNKNOWN":
      default:
        return "bg-secondary";
    }
  };

  return <span className={`badge ${getBadgeColor()}`}>{riskLevel}</span>;
};

export default RiskBadge;
