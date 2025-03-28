package lu.joaofaria.java.mapdata.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lu.joaofaria.java.mapdata.api.entity.MapdataEntity;

@Repository
public interface MapDataRepository extends JpaRepository<MapdataEntity, Integer> {
}
