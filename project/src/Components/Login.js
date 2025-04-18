import React, { useState } from "react";
import axios from "axios";
import { useNavigate, Link } from "react-router-dom";

const Login = () => {
  const navigate = useNavigate();
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const res = await axios.post("http://localhost:8088/api/login", {
        email,
        password,
      });

      if (res.data.token) {
        localStorage.setItem("authToken", res.data.token);
        alert("Login successful!");
        navigate("/dashboard");
      }
    } catch (err) {
      setError("Invalid email or password.");
      console.error(err);
    }
  };

  return (
    <div style={styles.container}>
      <h2>Login</h2>
      <p style={styles.error}>{error}</p>
      <form onSubmit={handleSubmit} style={styles.form}>
        <div>
          <label>Email: </label>
          <input
            type="email"
            required
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
        </div>
        <div>
          <label>Password: </label>
          <input
            type="password"
            required
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
        </div>
        <button type="submit">Login</button>
      </form>
      <p>
        Don't have an account? <Link to="/signup">Sign Up here</Link>
      </p>
    </div>
  );
};

const styles = {
  container: { padding: 20, textAlign: "center" },
  form: {
    display: "flex",
    flexDirection: "column",
    gap: 10,
    maxWidth: 300,
    margin: "0 auto",
  },
  error: {
    color: "red",
    marginBottom: "10px",
  },
};

export default Login;
