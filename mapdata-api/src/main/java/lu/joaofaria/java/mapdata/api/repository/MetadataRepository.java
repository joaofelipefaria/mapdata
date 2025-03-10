package lu.joaofaria.java.hello.api.repository;

import lu.joaofaria.java.hello.api.entity.MetadataEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MetadataRepository extends JpaRepository<MetadataEntity, Integer> {
	List<MetadataEntity> findByMapdataId(Integer idmapdata);
	
	@Modifying
    @Transactional
    @Query("DELETE FROM MetadataEntity m WHERE m.mapdata.id = :mapdataId")
    void deleteAllByMapdataId(@Param("mapdataId") Integer mapdataId);
}
