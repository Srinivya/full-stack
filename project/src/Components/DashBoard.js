import React, { useEffect, useState } from "react";

const Dashboard = () => {
  const [username, setUsername] = useState("");

  useEffect(() => {
    const email = localStorage.getItem("username");
    if (email) {
      const name = email.split("@")[0];
      setUsername(name);
    }
  }, []);

  return (
    <div style={styles.container}>
      <h2 style={styles.heading}>Welcome, <span style={styles.username}>{username}</span> from Geekbase!</h2>
    </div>
  );
};

const styles = {
  container: {
    maxWidth: "600px",
    margin: "50px auto",
    padding: "20px",
    borderRadius: "10px",
    backgroundColor: "#f0f8ff",
    textAlign: "center",
    fontFamily: "Arial, sans-serif",
    boxShadow: "0 4px 10px rgba(0, 0, 0, 0.1)",
  },
  heading: {
    color: "#333",
    fontSize: "24px",
  },
  username: {
    color: "#007bff",
    fontWeight: "bold",
  },
};

export default Dashboard;
