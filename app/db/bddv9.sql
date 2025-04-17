CREATE TYPE user_role AS ENUM ('admin', 'part', 'pro');
CREATE TYPE account_status AS ENUM ('active','suspended','banned','overlays','pro_prending');
CREATE TYPE request_status AS ENUM ('pending', 'approved', 'rejected', 'cancelled');
CREATE TYPE ad_status AS ENUM ('active', 'completed', 'cancelled', 'accepted', 'pending');
CREATE TYPE dispute_status AS ENUM ('pending', 'finished');
CREATE TYPE payment_direction AS ENUM ('client', 'presta');
CREATE TYPE payment_status AS ENUM ('pending', 'completed', 'failed');
CREATE TYPE request_ad_status AS ENUM ('pending', 'accept', 'refused');
CREATE TYPE svc_category AS ENUM ('sp', 'tl', 'tr', 'id', 'el');
CREATE TYPE svc_authorization AS ENUM ('part', 'pro', 'all');

CREATE TABLE "users" (
  "id_user" serial PRIMARY KEY,
  "date_registration" timestamp DEFAULT 'now()',
  "date_accept_CGU" timestamp, /**/
  "date_accept_CGV" timestamp, /**/
  "role" user_role DEFAULT 'part',
  "account_status" account_status,
  "date_status" timestamp DEFAULT 'now()',
  "email" varchar UNIQUE,
  "password" varchar(255),
  "phone_number" integer,
  "first_name" varchar(50),
  "last_name" varchar(50),
  "company_name" varchar(100),
  "siret" integer(14) DEFAULT NULL, /**/
  "photo_user" text,
  "bio" varchar(255),
  "location" varchar(255), /**/
  "suite" varchar(255), /**/
  "locality" varchar(255),   /**/
  "state" varchar(255), /**/
  "postal_code" varchar(255), /**/
  "country" varchar(255), /**/
  "id_langue" integer NOT NULL,
  "id_subscription" integer DEFAULT 1
);

CREATE TABLE "services" (
  "id_svc" serial PRIMARY KEY,
  "id_admin_creator" integer NOT NULL,
  "date_creation_svc" timestamp DEFAULT 'now()',
  "name_svc" varchar(100) UNIQUE,
  "category" svc_category,
  /*"subcategory" varchar(50),*/
  "auth" svc_authorization DEFAULT 'pro'
);

CREATE TABLE "services_docs" (
  "id_doc_svc" serial PRIMARY KEY,
  "id_svc" integer NOT NULL,
  "name_doc" varchar(100)
);

CREATE TABLE "depots" (
  "id_depot" serial PRIMARY KEY,
  "storage_capacity_depot" integer,
  "location" varchar(255), /**/
  "suite" varchar(255), /**/
  "locality" varchar(255),   /**/
  "state" varchar(255), /**/
  "postal_code" varchar(255), /**/
  "country" varchar(255) /**/
);

CREATE TABLE "requests_services" (
  "id_req_svc" serial PRIMARY KEY, /**/
  "id_user_req" integer NOT NULL,
  "id_admin_res" integer,
  "id_service" integer NOT NULL,
  "status_req" DEFAULT 'pending',
  "date_req" timestamp DEFAULT 'now()',
  "date_res" timestamp,
  "reason_res" varchar(255)
);

CREATE TABLE "requests_docs" (
  "id_doc_req" serial PRIMARY KEY,
  "id_req_svc" integer NOT NULL, /**/
  "doc_req" text,
  "comment" varchar(255) /**/
);

CREATE TABLE "materials" (
  "id_mat" serial PRIMARY KEY,
  "id_svc" integer NOT NULL,
  "name_mat" varchar(100),
  "description_mat" text
);

CREATE TABLE "stock_control" (
  "id_package" integer NOT NULL,
  "id_depot" integer NOT NULL
);

CREATE TABLE "ads" (
  "id_ad" serial PRIMARY KEY,
  "id_user_creator" integer NOT NULL,
  "id_user_accept" integer NOT NULL,
  "id_svc" integer NOT NULL,
  "status_ad" ad_status DEFAULT 'pending',
  "date_creation_ad" timestamp DEFAULT 'now()',
  "date_accept_ad" timestamp,
  "date_start_ad" timestamp,
  "location_start" varchar(255), /**/
  "suite_start" varchar(255), /**/
  "locality_start" varchar(255),   /**/
  "state_start" varchar(255), /**/
  "postal_code_start" varchar(255), /**/
  "country_start" varchar(255), /**/
  "date_end_ad" timestamp,
  "location_end" varchar(255), /**/
  "suite_end" varchar(255), /**/
  "locality_end" varchar(255),   /**/
  "state_end" varchar(255), /**/
  "postal_code_end" varchar(255), /**/
  "country_end" varchar(255), /**/
  "description_ad" varchar(255),
  "price_ad" decimal(10,2),
  "photo_ad" text
);

CREATE TABLE "languages" (
  "id_langue" serial PRIMARY KEY,
  "langue" varchar UNIQUE,
  "iso" varchar(2) UNIQUE NOT NULL
);

CREATE TABLE "nfc" (
  "id_card_nfc" serial PRIMARY KEY,
  "id_user" integer
);

