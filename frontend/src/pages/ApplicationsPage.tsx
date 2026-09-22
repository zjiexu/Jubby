import { useEffect, useState } from "react";
import { getJobApplications } from "../api/jobApplicationsApi";
import type { JobApplication } from "../types/jobApplication";

function ApplicationsPage() {
  const [applications, setApplications] = useState<JobApplication[]>([]);
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    getJobApplications()
      .then((data) => {
        setApplications(data);
      })
      .catch(() => {
        setError("Unable to load job applications");
      })
      .finally(() => {
        setIsLoading(false);
      });
  }, []);

  if (isLoading) {
    return <p>Loading applications...</p>
  }

  if (error) {
    return <p>{error}</p>
  }

  return (
    <section>
      <h2>Applications</h2>

      {applications.length === 0 ? (
        <p>No job applications yet.</p>
      ) : (
        <ul>
          {applications.map((application) => (
            <li key={application.id}>
              <strong>{application.position}</strong> at {application.company}
              <br />
              <span>Status: {application.status}</span>
              <br />
              {application.location && <span>Location: {application.location}</span>}
            </li>
          ))}
        </ul>
      )}
    </section>
  );
}

export default ApplicationsPage;
