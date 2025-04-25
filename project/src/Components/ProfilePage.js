import React, { useState, useEffect } from "react";
import axios from "axios";
import Header from "./Header";
import "./ProfilPage.css";

const ProfilePage = () => {
  const [user, setUser] = useState({ name: "", address: "", phone: "" });
  const email = sessionStorage.getItem("email");

  useEffect(() => {
    if (email) {
      axios
        .get(`http://localhost:8070/auth/getProfile?email=${email}`)
        .then((res) => {
          setUser(res.data);
        })
        .catch((err) => {
          console.error("Failed to load profile", err);
        });
    }
  }, [email]);

  const handleChange = (e) => {
    setUser({ ...user, [e.target.name]: e.target.value });
  };

  const handleUpdate = () => {
    axios
      .post("http://localhost:8070/auth/updateProfile", null, {
        params: {
          email: email,
          name: user.name,
          address: user.address,
          phone: user.phone,
        },
      })
      .then((res) => {
        alert(res.data);
      })
      .catch((err) => {
        alert("Failed to update profile");
        console.error(err);
      });
  };

  if (!email) return <p>Please log in to view your profile.</p>;

  return (
    <div style={{ padding: "20px" }}>
      <Header email={email} />
      <div className="profile-container">
        <h2>Profile</h2>
        <label>Name:</label>
        <input
          type="text"
          name="name"
          value={user.name}
          onChange={handleChange}
        />
        <br />
        <label>Address:</label>
        <input
          type="text"
          name="address"
          value={user.address}
          onChange={handleChange}
        />
        <br />
        <label>Phone:</label>
        <input
          type="text"
          name="phone"
          value={user.phone}
          onChange={handleChange}
        />
        <br />
        <button onClick={handleUpdate}>Update Profile</button>
      </div>
    </div>
  );
};

export default ProfilePage;
