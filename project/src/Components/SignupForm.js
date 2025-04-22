import React, { useState } from "react";
import axios from "axios";
import { Link, useNavigate } from "react-router-dom";

const SignupForm = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [message, setMessage] = useState("");
  const [isModalVisible, setIsModalVisible] = useState(false);
  const navigate = useNavigate();

  const handleSignup = async (e) => {
    e.preventDefault();
    const data = { email, password };

    try {
      const response = await axios.post(
        "http://localhost:8070/user/signup",
        data
      );
      setMessage(response.data);
      setIsModalVisible(true);
    } catch (error) {
      setMessage(error.response?.data || "An error occurred");
    }
  };

  const handleCloseModal = () => {
    setIsModalVisible(false);
    navigate("/verify"); 
  };

  return (
    <div style={styles.container}>
      <h2 style={styles.title}>Sign Up</h2>
      <form onSubmit={handleSignup} style={styles.form}>
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
          <label>Password:</label>
          <input
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
            style={styles.input}
          />
        </div>

        <button type="submit" style={styles.button}>
          Sign Up
        </button>
      </form>

      {message && (
        <div style={styles.messageBox}>
          <p>{message}</p>
          {message.includes("OTP sent to your email") && (
            <p>
              Got your OTP? <Link to="/verify">Click here to verify</Link>
            </p>
          )}
        </div>
      )}

      {isModalVisible && (
        <div style={styles.modal}>
          <div style={styles.modalContent}>
            <h3>OTP sent successfully!</h3>
            <button onClick={handleCloseModal} style={styles.button}>
              Close and Proceed to Verification
            </button>
          </div>
        </div>
      )}

      <p style={{ marginTop: "10px" }}>
        Already have an account? <Link to="/login">Login here</Link>
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
  messageBox: {
    marginTop: "15px",
    padding: "10px",
    backgroundColor: "#e6f7ff",
    border: "1px solid #91d5ff",
    borderRadius: "5px",
  },
  modal: {
    position: "fixed",
    top: 0,
    left: 0,
    width: "100%",
    height: "100%",
    backgroundColor: "rgba(0, 0, 0, 0.5)",
    display: "flex",
    justifyContent: "center",
    alignItems: "center",
  },
  modalContent: {
    backgroundColor: "white",
    padding: "20px",
    borderRadius: "10px",
    textAlign: "center",
  },
};

export default SignupForm;
