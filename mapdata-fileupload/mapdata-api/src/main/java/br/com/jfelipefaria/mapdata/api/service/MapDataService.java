package br.com.jfelipefaria.mapdata.api.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jfelipefaria.mapdata.api.dto.MapdataDTO;
import br.com.jfelipefaria.mapdata.api.dto.MetadataDTO;
import br.com.jfelipefaria.mapdata.api.entity.MapdataEntity;
import br.com.jfelipefaria.mapdata.api.entity.MetadataEntity;
import br.com.jfelipefaria.mapdata.api.repository.MapDataRepository;
import br.com.jfelipefaria.mapdata.api.repository.MetadataRepository;
import jakarta.persistence.EntityNotFoundException;

/**
 * Service class for business logic related to MapData and Metadata entities.
 * Handles CRUD operations and conversion between DTOs and entities.
 * Annotated with @Service to be managed by Spring's dependency injection.
 */
@Service
public class MapDataService {

    @Autowired
    private MapDataRepository mapDataRepository;
    @Autowired
    private MetadataRepository metadataRepository;

    // --- MapData Methods ---

    /**
     * Retrieve all MapData records.
     * @return List of MapdataDTO objects.
     */
    public List<MapdataDTO> getAllMapData() {
        return mapDataRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retrieve a MapData record by its ID.
     * @param id MapData ID.
     * @return Optional containing MapdataDTO if found.
     */
    public Optional<MapdataDTO> getMapDataById(Integer id) {
        return mapDataRepository.findById(id)
                .map(this::convertToDTO);
    }

    /**
     * Delete a MapData record and its associated Metadata by ID.
     * @param id MapData ID.
     */
    public void deleteById(Integer id) {
        metadataRepository.deleteAllByMapdataId(id);
        mapDataRepository.deleteById(id);
    }

    /**
     * Delete all MapData and Metadata records.
     */
    public void deleteAllMapData() {
        metadataRepository.deleteAll();
        mapDataRepository.deleteAll();
    }

    /**
     * Create a new MapData record.
     * @param data MapdataDTO object.
     * @return Created MapdataDTO.
     */
    public MapdataDTO create(MapdataDTO data) {
        MapdataEntity mapdataDb = convertToEntity(data);
        return convertToDTO(mapDataRepository.save(mapdataDb));
    }

    /**
     * Update an existing MapData record.
     * @param data MapdataDTO object.
     * @return Updated MapdataDTO.
     */
    public MapdataDTO update(MapdataDTO data) {
        MapdataEntity mapdataDb = convertToEntity(data);
        return convertToDTO(mapDataRepository.save(mapdataDb));
    }

    // --- Metadata Methods ---

    /**
     * Retrieve a Metadata record by its ID.
     * @param id Metadata ID.
     * @return Optional containing MetadataEntity if found.
     */
    public Optional<MetadataEntity> getMetadataById(Integer id) {
        return metadataRepository.findById(id);
    }

    /**
     * Retrieve all Metadata records for a given MapData ID.
     * @param mapdataId MapData ID.
     * @return List of MetadataEntity objects.
     */
    public List<MetadataEntity> getMetadataByMapdataId(Integer mapdataId) {
        return metadataRepository.findByMapdataId(mapdataId);
    }

    /**
     * Create a new Metadata record for a given MapData.
     * @param mapdataId MapData ID.
     * @param metadataDTO MetadataDTO object.
     * @return Created MetadataDTO.
     */
    public MetadataDTO createMetadata(Integer mapdataId, MetadataDTO metadataDTO) {
        Optional<MapdataEntity> mapdataEntityOptional = mapDataRepository.findById(mapdataId);
        if (!mapdataEntityOptional.isPresent()) {
            throw new EntityNotFoundException("MapdataEntity not found with ID " + mapdataId);
        }
        MapdataEntity mapdataEntity = mapdataEntityOptional.get();
        MetadataEntity metadataEntity = convertMetadataToEntity(metadataDTO);
        metadataEntity.setMapdata(mapdataEntity);

        MetadataEntity savedMetadataEntity = metadataRepository.save(metadataEntity);

        return convertMetadataToDTO(savedMetadataEntity);
    }

    /**
     * Update an existing Metadata record.
     * @param mapdataId MapData ID.
     * @param metadataId Metadata ID.
     * @param metadataDTO MetadataDTO object.
     * @return Updated MetadataDTO.
     */
    public MetadataDTO updateMetadata(Integer mapdataId, Integer metadataId, MetadataDTO metadataDTO) {
        Optional<MetadataEntity> metadataEntityOptional = metadataRepository.findById(metadataId);
        if (!metadataEntityOptional.isPresent()) {
            throw new EntityNotFoundException("MetadataEntity not found with ID " + metadataId);
        }

        MetadataEntity metadataEntity = metadataEntityOptional.get();

        if (!metadataEntity.getMapdata().getId().equals(mapdataId)) {
            throw new IllegalArgumentException("The Metadata does not belong to the specified Mapdata.");
        }

        metadataEntity.setValue1(metadataDTO.getValue1());
        metadataEntity.setValue2(metadataDTO.getValue2());
        metadataEntity.setFileName(metadataDTO.getFileName());
        
        MetadataEntity updatedMetadataEntity = metadataRepository.save(metadataEntity);

        return convertMetadataToDTO(updatedMetadataEntity);
    }

    /**
     * Delete a Metadata record by its ID.
     * @param id Metadata ID.
     */
    public void deleteMetadataById(Integer id) {
        metadataRepository.deleteById(id);
    }

    /**
     * Delete all Metadata records for a given MapData ID.
     * @param id MapData ID.
     */
    public void deleteMetadataByMapdataId(Integer id) {
        metadataRepository.deleteAllByMapdataId(id);
    }

    // --- Conversion Methods ---

    /**
     * Convert MapdataEntity to MapdataDTO.
     * @param mapdata MapdataEntity object.
     * @return MapdataDTO object.
     */
    private MapdataDTO convertToDTO(MapdataEntity mapdata) {
        MapdataDTO dto = new MapdataDTO();
        dto.setId(mapdata.getId());
        dto.setValue(mapdata.getValue());
        if (mapdata.getMetadata() != null) {
            dto.setMetadata(
                mapdata.getMetadata().stream()
                    .map(this::convertMetadataToDTO)
                    .collect(Collectors.toList())
            );
        }
        return dto;
    }

    /**
     * Convert MapdataDTO to MapdataEntity.
     * @param dto MapdataDTO object.
     * @return MapdataEntity object.
     */
    private MapdataEntity convertToEntity(MapdataDTO dto) {
        MapdataEntity entity = new MapdataEntity();
        entity.setId(dto.getId());
        entity.setValue(dto.getValue());
        if (dto.getMetadata() != null) {
            entity.setMetadata(
                dto.getMetadata().stream()
                    .map(this::convertMetadataToEntity)
                    .collect(Collectors.toList())
            );
        }
        return entity;
    }

    /**
     * Convert MetadataEntity to MetadataDTO.
     * @param metadata MetadataEntity object.
     * @return MetadataDTO object.
     */
    private MetadataDTO convertMetadataToDTO(MetadataEntity metadata) {
        MetadataDTO dto = new MetadataDTO();
        dto.setId(metadata.getId());
        dto.setValue1(metadata.getValue1());
        dto.setValue2(metadata.getValue2());
        return dto;
    }

    /**
     * Convert MetadataDTO to MetadataEntity.
     * @param dto MetadataDTO object.
     * @return MetadataEntity object.
     */
    private MetadataEntity convertMetadataToEntity(MetadataDTO dto) {
        MetadataEntity entity = new MetadataEntity();
        entity.setId(dto.getId());
        entity.setValue1(dto.getValue1());
        entity.setValue2(dto.getValue2());
        return entity;
    }
}