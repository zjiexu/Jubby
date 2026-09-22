import { useEffect, useState } from "react";
import {
  createJobApplication,
  deleteJobApplication,
  getJobApplications,
} from "../api/jobApplicationsApi";
import JobApplicationForm from "../components/JobApplicationForm";
import type {
  JobApplication,
  JobApplicationRequest,
} from "../types/jobApplication";

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

  async function handleDeleteApplication(id: number) {
    const shouldDelete = window.confirm("Delete this job application?");

    if (!shouldDelete) {
      return;
    }

    try {
      await deleteJobApplication(id);
      await loadApplications();
    } catch {
      setError("Unable to delete job application");
    }
  }

  return (
    <section>
      <JobApplicationForm onSubmit={handleCreateApplication} />

      <h2>Applications</h2>

      {isLoading && <p>Loading applications...</p>}

      {error && <p>{error}</p>}

      {!isLoading && !error && applications.length === 0 && (
        <p>No job applications yet.</p>
      )}

      {!isLoading && !error && applications.length > 0 && (
        <ul>
          {applications.map((application) => (
            <li key={application.id}>
              <strong>{application.position}</strong> at {application.company}
              <br />
              <span>Status: {application.status}</span>
              <br />

              {application.location && (
                <>
                  <span>Location: {application.location}</span>
                  <br />
                </>
              )}

              <button
                type="button"
                onClick={() => handleDeleteApplication(application.id)}
              >
                Delete
              </button>
            </li>
          ))}
        </ul>
      )}
    </section>
  );
}

export default ApplicationsPage;
