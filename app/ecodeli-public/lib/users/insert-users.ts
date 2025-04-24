import { API_BASE_URL, API_HEADERS } from "@/lib/config";

export async function insertUser(data: FormData): Promise<{ status: number; data: any }> {
  try {
    const res = await fetch(`${API_BASE_URL}/users/create`, {
      method: "POST",
      headers: {
        // Attention : NE PAS mettre "Content-Type" ici, sinon ça casse le boundary de FormData
        ...API_HEADERS,
      },
      body: data,
    });

    const resData = await res.json();

    if (!res.ok) {
      throw new Error("Erreur lors de la création de l'utilisateur");
    }

    return { status: res.status, data: resData };
  } catch (error) {
    console.error(error);
    return { status: 500, data: null };
  }
}
