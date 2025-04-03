import { API_BASE_URL, API_HEADERS } from "@/lib/config";

export async function fetchUserByID(userID: string) {
  try {
    const response = await fetch(`${API_BASE_URL}/users/read/${userID}`, {
      method: "GET",
      headers: API_HEADERS,
    });

    if (!response.ok) {
      throw new Error("Erreur lors de la récupération de l'utilisateur");
    }

    return await response.json();
  } catch (error) {
    console.error(error);
    return null;
  }
}