CREATE TABLE "subscriptions" (
  "id_sub" serial PRIMARY KEY,
  "name_sub" varchar(50),
  "description_sub" text,
  "price" decimal(10,2),
  "insurance" decimal(10,2),
  "shipping_reduction" integer,
  "permanent_reduction" integer,
  "send_priority" integer
);

CREATE TABLE "requests_ads" (
  "id_req_ad" serial PRIMARY KEY, /**/
  "id_user" integer NOT NULL,
  "id_ad" integer NOT NULL,
  "status_req_ad" request_ad_status DEFAULT 'pending', /**/
  "date_creation_req_ad" timestamp DEFAULT 'now()', /**/
  "date_accept_req_ad" timestamp, /**/
  "date_start_req_ad" timestamp, /**/
  "location_start_req_ad" varchar(255), /**/
  "suite_start_req_ad" varchar(255), /**/
  "locality_start_req_ad" varchar(255),   /**/
  "state_start_req_ad" varchar(255), /**/
  "postal_code_start_req_ad" varchar(255), /**/
  "country_start_req_ad" varchar(255), /**/
  "date_end_req_ad" timestamp, /**/
  "location_end_req_ad" varchar(255), /**/
  "suite_end_req_ad" varchar(255), /**/
  "locality_end_req_ad" varchar(255),   /**/
  "state_end_req_ad" varchar(255), /**/
  "postal_code_end_req_ad" varchar(255), /**/
  "country_end_req_ad" varchar(255), /**/
  "message_req_ad" varchar(255), /**/
  "price_req_ad" decimal(10,2) /**/
);
  
CREATE TABLE "routes" (
  "id_route" serial PRIMARY KEY,
  "id_user" integer NOT NULL,
  "date_creation_route" timestamp DEFAULT 'now()',
  "date_start_route" timestamp,
  "location_start_route" varchar(255), /**/
  "suite_start_route" varchar(255), /**/
  "locality_start_route" varchar(255),   /**/
  "state_start_route" varchar(255), /**/
  "postal_code_start_route" varchar(255), /**/
  "country_start_route" varchar(255), /**/
  "date_end_route" timestamp,
  "location_end_route" varchar(255), /**/
  "suite_end_route" varchar(255), /**/
  "locality_end_route" varchar(255),   /**/
  "state_end_route" varchar(255), /**/
  "postal_code_end_route" varchar(255), /**/
  "country_end_route" varchar(255), /**/
  "description_route" text,
  "step_route" integer
);

CREATE TABLE "packages" (
  "id_pack" serial PRIMARY KEY,
  "id_ad" integer NOT NULL,
  "content_pack" text,
  "quantity_pack" integer NOT NULL DEFAULT 1,
  "details_pack" varchar(255),
  "weight_pack" decimal(10,2),
  "length_pack" integer,
  "width_pack" integer,
  "height_pack" integer,
  "photo_pack" text,
  "fragile" boolean
);

CREATE TABLE "disputes" (
  "id_dispute" serial PRIMARY KEY,
  "id_ad" integer NOT NULL,
  "id_user" integer NOT NULL,
  "date_status_dispute" timestamp DEFAULT 'now()',
  "status_dispute" dispute_status,
  "description_dispute" text,
  "date_start_dispute" timestamp,
  "date_end_dispute" timestamp,
  "photo_dispute" text,
  "resolution_text" text
);

CREATE TABLE "opinions" (
  "id_opinion" serial PRIMARY KEY,
  "id_ad" integer NOT NULL,
  "note_opinion" smallint,
  "title_opinion" varchar(255),
  "description_opinion" text,
  "date_opinion" timestamp DEFAULT 'now()'
);

CREATE TABLE "payments" (
  "id_payment" serial PRIMARY KEY,
  "id_ad" integer NOT NULL,
  "direction_payment" payment_direction,
  "status_payment" payment_status DEFAULT 'pending',
  "date_payment" timestamp DEFAULT 'now()',
  "bill_payment" text
);

CREATE TABLE "matches" (
  "id_route" integer NOT NULL,
  "id_ad" integer NOT NULL
);

ALTER TABLE "users" ADD FOREIGN KEY ("id_langue") REFERENCES "languages" ("id_langue");

ALTER TABLE "users" ADD FOREIGN KEY ("id_subscription") REFERENCES "subscriptions" ("id_sub");

ALTER TABLE "services" ADD FOREIGN KEY ("id_admin_creator") REFERENCES "users" ("id_user");

ALTER TABLE "services_docs" ADD FOREIGN KEY ("id_svc") REFERENCES "services" ("id_svc");

ALTER TABLE "requests_services" ADD FOREIGN KEY ("id_user_req") REFERENCES "users" ("id_user");

ALTER TABLE "requests_services" ADD FOREIGN KEY ("id_admin_res") REFERENCES "users" ("id_user");

ALTER TABLE "requests_services" ADD FOREIGN KEY ("id_service") REFERENCES "services" ("id_svc");

ALTER TABLE "requests_docs" ADD FOREIGN KEY ("id_req") REFERENCES "requests_services" ("id_req_svc");

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