package br.com.joaofelipefaira.mapdata.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.jfelipefaria.mapdata.api.dto.MapdataDTO;
import br.com.jfelipefaria.mapdata.api.dto.MetadataDTO;
import br.com.jfelipefaria.mapdata.api.entity.MapdataEntity;
import br.com.jfelipefaria.mapdata.api.entity.MetadataEntity;
import br.com.jfelipefaria.mapdata.api.repository.MapDataRepository;
import br.com.jfelipefaria.mapdata.api.repository.MetadataRepository;
import br.com.jfelipefaria.mapdata.api.service.MapDataService;
import jakarta.persistence.EntityNotFoundException;

@ExtendWith(MockitoExtension.class)
@DisplayName("MapDataServiceComplete")
class MapDataServiceCompleteTest {

	@Mock
	private MapDataRepository mapDataRepository;

	@Mock
	private MetadataRepository metadataRepository;

	@InjectMocks
	private MapDataService mapDataService;

	// ---------------------------------------------------------------
	// Test data builders
	// ---------------------------------------------------------------

	private static MapdataEntity mapdataEntity(Integer id, String value) {
		MapdataEntity entity = new MapdataEntity();
		entity.setId(id);
		entity.setValue(value);
		return entity;
	}

	private static MetadataEntity metadataEntity(Integer id, MapdataEntity owner, String value1, String value2) {
		MetadataEntity entity = new MetadataEntity();
		entity.setId(id);
		entity.setMapdata(owner);
		entity.setValue1(value1);
		entity.setValue2(value2);
		return entity;
	}

	private static MapdataDTO mapdataDTO(Integer id, String value, List<MetadataDTO> metadata) {
		MapdataDTO dto = new MapdataDTO();
		dto.setId(id);
		dto.setValue(value);
		dto.setMetadata(metadata);
		return dto;
	}

	private static MetadataDTO metadataDTO(Integer id, String value1, String value2) {
		MetadataDTO dto = new MetadataDTO();
		dto.setId(id);
		dto.setValue1(value1);
		dto.setValue2(value2);
		return dto;
	}

	// =================================================================
	// getAllMapData()
	// =================================================================

	@Nested
	@DisplayName("getAllMapData()")
	class GetAllMapData {

	@Test
 @DisplayName("returns empty list when there are no records")
 void returnsEmptyList_whenRepositoryHasNoRecords() {
 when(mapDataRepository.findAll()).thenReturn(Collections.emptyList());

 List<MapdataDTO> result = mapDataService.getAllMapData();

 assertThat(result).isEmpty();
 verify(mapDataRepository, times(1)).findAll();
 }

		@Test
		@DisplayName("maps each entity to DTO, preserving id and value")
		void mapsEachEntityToDto() {
			MapdataEntity entity1 = mapdataEntity(1, "value-1");
			MapdataEntity entity2 = mapdataEntity(2, "value-2");
			when(mapDataRepository.findAll()).thenReturn(List.of(entity1, entity2));

			List<MapdataDTO> result = mapDataService.getAllMapData();

			assertThat(result).hasSize(2);
			assertThat(result.get(0).getId()).isEqualTo(1);
			assertThat(result.get(0).getValue()).isEqualTo("value-1");
			assertThat(result.get(1).getId()).isEqualTo(2);
			assertThat(result.get(1).getValue()).isEqualTo("value-2");
		}

		@Test
		@DisplayName("when the entity has no metadata (null), the DTO has metadata null")
		void leavesMetadataNull_whenEntityMetadataIsNull() {
			MapdataEntity entity = mapdataEntity(1, "value-1");
			entity.setMetadata(null);
			when(mapDataRepository.findAll()).thenReturn(List.of(entity));

			List<MapdataDTO> result = mapDataService.getAllMapData();

			assertThat(result.get(0).getMetadata()).isNull();
		}

		@Test
		@DisplayName("when the entity has metadata, each item is also converted to DTO")
		void mapsNestedMetadata_whenEntityHasMetadata() {
			MapdataEntity entity = mapdataEntity(1, "value-1");
			MetadataEntity meta = metadataEntity(10, entity, "v1", "v2");
			entity.setMetadata(List.of(meta));
			when(mapDataRepository.findAll()).thenReturn(List.of(entity));

			List<MapdataDTO> result = mapDataService.getAllMapData();

			assertThat(result.get(0).getMetadata()).hasSize(1);
			assertThat(result.get(0).getMetadata().get(0).getId()).isEqualTo(10);
			assertThat(result.get(0).getMetadata().get(0).getValue1()).isEqualTo("v1");
			assertThat(result.get(0).getMetadata().get(0).getValue2()).isEqualTo("v2");
		}
	}

