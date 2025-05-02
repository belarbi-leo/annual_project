"use client";

import "../../globals.css";
import { useState, useEffect } from "react";
import Sidebar from "@/components/sidebar";
import HeaderCo from "@/components/headerCo";

export default function Home({ children } : { children: React.ReactNode }) {
  const [isCollapsed, setIsCollapsed] = useState<boolean | null>(null);
  const [isSidebarVisible, setIsSidebarVisible] = useState<boolean>(false);
  const [isReady, setIsReady] = useState<boolean>(false);


  useEffect(() => {
    setIsReady(true);
    const handleResize = () => setIsCollapsed(window.innerWidth < 768);
    handleResize();
    window.addEventListener("resize", handleResize);
    return () => window.removeEventListener("resize", handleResize);
  }, []);

  if (!isReady) {
    return null;
  }

  return (
    <div className="flex min-h-screen bg-gray-100 dark:bg-gray-900">
        <Sidebar isMobile={false} />
        <Sidebar isMobile={true} isVisible={isSidebarVisible} onClose={() => setIsSidebarVisible(false)} />
        <div className="flex-1 flex flex-col">
          <HeaderCo onToggleSidebar={() => setIsSidebarVisible(true)} />
          <main className="flex-1 p-6">
            {children}
          </main>
        </div>
    </div>
  );
}