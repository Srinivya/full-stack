import React from "react";
import { useLocation } from "react-router-dom";
import Header from "./Header";


const Home = () => {
  const location = useLocation();
  const email = location.state?.email || "";

  return (
    <div>
      <Header email={email} />
    </div>
  );
};

export default Home;
