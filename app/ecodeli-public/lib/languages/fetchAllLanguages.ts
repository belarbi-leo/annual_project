import { API_BASE_URL, API_HEADERS } from "@/lib/config";
import type { Languages } from '@/lib/types';

export async function fetchAllLanguages(): Promise<{ status: number; data: Languages[]; error?: string }> {
  try {
    const res = await fetch(`${API_BASE_URL}/languages/read`, {
      method: "GET",
      headers: API_HEADERS,
    });
    const res_data = await res.json();
    return { status: res.status, data: res_data.content };
  } catch (error) {
    return { status: 500, data: [] , error: 'Error server'};
  }
}
