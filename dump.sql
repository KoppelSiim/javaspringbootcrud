-- H2 2.1.214;
;             
CREATE USER IF NOT EXISTS "SA" SALT '9596885e4e07f032' HASH '972996e53e4bdd51dcb51c5f1ad9f770e2aa7f5bba25ac801d6a7e84655a1a8b' ADMIN;         
CREATE MEMORY TABLE "PUBLIC"."SPRING_SESSION"(
    "PRIMARY_ID" CHARACTER(36) NOT NULL,
    "SESSION_ID" CHARACTER(36) NOT NULL,
    "CREATION_TIME" BIGINT NOT NULL,
    "LAST_ACCESS_TIME" BIGINT NOT NULL,
    "MAX_INACTIVE_INTERVAL" INTEGER NOT NULL,
    "EXPIRY_TIME" BIGINT NOT NULL,
    "PRINCIPAL_NAME" CHARACTER VARYING(100)
);         
ALTER TABLE "PUBLIC"."SPRING_SESSION" ADD CONSTRAINT "PUBLIC"."SPRING_SESSION_PK" PRIMARY KEY("PRIMARY_ID");  
-- 1 +/- SELECT COUNT(*) FROM PUBLIC.SPRING_SESSION;          
INSERT INTO "PUBLIC"."SPRING_SESSION" VALUES
('f62cabdc-58a4-456d-a614-74c4c24375b7', 'de929505-41e0-4997-ab9d-af09cfcb4546', 1688026030266, 1688026053492, 1800, 1688027853492, NULL);      
CREATE UNIQUE INDEX "PUBLIC"."SPRING_SESSION_IX1" ON "PUBLIC"."SPRING_SESSION"("SESSION_ID" NULLS FIRST);     
CREATE INDEX "PUBLIC"."SPRING_SESSION_IX2" ON "PUBLIC"."SPRING_SESSION"("EXPIRY_TIME" NULLS FIRST);           
CREATE INDEX "PUBLIC"."SPRING_SESSION_IX3" ON "PUBLIC"."SPRING_SESSION"("PRINCIPAL_NAME" NULLS FIRST);        
CREATE MEMORY TABLE "PUBLIC"."SPRING_SESSION_ATTRIBUTES"(
    "SESSION_PRIMARY_ID" CHARACTER(36) NOT NULL,
    "ATTRIBUTE_NAME" CHARACTER VARYING(200) NOT NULL,
    "ATTRIBUTE_BYTES" BINARY VARYING NOT NULL
);         
ALTER TABLE "PUBLIC"."SPRING_SESSION_ATTRIBUTES" ADD CONSTRAINT "PUBLIC"."SPRING_SESSION_ATTRIBUTES_PK" PRIMARY KEY("SESSION_PRIMARY_ID", "ATTRIBUTE_NAME");  
-- 2 +/- SELECT COUNT(*) FROM PUBLIC.SPRING_SESSION_ATTRIBUTES;               
INSERT INTO "PUBLIC"."SPRING_SESSION_ATTRIBUTES" VALUES
('f62cabdc-58a4-456d-a614-74c4c24375b7', 'formInput', X'aced00057372001c636f6d2e637275642e6170702e6d6f64656c2e466f726d496e7075746aed038831fb69b50200045a000d616772656564546f5465726d734c000269647400104c6a6176612f6c616e672f4c6f6e673b4c00046e616d657400124c6a6176612f6c616e672f537472696e673b5b000f73656c65637465644f7074696f6e737400135b4c6a6176612f6c616e672f537472696e673b7870017074000b5369696d204b6f7070656c757200135b4c6a6176612e6c616e672e537472696e673badd256e7e91d7b4702000078700000000374000131740002313974000136'),
('f62cabdc-58a4-456d-a614-74c4c24375b7', 'formPrimaryKey', X'aced00057372000e6a6176612e6c616e672e4c6f6e673b8be490cc8f23df0200014a000576616c7565787200106a6176612e6c616e672e4e756d62657286ac951d0b94e08b02000078700000000000000001');
CREATE MEMORY TABLE "PUBLIC"."FORM_INPUT"(
    "AGREED_TO_TERMS" BOOLEAN NOT NULL,
    "ID" BIGINT GENERATED BY DEFAULT AS IDENTITY(START WITH 1 RESTART WITH 2) NOT NULL,
    "NAME" CHARACTER VARYING(255) NOT NULL,
    "SELECTED_OPTIONS" CHARACTER VARYING(255) ARRAY NOT NULL
);   
ALTER TABLE "PUBLIC"."FORM_INPUT" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_6" PRIMARY KEY("ID");   
-- 1 +/- SELECT COUNT(*) FROM PUBLIC.FORM_INPUT;              
INSERT INTO "PUBLIC"."FORM_INPUT" VALUES
(TRUE, 1, 'Siim Koppel', ARRAY ['1', '19', '6', '342', '42', '40']);
CREATE MEMORY TABLE "PUBLIC"."WEB_DATA"(
    "INDENTATION" INTEGER NOT NULL,
    "ID" BIGINT GENERATED BY DEFAULT AS IDENTITY(START WITH 1 RESTART WITH 80) NOT NULL,
    "OPTION_TEXT" CHARACTER VARYING(255),
    "OPTION_VALUE" CHARACTER VARYING(255)
);             
ALTER TABLE "PUBLIC"."WEB_DATA" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_5" PRIMARY KEY("ID");     
-- 79 +/- SELECT COUNT(*) FROM PUBLIC.WEB_DATA;               
INSERT INTO "PUBLIC"."WEB_DATA" VALUES
(0, 1, 'Manufacturing', '1'),
(1, 2, 'Construction materials', '19'),
(1, 3, 'Electronics and Optics', '18'),
(1, 4, 'Food and Beverage', '6'),
(2, 5, 'Bakery & confectionery products', '342'),
(2, 6, 'Beverages', '43'),
(2, 7, 'Fish & fish products', '42'),
(2, 8, 'Meat & meat products', '40'),
(2, 9, 'Milk & dairy products', '39'),
(2, 10, 'Other', '437'),
(2, 11, 'Sweets & snack food', '378'),
(1, 12, 'Furniture', '13'),
(2, 13, 'Bathroom/sauna', '389'),
(2, 14, 'Bedroom', '385'),
(2, 15, U&'Children\2019s room', '390'),
(2, 16, 'Kitchen', '98'),
(2, 17, 'Living room', '101'),
(2, 18, 'Office', '392'),
(2, 19, 'Other (Furniture)', '394'),
(2, 20, 'Outdoor', '341'),
(2, 21, 'Project furniture', '99'),
(1, 22, 'Machinery', '12'),
(2, 23, 'Machinery components', '94'),
(2, 24, 'Machinery equipment/tools', '91'),
(2, 25, 'Manufacture of machinery', '224'),
(2, 26, 'Maritime', '97'),
(3, 27, 'Aluminium and steel workboats', '271'),
(3, 28, 'Boat/Yacht building', '269'),
(3, 29, 'Ship repair and conversion', '230'),
(2, 30, 'Metal structures', '93'),
(2, 31, 'Other', '508'),
(2, 32, 'Repair and maintenance service', '227'),
(1, 33, 'Metalworking', '11'),
(2, 34, 'Construction of metal structures', '67'),
(2, 35, 'Houses and buildings', '263'),
(2, 36, 'Metal products', '267'),
(2, 37, 'Metal works', '542'),
(3, 38, 'CNC-machining', '75'),
(3, 39, 'Forgings, Fasteners', '62'),
(3, 40, 'Gas, Plasma, Laser cutting', '69'),
(3, 41, 'MIG, TIG, Aluminum welding', '66'),
(1, 42, 'Plastic and Rubber', '9'),
(2, 43, 'Packaging', '54'),
(2, 44, 'Plastic goods', '556'),
(2, 45, 'Plastic processing technology', '559'),
(3, 46, 'Blowing', '55'),
(3, 47, 'Moulding', '57'),
(3, 48, 'Plastics welding and processing', '53'),
(2, 49, 'Plastic profiles', '560'),
(1, 50, 'Printing', '5'),
(2, 51, 'Advertising', '148'),
(2, 52, 'Book/Periodicals printing', '150'),
(2, 53, 'Labelling and packaging printing', '145'),
(1, 54, 'Textile and Clothing', '7'),
(2, 55, 'Clothing', '44'),
(2, 56, 'Textile', '45'),
(1, 57, 'Wood', '8'),
(2, 58, 'Other (Wood)', '337'),
(2, 59, 'Wooden building materials', '51'),
(2, 60, 'Wooden houses', '47'),
(0, 61, 'Other', '3'),
(1, 62, 'Creative industries', '37'),
(1, 63, 'Energy technology', '29'),
(1, 64, 'Environment', '33'),
(0, 65, 'Service', '2'),
(1, 66, 'Business services', '25'),
(1, 67, 'Engineering', '35'),
(1, 68, 'Information Technology and Telecommunications', '28'),
(2, 69, 'Data processing, Web portals, E-marketing', '581'),
(2, 70, 'Programming, Consultancy', '576'),
(2, 71, 'Software, Hardware', '121'),
(2, 72, 'Telecommunications', '122'),
(1, 73, 'Tourism', '22'),
(1, 74, 'Translation services', '141'),
(1, 75, 'Transport and Logistics', '21'),
(2, 76, 'Air', '111'),
(2, 77, 'Rail', '114'),
(2, 78, 'Road', '112'),
(2, 79, 'Water', '113');            
ALTER TABLE "PUBLIC"."SPRING_SESSION_ATTRIBUTES" ADD CONSTRAINT "PUBLIC"."SPRING_SESSION_ATTRIBUTES_FK" FOREIGN KEY("SESSION_PRIMARY_ID") REFERENCES "PUBLIC"."SPRING_SESSION"("PRIMARY_ID") ON DELETE CASCADE NOCHECK;       
