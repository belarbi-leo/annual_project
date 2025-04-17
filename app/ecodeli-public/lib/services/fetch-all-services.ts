import { API_BASE_URL, API_HEADERS } from "@/lib/config";

export async function fetchAllServices(type: string | null) {
  try {
    const url = type ? `${API_BASE_URL}/services/read?type=${type}` : `${API_BASE_URL}/services/read`;
    const response = await fetch(url, {
      method: "GET",
      headers: API_HEADERS,
    });

    if (!response.ok) {
      throw new Error("Erreur lors de la récupération des services");
    }

    return await response.json();
  } catch (error) {
    console.error(error);
    return [];
  }
}