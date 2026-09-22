import { useState } from "react";
import type { ApplicationStatus, JobApplicationRequest } from "../types/jobApplication";

interface JobApplicationFormProps {
  onSubmit: (request: JobApplicationRequest) => Promise<void>;
}

const statusOptions: ApplicationStatus[] = [
  "SAVED",
  "APPLIED",
  "INTERVIEW",
  "OFFER",
  "REJECTED",
];

function JobApplicationForm({ onSubmit }: JobApplicationFormProps) {
  const [company, setCompany] = useState("");
  const [position, setPosition] = useState("");
  const [location, setLocation] = useState("");
  const [salary, setSalary] = useState("");
  const [jobUrl, setJobUrl] = useState("");
  const [status, setStatus] = useState<ApplicationStatus>("SAVED");
  const [applicationDate, setApplicationDate] = useState("");
  const [notes, setNotes] = useState("");
  const [isSubmitting, setIsSubmitting] = useState(false);
  const [error, setError] = useState<string | null>(null);

  async function handleSubmit(event: React.FormEvent<HTMLFormElement>) {
    event.preventDefault();
    setError(null);
    setIsSubmitting(true);

    try{
      await onSubmit({
        company,
        position,
        location: location || undefined,
        salary: salary || undefined,
        jobUrl: jobUrl || undefined,
        status,
        applicationDate: applicationDate || undefined,
        notes: notes || undefined,
      });

      setCompany("");
      setPosition("");
      setLocation("");
      setSalary("");
      setJobUrl("");
      setStatus("SAVED");
      setApplicationDate("");
      setNotes("");
    } catch {
      setError("Unable to create job application");
    } finally {
      setIsSubmitting(false);
    }
  }

  return (
    <form onSubmit={handleSubmit}>
      <h2>Add Application</h2>

      {error && <p>{error}</p>}

      <div>
        <label htmlFor="company">Company</label>
        <input
          id="company"
          value={company}
          onChange={(event) => setCompany(event.target.value)}
          required
        />
      </div>

      <div>
        <label htmlFor="position">Position</label>
        <input
          id="position"
          value={position}
          onChange={(event) => setPosition(event.target.value)}
          required
        />
      </div>

      <div>
        <label htmlFor="location">Location</label>
        <input
          id="location"
          value={location}
          onChange={(event) => setLocation(event.target.value)}
        />
      </div>

      <div>
        <label htmlFor="salary">Salary</label>
        <input
          id="salary"
          value={salary}
          onChange={(event) => setSalary(event.target.value)}
        />
      </div>

      <div>
        <label htmlFor="jobUrl">Job URL</label>
        <input
          id="jobUrl"
          value={jobUrl}
          onChange={(event) => setJobUrl(event.target.value)}
        />
      </div>

      <div>
        <label htmlFor="status">Status</label>
        <select
          id="status"
          value={status}
          onChange={(event) => setStatus(event.target.value as ApplicationStatus)}
        >
          {statusOptions.map((statusOption) => (
            <option key={statusOption} value={statusOption}>
              {statusOption}
            </option>
          ))}
        </select>
      </div>

      <div>
        <label htmlFor="applicationDate">Application Date</label>
        <input
          id="applicationDate"
          type="date"
          value={applicationDate}
          onChange={(event) => setApplicationDate(event.target.value)}
        />
      </div>

      <div>
        <label htmlFor="notes">Notes</label>
        <textarea
          id="notes"
          value={notes}
          onChange={(event) => setNotes(event.target.value)}
        />
      </div>

      <button type="submit" disabled={isSubmitting}>
        {isSubmitting ? "Saving..." : "Add Application"}
      </button>
    </form>
  );
}

export default JobApplicationForm;