	// =================================================================
	// getMapDataById(Integer id)
	// =================================================================

	@Nested
	@DisplayName("getMapDataById(Integer id)")
	class GetMapDataById {

		@Test
		@DisplayName("returns present Optional when the id exists")
		void returnsPresentOptional_whenFound() {
			MapdataEntity entity = mapdataEntity(1, "value-1");
			when(mapDataRepository.findById(1)).thenReturn(Optional.of(entity));

			Optional<MapdataDTO> result = mapDataService.getMapDataById(1);

			assertThat(result).isPresent();
			assertThat(result.get().getId()).isEqualTo(1);
			assertThat(result.get().getValue()).isEqualTo("value-1");
		}

	@Test
 @DisplayName("returns empty Optional when the id does not exist")
 void returnsEmptyOptional_whenNotFound() {
 when(mapDataRepository.findById(99)).thenReturn(Optional.empty());

 Optional<MapdataDTO> result = mapDataService.getMapDataById(99);

 assertThat(result).isEmpty();
 }

	@Test
 @DisplayName("forwards the search for the correct id to the repository")
 void queriesRepositoryWithGivenId() {
 when(mapDataRepository.findById(42)).thenReturn(Optional.empty());

 mapDataService.getMapDataById(42);

 verify(mapDataRepository, times(1)).findById(42);
 }
	}

	// =================================================================
	// deleteById(Integer id)
	// =================================================================

	@Nested
	@DisplayName("deleteById(Integer id)")
	class DeleteById {

		@Test
		@DisplayName("removes associated metadata before removing the mapdata")
		void deletesMetadataBeforeMapdata() {
			mapDataService.deleteById(5);

			InOrder inOrder = Mockito.inOrder(metadataRepository, mapDataRepository);
			inOrder.verify(metadataRepository).deleteAllByMapdataId(5);
			inOrder.verify(mapDataRepository).deleteById(5);
		}

		@Test
		@DisplayName("calls each repository exactly once, with the correct id")
		void callsEachRepositoryOnceWithGivenId() {
			mapDataService.deleteById(7);

			verify(metadataRepository, times(1)).deleteAllByMapdataId(7);
			verify(mapDataRepository, times(1)).deleteById(7);
		}
	}

	// =================================================================
	// deleteAllMapData()
	// =================================================================

	@Nested
	@DisplayName("deleteAllMapData()")
	class DeleteAllMapData {

		@Test
		@DisplayName("removes all metadata before removing all mapdata")
		void deletesAllMetadataBeforeAllMapdata() {
			mapDataService.deleteAllMapData();

			InOrder inOrder = Mockito.inOrder(metadataRepository, mapDataRepository);
			inOrder.verify(metadataRepository).deleteAll();
			inOrder.verify(mapDataRepository).deleteAll();
		}

		@Test
		@DisplayName("does not filter by id - deleteAll() is called without arguments on both repositories")
		void callsDeleteAllWithNoArguments() {
			mapDataService.deleteAllMapData();

			verify(metadataRepository, times(1)).deleteAll();
			verify(mapDataRepository, times(1)).deleteAll();
		}
	}

	// =================================================================
	// create(MapdataDTO data)
	// =================================================================

	@Nested
	@DisplayName("create(MapdataDTO data)")
	class Create {

		@Test
		@DisplayName("converts the DTO to entity, saves and returns the resulting DTO")
		void savesConvertedEntity_andReturnsDto() {
			MapdataDTO input = mapdataDTO(null, "new-value", null);
			MapdataEntity saved = mapdataEntity(1, "new-value");
			when(mapDataRepository.save(any(MapdataEntity.class))).thenReturn(saved);

			MapdataDTO result = mapDataService.create(input);

			assertThat(result.getId()).isEqualTo(1);
			assertThat(result.getValue()).isEqualTo("new-value");
		}

