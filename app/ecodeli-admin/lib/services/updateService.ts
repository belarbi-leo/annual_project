import { API_BASE_URL, API_HEADERS } from "@/lib/config";
import type { Service } from "@/lib/types";

export async function updateService(id: string, data: Partial<Service>) {
  try {
    const response = await fetch(`${API_BASE_URL}/services/update/${id}`, {
      method: "PUT",
      headers: {
        ...API_HEADERS,
        "Content-Type": "application/json",
      },
      body: JSON.stringify(data),
    });

    if (!response.ok) {
      throw new Error("Erreur lors de la mise à jour du service");
    }

    const updatedData: Service = await response.json();
    return { status: response.status, data: updatedData };
  } catch (error) {
    console.error(error);
    throw error;
  }
}