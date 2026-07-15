-- Insert data into table mapdata
INSERT INTO mapdata (value) VALUES ('Map Data 1');
INSERT INTO mapdata (value) VALUES ('Map Data 2');

-- Insert data into table metadata
INSERT INTO metadata (idmapdata, value1, value2, file_name) VALUES (1, 'Meta Data 1.1', 'Meta Data 1.2', null);
INSERT INTO metadata (idmapdata, value1, value2, file_name) VALUES (1, 'Meta Data 1.2', 'Meta Data 1.22', null);
INSERT INTO metadata (idmapdata, value1, value2, file_name) VALUES (2, 'Meta Data 2.1', 'Meta Data 2.2', null);
