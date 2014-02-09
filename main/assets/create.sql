CREATE TABLE machines
( _id INTEGER PRIMARY KEY AUTOINCREMENT
, name TEXT
, description TEXT
, thumbnail TEXT
, qr_code TEXT
);
BEGIN TRANSACTION;
INSERT INTO machines (_id, name, description, thumbnail)
VALUES ( 1
       , "Inclined Bench Press"
       , "file:///android_asset/desc/InclinedBench.html"
       , "img/InclinedBenchThumb.jpeg"
       );
INSERT INTO machines (_id, name, description, thumbnail)
VALUES ( 2
       , "Seated Row"
       , "file:///android_asset/desc/SeatedRow.html"
       , "img/SeatedRowThumb.jpeg"
       );
INSERT INTO machines (_id, name, description, thumbnail)
VALUES ( 3
       , "Seated Tricep Extension"
       , "file:///android_asset/desc/SeatedTricepExtension.html"
       , "img/TricepExtensionThumb.jpeg"
       );
INSERT INTO machines (_id, name, description, thumbnail)
VALUES ( 4
       , "Leg Extension"
       , "file:///android_asset/desc/LegExtension.html"
       , "img/LegExtensionThumb.jpeg"
       );
INSERT INTO machines (_id, name, description, thumbnail)
VALUES ( 5
       , "Leg Press"
       , "file:///android_asset/desc/LegPress.html"
       , "img/LegPressThumb.jpeg"
       );
INSERT INTO machines (_id, name, description, thumbnail)
VALUES ( 6
       , "Hack Squat"
       , "file:///android_asset/desc/HackSquat.html"
       , "img/HackSquatThumb.jpeg"
       );
COMMIT;

CREATE TABLE images
( _id INTEGER PRIMARY KEY AUTOINCREMENT
, machine_id INTEGER
, image_uri TEXT
);
BEGIN TRANSACTION;
INSERT INTO images (machine_id, image_uri)
VALUES ( 1, "img/InclinedBench01.jpeg" );
INSERT INTO images (machine_id, image_uri)
VALUES ( 1, "img/InclinedBench02.jpeg" );
INSERT INTO images (machine_id, image_uri)
VALUES ( 1, "img/InclinedBench03.jpeg" );
INSERT INTO images (machine_id, image_uri)
VALUES ( 2, "img/SeatedRow01.jpeg" );
INSERT INTO images (machine_id, image_uri)
VALUES ( 2, "img/SeatedRow02.jpeg" );
INSERT INTO images (machine_id, image_uri)
VALUES ( 3, "img/TricepExtension01.jpeg" );
INSERT INTO images (machine_id, image_uri)
VALUES ( 3, "img/TricepExtension02.jpeg" );
INSERT INTO images (machine_id, image_uri)
VALUES ( 4, "img/LegExtension01.jpeg" );
INSERT INTO images (machine_id, image_uri)
VALUES ( 4, "img/LegExtension02.jpeg" );
INSERT INTO images (machine_id, image_uri)
VALUES ( 5, "img/LegPress01.jpeg" );
INSERT INTO images (machine_id, image_uri)
VALUES ( 5, "img/LegPress02.jpeg" );
INSERT INTO images (machine_id, image_uri)
VALUES ( 6, "img/HackSquat01.jpeg" );
INSERT INTO images (machine_id, image_uri)
VALUES ( 6, "img/HackSquat02.jpeg" );
COMMIT;
