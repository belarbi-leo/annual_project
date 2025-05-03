"use client";
import React, { useState } from "react";
import { 
  SettingsIcon, 
  ArrowDownAZIcon, 
  TrashIcon, 
  KeyIcon, 
  PencilIcon, 
  BellIcon, 
  SparklesIcon, 
  PackageIcon,
  TruckIcon,
  GroupIcon,
  HandIcon,
  FileTextIcon
} from "lucide-react";
 // ajoute changement subscriptions
export default function AccountPage() {
  const [activeTab, setActiveTab] = useState("dashboard");
  const [username, setUsername] = useState("Sophie Martin");
  const [memberType, setMemberType] = useState("standard"); // standard ou habilitée
  const [notifications, setNotifications] = useState({
    email: true,
    push: true,
    sms: false
  });
  
  // Statistiques utilisateur
  const stats = {
    delivered: 24,
    deliveredIfEnabled: 0,
    servicesRequested: 17,
    servicesProvidedIfEnabled: 0
  };
  
  // Données pour les demandes d'habilitation
  const [enablementRequests, setEnablementRequests] = useState([
    { id: 1, type: "Livreur", status: "En attente", date: "15/04/2025" },
    { id: 2, type: "Prestataire", status: "Refusée", date: "02/03/2025" }
  ]);

  return (
    <div className="min-h-screen bg-gray-50 dark:bg-gray-900 p-4 md:p-6">
      <div className="max-w-6xl mx-auto">
        {/* En-tête du profil */}
        <div className="bg-white dark:bg-gray-800 rounded-xl shadow p-6 mb-6">
          <div className="flex flex-col md:flex-row items-center justify-between gap-4">
            <div className="flex flex-col md:flex-row items-center gap-4">
              <div className="relative">
                <img 
                  src="/api/placeholder/120/120" 
                  alt="Photo de profil" 
                  className="w-24 h-24 rounded-full object-cover border-4 border-gray-200 dark:border-gray-700"
                />
                <button className="absolute bottom-0 right-0 bg-blue-600 rounded-full p-2 text-white hover:bg-blue-700 transition">
                  <PencilIcon size={16} />
                </button>
              </div>
              
              <div className="text-center md:text-left">
                <h1 className="text-xl md:text-2xl font-bold text-gray-900 dark:text-white">
                  {username}
                </h1>
                <div className="flex items-center justify-center md:justify-start mt-2">
                  <span className={`inline-flex items-center px-3 py-1 rounded-full text-sm font-medium ${
                    memberType === "habilitée" ? "bg-emerald-100 text-emerald-800" : "bg-blue-100 text-blue-800"
                  }`}>
                    {memberType === "habilitée" ? (
                      <><SparklesIcon size={16} className="mr-1" /> Utilisateur habilité</>
                    ) : (
                      "Compte standard"
                    )}
                  </span>
                </div>
                <p className="text-gray-500 dark:text-gray-400 mt-2">
                  Membre depuis le 10/01/2025
                </p>
              </div>
            </div>
            
            <div className="flex gap-2">
              <button className="px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700 transition flex items-center gap-2">
                <PencilIcon size={18} />
                <span>Modifier le profil</span>
              </button>
            </div>
          </div>
        </div>
        
        {/* Navigation des onglets */}
        <div className="bg-white dark:bg-gray-800 rounded-xl shadow mb-6 overflow-x-auto">
          <div className="flex min-w-max">
            <TabButton 
              active={activeTab === "dashboard"} 
              onClick={() => setActiveTab("dashboard")}
              icon={<SettingsIcon size={20} />}
              label="Tableau de bord" 
            />
            <TabButton 
              active={activeTab === "stats"} 
              onClick={() => setActiveTab("stats")}
              icon={<PackageIcon size={20} />}
              label="Statistiques" 
            />
            <TabButton 
              active={activeTab === "enablement"} 
              onClick={() => setActiveTab("enablement")}
              icon={<GroupIcon size={20} />}
              label="Habilitations" 
            />
            <TabButton 
              active={activeTab === "notifications"} 
              onClick={() => setActiveTab("notifications")}
              icon={<BellIcon size={20} />}
              label="Notifications" 
            />
            <TabButton 
              active={activeTab === "legal"} 
              onClick={() => setActiveTab("legal")}
              icon={<FileTextIcon size={20} />}
              label="Documents légaux" 
            />
          </div>
        </div>
        
        {/* Contenu principal */}
        <div className="bg-white dark:bg-gray-800 rounded-xl shadow p-6">
          {/* Dashboard */}
          {activeTab === "dashboard" && (
            <div className="space-y-6">
              <div className="border-b pb-6">
                <h2 className="text-xl font-semibold mb-4 text-gray-900 dark:text-white">
                  Gérer votre compte
                </h2>
                <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                  <ActionCard 
                    icon={<PencilIcon size={24} />}
                    title="Modifier les informations personnelles"
                    description="Mettez à jour vos coordonnées et informations de profil"
                  />
                  <ActionCard 
                    icon={<KeyIcon size={24} />}
                    title="Changer de mot de passe"
                    description="Mettez à jour vos identifiants de connexion"
                  />
                  <ActionCard 
                    icon={<ArrowDownAZIcon size={24} />}
                    title="Exporter l'historique des paiements"
                    description="Téléchargez une liste complète de vos transactions"
                  />
                  <ActionCard 
                    icon={<ArrowDownAZIcon size={24} />}
                    title="Exporter vos données personnelles"
                    description="Téléchargez toutes les données liées à votre compte"
                  />
                </div>
              </div>
              
              <div>
                <h2 className="text-xl font-semibold mb-4 text-gray-900 dark:text-white">
                  Danger
                </h2>
                <div>
                  <ActionCard 
                    icon={<TrashIcon size={24} className="text-red-500" />}
                    title="Supprimer votre compte"
                    description="Cette action est irréversible et supprimera définitivement toutes vos données"
                    danger
                  />
                </div>
              </div>
            </div>
          )}
          
          {/* Statistiques */}
          {activeTab === "stats" && (
            <div>
              <h2 className="text-xl font-semibold mb-6 text-gray-900 dark:text-white">
                Statistiques du compte
              </h2>
              <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-6">
                <StatCard 
                  icon={<PackageIcon />}
                  title="Colis expédiés" 
                  value={stats.delivered} 
                  bgColor="bg-blue-50 dark:bg-blue-900/20" 
                  textColor="text-blue-700 dark:text-blue-300"
                />
                {memberType === "habilitée" && (
                  <StatCard 
                    icon={<TruckIcon />}
                    title="Colis livrés" 
                    value={stats.deliveredIfEnabled} 
                    bgColor="bg-emerald-50 dark:bg-emerald-900/20" 
                    textColor="text-emerald-700 dark:text-emerald-300"
                  />
                )}
                <StatCard 
                  icon={<HandIcon />}
                  title="Prestations demandées" 
                  value={stats.servicesRequested} 
                  bgColor="bg-purple-50 dark:bg-purple-900/20" 
                  textColor="text-purple-700 dark:text-purple-300"
                />
                {memberType === "habilitée" && (
                  <StatCard 
                    icon={<GroupIcon />}
                    title="Prestations réalisées" 
                    value={stats.servicesProvidedIfEnabled} 
                    bgColor="bg-amber-50 dark:bg-amber-900/20" 
                    textColor="text-amber-700 dark:text-amber-300"
                  />
                )}
              </div>
            </div>
          )}
          
          {/* Habilitations */}
          {activeTab === "enablement" && (
            <div>
              <div className="flex justify-between items-center mb-6">
                <h2 className="text-xl font-semibold text-gray-900 dark:text-white">
                  Demandes d'habilitation
                </h2>
                <button className="px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700 transition">
                  Nouvelle demande
                </button>
              </div>
              
              {enablementRequests.length > 0 ? (
                <div className="overflow-x-auto">
                  <table className="min-w-full divide-y divide-gray-200 dark:divide-gray-700">
                    <thead>
                      <tr>
                        <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 dark:text-gray-400 uppercase tracking-wider">
                          Type
                        </th>
                        <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 dark:text-gray-400 uppercase tracking-wider">
                          Date
                        </th>
                        <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 dark:text-gray-400 uppercase tracking-wider">
                          Statut
                        </th>
                        <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 dark:text-gray-400 uppercase tracking-wider">
                          Actions
                        </th>
                      </tr>
                    </thead>
                    <tbody className="divide-y divide-gray-200 dark:divide-gray-700">
                      {enablementRequests.map((request) => (
                        <tr key={request.id}>
                          <td className="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900 dark:text-white">
                            {request.type}
                          </td>
                          <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500 dark:text-gray-400">
                            {request.date}
                          </td>
                          <td className="px-6 py-4 whitespace-nowrap">
                            <span className={`inline-flex px-2 py-1 text-xs font-semibold rounded-full 
                              ${request.status === "En attente" ? "bg-yellow-100 text-yellow-800" : 
                                request.status === "Approuvée" ? "bg-green-100 text-green-800" :
                                "bg-red-100 text-red-800"}`}>
                              {request.status}
                            </span>
                          </td>
                          <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500 dark:text-gray-400">
                            <button className="text-blue-600 hover:text-blue-800 dark:text-blue-400 dark:hover:text-blue-300">
                              Détails
                            </button>
                          </td>
                        </tr>
                      ))}
                    </tbody>
                  </table>
                </div>
              ) : (
                <div className="text-center py-12 text-gray-500 dark:text-gray-400">
                  Vous n'avez aucune demande d'habilitation en cours.
                </div>
              )}
            </div>
          )}
          
          {/* Notifications */}
          {activeTab === "notifications" && (
            <div>
              <h2 className="text-xl font-semibold mb-6 text-gray-900 dark:text-white">
                Préférences de notifications
              </h2>
              
              <div className="space-y-6">
                <NotificationSetting
                  title="Notifications par email"
                  description="Recevez des mises à jour concernant vos commandes et votre compte par email"
                  checked={notifications.email}
                  onChange={() => setNotifications({...notifications, email: !notifications.email})}
                />
                
                <NotificationSetting
                  title="Notifications push"
                  description="Recevez des alertes importantes directement sur votre appareil"
                  checked={notifications.push}
                  onChange={() => setNotifications({...notifications, push: !notifications.push})}
                />
                
                <NotificationSetting
                  title="Notifications SMS"
                  description="Recevez des mises à jour par SMS sur votre téléphone mobile"
                  checked={notifications.sms}
                  onChange={() => setNotifications({...notifications, sms: !notifications.sms})}
                />
              </div>
              
              <div className="mt-8 flex justify-end">
                <button className="px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700 transition">
                  Enregistrer les préférences
                </button>
              </div>
            </div>
          )}
          
          {/* Documents légaux */}
          {activeTab === "legal" && (
            <div>
              <h2 className="text-xl font-semibold mb-6 text-gray-900 dark:text-white">
                Documents légaux
              </h2>
              
              <div className="space-y-4">
                <LegalDocument 
                  title="Conditions générales de vente (CGV)" 
                  lastUpdated="01/02/2025"
                />
                <LegalDocument 
                  title="Conditions générales d'utilisation (CGU)" 
                  lastUpdated="01/02/2025"
                />
                <LegalDocument 
                  title="Politique de confidentialité" 
                  lastUpdated="15/03/2025"
                />
                <LegalDocument 
                  title="Mentions légales" 
                  lastUpdated="01/01/2025"
                />
              </div>
            </div>
          )}
        </div>
      </div>
    </div>
  );
}

