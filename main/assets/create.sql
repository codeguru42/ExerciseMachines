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
       ),
       ( 2
       , "Seated Row"
       , "file:///android_asset/desc/SeatedRow.html"
       , "img/SeatedRowThumb.jpeg"
       ),
       ( 3
       , "Seated Tricep Extension"
       , "file:///android_asset/desc/SeatedTricepExtension.html"
       , "img/TricepExtensionThumb.jpeg"
       ),
       ( 4
       , "Leg Extension"
       , "file:///android_asset/desc/LegExtension.html"
       , "img/LegExtensionThumb.jpeg"
       ),
       ( 5
       , "Leg Press"
       , "file:///android_asset/desc/LegPress.html"
       , "img/LegPressThumb.jpeg"
       ),
       ( 6
       , "Hack Squat"
       , "file:///android_asset/desc/HackSquat.html"
       , "img/HackSquatThumb.jpeg"
       ),
       ( 7
       , "Leg Curl"
       , "file:///android_asset/desc/LegCurl.html"
       , "img/LegCurlThumb.jpeg"
       );
COMMIT;

CREATE TABLE images
( _id INTEGER PRIMARY KEY AUTOINCREMENT
, machine_id INTEGER
, image_uri TEXT
);
BEGIN TRANSACTION;
INSERT INTO images (machine_id, image_uri)
VALUES ( 1, "img/InclinedBench01.jpeg" ),
       ( 1, "img/InclinedBench02.jpeg" ),
       ( 1, "img/InclinedBench03.jpeg" ),
       ( 2, "img/SeatedRow01.jpeg" ),
       ( 2, "img/SeatedRow02.jpeg" ),
       ( 3, "img/TricepExtension01.jpeg" ),
       ( 3, "img/TricepExtension02.jpeg" ),
       ( 4, "img/LegExtension01.jpeg" ),
       ( 4, "img/LegExtension02.jpeg" ),
       ( 5, "img/LegPress01.jpeg" ),
       ( 5, "img/LegPress02.jpeg" ),
       ( 6, "img/HackSquat01.jpeg" ),
       ( 6, "img/HackSquat02.jpeg" ),
       ( 7, "img/LegCurl01.jpeg" ),
       ( 7, "img/LegCurl02.jpeg" );
COMMIT;
