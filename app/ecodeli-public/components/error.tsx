import React from 'react';

type BlocErreurGlobalProps = {
  errors: Record<string, string | undefined>;
  fields: string[];
};

export default function BlocErreurGlobal({ errors, fields }: BlocErreurGlobalProps) {
  const erreursFiltrees = fields
    .filter((field) => errors[field])
    .map((field) => errors[field]);

  if (erreursFiltrees.length === 0) return null;

  return (
    <div className="mt-2 p-4 rounded-md bg-red-100 text-red-600 flex items-center justify-center text-sm">
      {/* <strong className="block mb-2">Veuillez corriger les erreurs suivantes :</strong> */}
        {erreursFiltrees.map((message, index) => (
          <p key={index}>{message}</p>
        ))}
    </div>
  );
}
