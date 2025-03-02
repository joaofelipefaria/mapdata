package lu.joaofaria.java.hello.api.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lu.joaofaria.java.hello.api.dto.MapdataDTO;
import lu.joaofaria.java.hello.api.dto.MetadataDTO;
import lu.joaofaria.java.hello.api.entity.MapdataEntity;
import lu.joaofaria.java.hello.api.entity.MetadataEntity;
import lu.joaofaria.java.hello.api.repository.MapDataRepository;
import lu.joaofaria.java.hello.api.repository.MetadataRepository;

@Service
public class MapDataService {

    @Autowired
    private MapDataRepository mapDataRepository;
    @Autowired
    private MetadataRepository metadataRepository;
    
    //MAP DATA METHODS
    public List<MapdataDTO> getAllMapData() {
        return mapDataRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<MapdataDTO> getMapDataById(Integer id) {
        return mapDataRepository.findById(id)
                .map(this::convertToDTO);
    }

    public void deleteById(Integer id) {
    	metadataRepository.deleteAllByMapdataId(id);
        mapDataRepository.deleteById(id);
    }

    public void deleteAllMapData() {
    	metadataRepository.deleteAll();
        mapDataRepository.deleteAll();
    }
    
    public MapdataDTO create(MapdataDTO data) {
    	MapdataEntity mapdataDb = convertToEntity(data);
		return convertToDTO(mapDataRepository.save(mapdataDb));
    }
    
    public MapdataDTO update(MapdataDTO data) {
    	MapdataEntity mapdataDb = convertToEntity(data);
		return convertToDTO(mapDataRepository.save(mapdataDb));
    }
    
    //METADAAT METHODS

	public Optional<MetadataEntity> getMetadataById(Integer id) {
        return metadataRepository.findById(id);
	}
	
    public List<MetadataEntity> getMetadataByMapdataId(Integer mapdataId) {
        return metadataRepository.findByMapdataId(mapdataId);
    }
    
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
        
        MetadataEntity updatedMetadataEntity = metadataRepository.save(metadataEntity);
        
        return convertMetadataToDTO(updatedMetadataEntity);
    }

    public void deleteMetadataById(Integer id) {
    	metadataRepository.deleteById(id);
    }

    public void deleteMetadataByMapdataId(Integer id) {
    	metadataRepository.deleteAllByMapdataId(id);
    }

    //CONVERSION
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

    private MetadataDTO convertMetadataToDTO(MetadataEntity metadata) {
        MetadataDTO dto = new MetadataDTO();
        dto.setId(metadata.getId());
        dto.setValue1(metadata.getValue1());
        dto.setValue2(metadata.getValue2());
        return dto;
    }
    
    private MetadataEntity convertMetadataToEntity(MetadataDTO dto) {
        MetadataEntity entity = new MetadataEntity();
        entity.setId(dto.getId());
        entity.setValue1(dto.getValue1());
        entity.setValue2(dto.getValue2());
        return entity;
    }
}
