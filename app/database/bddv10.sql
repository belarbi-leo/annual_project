CREATE TYPE user_role AS ENUM ('admin', 'part', 'pro');
CREATE TYPE account_status AS ENUM ('active','suspended','banned','overlays','pro_pending');
CREATE TYPE request_status AS ENUM ('pending', 'approved', 'rejected', 'cancelled');
CREATE TYPE ad_status AS ENUM ('active', 'completed', 'cancelled', 'accepted', 'pending');
CREATE TYPE dispute_status AS ENUM ('pending', 'finished');
CREATE TYPE payment_direction AS ENUM ('client', 'presta');
CREATE TYPE payment_status AS ENUM ('pending', 'completed', 'failed');
CREATE TYPE request_ad_status AS ENUM ('pending', 'accept', 'refused');
CREATE TYPE svc_category AS ENUM ('sp', 'tl', 'tr', 'id', 'el');
CREATE TYPE svc_authorization AS ENUM ('part', 'pro', 'all');
CREATE TYPE audience AS ENUM ('part', 'pro', 'all');

CREATE TABLE "users" (
  "id_user" serial PRIMARY KEY,
  "date_registration" timestamp DEFAULT 'now()',
  "date_accept_cgu" timestamp, 
  "date_accept_cgv" timestamp, 
  "role" user_role DEFAULT 'part',
  "account_status" account_status,
  "date_status" timestamp DEFAULT 'now()',
  "email" varchar UNIQUE NOT NULL, /*NOT NULL*/
  "password" varchar(255) NOT NULL, /*NOT NULL*/
  "phone_number" varchar(10),
  "first_name" varchar(50),
  "last_name" varchar(50),
  "company_name" varchar(100),
  "siret" varchar(14) DEFAULT NULL, /*J'ai hésité à mettre UNIQUE, mais je me suis dit que si c'était Auchan, il pourrait y avoir plusieurs boutique qui utiliserait ce siret*/
  "photo_user" text,
  "bio" varchar(255),
  "location" varchar(255), 
  "suite" varchar(255), 
  "locality" varchar(255),   
  "state" varchar(255), 
  "postal_code" varchar(20), 
  "country" varchar(255), 
  "latitude" decimal(9,6),
  "longitude" decimal(9,6),
  "id_language" integer NOT NULL,
  "id_subscription" integer DEFAULT 1
);

CREATE TABLE "services" (
  "id_svc" serial PRIMARY KEY,
  "id_admin_creator" integer NOT NULL,
  "date_creation_svc" timestamp DEFAULT 'now()',
  "name_svc" varchar(100) UNIQUE NOT NULL, /*NOT NULL*/
  "category" svc_category,
  "auth" svc_authorization DEFAULT 'pro'
);

CREATE TABLE "services_docs" (
  "id_doc_svc" serial PRIMARY KEY,
  "id_svc" integer NOT NULL,
  "name_doc" varchar(100) NOT NULL /*NOT NULL*/
);

CREATE TABLE "depots" (
  "id_depot" serial PRIMARY KEY,
  "storage_capacity_depot" integer DEFAULT 0, /*DEFAULT 0, parce que peut etre qu'il est en construction*/
  "location" varchar(255), 
  "suite" varchar(255), 
  "locality" varchar(255),   
  "state" varchar(255), 
  "postal_code" varchar(20), 
  "country" varchar(255),
  "latitude" decimal(9,6),
  "longitude" decimal(9,6)
);

CREATE TABLE "requests_services" (
  "id_req_svc" serial PRIMARY KEY, 
  "id_user_req" integer NOT NULL,
  "id_admin_res" integer,
  "id_svc" integer NOT NULL,
  "status_req" request_status DEFAULT 'pending',
  "date_req" timestamp DEFAULT 'now()',
  "date_res" timestamp,
  "reason_res" varchar(255)
);

CREATE TABLE "requests_docs" (
  "id_doc_req" serial PRIMARY KEY,
  "id_req_svc" integer NOT NULL, 
  "doc_req" text,
  "comment" varchar(255) 
);

CREATE TABLE "materials" (
  "id_mat" serial PRIMARY KEY,
  "id_svc" integer NOT NULL,
  "name_mat" varchar(100) NOT NULL, /*NOT NULL*/
  "description_mat" text
);

CREATE TABLE "stock_control" (
  "id_package" integer NOT NULL,
  "id_depot" integer NOT NULL
);

