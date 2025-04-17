import { API_BASE_URL, API_HEADERS } from "@/lib/config";
import type { Service } from "@/lib/types";

export async function fetchAllServices(): Promise<Service[]> {
  try {
    const response = await fetch(`${API_BASE_URL}/services/read`, {
      method: "GET",
      headers: API_HEADERS,
    });

    if (!response.ok) {
      throw new Error("Erreur lors de la récupération des services");
    }

    const data: Service[] = await response.json();
    return data;
  } catch (error) {
    console.error(error);
    return [];
  }
}