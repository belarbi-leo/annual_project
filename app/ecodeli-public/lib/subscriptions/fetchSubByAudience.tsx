import { API_BASE_URL, API_HEADERS } from "@/lib/config";
import type { Subscriptions } from '@/lib/types';

export async function fetchAllSubscriptions(): Promise<{ status: number; data: Subscriptions[]; error?: string }> {
    try {
        const res = await fetch(`${API_BASE_URL}/subscriptions/read`, {
            method: "GET",
            headers: API_HEADERS,
        });
        const res_data = await res.json();
        return { status: res.status, data: res_data.content };
    } catch (error) {
        return { status: 500, data: [] , error: 'Error server'};
    }
}