package lu.joaofaria.java.hello.api.repository;

import lu.joaofaria.java.hello.api.entity.MapdataEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MapDataRepository extends JpaRepository<MapdataEntity, Integer> {
}
