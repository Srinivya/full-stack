import React, { useState } from "react";
import axios from "axios";
import { Link, useNavigate } from "react-router-dom";

const SignupForm = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [message, setMessage] = useState("");
  const [otpSuccess, setOtpSuccess] = useState(false);
  const [otp, setOtp] = useState("");
  const navigate = useNavigate();

  const handleSignup = async (e) => {
    e.preventDefault();
    const data = { email, password };

    try {
      const response = await axios.post(
        "http://localhost:8070/auth/signup",
        data
      );
      console.log(response.data);
      setOtpSuccess(true);
    } catch (error) {
      setMessage(error.response?.data || "An error occurred");
    }
  };

  const handleOtpVerification = async (e) => {
    e.preventDefault();

    const data = { email, otp };

    try {
      const response = await axios.post(
        "http://localhost:8070/auth/verify",
        data
      );
      navigate("/login");
    } catch (error) {
      setMessage(error.response?.data || "An error occurred");
    }
  };

  if (otpSuccess) {
    return (
      <div style={styles.container}>
        <h2 style={styles.title}>Verify OTP</h2>
        <form onSubmit={handleOtpVerification} style={styles.form}>
          <h3>OTP sent successfully! Type you OTP here</h3>
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
          {message && <p>{message}</p>}
        </form>
      </div>
    );
  }

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

        {message && <p>{message}</p>}
      </form>

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
    border: "1px solid #ddd",
    borderRadius: "12px",
    backgroundColor: "#ffffff",
    fontFamily: "'Segoe UI', Tahoma, Geneva, Verdana, sans-serif",
    boxShadow: "0 4px 10px rgba(0, 0, 0, 0.1)",
  },
  title: {
    textAlign: "center",
    color: "#2c3e50",
    marginBottom: "20px",
    fontSize: "24px",
    fontWeight: "bold",
  },
  form: {
    display: "flex",
    flexDirection: "column",
  },
  formGroup: {
    marginBottom: "16px",
  },
  input: {
    width: "100%",
    padding: "10px",
    marginTop: "5px",
    borderRadius: "6px",
    border: "1px solid #ccc",
    fontSize: "14px",
  },
  button: {
    padding: "10px",
    backgroundColor: "#3498db",
    color: "#fff",
    border: "none",
    borderRadius: "6px",
    cursor: "pointer",
    fontSize: "16px",
    transition: "background-color 0.3s",
  },
  message: {
    marginTop: "15px",
    textAlign: "center",
    color: "red",
  },
  linkText: {
    textAlign: "center",
    marginTop: "15px",
    fontSize: "14px",
  },
  link: {
    color: "#3498db",
    textDecoration: "none",
    fontWeight: "bold",
  },
};

export default SignupForm;