// Composant bouton d'onglet
function TabButton({ active, onClick, icon, label }) {
  return (
    <button
      onClick={onClick}
      className={`flex items-center px-4 py-3 text-sm font-medium border-b-2 min-w-max ${
        active 
          ? "border-blue-600 text-blue-600 dark:border-blue-500 dark:text-blue-500" 
          : "border-transparent text-gray-500 hover:text-gray-700 hover:border-gray-300 dark:text-gray-400 dark:hover:text-gray-300 dark:hover:border-gray-700"
      }`}
    >
      <span className="mr-2">{icon}</span>
      {label}
    </button>
  );
}

// Composant carte d'action
function ActionCard({ icon, title, description, danger = false }) {
  return (
    <div className={`border rounded-lg p-4 flex hover:bg-gray-50 dark:hover:bg-gray-750 cursor-pointer transition ${
      danger ? "border-red-200 dark:border-red-800" : "border-gray-200 dark:border-gray-700"
    }`}>
      <div className={`mr-4 mt-1 p-2 rounded-lg ${
        danger 
          ? "text-red-500 bg-red-50 dark:bg-red-900/20" 
          : "text-blue-500 bg-blue-50 dark:bg-blue-900/20"
      }`}>
        {icon}
      </div>
      <div>
        <h3 className={`font-medium ${
          danger ? "text-red-600 dark:text-red-400" : "text-gray-900 dark:text-white"
        }`}>
          {title}
        </h3>
        <p className="text-sm text-gray-500 dark:text-gray-400 mt-1">
          {description}
        </p>
      </div>
    </div>
  );
}

