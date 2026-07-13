package br.com.jfelipefaria.mapdata.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jfelipefaria.mapdata.api.entity.MapdataEntity;

/**
 * Repository interface for MapdataEntity.
 * Extends JpaRepository to provide CRUD operations.
 * Annotated with @Repository for Spring Data JPA.
 */
@Repository
public interface MapDataRepository extends JpaRepository<MapdataEntity, Integer> {
}