import { Observable } from "rxjs";
import { Data } from "../model/data";
import { Metadata } from "../model/metadata";

export interface IDataService {
    getData(): Observable<Data[]>;

    // Returns a specific data item by ID
    getDataById(id: number): Observable<Data | undefined>;

    // Creates a new data item and adds it to the list
    createData(newData: Data): Observable<Data>;

    // Updates an existing data item by ID
    updateData(id: number, updatedData: Data): Observable<Data | undefined>;

    // Deletes a data item by ID
    deleteData(id: number): Observable<boolean>;

    // CRUD operations for Metadata
    // Returns metadata items associated with a specific dataId
    getMetadataByDataId(dataId: number): Observable<Metadata[]>;
    // Returns metadata items associated with a specific dataId
    getMetadataById(metadataId: number, dataId: number): Observable<Metadata | undefined>;

    // Creates a new metadata item and adds it to the list
    createMetadata(newMetadata: Metadata): Observable<Metadata>;
    // Updates an existing metadata item by ID
    updateMetadata(id: number, updatedMetadata: Metadata): Observable<Metadata | undefined>;

    // Deletes a metadata item by ID
    deleteMetadata(id: number, dataId: number): Observable<boolean>;
}