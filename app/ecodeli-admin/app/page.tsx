"use client";

import { useState } from "react";
import Image from "next/image";

export default function LoginPage() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  return (
    <div className="min-h-screen flex items-center justify-center px-6 overflow-hidden relative">
      
      <div aria-hidden="true" className="absolute top-[-10%] left-[-10%] -z-10 transform-gpu blur-3xl opacity-50">
        <div className="h-100 w-100 bg-gradient-to-tr from-[#89c8fd] to-[#60b6ff] dark:from-[#245b90] dark:to-[#1a426a] rounded-full" style={{ filter: 'blur(100px)' }}/>
      </div>
      <div aria-hidden="true" className="absolute bottom-[-10%] right-[-10%] -z-10 transform-gpu blur-3xl opacity-50">
        <div className="h-100 w-100 bg-gradient-to-tr from-[#96d629] to-[#baeb6c] dark:from-[#3f7d1c] dark:to-[#67a731] rounded-full" style={{ filter: 'blur(100px)' }}/>
      </div>

      <div className="w-full max-w-md p-8 space-y-6 rounded-2xl shadow-lg bg-gray-100 text-gray-900 dark:bg-gray-800 dark:text-white">
        <div className="flex items-center justify-center space-x-3">
          <Image src="/favicon.ico" alt="Logo EcoDeli" width={40} height={40} className="h-10 w-10"/>
        </div>
        <form className="space-y-4" onSubmit={(e) => {
          e.preventDefault();
          console.log("Email:", email, "Password:", password);
        }}>
          <div>
            <label htmlFor="email" className="block text-sm font-medium">Email</label>
            <input
              type="email"
              id="email"
              className="mt-1 block w-full px-4 py-2 border rounded-md focus:outline-[#49cb5c] bg-white border-gray-300 text-black dark:bg-gray-700 dark:border-gray-600 dark:text-white"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
            />
          </div>
          <div>
            <label htmlFor="password" className="block text-sm font-medium">Mot de passe</label>
            <input
              type="password"
              id="password"
              className="mt-1 block w-full px-4 py-2 border rounded-md focus:outline-[#49cb5c] bg-white border-gray-300 text-black dark:bg-gray-700 dark:border-gray-600 dark:text-white"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
            />
          </div>
          <button type="submit" className="w-full rounded-md bg-[#49cb5c] px-4 py-2 text-white font-semibold hover:bg-[#07b128] focus:outline-none">
            Se connecter
          </button>
        </form>
      </div>
    </div>
  );
}