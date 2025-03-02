package lu.joaofaria.java.hello.api.dto;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MapdataDTO {
    private Integer id;
    private String value;
    private List<MetadataDTO> metadata;
    
}
