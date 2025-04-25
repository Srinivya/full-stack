import React, { useState } from "react";
import axios from "axios";
import { useNavigate, Link } from "react-router-dom";

const LoginForm = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [message, setMessage] = useState("");
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();
    const data = { email, password };

    try {
      const response = await axios.post(
        `${process.env.REACT_APP_API_URL}/auth/login`,
        data
      );

      navigate("/home", { state: { email } });
    } catch (error) {
      setMessage(error.response?.data || "Login failed");
    }
  };

  return (
    <div style={styles.container}>
      <h2 style={styles.title}>Login</h2>
      <form onSubmit={handleLogin} style={styles.form}>
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
          Login
        </button>
      </form>

      {message && <p style={styles.message}>{message}</p>}

      <p style={{ marginTop: "10px" }}>
        Don't have an account? <Link to="/">SignUp here</Link>
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
    backgroundColor: "#28a745",
    color: "white",
    border: "none",
    borderRadius: "5px",
    cursor: "pointer",
  },
  message: {
    marginTop: "15px",
    color: "red",
    textAlign: "center",
  },
};

export default LoginForm;
