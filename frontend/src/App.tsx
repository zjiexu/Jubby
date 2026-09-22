import { useEffect, useState } from "react";
import { getHealthStatus } from "./api/healthApi";

function App() {
  const [healthStatus, setHealthStatus] = useState<string>("Checking backend...");
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    getHealthStatus()
      .then(setHealthStatus)
      .catch(() => {
        setError("Unable to connect to backend");
      });
  }, []);

  return (
    <main>
      <h1>Jubby</h1>
      <p>Job application management platform</p>

      <section>
        <h2>Backend Status</h2>
        {error ? <p>{error}</p> : <p>{healthStatus}</p>}
      </section>
    </main>
  );
}

export default App;
