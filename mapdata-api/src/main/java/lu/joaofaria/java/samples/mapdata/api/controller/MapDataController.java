package lu.joaofaria.java.samples.mapdata.api.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lu.joaofaria.java.samples.mapdata.api.dto.MapDataDTO;
import lu.joaofaria.java.samples.mapdata.api.exception.ResourceNotFoundException;
import lu.joaofaria.java.samples.mapdata.api.model.MapData;
import lu.joaofaria.java.samples.mapdata.api.repository.MapDataRepository;

@RestController
@RequestMapping("/api/v1")
public class MapDataController {
	@Autowired
	private MapDataRepository mapDataRepository;

	@GetMapping("/all-data")
	public List<MapDataDTO> getAllData() {
		List<MapData> list = mapDataRepository.findAll();
		List<MapDataDTO> returnList = new ArrayList<>();
		for(MapData d : list) {
			MapDataDTO dto = MapDataDTO.build(d);
			returnList.add(dto);
		}
		System.out.println("size:::"+list.size());
		return returnList;
	}
	
	@GetMapping("/data/{id}")
	public ResponseEntity<MapData> getDataById(@PathVariable(value = "id") Long id)
			throws ResourceNotFoundException {
		MapData data = mapDataRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Data not found for this id :: " + id));
		return ResponseEntity.ok().body(data);
	}

	@PostMapping("/data")
	public MapData createData(@RequestBody MapData data) {
		data.setId(0);
		return mapDataRepository.save(data);
	}

	@PutMapping("/data/{id}")
	public ResponseEntity<MapData> updateData(@PathVariable(value = "id") Long id,
			@RequestBody MapData details  ) throws ResourceNotFoundException {
		MapData data = mapDataRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Data not found for this id :: " + id));
		data.setValue(details.getValue());
		final MapData updatedEmployee = mapDataRepository.save(data);
		return ResponseEntity.ok(updatedEmployee);
	}

	@DeleteMapping("/data/{id}")
	public Map<String, Boolean> deleteData(@PathVariable(value = "id") Long id)
			throws ResourceNotFoundException {
		MapData data = mapDataRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Data not found for this id :: " + id));

		mapDataRepository.delete(data);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
