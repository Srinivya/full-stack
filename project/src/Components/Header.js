import React, { useState, useEffect } from "react";
import logo from "../Assets/logo.png";
import { useNavigate } from "react-router-dom";
import "./Header.css";

const Header = ({ email }) => {
  const [showMenu, setShowMenu] = useState(false);
  const [userEmail, setUserEmail] = useState(email);
  const navigate = useNavigate();

  useEffect(() => {
    const storedEmail = sessionStorage.getItem("email");
    if (storedEmail && storedEmail !== userEmail) {
      setUserEmail(storedEmail);
    }
  }, [email, userEmail]);

  const firstLetter = userEmail?.charAt(0).toUpperCase() || "";

  const handleLogout = () => {
    sessionStorage.clear();
    navigate("/");
  };

  return (
    <header className="header-container">
      <div className="logo-section">
        <img src={logo} alt="GeekBase Logo" className="logo-image" />
        <div className="logo-text">
          <h1>GeekBase</h1>
          <p>Code Your Success Story</p>
        </div>
      </div>

      <div className="avatar-wrapper">
        <div
          className="user-avatar"
          onClick={() => setShowMenu((prev) => !prev)}
        >
          {firstLetter}
        </div>
        {showMenu && (
          <div className="dropdown-menu">
            <p onClick={() => navigate("/profile-page")}>View Profile</p>
            <p onClick={() => navigate("/change-password")}>Change Password</p>
            <p onClick={handleLogout}>Logout</p>
          </div>
        )}
      </div>
    </header>
  );
};

export default Header;
