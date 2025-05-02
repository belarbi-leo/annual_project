import "../../globals.css";
import HeaderNotCo from "@/components/headerNotCo";

export default async function Index({ children } : { children: React.ReactNode }) {
  return (
    <main className="min-h-screen w-full overflow-x-hidden relative">
      <HeaderNotCo />
      {children}
    </main>
  );
}