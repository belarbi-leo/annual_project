------------------------------
-- 1. Insertion dans les tables sans dépendances
------------------------------
INSERT INTO "languages" (language, iso) VALUES
  ('Français','FR'),
  ('Anglais','EN'),
  ('Espagnol','ES'),
  ('Allemand','DE'),
  ('Italien','IT');
INSERT INTO "subscriptions" (name_sub, description_sub) VALUES
  ('Basic', 'Abonnement de base'),
  ('Premium', 'Abonnement premium avec plus de fonctionnalités'),
  ('Gold', 'Accès à toutes les fonctionnalités'),
  ('Silver', 'Abonnement intermédiaire'),
  ('Platinum', 'Abonnement haut de gamme');

-- depots (5 lignes)
-- nfc (5 lignes)


------------------------------
-- 2. Insertion dans la table users. Remarque : Les colonnes id_subscription et id_langue font référence aux tables précédentes.
------------------------------
INSERT INTO "users" (date_registration, date_accept_cgu, date_accept_cgv, role, account_status, date_status, email, password, phone_number, first_name, last_name, company_name, siret, bio, id_subscription, id_language) VALUES
  ('2025-01-01 09:00:00', '2025-01-01 08:59:50', '2025-01-01 08:59:51', 'admin', 'active', '2025-01-01 09:00:00', 'admin1@example.com', 'password0000', 0612345678, 'Alice', 'Dupont', NULL, NULL, NULL, 1, 1),
  ('2025-01-01 09:00:00', '2025-01-01 08:59:50', '2025-01-01 08:59:51', 'admin', 'active', '2025-01-01 09:00:00', 'admin2@example.com', 'password0000', 0612345678, 'David', 'Lefevre', NULL, NULL, NULL, 1, 1),
  ('2025-01-01 09:00:00', '2025-01-01 08:59:50', '2025-01-01 08:59:51', 'part', 'active', '2025-01-01 09:00:00', 'part1@example.com', 'password0000', 0612345678, 'Bob', 'Martin', NULL, NULL, 'Juste un gars chill, qui veux payer plus chère ses livraisons', 1, 1),
  ('2025-01-01 09:00:00', '2025-01-01 08:59:50', '2025-01-01 08:59:51', 'part', 'active', '2025-01-01 09:00:00', 'part2@example.com', 'password0000', 0612345678, 'Emma', 'Moreau', NULL, NULL, 'Juste un gars chill, qui veux payer plus chère ses livraisons', 1, 1),
  ('2025-01-01 09:00:00', '2025-01-01 08:59:50', '2025-01-01 08:59:51', 'part', 'active', '2025-01-01 09:00:00', 'part3@example.com', 'password0000', 0612345678, 'Bob', 'Martin', NULL, NULL, 'Juste un gars chill, qui veux payer plus chère ses livraisons', 2, 1),
  ('2025-01-01 09:00:00', '2025-01-01 08:59:50', '2025-01-01 08:59:51', 'part', 'overlays', '2025-01-01 09:00:00', 'part4@example.com', 'password0000', 0612345678, 'Bob', 'Martin', NULL, NULL, 'Juste un gars chill, qui veux payer plus chère ses livraisons', 2, 1),
  ('2025-01-01 09:00:00', '2025-01-01 08:59:50', '2025-01-01 08:59:51', 'part', 'banned', '2025-01-02 09:00:00', 'part5@example.com', 'password0000', 0612345678, 'Bob', 'Martin', NULL, NULL, 'Juste un gars chill, qui veux payer plus chère ses livraisons', 2, 1),
  ('2025-01-01 09:00:00', '2025-01-01 08:59:50', '2025-01-01 08:59:51', 'pro', 'active', '2025-01-02 09:00:00', 'pro1@example.com', 'password0000', 0612345678, NULL, NULL, 'ProServices', 10000000000003, 'Juste pour parler de notre entreprise, on est les meilleurs, pas de débat', 1, 1),
  ('2025-01-01 09:00:00', '2025-01-01 08:59:50', '2025-01-01 08:59:51', 'pro', 'active', '2025-01-02 09:00:00', 'pro2@example.com', 'password0000', 0612345678, NULL, NULL, 'EnzoServices', 10000000000003, 'Juste pour parler de notre entreprise, on est les meilleurs, pas de débat', 3, 1),
  ('2025-01-01 09:00:00', '2025-01-01 08:59:50', '2025-01-01 08:59:51', 'pro', 'active', '2025-01-02 09:00:00', 'pro3@example.com', 'password0000', 0612345678, NULL, NULL, 'BryanServices', 10000000000003, 'Juste pour parler de notre entreprise, on est les meilleurs, pas de débat', 1, 1),
  ('2025-01-01 09:00:00', '2025-01-01 08:59:50', '2025-01-01 08:59:51', 'pro', 'pro_pending', '2025-01-01 09:00:00', 'pro4@example.com', 'password0000', 0612345678, NULL, NULL, 'Lesmeilleursdelaplace', 10000000000003, 'Juste pour parler de notre entreprise, on est les meilleurs, pas de débat', 1, 1),
  ('2025-01-01 09:00:00', '2025-01-01 08:59:50', '2025-01-01 08:59:51', 'pro', 'pro_pending', '2025-01-01 09:00:00', 'pro5@example.com', 'password0000', 0612345678, NULL, NULL, 'ESGI_Services', 10000000000003, 'Juste pour parler de notre entreprise, on est les meilleurs, pas de débat', 3, 1),
  ('2025-01-01 09:00:00', '2025-01-01 08:59:50', '2025-01-01 08:59:51', 'pro', 'overlays', '2025-01-02 09:00:00', 'pro6@example.com', 'password0000', 0612345678, NULL, NULL, 'Léo-Services', 10000000000003, 'Juste pour parler de notre entreprise, on est les meilleurs, pas de débat', 1, 1);