CREATE TABLE "ads" (
  "id_ad" serial PRIMARY KEY,
  "id_user_creator" integer NOT NULL,
  "id_user_accept" integer /*NOT NULL*/,
  "id_svc" integer NOT NULL,
  "status_ad" ad_status DEFAULT 'pending',
  "date_creation_ad" timestamp DEFAULT 'now()',
  "date_accept_ad" timestamp,
  "date_start_ad" timestamp,
  "location_start" varchar(255), 
  "suite_start" varchar(255), 
  "locality_start" varchar(255),   
  "state_start" varchar(255), 
  "postal_code_start" varchar(255), 
  "country_start" varchar(255), 
  "latitude_start" decimal(9,6),
  "longitude_start" decimal(9,6),
  "date_end_ad" timestamp,
  "location_end" varchar(255), 
  "suite_end" varchar(255), 
  "locality_end" varchar(255),   
  "state_end" varchar(255), 
  "postal_code_end" varchar(20), 
  "country_end" varchar(255), 
  "latitude_end" decimal(9,6),
  "longitude_end" decimal(9,6),
  "title_ad" varchar(255) NOT NULL,
  "description_ad" varchar(255),
  "price_ad" decimal(10,2) NOT NULL, /*NOT NULL plutot que DEFAULT 0.0 parce que c'est mieux que ce soit les gens de l'appli qui choississent un prix*/
  "photo_ad" text
);

CREATE TABLE "languages" (
  "id_language" serial PRIMARY KEY,
  "name" varchar(30) UNIQUE NOT NULL, /*NOT NULL*/
  "iso" varchar(2) UNIQUE NOT NULL,
  "available" boolean DEFAULT false
);

CREATE TABLE "nfc" (
  "id_card_nfc" serial PRIMARY KEY,
  "id_user" integer
);

CREATE TABLE "subscriptions" (
  "id_sub" serial PRIMARY KEY,
  "name_sub" varchar(50) UNIQUE NOT NULL, /*UNIQUE NOT NULL*/
  "description_sub" text,
  "price" decimal(10,2) DEFAULT 0.0, /*DEFAULT 0.0*/
  "insurance" decimal(10,2) DEFAULT 0.0, /*DEFAULT 0.0*/
  "shipping_reduction" integer DEFAULT 0, /*DEFAULT 0*/
  "permanent_reduction" integer DEFAULT 0, /*DEFAULT 0*/
  "send_priority" integer DEFAULT 0, /*DEFAULT 0*/
  "target_audience" audience DEFAULT 'part',
  "active" boolean DEFAULT true
);

CREATE TABLE "requests_ads" (
  "id_req_ad" serial PRIMARY KEY, 
  "id_user" integer NOT NULL,
  "id_ad" integer NOT NULL,
  "status_req_ad" request_ad_status DEFAULT 'pending', 
  "date_creation_req_ad" timestamp DEFAULT 'now()', 
  "date_accept_req_ad" timestamp, 
  "date_start_req_ad" timestamp, 
  "location_start_req_ad" varchar(255), 
  "suite_start_req_ad" varchar(255), 
  "locality_start_req_ad" varchar(255),   
  "state_start_req_ad" varchar(255), 
  "postal_code_start_req_ad" varchar(255), 
  "country_start_req_ad" varchar(255), 
  "latitude_start_req_ad" decimal(9,6),
  "longitude_start_req_ad" decimal(9,6),
  "date_end_req_ad" timestamp, 
  "location_end_req_ad" varchar(255), 
  "suite_end_req_ad" varchar(255), 
  "locality_end_req_ad" varchar(255),   
  "state_end_req_ad" varchar(255), 
  "postal_code_end_req_ad" varchar(20), 
  "country_end_req_ad" varchar(255), 
  "latitude_end_req_ad" decimal(9,6),
  "longitude_end_req_ad" decimal(9,6),
  "message_req_ad" varchar(255), 
  "price_req_ad" decimal(10,2) DEFAULT 0.0
);
  
CREATE TABLE "routes" (
  "id_route" serial PRIMARY KEY,
  "id_user" integer NOT NULL,
  "date_creation_route" timestamp DEFAULT 'now()',
  "date_start_route" timestamp,
  "location_start_route" varchar(255), 
  "suite_start_route" varchar(255), 
  "locality_start_route" varchar(255),   
  "state_start_route" varchar(255), 
  "postal_code_start_route" varchar(255), 
  "country_start_route" varchar(255), 
  "latitude_start_route" decimal(9,6),
  "longitude_start_route" decimal(9,6),
  "date_end_route" timestamp,
  "location_end_route" varchar(255), 
  "suite_end_route" varchar(255), 
  "locality_end_route" varchar(255),   
  "state_end_route" varchar(255), 
  "postal_code_end_route" varchar(20), 
  "country_end_route" varchar(255), 
  "latitude_end_route" decimal(9,6),
  "longitude_end_route" decimal(9,6),
  "description_route" text,
  "step_route" integer DEFAULT 0 /*DEFAULT 0*/
);

