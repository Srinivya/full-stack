import "./App.css";
import Login from "./Components/Login";
import OTPVerification from "./Components/OTPVerification";
import SignUp from "./Components/SignUp";
import DashBoard from "./Components/DashBoard";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/signup" element={<SignUp />} />
        <Route path="/verify-otp" element={<OTPVerification />} />
        <Route path="/dashboard" element={<DashBoard />} />
       
      </Routes>
    </Router>
  );
}

export default App;
