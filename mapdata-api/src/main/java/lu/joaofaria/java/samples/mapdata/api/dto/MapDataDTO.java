package lu.joaofaria.java.samples.mapdata.api.dto;

import lu.joaofaria.java.samples.mapdata.api.model.MapData;

public class MapDataDTO {

	private String id;
	private String value;
	
	public MapDataDTO() {
		
	}
	
	public MapDataDTO(String id, String value) {
		this.id = id;
		this.value = value;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "MapData [id=" + id + ", value=" + value + "]";
	}

	public static MapDataDTO build(MapData input) {
		MapDataDTO dto = new MapDataDTO(Long.toString(input.getId()),input.getValue());
		return dto;
	}
	
}
