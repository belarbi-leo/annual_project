import { API_BASE_URL, API_HEADERS } from "@/lib/config";
import type { Service } from '@/lib/types';
export async function fetchServicesByAuth(auth: "pro" | "all"): Promise<Service[]> {

  try {
    const res = await fetch(`${API_BASE_URL}/services/read`, {
      method: "GET",
      headers: API_HEADERS,
    });

    if (!res.ok) {
      throw new Error("Erreur lors de la récupération des services");
    }

    const allServices: Service[] = await res.json();
    return allServices.filter(service => service.auth === auth);

  } catch (error) {
    console.error(error);
    return [];
  }
}