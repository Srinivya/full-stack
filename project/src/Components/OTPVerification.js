import React, { useState } from "react";
import { useLocation } from "react-router-dom";

const OTPVerification = () => {
  const location = useLocation();
  const email = location.state?.email || "";
  const [otp, setOtp] = useState("");
  const [loading, setLoading] = useState(false);

  const handleVerify = async () => {
    if (!otp || otp.length !== 6) {
      alert("Please enter a valid 6-digit OTP.");
      return;
    }

    setLoading(true);

    try {
      const response = await fetch(
        `http://localhost:8088/api/validateOtp?email=${email}&otp=${otp}`,
        {
          method: "POST",
        }
      );
      const result = await response.text();

      if (result.includes("OTP validated successfully")) {
        alert("OTP validated successfully!");
        setOtp(""); // Clear OTP after success
      } else {
        alert("Invalid OTP. Please try again.");
      }
    } catch (error) {
      console.error("Error verifying OTP:", error);
      alert("An error occurred. Please try again.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div>
      <h2>Verify OTP for {email}</h2>
      <input
        type="number"
        placeholder="Enter OTP"
        value={otp}
        onChange={(e) => setOtp(e.target.value)}
        maxLength="6"
      />
      <button onClick={handleVerify} disabled={loading}>
        {loading ? "Verifying..." : "Verify OTP"}
      </button>
    </div>
  );
};

export default OTPVerification;
