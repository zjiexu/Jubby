import { useEffect, useState } from "react";
import {
  createJobApplication,
  getJobApplications,
} from "../api/jobApplicationsApi";
import JobApplicationForm from "../components/JobApplicationForm";
import type { JobApplication, JobApplicationRequest } from "../types/jobApplication";

function ApplicationsPage() {
  const [applications, setApplications] = useState<JobApplication[]>([]);
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    loadApplications();
  }, []);

  async function loadApplications() {
    setIsLoading(true);
    setError(null);

    try {
      const data = await getJobApplications();
      setApplications(data);
    } catch {
      setError("Unable to load job applications");
    } finally {
      setIsLoading(false);
    }
  }

  async function handleCreateApplication(request: JobApplicationRequest) {
    await createJobApplication(request);
    await loadApplications();
  }

  if (isLoading) {
    return <p>Loading applications...</p>
  }

  if (error) {
    return <p>{error}</p>
  }

  <JobApplicationForm onSubmit={handleCreateApplication} />

  return (
    <section>
      <JobApplicationForm onSubmit={handleCreateApplication} />

      <h2>Applications</h2>

      {applications.length === 0 ? (
        <p>No job applications yet.</p>
      ) : (
        <ul>
          ...
        </ul>
      )}
    </section>
  );
}

export default ApplicationsPage;