		@Test
		@DisplayName("passes id and value from input DTO to the persisted entity")
		void buildsEntityWithFieldsFromInputDto() {
			MapdataDTO input = mapdataDTO(null, "new-value", null);
			when(mapDataRepository.save(any(MapdataEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

			ArgumentCaptor<MapdataEntity> captor = ArgumentCaptor.forClass(MapdataEntity.class);

			mapDataService.create(input);

			verify(mapDataRepository).save(captor.capture());
			assertThat(captor.getValue().getId()).isNull();
			assertThat(captor.getValue().getValue()).isEqualTo("new-value");
		}

		@Test
		@DisplayName("when the DTO has no metadata (null), the entity also has metadata null")
		void leavesEntityMetadataNull_whenDtoMetadataIsNull() {
			MapdataDTO input = mapdataDTO(null, "new-value", null);
			when(mapDataRepository.save(any(MapdataEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

			ArgumentCaptor<MapdataEntity> captor = ArgumentCaptor.forClass(MapdataEntity.class);

			mapDataService.create(input);

			verify(mapDataRepository).save(captor.capture());
			assertThat(captor.getValue().getMetadata()).isNull();
		}

		@Test
		@DisplayName("when the DTO has metadata, each item is converted to MetadataEntity")
		void convertsNestedMetadata_whenDtoHasMetadata() {
			MetadataDTO metaDto = metadataDTO(1, "v1", "v2");
			MapdataDTO input = mapdataDTO(null, "new-value", List.of(metaDto));
			when(mapDataRepository.save(any(MapdataEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

			ArgumentCaptor<MapdataEntity> captor = ArgumentCaptor.forClass(MapdataEntity.class);

			mapDataService.create(input);

			verify(mapDataRepository).save(captor.capture());
			assertThat(captor.getValue().getMetadata()).hasSize(1);
			assertThat(captor.getValue().getMetadata().get(0).getValue1()).isEqualTo("v1");
			assertThat(captor.getValue().getMetadata().get(0).getValue2()).isEqualTo("v2");
		}

		@Test
		@DisplayName("an empty metadata list is preserved as an empty list (does not become null)")
		void preservesEmptyMetadataList() {
			MapdataDTO input = mapdataDTO(null, "new-value", Collections.emptyList());
			when(mapDataRepository.save(any(MapdataEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

			ArgumentCaptor<MapdataEntity> captor = ArgumentCaptor.forClass(MapdataEntity.class);

			mapDataService.create(input);

			verify(mapDataRepository).save(captor.capture());
			assertThat(captor.getValue().getMetadata()).isNotNull().isEmpty();
		}

		@Test
		@DisplayName("does not interact with the MetadataRepository - nested metadata is not persisted separately")
		void doesNotInteractWithMetadataRepository() {
			MapdataDTO input = mapdataDTO(null, "new-value", List.of(metadataDTO(1, "v1", "v2")));
			when(mapDataRepository.save(any(MapdataEntity.class))).thenReturn(mapdataEntity(1, "new-value"));

			mapDataService.create(input);

			verifyNoInteractions(metadataRepository);
		}
	}

	// =================================================================
	// update(MapdataDTO data)
	// =================================================================

	@Nested
	@DisplayName("update(MapdataDTO data)")
	class Update {

		@Test
		@DisplayName("converts the DTO to entity, saves and returns the resulting DTO")
		void savesConvertedEntity_andReturnsDto() {
			MapdataDTO input = mapdataDTO(1, "updated-value", null);
			MapdataEntity saved = mapdataEntity(1, "updated-value");
			when(mapDataRepository.save(any(MapdataEntity.class))).thenReturn(saved);

			MapdataDTO result = mapDataService.update(input);

			assertThat(result.getId()).isEqualTo(1);
			assertThat(result.getValue()).isEqualTo("updated-value");
		}

		@Test
		@DisplayName("preserves the existing id from the DTO on the entity (save() performs merge/update)")
		void preservesExistingIdOnEntity() {
			MapdataDTO input = mapdataDTO(1, "updated-value", null);
			when(mapDataRepository.save(any(MapdataEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

			ArgumentCaptor<MapdataEntity> captor = ArgumentCaptor.forClass(MapdataEntity.class);

			mapDataService.update(input);

			verify(mapDataRepository).save(captor.capture());
			assertThat(captor.getValue().getId()).isEqualTo(1);
		}

		@Test
		@DisplayName("when the DTO has metadata, each item is converted to MetadataEntity")
		void convertsNestedMetadata_whenDtoHasMetadata() {
			MapdataDTO input = mapdataDTO(1, "updated-value", List.of(metadataDTO(10, "v1", "v2")));
			when(mapDataRepository.save(any(MapdataEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

			ArgumentCaptor<MapdataEntity> captor = ArgumentCaptor.forClass(MapdataEntity.class);

			mapDataService.update(input);

			verify(mapDataRepository).save(captor.capture());
			assertThat(captor.getValue().getMetadata()).hasSize(1);
			assertThat(captor.getValue().getMetadata().get(0).getId()).isEqualTo(10);
		}
	}

	// =================================================================
	// getMetadataById(Integer id)
	// =================================================================

	@Nested
	@DisplayName("getMetadataById(Integer id)")
	class GetMetadataById {

		@Test
		@DisplayName("returns present Optional when the id exists")
		void returnsPresentOptional_whenFound() {
			MetadataEntity entity = metadataEntity(10, mapdataEntity(1, "owner"), "v1", "v2");
			when(metadataRepository.findById(10)).thenReturn(Optional.of(entity));

			Optional<MetadataEntity> result = mapDataService.getMetadataById(10);

			assertThat(result).contains(entity);
		}

	@Test
 @DisplayName("returns empty Optional when the id does not exist")
 void returnsEmptyOptional_whenNotFound() {
 when(metadataRepository.findById(999)).thenReturn(Optional.empty());

 Optional<MetadataEntity> result = mapDataService.getMetadataById(999);

 assertThat(result).isEmpty();
 }
	}

	// =================================================================
	// getMetadataByMapdataId(Integer mapdataId)
	// =================================================================

	@Nested
	@DisplayName("getMetadataByMapdataId(Integer mapdataId)")
	class GetMetadataByMapdataId {

		@Test
		@DisplayName("returns the repository's metadata list unchanged")
		void delegatesDirectlyToRepository() {
			MapdataEntity owner = mapdataEntity(1, "owner");
			List<MetadataEntity> expected = List.of(metadataEntity(10, owner, "v1", "v2"));
			when(metadataRepository.findByMapdataId(1)).thenReturn(expected);

			List<MetadataEntity> result = mapDataService.getMetadataByMapdataId(1);

			assertThat(result).isEqualTo(expected);
		}

	@Test
 @DisplayName("returns empty list when there is no metadata for the mapdataId")
 void returnsEmptyList_whenNoneFound() {
 when(metadataRepository.findByMapdataId(1)).thenReturn(Collections.emptyList());

 List<MetadataEntity> result = mapDataService.getMetadataByMapdataId(1);

 assertThat(result).isEmpty();
 }
	}

	// =================================================================
	// createMetadata(Integer mapdataId, MetadataDTO metadataDTO)
	// =================================================================

	@Nested
	@DisplayName("createMetadata(Integer mapdataId, MetadataDTO metadataDTO)")
	class CreateMetadata {

		@Test
		@DisplayName("when the Mapdata exists, associates, saves and returns the created metadata DTO")
		void createsMetadata_whenMapdataExists() {
			MapdataEntity owner = mapdataEntity(1, "owner");
			MetadataDTO inputDto = metadataDTO(null, "v1", "v2");
			MetadataEntity saved = metadataEntity(10, owner, "v1", "v2");

			when(mapDataRepository.findById(1)).thenReturn(Optional.of(owner));
			when(metadataRepository.save(any(MetadataEntity.class))).thenReturn(saved);

			MetadataDTO result = mapDataService.createMetadata(1, inputDto);

			assertThat(result.getId()).isEqualTo(10);
			assertThat(result.getValue1()).isEqualTo("v1");
			assertThat(result.getValue2()).isEqualTo("v2");
		}

		@Test
		@DisplayName("associates the found Mapdata entity to the MetadataEntity before saving")
		void associatesFoundMapdataEntity_beforeSaving() {
			MapdataEntity owner = mapdataEntity(1, "owner");
			when(mapDataRepository.findById(1)).thenReturn(Optional.of(owner));
			when(metadataRepository.save(any(MetadataEntity.class)))
					.thenAnswer(invocation -> invocation.getArgument(0));

			ArgumentCaptor<MetadataEntity> captor = ArgumentCaptor.forClass(MetadataEntity.class);

			mapDataService.createMetadata(1, metadataDTO(null, "v1", "v2"));

			verify(metadataRepository).save(captor.capture());
			assertThat(captor.getValue().getMapdata()).isSameAs(owner);
		}

	@Test
 @DisplayName("when the Mapdata does not exist, throws EntityNotFoundException and does not save metadata")
 void throwsEntityNotFoundException_whenMapdataDoesNotExist() {
 when(mapDataRepository.findById(99)).thenReturn(Optional.empty());

 assertThatThrownBy(() -> mapDataService.createMetadata(99, metadataDTO(null, "v1", "v2")))
 .isInstanceOf(EntityNotFoundException.class)
 .hasMessageContaining("99");

 verify(metadataRepository, never()).save(any());
 }
	}

	// =================================================================
	// updateMetadata(Integer mapdataId, Integer metadataId, MetadataDTO
	// metadataDTO)
	// =================================================================

	@Nested
	@DisplayName("updateMetadata(Integer mapdataId, Integer metadataId, MetadataDTO metadataDTO)")
	class UpdateMetadata {

		@Test
		@DisplayName("when the metadata exists and belongs to the provided mapdata, updates value1/value2 and saves")
		void updatesValues_whenMetadataExistsAndBelongsToMapdata() {
			MapdataEntity owner = mapdataEntity(1, "owner");
			MetadataEntity existing = metadataEntity(10, owner, "old-v1", "old-v2");
			MetadataDTO updateDto = metadataDTO(10, "new-v1", "new-v2");

			when(metadataRepository.findById(10)).thenReturn(Optional.of(existing));
			when(metadataRepository.save(any(MetadataEntity.class)))
					.thenAnswer(invocation -> invocation.getArgument(0));

			MetadataDTO result = mapDataService.updateMetadata(1, 10, updateDto);

			assertThat(result.getValue1()).isEqualTo("new-v1");
			assertThat(result.getValue2()).isEqualTo("new-v2");
		}

		@Test
		@DisplayName("persists the MetadataEntity with the new values before returning")
		void savesEntityWithUpdatedValues() {
			MapdataEntity owner = mapdataEntity(1, "owner");
			MetadataEntity existing = metadataEntity(10, owner, "old-v1", "old-v2");

			when(metadataRepository.findById(10)).thenReturn(Optional.of(existing));
			when(metadataRepository.save(any(MetadataEntity.class)))
					.thenAnswer(invocation -> invocation.getArgument(0));

			ArgumentCaptor<MetadataEntity> captor = ArgumentCaptor.forClass(MetadataEntity.class);

			mapDataService.updateMetadata(1, 10, metadataDTO(10, "new-v1", "new-v2"));

			verify(metadataRepository).save(captor.capture());
			assertThat(captor.getValue().getValue1()).isEqualTo("new-v1");
			assertThat(captor.getValue().getValue2()).isEqualTo("new-v2");
			// owner association is untouched by the update
			assertThat(captor.getValue().getMapdata()).isSameAs(owner);
		}

	@Test
 @DisplayName("when the metadata does not exist, throws EntityNotFoundException and does not save")
 void throwsEntityNotFoundException_whenMetadataDoesNotExist() {
 when(metadataRepository.findById(10)).thenReturn(Optional.empty());

 assertThatThrownBy(() -> mapDataService.updateMetadata(1, 10, metadataDTO(10, "v1", "v2")))
 .isInstanceOf(EntityNotFoundException.class)
 .hasMessageContaining("10");

 verify(metadataRepository, never()).save(any());
 }

		@Test
		@DisplayName("when the metadata exists but belongs to another mapdata, throws IllegalArgumentException and does not save")
		void throwsIllegalArgumentException_whenMetadataBelongsToDifferentMapdata() {
			MapdataEntity actualOwner = mapdataEntity(2, "owner-2");
			MetadataEntity existing = metadataEntity(10, actualOwner, "old-v1", "old-v2");

			when(metadataRepository.findById(10)).thenReturn(Optional.of(existing));

			assertThatThrownBy(() -> mapDataService.updateMetadata(1, 10, metadataDTO(10, "new-v1", "new-v2")))
					.isInstanceOf(IllegalArgumentException.class);

			verify(metadataRepository, never()).save(any());
		}
	}

	// =================================================================
	// deleteMetadataById(Integer id)
	// =================================================================

	@Nested
	@DisplayName("deleteMetadataById(Integer id)")
	class DeleteMetadataById {

		@Test
		@DisplayName("delegates to metadataRepository.deleteById with the correct id")
		void delegatesToRepositoryWithGivenId() {
			mapDataService.deleteMetadataById(10);

			verify(metadataRepository, times(1)).deleteById(10);
			verifyNoInteractions(mapDataRepository);
		}
	}

	// =================================================================
	// deleteMetadataByMapdataId(Integer id)
	// =================================================================

	@Nested
	@DisplayName("deleteMetadataByMapdataId(Integer id)")
	class DeleteMetadataByMapdataId {

		@Test
		@DisplayName("delegates to metadataRepository.deleteAllByMapdataId with the correct id")
		void delegatesToRepositoryWithGivenId() {
			mapDataService.deleteMetadataByMapdataId(1);

			verify(metadataRepository, times(1)).deleteAllByMapdataId(1);
			verifyNoInteractions(mapDataRepository);
		}
	}
}