CREATE TABLE "packages" (
  "id_pack" serial PRIMARY KEY,
  "id_ad" integer NOT NULL,
  "content_pack" text, /*limitation de 1024 characters dans le code*/
  "quantity_pack" integer NOT NULL DEFAULT 1,
  "details_pack" varchar(255),
  "weight_pack" decimal(10,2),
  "length_pack" integer,
  "width_pack" integer,
  "height_pack" integer,
  "photo_pack" text,
  "fragile" boolean DEFAULT false /*DEFAULT false*/
);

CREATE TABLE "disputes" (
  "id_dispute" serial PRIMARY KEY,
  "id_ad" integer NOT NULL,
  "id_user" integer NOT NULL,
  "date_status_dispute" timestamp DEFAULT 'now()',
  "status_dispute" dispute_status DEFAULT 'pending', /*DEFAULT 'pending'*/
  "description_dispute" text NOT NULL, /*NOT NULL*/
  "date_start_dispute" timestamp,
  "date_end_dispute" timestamp,
  "photo_dispute" text,
  "resolution_text" text
);

CREATE TABLE "opinions" (
  "id_opinion" serial PRIMARY KEY,
  "id_ad" integer NOT NULL,
  "note_opinion" smallint, /*j'ai limité la valeur pour qu'elle soit max <= 5*/
  "title_opinion" varchar(255) NOT NULL,
  "description_opinion" text NOT NULL, /*NOT NULL*/
  "date_opinion" timestamp DEFAULT 'now()'
);

CREATE TABLE "payments" (
  "id_payment" serial PRIMARY KEY,
  "id_ad" integer NOT NULL,
  "direction_payment" payment_direction NOT NULL, /*NOT NULL*/
  "status_payment" payment_status DEFAULT 'pending',
  "date_payment" timestamp DEFAULT 'now()',
  "bill_payment" text
);

CREATE TABLE "matches" (
  "id_route" integer NOT NULL,
  "id_ad" integer NOT NULL
);

ALTER TABLE "users" ADD FOREIGN KEY ("id_language") REFERENCES "languages" ("id_language");

ALTER TABLE "users" ADD FOREIGN KEY ("id_subscription") REFERENCES "subscriptions" ("id_sub");

ALTER TABLE "services" ADD FOREIGN KEY ("id_admin_creator") REFERENCES "users" ("id_user");

ALTER TABLE "services_docs" ADD FOREIGN KEY ("id_svc") REFERENCES "services" ("id_svc");

ALTER TABLE "requests_services" ADD FOREIGN KEY ("id_user_req") REFERENCES "users" ("id_user");

ALTER TABLE "requests_services" ADD FOREIGN KEY ("id_admin_res") REFERENCES "users" ("id_user");

ALTER TABLE "requests_services" ADD FOREIGN KEY ("id_svc") REFERENCES "services" ("id_svc");

ALTER TABLE "requests_docs" ADD FOREIGN KEY ("id_req_svc") REFERENCES "requests_services" ("id_req_svc");

ALTER TABLE "materials" ADD FOREIGN KEY ("id_svc") REFERENCES "services" ("id_svc");

ALTER TABLE "stock_control" ADD FOREIGN KEY ("id_package") REFERENCES "packages" ("id_pack");

ALTER TABLE "stock_control" ADD FOREIGN KEY ("id_depot") REFERENCES "depots" ("id_depot");

ALTER TABLE "ads" ADD FOREIGN KEY ("id_user_creator") REFERENCES "users" ("id_user");

ALTER TABLE "ads" ADD FOREIGN KEY ("id_user_accept") REFERENCES "users" ("id_user");

ALTER TABLE "ads" ADD FOREIGN KEY ("id_svc") REFERENCES "services" ("id_svc");

ALTER TABLE "nfc" ADD FOREIGN KEY ("id_user") REFERENCES "users" ("id_user");

ALTER TABLE "requests_ads" ADD FOREIGN KEY ("id_user") REFERENCES "users" ("id_user");

ALTER TABLE "requests_ads" ADD FOREIGN KEY ("id_ad") REFERENCES "ads" ("id_ad");

ALTER TABLE "routes" ADD FOREIGN KEY ("id_user") REFERENCES "users" ("id_user");

ALTER TABLE "packages" ADD FOREIGN KEY ("id_ad") REFERENCES "ads" ("id_ad");

ALTER TABLE "disputes" ADD FOREIGN KEY ("id_ad") REFERENCES "ads" ("id_ad");

ALTER TABLE "disputes" ADD FOREIGN KEY ("id_user") REFERENCES "users" ("id_user");

ALTER TABLE "opinions" ADD FOREIGN KEY ("id_ad") REFERENCES "ads" ("id_ad");

ALTER TABLE "payments" ADD FOREIGN KEY ("id_ad") REFERENCES "ads" ("id_ad");

ALTER TABLE "matches" ADD FOREIGN KEY ("id_route") REFERENCES "routes" ("id_route");

ALTER TABLE "matches" ADD FOREIGN KEY ("id_ad") REFERENCES "ads" ("id_ad");