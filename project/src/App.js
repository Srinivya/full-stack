import React from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import SignupForm from "./Components/SignupForm";
import OtpVerificationForm from "./Components/OtpVerificationForm";
import LoginForm from "./Components/LoginForm";

import Home from "./Components/Home";
import ProfilePage from "./Components/ProfilePage";

function App() {
  return (
    <Router>
      <div>
      
        <Routes>
          <Route path="/" element={<SignupForm />} />
          <Route path="/verify" element={<OtpVerificationForm />} />
          <Route path="/login" element={<LoginForm />} />
          <Route path="/home" element={<Home/>}/>
          <Route path="/profile-page" element={<ProfilePage/>}/>
        </Routes>
      </div>
    </Router>
  );
}

export default App;
