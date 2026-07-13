package br.com.jfelipefaria.mapdata.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.jfelipefaria.mapdata.api.entity.MetadataEntity;

/**
 * Repository interface for MetadataEntity.
 * Extends JpaRepository to provide CRUD operations.
 * Includes custom queries for finding and deleting metadata by MapData ID.
 */
@Repository
public interface MetadataRepository extends JpaRepository<MetadataEntity, Integer> {
    /**
     * Find all MetadataEntity records by MapData ID.
     * @param idmapdata MapData ID.
     * @return List of MetadataEntity objects.
     */
    List<MetadataEntity> findByMapdataId(Integer idmapdata);

    /**
     * Delete all MetadataEntity records by MapData ID.
     * Uses a custom JPQL query.
     * @param mapdataId MapData ID.
     */
    @Modifying
    @Transactional
    @Query("DELETE FROM MetadataEntity m WHERE m.mapdata.id = :mapdataId")
    void deleteAllByMapdataId(@Param("mapdataId") Integer mapdataId);
}