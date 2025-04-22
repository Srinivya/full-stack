import React, { useState } from "react";
import axios from "axios";
import { Link } from "react-router-dom";

const OtpVerificationForm = () => {
  const [email, setEmail] = useState("");
  const [otp, setOtp] = useState("");
  const [message, setMessage] = useState("");

  const handleOtpVerification = async (e) => {
    e.preventDefault();

    const data = { email, otp };

    try {
      const response = await axios.post(
        "http://localhost:8070/user/verify",
        data
      );
      setMessage(response.data);
    } catch (error) {
      setMessage(error.response?.data || "An error occurred");
    }
  };

  return (
    <div style={styles.container}>
      <h2 style={styles.title}>Verify OTP</h2>
      <form onSubmit={handleOtpVerification} style={styles.form}>
        <div style={styles.formGroup}>
          <label>Email:</label>
          <input
            type="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
            style={styles.input}
          />
        </div>
        <div style={styles.formGroup}>
          <label>OTP:</label>
          <input
            type="text"
            value={otp}
            onChange={(e) => setOtp(e.target.value)}
            required
            style={styles.input}
          />
        </div>
        <button type="submit" style={styles.button}>
          Verify OTP
        </button>
      </form>

      {message && <p style={styles.message}>{message}</p>}
      <p style={styles.linkText}>
        <Link to="/login" style={styles.link}>
          Go to Login
        </Link>
      </p>
    </div>
  );
};

const styles = {
  container: {
    maxWidth: "400px",
    margin: "50px auto",
    padding: "30px",
    border: "1px solid #ccc",
    borderRadius: "10px",
    backgroundColor: "#f9f9f9",
    fontFamily: "Arial, sans-serif",
  },
  title: {
    textAlign: "center",
    color: "#333",
  },
  form: {
    display: "flex",
    flexDirection: "column",
  },
  formGroup: {
    marginBottom: "15px",
  },
  input: {
    width: "100%",
    padding: "8px",
    marginTop: "5px",
    borderRadius: "5px",
    border: "1px solid #ccc",
  },
  button: {
    padding: "10px",
    backgroundColor: "#007bff",
    color: "white",
    border: "none",
    borderRadius: "5px",
    cursor: "pointer",
  },
  message: {
    marginTop: "15px",
    color: "green",
    textAlign: "center",
  },
  linkText: {
    textAlign: "center",
    marginTop: "10px",
  },
  link: {
    color: "#007bff",
    textDecoration: "underline",
    fontSize: "16px",
  },
};

export default OtpVerificationForm;
