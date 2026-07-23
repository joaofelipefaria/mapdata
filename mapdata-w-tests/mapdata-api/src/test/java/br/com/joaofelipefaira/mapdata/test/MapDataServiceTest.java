package br.com.joaofelipefaira.mapdata.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.jfelipefaria.mapdata.api.dto.MapdataDTO;
import br.com.jfelipefaria.mapdata.api.entity.MapdataEntity;
import br.com.jfelipefaria.mapdata.api.repository.MapDataRepository;
import br.com.jfelipefaria.mapdata.api.repository.MetadataRepository;
import br.com.jfelipefaria.mapdata.api.service.MapDataService;

@ExtendWith(MockitoExtension.class)
class MapDataServiceTest {
 
    @Mock
    private MapDataRepository mapDataRepository;
 
    @Mock
    private MetadataRepository metadataRepository;
 
    @InjectMocks
    private MapDataService mapDataService;
 
    @Test
    void getAllMapData_returnsMappedDtos() {
        MapdataEntity entity = new MapdataEntity();
        entity.setId(1);
        entity.setValue("value-1");
 
        when(mapDataRepository.findAll()).thenReturn(List.of(entity));
 
        List<MapdataDTO> result = mapDataService.getAllMapData();
 
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getId()).isEqualTo(1);
        assertThat(result.get(0).getValue()).isEqualTo("value-1");
    }
}