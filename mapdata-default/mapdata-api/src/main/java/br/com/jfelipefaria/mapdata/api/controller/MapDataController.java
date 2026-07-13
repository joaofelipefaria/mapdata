package br.com.jfelipefaria.mapdata.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jfelipefaria.mapdata.api.dto.MapdataDTO;
import br.com.jfelipefaria.mapdata.api.dto.MetadataDTO;
import br.com.jfelipefaria.mapdata.api.entity.MetadataEntity;
import br.com.jfelipefaria.mapdata.api.service.MapDataService;

/**
 * REST controller for managing MapData and Metadata entities.
 * Provides endpoints for CRUD operations on mapdata and metadata.
 * Uses Spring's @RestController and @RequestMapping annotations to define routes.
 */
@RestController
@RequestMapping("/api")
public class MapDataController {

    @Autowired
    private MapDataService mapDataService;
    
    //file upload folder
    private static final String UPLOAD_DIR = "uploads";

    // --- MapData Endpoints ---

    /**
     * Get all MapData records.
     * @return List of MapdataDTO objects.
     */
    @GetMapping("/mapdata/all")
    public List<MapdataDTO> getAllMapData() {
        return mapDataService.getAllMapData();
    }

    /**
     * Get a MapData record by its ID.
     * @param id MapData ID.
     * @return MapdataDTO wrapped in ResponseEntity.
     */
    @GetMapping("/mapdata/{id}")
    public ResponseEntity<MapdataDTO> getMapDataById(@PathVariable("id") Integer id) {
        Optional<MapdataDTO> mapData = mapDataService.getMapDataById(id);
        return mapData.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Create a new MapData record.
     * @param data MapdataDTO object.
     * @return Created MapdataDTO with HTTP 201 status.
     */
    @PostMapping("/mapdata")
    public ResponseEntity<MapdataDTO> createMapData(@RequestBody MapdataDTO data) {
        MapdataDTO createdMapData = mapDataService.create(data);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdMapData);
    }

    /**
     * Update an existing MapData record.
     * @param id MapData ID.
     * @param data MapdataDTO object.
     * @return Updated MapdataDTO.
     */
    @PutMapping("/mapdata/{id}")
    public ResponseEntity<MapdataDTO> updateMapData(@PathVariable("id") Integer id, @RequestBody MapdataDTO data) {
        if (!id.equals(data.getId())) {
            return ResponseEntity.badRequest().build();
        }
        MapdataDTO updatedMapData = mapDataService.update(data);
        return ResponseEntity.ok(updatedMapData);
    }

    /**
     * Delete a MapData record by its ID.
     * @param id MapData ID.
     * @return No content response.
     */
    @DeleteMapping("/mapdata/{id}")
    public ResponseEntity<Void> deleteMapDataById(@PathVariable("id") Integer id) {
        mapDataService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Delete all MapData records.
     * @return No content response.
     */
    @DeleteMapping("/mapdata/all")
    public ResponseEntity<Void> deleteAllMapData() {
        mapDataService.deleteAllMapData();
        return ResponseEntity.noContent().build();
    }

    // --- Metadata Endpoints ---

    /**
     * Get all Metadata records for a given MapData ID.
     * @param id MapData ID.
     * @return List of MetadataEntity objects.
     */
    @GetMapping("/mapdata/{id}/metadata")
    public ResponseEntity<List<MetadataEntity>> getMetadata(@PathVariable("id") Integer id) {
        List<MetadataEntity> metadataList = mapDataService.getMetadataByMapdataId(id);
        if (metadataList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(metadataList);
    }

    /**
     * Get a Metadata record by its ID.
     * @param idmetadata Metadata ID.
     * @return MetadataEntity wrapped in ResponseEntity.
     */
    @GetMapping("/mapdata/{id}/metadata/{idmetadata}")
    public ResponseEntity<MetadataEntity> getMetadataById(@PathVariable("idmetadata") Integer idmetadata) {
        Optional<MetadataEntity> metadata = mapDataService.getMetadataById(idmetadata);
        return metadata.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Create a new Metadata record for a given MapData.
     * @param mapdataId MapData ID.
     * @param metadataDTO MetadataDTO object.
     * @return Created MetadataDTO with HTTP 201 status.
     */
    @PostMapping("/mapdata/{mapdataId}/metadata")
    public ResponseEntity<MetadataDTO> createMetadata(
            @PathVariable("mapdataId") Integer mapdataId,
            @RequestBody MetadataDTO metadataDTO) {
        MetadataDTO createdMetadata = mapDataService.createMetadata(mapdataId, metadataDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMetadata);
    }

    /**
     * Update an existing Metadata record.
     * @param mapdataId MapData ID.
     * @param metadataId Metadata ID.
     * @param metadataDTO MetadataDTO object.
     * @return Updated MetadataDTO.
     */
    @PutMapping("/mapdata/{mapdataId}/metadata/{metadataId}")
    public ResponseEntity<MetadataDTO> updateMetadata(
            @PathVariable("mapdataId") Integer mapdataId,
            @PathVariable("metadataId") Integer metadataId,
            @RequestBody MetadataDTO metadataDTO) {
        MetadataDTO updatedMetadata = mapDataService.updateMetadata(mapdataId, metadataId, metadataDTO);
        return ResponseEntity.ok(updatedMetadata);
    }

    /**
     * Delete a Metadata record by its ID.
     * @param mapdataId MapData ID.
     * @param metadataId Metadata ID.
     * @return No content response.
     */
    @DeleteMapping("/mapdata/{mapdataId}/metadata/{metadataId}")
    public ResponseEntity<Void> deleteMetadata(
            @PathVariable("mapdataId") Integer mapdataId,
            @PathVariable("metadataId") Integer metadataId) {
        mapDataService.deleteMetadataById(metadataId);
        return ResponseEntity.noContent().build();
    }

    /**
     * Delete all Metadata records for a given MapData.
     * @param mapdataId MapData ID.
     * @return No content response.
     */
    @DeleteMapping("/mapdata/{mapdataId}/metadata/all")
    public ResponseEntity<Void> deleteAllMetadata(@PathVariable("mapdataId") Integer mapdataId) {
        mapDataService.deleteMetadataByMapdataId(mapdataId);
        return ResponseEntity.noContent().build();
    }
}