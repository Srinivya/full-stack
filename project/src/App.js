import React from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import SignupForm from "./Components/SignupForm";
import OtpVerificationForm from "./Components/OtpVerificationForm";
import LoginForm from "./Components/LoginForm";
import Dashboard from "./Components/Dashboard";

function App() {
  return (
    <Router>
      <div>
        <h1>Welcome to the User Authentication App</h1>
        <Routes>
          <Route path="/" element={<SignupForm />} />
          <Route path="/verify" element={<OtpVerificationForm />} />
          <Route path="/login" element={<LoginForm />} />
          <Route path="/dashboard" element={<Dashboard />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
