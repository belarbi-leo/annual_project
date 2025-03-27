export async function fetchUsers() {
    try {
      const response = await fetch("https://api.example.com/users"); // Remplace par ton URL API
      if (!response.ok) {
        throw new Error("Failed to fetch users");
      }
      return await response.json();
    } catch (error) {
      console.error("Error fetching users:", error);
      return [];
    }
}