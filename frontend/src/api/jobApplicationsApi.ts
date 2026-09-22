import type {
  JobApplication,
  JobApplicationRequest,
} from "../types/jobApplication";

const API_BASE_URL = import.meta.env.VITE_API_BASE_URL ?? "http://localhost:8080";

export async function getJobApplications(): Promise<JobApplication[]> {
  const response = await fetch(`${API_BASE_URL}/api/applications`);

  if (!response.ok) {
    throw new Error("Failed to fetch job applications");
  }

  return response.json();
}

export async function createJobApplication(
  request: JobApplicationRequest,
): Promise<JobApplication> {
  const response = await fetch(`${API_BASE_URL}/api/applications`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(request),
  });

  if (!response.ok) {
    throw new Error("Failed to create job ")
  }

  return response.json();
}

export async function updateJobApplication(
  id: number,
  request: JobApplicationRequest,
): Promise<JobApplication> {
  const response = await fetch(`${API_BASE_URL}/api/applications/${id}`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(request),
  });

  if (!response.ok) {
    throw new Error("Failed to update job application");
  }

  return response.json();
}

export async function deleteJobApplication(id: number): Promise<void> {
  const response = await fetch(`${API_BASE_URL}/api/applications/${id}`, {
    method: "DELETE",
  });

  if (!response.ok) {
    throw new Error("Failed to delete job application");
  }
}
