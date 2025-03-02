-- Create sequence for table mapdata
CREATE SEQUENCE mapdata_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

-- Create table mapdata
CREATE TABLE mapdata (
    id INTEGER NOT NULL DEFAULT nextval('mapdata_id_seq') PRIMARY KEY,
    value TEXT NOT NULL
);

-- Create sequence for table metadata
CREATE SEQUENCE metadata_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

-- Create table metadata
CREATE TABLE metadata (
    id INTEGER NOT NULL DEFAULT nextval('metadata_id_seq') PRIMARY KEY,
    idmapdata INTEGER NOT NULL REFERENCES mapdata(id),
    value1 TEXT NOT NULL,
    value2 TEXT NOT NULL
);
