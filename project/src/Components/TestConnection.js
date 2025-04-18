import { useEffect } from "react";

const TestConnection = () => {
  useEffect(() => {
    fetch("http://localhost:8088/api/test")
      .then((res) => res.text())
      .then((data) => console.log(" Backend says:", data))
      .catch((err) => console.error(" Error connecting to backend:", err));
  }, []);

  return <p>Check your console for backend response</p>;
};

export default TestConnection;
