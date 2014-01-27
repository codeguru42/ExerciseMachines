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
       , "file:///android_asset/img/InclinedBenchThumb.png"
       );
COMMIT;

CREATE TABLE images
( _id INTEGER PRIMARY KEY AUTOINCREMENT
, machine_id INTEGER
, image_uri TEXT
);
BEGIN TRANSACTION;
INSERT INTO images (machine_id, image_uri)
VALUES ( 1, "img/InclinedBench01.png" );
INSERT INTO images (machine_id, image_uri)
VALUES ( 1, "img/InclinedBench02.png" );
INSERT INTO images (machine_id, image_uri)
VALUES ( 1, "img/InclinedBench03.png" );
COMMIT;
