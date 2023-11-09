package lu.joaofaria.java.samples.mapdata.api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "map_data")
public class MapData {

	private long id;
	private String value;
	
	public MapData() {
		
	}
	
	public MapData(String value) {
		this.value = value;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="map_data_sequence")
	@SequenceGenerator(name = "map_data_sequence", sequenceName = "map_data_seq", allocationSize = 1)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name = "value", nullable = false)
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
	
}
