import { API_BASE_URL, API_HEADERS } from "@/lib/config";
import type { Languages } from '@/lib/types';

export async function fetchAllLanguages(): Promise<{ status: number; data: Languages[]; error?: string }> {
  try {
    const res = await fetch(`${API_BASE_URL}/languages/read`, {
      method: "GET",
      headers: API_HEADERS,
    });

    if (!res.ok) {
      throw new Error("Erreur lors de la récupération des langues");
    }

    return await res.json();
  } catch (error) {
    console.error(error);
    return []; 
  }
}