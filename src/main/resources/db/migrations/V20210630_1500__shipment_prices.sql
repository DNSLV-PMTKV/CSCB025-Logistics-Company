ALTER TABLE shipments
ADD COLUMN price REAL NOT NULL default 0.0;
ALTER TABLE shipments
ADD COLUMN to_office BOOLEAN NOT NULL default 'true';