import { API_BASE_URL, API_HEADERS } from "@/lib/config";
import type { Users } from '@/lib/types';

export async function insertUser(data: Users): Promise<{ status: number; data: any; error?: string }> {
  try {
    const res = await fetch(`${API_BASE_URL}/users/create`, {
      method: "POST",
      headers: API_HEADERS,
      body: JSON.stringify(data),
    });
    const res_data = await res.json();
    return { status: res.status, data: res_data };
  } catch (error) {
    return { status: 500, data: [] , error: 'Error server'};
  }
}