------------------------------
-- 3. Insertion dans les tables principales et dépendantes
------------------------------
INSERT INTO "services" (id_admin_creator, date_creation_svc, name_svc, category, auth) VALUES
  (1, '2025-01-05 08:00:00', 'Garde d''enfants', 'sp', 'pro'),
  (1, '2025-01-06 09:30:00', 'Aide à domicile', 'sp', 'pro'),
  (1, '2025-01-07 10:00:00', 'Coach personnel', 'sp', 'pro'),
  (4, '2025-01-08 11:15:00', 'Déménagement', 'tl', 'pro'),
  (4, '2025-01-09 12:45:00', 'Livraison de colis', 'tl', 'all'),
  (1, '2025-01-06 09:30:00', 'Taxi privé', 'tl', 'pro'),
  (1, '2025-01-07 10:00:00', 'Plomberie', 'tr', 'pro'),
  (4, '2025-01-08 11:15:00', 'Électricité', 'tr', 'pro'),
  (4, '2025-01-09 12:45:00', 'Menuiserie', 'tr', 'pro'),
  (1, '2025-01-05 08:00:00', 'Développement Web', 'id', 'pro'),
  (1, '2025-01-06 09:30:00', 'Support IT', 'id', 'pro'),
  (1, '2025-01-07 10:00:00', 'Montage vidéo', 'id', 'pro'),
  (4, '2025-01-08 11:15:00', 'DJ', 'el', 'pro'),
  (4, '2025-01-08 11:15:00', 'Photographe', 'el', 'pro'),
  (4, '2025-01-09 12:45:00', 'Organisateur d''événements', 'el', 'pro'),
  (1, '2025-01-05 08:00:00', 'Consultant marketing', 'el', 'pro'),
  (1, '2025-01-06 09:30:00', 'Rédacteur de contenu', 'el', 'pro'),
  (1, '2025-01-07 10:00:00', 'Graphiste', 'el', 'pro');
INSERT INTO "services_docs" (id_svc, name_doc) VALUES
  (1, 'Attestation de compétence, de conformité et de moralité'),
  (1, 'Certificat d''assurance responsabilité civile professionnelle'),
  (2, 'Attestation de compétence, de conformité et de moralité'),
  (2, 'Certificat d''assurance responsabilité civile professionnelle'),
  (3, 'Attestation de compétence, de conformité et de moralité'),
  (3, 'Certificat d''assurance responsabilité civile professionnelle'),
  (4, 'Attestation de compétence, de conformité et de moralité'),
  (4, 'Certificat d''assurance responsabilité civile professionnelle'),
  (5, 'Attestation de compétence, de conformité et de moralité'),
  (5, 'CNI ou passeport'),
  (5, 'Permis de conduire'),
  (5, 'Certificat d''assurance responsabilité civile professionnelle'),
  (6, 'Attestation de compétence, de conformité et de moralité'),
  (6, 'Certificat d''assurance responsabilité civile professionnelle'),
  (7, 'Attestation de compétence, de conformité et de moralité'),
  (7, 'Certificat d''assurance responsabilité civile professionnelle'),
  (8, 'Attestation de compétence, de conformité et de moralité'),
  (8, 'Certificat d''assurance responsabilité civile professionnelle'),
  (9, 'Attestation de compétence, de conformité et de moralité'),
  (9, 'Certificat d''assurance responsabilité civile professionnelle'),
  (10, 'Attestation de compétence, de conformité et de moralité'),
  (10, 'Certificat d''assurance responsabilité civile professionnelle'),
  (11, 'Attestation de compétence, de conformité et de moralité'),
  (11, 'Certificat d''assurance responsabilité civile professionnelle'),
  (12, 'Attestation de compétence, de conformité et de moralité'),
  (12, 'Certificat d''assurance responsabilité civile professionnelle'), 
  (13, 'Attestation de compétence, de conformité et de moralité'),
  (13, 'Certificat d''assurance responsabilité civile professionnelle'),
  (14, 'Attestation de compétence, de conformité et de moralité'),
  (14, 'Certificat d''assurance responsabilité civile professionnelle'),
  (15, 'Attestation de compétence, de conformité et de moralité'),
  (15, 'Certificat d''assurance responsabilité civile professionnelle'),
  (16, 'Attestation de compétence, de conformité et de moralité'),
  (16, 'Certificat d''assurance responsabilité civile professionnelle'),
  (17, 'Attestation de compétence, de conformité et de moralité'),
  (17, 'Certificat d''assurance responsabilité civile professionnelle'),
  (18, 'Attestation de compétence, de conformité et de moralité'),
  (18, 'Certificat d''assurance responsabilité civile professionnelle');
