package br.com.jfelipefaria.mapdata.api.dto;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for MapData.
 * Used to transfer data between layers without exposing entity details.
 */
@Data
@NoArgsConstructor
public class MapdataDTO {
    private Integer id;
    private String value;
    private List<MetadataDTO> metadata;
}