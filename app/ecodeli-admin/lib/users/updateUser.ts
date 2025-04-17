import { API_BASE_URL, API_HEADERS } from "@/lib/config";
import type { User } from "@/lib/types";

export async function updateUser(id: string, data: Partial<User>): Promise<{ status: number; data: any }> {
  try {
    const response = await fetch(`${API_BASE_URL}/users/update/${id}`, {
      method: "PUT",
      headers: {
        ...API_HEADERS,
        "Content-Type": "application/json",
      },
      body: JSON.stringify(data),
    });

    const responseData = await response.json();

    if (!response.ok) {
      throw new Error("Erreur lors de la mise à jour de l'utilisateur");
    }

    return { status: response.status, data: responseData };
  } catch (error) {
    console.error(error);
    return { status: 500, data: null };
  }
}