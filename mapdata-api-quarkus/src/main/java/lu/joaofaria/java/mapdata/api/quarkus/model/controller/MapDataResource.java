package lu.joaofaria.java.mapdata.api.quarkus.model.controller;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lu.joaofaria.java.mapdata.api.quarkus.model.entity.MapData;
import lu.joaofaria.java.mapdata.api.quarkus.model.entity.Metadata;
import lu.joaofaria.java.mapdata.api.quarkus.model.service.MapDataService;

@Path("mapdata")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MapDataResource {

    @Inject
    MapDataService mapDataService;

    @GET
    public List<MapData> getAll() {
        return mapDataService.getAllMapData();
    }

    @POST
    public MapData create(MapData mapData) {
        return mapDataService.createMapData(mapData.value);
    }
    @POST
    @Path("/metadata")
    public Metadata create(Metadata metadata) {
        return mapDataService.createMetadata(metadata.mapData, metadata.value1, metadata.value2);
    }

    @GET
    @Path("/mapdata/{mapDataId}")
    public List<Metadata> getByMapData(@PathParam("mapDataId") Long mapDataId) {
        MapData mapData = MapData.findById(mapDataId);
        return mapDataService.getMetadataByMapData(mapData);
    }

}
