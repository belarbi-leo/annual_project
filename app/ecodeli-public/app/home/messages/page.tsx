"use client";

import { useState } from "react";
import Image from "next/image";
import clsx from "clsx";

// Exemple de données pour les conversations et les messages
const conversations = [
  { id: 1, name: "Jean Dupont", lastMessage: "On se voit demain ?", profilePic: "/profile1.jpg" },
  { id: 2, name: "Marie Dubois", lastMessage: "J'ai trouvé ça pour toi !", profilePic: "/profile2.jpg" },
  { id: 3, name: "Pierre Martin", lastMessage: "Ok, je te tiens au courant.", profilePic: "/profile3.jpg" },
  // Ajoute d'autres conversations ici
];

// Définition du type des messages
type Message = {
  sender: string;
  message: string;
  time: string;
};

// Type pour les messages (indexé par des nombres)
type Messages = {
  [key: number]: Message[];
};

// Exemple de messages (avec les clés 1, 2, 3)
const messages: Messages = {
  1: [
    { sender: "Jean Dupont", message: "Salut !", time: "10:30" },
    { sender: "Moi", message: "Salut, comment ça va ?", time: "10:31" },
  ],
  2: [
    { sender: "Marie Dubois", message: "Tu veux un café ?", time: "11:00" },
    { sender: "Moi", message: "Avec plaisir !", time: "11:01" },
    { sender: "Moi", message: "Du coup ?", time: "11:11" },
    { sender: "Marie Dubois", message: "Nan en fait", time: "14:00" },
    { sender: "John Cena", message: "Coup dur", time: "14:01" },
  ],
  3: [
    { sender: "Pierre Martin", message: "Des nouvelles ?", time: "12:00" },
    { sender: "Moi", message: "Pas encore, je t'appelle ce soir.", time: "12:01" },
  ],
};

export default function MessagingPage() {
  const [selectedConversation, setSelectedConversation] = useState<number | null>(null);

  return (
    <div className="flex min-h-screen">
      {/* Section des conversations à gauche */}
      <div className="w-80">
        <h2 className="text-2xl font-semibold text-gray-900 dark:text-white mb-6">Conversations</h2>
        <ul>
          {conversations.map((conversation) => (
            <li
              key={conversation.id}
              onClick={() => setSelectedConversation(conversation.id)}
              className={clsx(
                "flex items-center space-x-4 p-2 cursor-pointer rounded-lg hover:bg-gray-200 dark:hover:bg-gray-700",
                selectedConversation === conversation.id ? "bg-gray-300 dark:bg-gray-600" : ""
              )}
            >
              <Image
                src={conversation.profilePic}
                alt={conversation.name}
                width={40}
                height={40}
                className="rounded-full"
              />
              <div className="flex flex-col">
                <span className="text-gray-900 dark:text-white font-medium">{conversation.name}</span>
                <span className="text-gray-600 dark:text-gray-400 text-sm">{conversation.lastMessage}</span>
              </div>
            </li>
          ))}
        </ul>
      </div>

      {/* Séparateur */}
      <div className="border-l ml-2 border-gray-300 dark:border-gray-600"></div>

      {/* Section des messages à droite */}
      <div className="flex-1 p-6">
        {selectedConversation ? (
          <>
            <h2 className="text-2xl font-semibold text-gray-900 dark:text-white mb-6">
              Conversation avec {conversations.find((conv) => conv.id === selectedConversation)?.name}
            </h2>

            <div className="space-y-4">
              {messages[selectedConversation]?.map((msg, index) => (
                <div key={index} className={clsx("flex", msg.sender === "Moi" ? "justify-end" : "justify-start")}>
                  <div
                    className={clsx(
                      "max-w-xs p-3 rounded-lg",
                      msg.sender === "Moi"
                        ? "bg-blue-500 text-white"
                        : "bg-gray-200 text-gray-800 dark:bg-gray-700 dark:text-white"
                    )}
                  >
                    <span className="text-xs text-gray-500 dark:text-gray-300">{msg.time}</span>
                    <p>{msg.message}</p>
                  </div>
                </div>
              ))}
            </div>
          </>
        ) : (
          <div className="text-center text-gray-500 dark:text-gray-400">
            <p>Sélectionne une conversation pour voir les messages.</p>
          </div>
        )}
      </div>
    </div>
  );
}