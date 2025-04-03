import { API_BASE_URL, API_HEADERS } from "@/lib/config";

export async function fetchUsers(type: string | null) {
  try {
    const url = type ? `${API_BASE_URL}/users/read?type=${type}` : `${API_BASE_URL}/users/read`;
    const response = await fetch(url, {
      method: "GET",
      headers: API_HEADERS,
    });

    if (!response.ok) {
      throw new Error("Erreur lors de la récupération des utilisateurs");
    }

    return await response.json();
  } catch (error) {
    console.error(error);
    return [];
  }
}