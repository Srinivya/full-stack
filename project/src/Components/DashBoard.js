import React from "react";
import TestConnection from "./TestConnection";

const DashBoard = () => {
  return (
    <div style={styles.container}>
      <h1>Welcome to the Dashboard</h1>
      <p>You have successfully logged in or verified your account.</p>
      <TestConnection></TestConnection>
    </div>
  );
};

const styles = {
  container: {
    textAlign: "center",
    marginTop: "50px",
    fontFamily: "Arial",
  },
};

export default DashBoard;
