import { API_BASE_URL, API_HEADERS } from "@/lib/config";
import type { Service } from "@/lib/types";

export async function fetchServiceByID(id: string): Promise<Service | null> {
  try {
    const response = await fetch(`${API_BASE_URL}/services/read/${id}`, {
      method: "GET",
      headers: API_HEADERS,
    });

    if (!response.ok) {
      throw new Error("Erreur lors de la récupération du service");
    }

    const data: Service = await response.json();
    return data;
  } catch (error) {
    console.error(error);
    return null;
  }
}