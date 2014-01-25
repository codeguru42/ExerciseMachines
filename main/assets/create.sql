CREATE TABLE machines
( _id INTEGER PRIMARY KEY AUTOINCREMENT
, name TEXT
, description TEXT
, thumbnail TEXT
);
BEGIN TRANSACTION;
INSERT INTO machines (name, description, thumbnail)
VALUES ( "Inclined Bench Press"
       , "file:///android_asset/desc/InclinedBench.html"
       , "file:///android_asset/img/ib01.png");
COMMIT;
