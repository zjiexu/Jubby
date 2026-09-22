export type ApplicationStatus =
  | "SAVED"
  | "APPLIED"
  | "INTERVIEW"
  | "OFFER"
  | "REJECTED";

export interface JobApplication {
  id: number;
  company: string;
  position: string;
  location: string | null;
  salary: string | null;
  jobUrl: string | null;
  status: ApplicationStatus;
  applicationDate: string | null;
  notes: string | null;
  createdAt: string;
  updatedAt: string;
}

export interface JobApplicationRequest {
  company: string;
  position: string;
  location?: string;
  salary?: string;
  jobUrl?: string;
  status: ApplicationStatus;
  applicationDate?: string;
  notes?: string;
}