-- materials (0 lignes)
-- requests_services
INSERT INTO "requests_services" (id_user_req, id_admin_res, id_service, status_req, date_req, date_res, reason_res) VALUES
  (8, 1, 1, 'approved', '2025-01-01 09:00:00', '2025-01-02 09:00:00', NULL),
  (9, 1, 8, 'approved', '2025-01-01 09:00:00', '2025-01-02 09:00:00', NULL),
  (10, 1, 14, 'approved', '2025-01-01 09:00:00', '2025-01-02 09:00:00', NULL),
  (11, 1, 6, 'pending', '2025-01-01 09:00:00', NULL, 'Retour, document illisible'),
  (12, 1, 9, 'pending', '2025-01-01 09:00:00', NULL, 'Retour, document illisible'),
  (13, 1, 5, 'approved', '2025-01-01 09:00:00', '2025-01-02 09:00:00', NULL),
  (9, 1, 5, 'approved', '2025-01-02 09:00:00', '2025-01-03 09:00:00', NULL),
  (9, 1, 16, 'rejected', '2025-01-02 09:00:00', '2025-01-03 09:00:00', 'Document manquant, veuillez fournir une attestation valide'),
  (10, 1, 5, 'approved', '2025-01-02 09:00:00', '2025-01-03 09:00:00', NULL),
  (8, NULL, 6, 'pending', '2025-01-02 09:00:00', NULL, NULL),
  (9, NULL, 9, 'pending', '2025-01-02 09:00:00', NULL, NULL),
  (10, NULL, 6, 'pending', '2025-01-02 09:00:00', NULL, NULL),
  (10, NULL, 5, 'pending', '2025-01-02 09:00:00', NULL, NULL),
  (10, NULL, 2, 'pending', '2025-01-02 09:00:00', NULL, NULL);

-- requests_docs (5 lignes)
INSERT INTO "requests_docs" (id_req_svc, doc_req, comment) VALUES
  (1, 'file.pdf', NULL),
  (1, 'file.pdf', NULL),
  (2, 'file.pdf', NULL),
  (2, 'file.pdf', NULL),
  (3, 'file.pdf', NULL),
  (3, 'file.pdf', NULL),
  (4, 'file.pdf', NULL),
  (4, 'file.pdf', NULL),
  (5, 'file.pdf', 'Mon attestation est plutôt ancienne j''espère qu''elle vous conviendras je suis toujours très compétent et motiver !'),
  (5, 'file.pdf', NULL),
  (6, 'file.pdf', NULL),
  (6, 'file.pdf', NULL),
  (6, 'file.pdf', NULL),
  (6, 'file.pdf', NULL),
  (7, 'file.pdf', NULL),
  (7, 'file.pdf', NULL),
  (7, 'file.pdf', NULL),
  (7, 'file.pdf', NULL),
  (8, 'file.pdf', NULL),
  (8, 'file.pdf', NULL),
  (9, 'file.pdf', NULL),
  (9, 'file.pdf', NULL),
  (9, 'file.pdf', NULL),
  (9, 'file.pdf', NULL),
  (10, 'file.pdf', NULL),
  (10, 'file.pdf', NULL),
  (11, 'file.pdf', NULL),
  (11, 'file.pdf', NULL),
  (12, 'file.pdf', NULL),
  (12, 'file.pdf', NULL),
  (13, 'file.pdf', NULL),
  (13, 'file.pdf', NULL),
  (13, 'file.pdf', NULL),
  (13, 'file.pdf', NULL),
  (14, 'file.pdf', 'Voici mon attestation de compétence, de conformité et de moralité, je suis très motivé pour ce service !'),
  (14, 'file.pdf', 'Voici mon assurance, datée de 2025.');


-- requests_ads (5 lignes)
-- routes (5 lignes)
-- packages (5 lignes)
-- stock_control (5 lignes)
-- disputes (5 lignes)
-- opinions (5 lignes)
-- payments (5 lignes)
-- matches (5 lignes)
-- ads (5 lignes)