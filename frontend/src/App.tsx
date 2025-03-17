import React from "react";
import LogStream from "./components/LogStream.tsx";

const App: React.FC = () => {
  return (
    <div>
      <h1>Kafka Log Stream</h1>
      <LogStream />
    </div>
  );
};

export default App;