// Composant carte de statistique
function StatCard({ icon, title, value, bgColor, textColor }) {
  return (
    <div className="border border-gray-200 dark:border-gray-700 rounded-xl p-6">
      <div className={`w-12 h-12 rounded-lg flex items-center justify-center mb-4 ${bgColor}`}>
        <span className={textColor}>{icon}</span>
      </div>
      <h3 className="text-lg font-medium text-gray-700 dark:text-gray-300">{title}</h3>
      <p className="text-3xl font-bold text-gray-900 dark:text-white mt-2">{value}</p>
    </div>
  );
}

// Composant paramètre de notification
function NotificationSetting({ title, description, checked, onChange }) {
  return (
    <div className="flex items-start justify-between">
      <div>
        <h3 className="font-medium text-gray-900 dark:text-white">{title}</h3>
        <p className="text-sm text-gray-500 dark:text-gray-400 mt-1">{description}</p>
      </div>
      <div className="relative inline-block w-12 h-6 flex-shrink-0">
        <input
          type="checkbox"
          checked={checked}
          onChange={onChange}
          className="opacity-0 w-0 h-0"
          id={`switch-${title}`}
        />
        <label
          htmlFor={`switch-${title}`}
          className={`absolute cursor-pointer top-0 left-0 right-0 bottom-0 rounded-full transition ${
            checked ? "bg-blue-600" : "bg-gray-200 dark:bg-gray-700"
          }`}
        >
          <span
            className={`absolute top-1 left-1 bg-white w-4 h-4 rounded-full transition transform ${
              checked ? "translate-x-6" : "translate-x-0"
            }`}
          />
        </label>
      </div>
    </div>
  );
}

// Composant document légal
function LegalDocument({ title, lastUpdated }) {
  return (
    <div className="border border-gray-200 dark:border-gray-700 rounded-lg p-4 flex justify-between items-center">
      <div>
        <h3 className="font-medium text-gray-900 dark:text-white">{title}</h3>
        <p className="text-sm text-gray-500 dark:text-gray-400 mt-1">
          Dernière mise à jour : {lastUpdated}
        </p>
      </div>
      <button className="px-4 py-2 bg-gray-100 dark:bg-gray-700 text-gray-700 dark:text-gray-300 rounded-lg hover:bg-gray-200 dark:hover:bg-gray-600 transition text-sm">
        Consulter
      </button>
    </div>
  );
}