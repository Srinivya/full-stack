import React, { useEffect, useState } from "react";
import Header from "./Header";

const Home = () => {
  const [email, setEmail] = useState("");

  useEffect(() => {
    const storedEmail = sessionStorage.getItem("email");
    if (storedEmail) {
      setEmail(storedEmail);
    }
  }, []);

  return (
    <div>
      <Header email={email} />
    
    </div>
  );
};

export default Home;
