package lu.joaofaria.java.mapdata.api.quarkus.model.service;

import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.enterprise.context.ApplicationScoped;
import lu.joaofaria.java.mapdata.api.quarkus.model.entity.MapData;
import lu.joaofaria.java.mapdata.api.quarkus.model.entity.Metadata;

@ApplicationScoped
public class MapDataService {

    public MapData createMapData(String value) {
        MapData mapData = new MapData(value);
        mapData.persist();  // Panache handles persistence
        return mapData;
    }

    public List<MapData> getAllMapData() {
        return MapData.listAll();  // Using Panache to fetch all
    }
    
    public Metadata createMetadata(MapData mapData, String value1, String value2) {
        Metadata metadata = new Metadata(mapData, value1, value2);
        metadata.persist();
        return metadata;
    }

	public List<Metadata> getMetadataByMapData(MapData mapData) {
		return PanacheEntity.find("mapData", mapData).list();
	}
}
