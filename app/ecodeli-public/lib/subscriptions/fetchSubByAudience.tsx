
import { API_BASE_URL, API_HEADERS } from "@/lib/config";
import type { Subscriptions } from '@/lib/types';
import { notFound } from "next/navigation";

export async function fetchAllSubscriptions(): Promise<{ status: number; data: Subscriptions[]; error?: string }> {
    try {
        const res = await fetch(`${API_BASE_URL}/subscriptions/read`, {
            method: "GET",
            headers: API_HEADERS,
        });
        if (!res.ok) notFound();
        const res_data = await res.json();
        return { status: res.status, data: res_data };
    } catch (error) {
        notFound();
    }
}