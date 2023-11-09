package lu.joaofaria.java.samples.mapdata.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lu.joaofaria.java.samples.mapdata.api.model.MapData;

@Repository
public interface MapDataRepository extends JpaRepository<MapData, Long>{

}
