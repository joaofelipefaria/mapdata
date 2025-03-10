package lu.joaofaria.java.hello.api.controller;

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

import lu.joaofaria.java.hello.api.dto.MapdataDTO;
import lu.joaofaria.java.hello.api.dto.MetadataDTO;
import lu.joaofaria.java.hello.api.entity.MetadataEntity;
import lu.joaofaria.java.hello.api.service.MapDataService;

@RestController
@RequestMapping("/api")
public class MapDataController {

    @Autowired
    private MapDataService mapDataService;
    
    //MAP DATA
    @GetMapping("/mapdata/all")
    public List<MapdataDTO> getAllMapData() {
        return mapDataService.getAllMapData();
    }

    @GetMapping("/mapdata/{id}")
    public ResponseEntity<MapdataDTO> getMapDataById(@PathVariable Integer id) {
        Optional<MapdataDTO> mapData = mapDataService.getMapDataById(id);
        return mapData.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/mapdata")
    public ResponseEntity<MapdataDTO> createMapData(@RequestBody MapdataDTO data) {
        MapdataDTO createdMapData = mapDataService.create(data);
        return ResponseEntity
                .status(HttpStatus.CREATED) 
                .body(createdMapData); 
    }
    
    @PutMapping("/mapdata/{id}")
    public ResponseEntity<MapdataDTO> updateMapData(@PathVariable Integer id, @RequestBody MapdataDTO data) {
        if (!id.equals(data.getId())) {
            return ResponseEntity.badRequest().build();
        }
        MapdataDTO updatedMapData = mapDataService.update(data);
        return ResponseEntity.ok(updatedMapData);
    }
    
    @DeleteMapping("/mapdata/{id}")
    public ResponseEntity<Void> deleteMapDataById(@PathVariable Integer id) {
        mapDataService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/mapdata/all")
    public ResponseEntity<Void> deleteAllMapData() {
        mapDataService.deleteAllMapData();
        return ResponseEntity.noContent().build();
    }

    //META DATA
    @GetMapping("/mapdata/{id}/metadata")
    public ResponseEntity<List<MetadataEntity>> getMetadata(@PathVariable Integer id) {
        List<MetadataEntity> metadataList = mapDataService.getMetadataByMapdataId(id);
        if (metadataList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(metadataList);
    }
    
    @GetMapping("/mapdata/{id}/metadata/{idmetadata}")
    public ResponseEntity<MetadataEntity> getMetadataById(@PathVariable Integer idmetadata) {
        Optional<MetadataEntity> metadata = mapDataService.getMetadataById(idmetadata);
        return metadata.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @PostMapping("/mapdata/{mapdataId}/metadata")
    public ResponseEntity<MetadataDTO> createMetadata(
            @PathVariable Integer mapdataId, 
            @RequestBody MetadataDTO metadataDTO) {
        MetadataDTO createdMetadata = mapDataService.createMetadata(mapdataId, metadataDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMetadata);
    }
    
    @PutMapping("/mapdata/{mapdataId}/metadata/{metadataId}")
    public ResponseEntity<MetadataDTO> updateMetadata(
            @PathVariable Integer mapdataId, 
            @PathVariable Integer metadataId, 
            @RequestBody MetadataDTO metadataDTO) {
        MetadataDTO updatedMetadata = mapDataService.updateMetadata(mapdataId, metadataId, metadataDTO);
        return ResponseEntity.ok(updatedMetadata);
    }
    
    @DeleteMapping("/mapdata/{mapdataId}/metadata/{metadataId}")
    public ResponseEntity<Void> deleteMetadata(
            @PathVariable Integer mapdataId, 
            @PathVariable Integer metadataId) {
        mapDataService.deleteMetadataById(metadataId);
        return ResponseEntity.noContent().build();
    }
    
    @DeleteMapping("/mapdata/{mapdataId}/metadata/all")
    public ResponseEntity<Void> deleteAllMetadata(@PathVariable Integer mapdataId) {
        mapDataService.deleteMetadataByMapdataId(mapdataId);
        return ResponseEntity.noContent().build();
    }
}
