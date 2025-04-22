import React, { useState, useEffect, useRef } from "react";
import axios from "axios";
import "./ProfilPage.css";

const ProfilePage = () => {
  const [email, setEmail] = useState("");
  const [name, setName] = useState("");
  const [address, setAddress] = useState("");
  const [image, setImage] = useState(null);
  const [previewUrl, setPreviewUrl] = useState(null);
  const [isLoading, setIsLoading] = useState(true);
  const [errorMessage, setErrorMessage] = useState("");
  const fileInputRef = useRef(null);

  useEffect(() => {
    // Get email from the JWT token stored in localStorage
    const token = localStorage.getItem("token");
    if (!token) {
      setErrorMessage("User not authenticated.");
      setIsLoading(false);
      return;
    }

    const fetchProfile = async () => {
      try {
        // Make API call to fetch the user profile
        const response = await axios.get("http://localhost:8070/user/profile", {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        });

        setEmail(response.data.email);
        setName(response.data.name);
        setAddress(response.data.address);
        if (response.data.imageUrl) {
          setPreviewUrl(
            `http://localhost:8070/images/${response.data.imageUrl}`
          );
        }
        setIsLoading(false);
      } catch (error) {
        setErrorMessage("Failed to load profile.");
        setIsLoading(false);
      }
    };

    fetchProfile();
  }, []);

  const handleImageChange = (e) => {
    const file = e.target.files[0];

    if (file && (file.type === "image/jpeg" || file.type === "image/png")) {
      setImage(file);
      setPreviewUrl(URL.createObjectURL(file));
    } else {
      alert("Please upload a valid image file (JPG, PNG).");
      setImage(null);
      setPreviewUrl(null);
    }
  };

  const handleButtonClick = () => {
    fileInputRef.current.click();
  };

  const handleSave = () => {
    const formData = new FormData();
    formData.append("email", email);
    formData.append("name", name);
    formData.append("address", address);
    if (image) {
      formData.append("image", image);
    }

    axios
      .put("http://localhost:8070/user/update", formData, {
        headers: {
          Authorization: `Bearer ${localStorage.getItem("token")}`,
          "Content-Type": "multipart/form-data",
        },
      })
      .then((response) => {
        alert("Profile updated successfully!");
      })
      .catch((error) => {
        alert("Failed to update profile");
      });
  };

  return (
    <div className="profile-container">
      {isLoading ? (
        <p>Loading...</p>
      ) : errorMessage ? (
        <p>{errorMessage}</p>
      ) : (
        <div className="profile-content">
          <div className="profile-header">
            <div className="profile-email-container">
              <span className="profile-email">{email}</span>
            </div>
            <div className="profile-image">
              {previewUrl ? (
                <img
                  src={previewUrl}
                  alt="Profile"
                  width="100"
                  height="100"
                  style={{ borderRadius: "50%" }}
                />
              ) : (
                <div className="default-avatar">No Image</div>
              )}
              <button type="button" onClick={handleButtonClick}>
                Upload Image
              </button>
              <input
                ref={fileInputRef}
                type="file"
                accept="image/jpeg, image/png"
                onChange={handleImageChange}
                style={{ display: "none" }}
              />
            </div>
          </div>

          <div className="profile-info">
            <div className="profile-field">
              <label htmlFor="name">Name:</label>
              <input
                type="text"
                id="name"
                value={name}
                onChange={(e) => setName(e.target.value)}
                placeholder="Enter your name"
              />
            </div>
            <div className="profile-field">
              <label htmlFor="address">Address:</label>
              <input
                type="text"
                id="address"
                value={address}
                onChange={(e) => setAddress(e.target.value)}
                placeholder="Enter your address"
              />
            </div>
            <button onClick={handleSave}>Save</button>
          </div>
        </div>
      )}
    </div>
  );
};

export default ProfilePage;
