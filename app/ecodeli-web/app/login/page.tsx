"use client";

import { useState } from "react";
import Link from "next/link";
import Image from "next/image";

export default function LoginPage() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  return (
    <div className="min-h-screen flex items-center justify-center px-6">
      
      <div className="w-full max-w-md p-8 space-y-6 rounded-2xl shadow-lg bg-gray-100 text-gray-900 dark:bg-gray-800 dark:text-white">
        <div className="flex items-center justify-center space-x-3">
          <Image src="/logo.png" alt="Logo" width={40} height={40} className="h-10 w-10" />
          <h2 className="text-3xl font-semibold text-[#49cb5c]">Connexion</h2>
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
          <button
            type="submit"
            className="w-full rounded-md bg-[#49cb5c] px-4 py-2 text-white font-semibold hover:bg-[#07b128] focus:outline-none"
          >
            Se connecter
          </button>
        </form>
        <p className="text-center text-sm">
          Pas encore de compte ? <Link href="#" className="text-[#49cb5c] hover:underline">Inscrivez-vous</Link>
        </p>
      </div>
    </div>
  );
}