import 'dart:convert';
import 'package:http/http.dart' as http;
import 'package:mapdata_app/models/meta_data_model.dart';
import '../models/map_data_model.dart';

class MapDataService {
  final String _baseUrl = 'http://localhost:8080/api';

  late final List<MapData> _data = [];
  late final List<MetaData> _metaDataList = [];

  // Get all MapData
  Future<List<MapData>> getAll() async {
    final response = await http.get(Uri.parse('$_baseUrl/mapdata/all'));

    if (response.statusCode >= 200 && response.statusCode < 300) {
      final List<dynamic> jsonData = json.decode(response.body);
      _data.clear();
      _data.addAll(jsonData.map((item) => MapData.fromJson(item)).toList());
      return _data;
    } else {
      throw Exception('Failed to load MapData');
    }
  }

  // Add a new MapData
  Future<void> add(MapData mapData) async {
    final response = await http.post(
      Uri.parse('$_baseUrl/mapdata'),
      headers: {'Content-Type': 'application/json'},
      body: json.encode(mapData.toJson()),
    );

    if (response.statusCode >= 200 && response.statusCode < 300) {
      _data.add(mapData);
    } else {
      throw Exception('Failed to add MapData');
    }
  }

  // Remove a MapData by ID
  Future<void> remove(int id) async {
    final response = await http.delete(Uri.parse('$_baseUrl/mapdata/$id'));

    if (response.statusCode >= 200 && response.statusCode < 300) {
      _data.removeWhere((item) => item.id == id);
    } else {
      throw Exception('Failed to delete MapData');
    }
  }

  // Update an existing MapData
  Future<void> update(int id, String newValue) async {
    final response = await http.put(
      Uri.parse('$_baseUrl/mapdata/$id'),
      headers: {'Content-Type': 'application/json'},
      body: json.encode({'value': newValue}),
    );

    if (response.statusCode >= 200 && response.statusCode < 300) {
      final index = _data.indexWhere((item) => item.id == id);
      if (index != -1) {
        _data[index] = MapData(id: id, value: newValue);
      }
    } else {
      throw Exception('Failed to update MapData');
    }
  }

  // Get all MetaData by MapData ID
  Future<List<MetaData>> getByMapData(int mapDataId) async {
    final response = await http.get(
      Uri.parse('$_baseUrl/mapdata/$mapDataId/metadata'),
    );

    if (response.statusCode >= 200 && response.statusCode < 300) {
      final List<dynamic> jsonData = json.decode(response.body);
      print(response.body);
      _metaDataList.clear();
      _metaDataList.addAll(
        jsonData.map((item) => MetaData.fromJson(item)).toList(),
      );
      return _metaDataList;
    } else {
      throw Exception('Failed to load MetaData for MapData ID $mapDataId');
    }
  }

  // Get MetaData by ID
  Future<MetaData?> getById(int id) async {
    final response = await http.get(Uri.parse('$_baseUrl/metadata/$id'));

    if (response.statusCode >= 200 && response.statusCode < 300) {
      final jsonData = json.decode(response.body);
      return MetaData.fromJson(jsonData);
    } else {
      throw Exception('Failed to load MetaData with ID $id');
    }
  }

  // Create new MetaData
  Future<void> createMetaData(MetaData metaData) async {
    final response = await http.post(
      Uri.parse('$_baseUrl/metadata'),
      headers: {'Content-Type': 'application/json'},
      body: json.encode(metaData.toJson()),
    );

    if (response.statusCode >= 200 && response.statusCode < 300) {
      throw Exception('Failed to create MetaData');
    }
  }

  // Update existing MetaData
  Future<void> updateMetaData(
    int id,
    String newValue1,
    String newValue2,
  ) async {
    final response = await http.put(
      Uri.parse('$_baseUrl/metadata/$id'),
      headers: {'Content-Type': 'application/json'},
      body: json.encode({'value1': newValue1, 'value2': newValue2}),
    );

    if (response.statusCode >= 200 && response.statusCode < 300) {
      final index = _metaDataList.indexWhere((meta) => meta.id == id);
      if (index != -1) {
        _metaDataList[index] = MetaData(
          id: id,
          mapData: _metaDataList[index].mapData,
          value1: newValue1,
          value2: newValue2,
        );
      }
    } else {
      throw Exception('Failed to update MetaData');
    }
  }

  // Remove MetaData by ID
  Future<void> removeMetaData(int id) async {
    final response = await http.delete(Uri.parse('$_baseUrl/metadata/$id'));

    if (response.statusCode >= 200 && response.statusCode < 300) {
      _metaDataList.removeWhere((meta) => meta.id == id);
    } else {
      throw Exception('Failed to delete MetaData');
    }
  }
}
