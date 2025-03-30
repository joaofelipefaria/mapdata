import 'package:mapdata_app/models/meta_data_model.dart';
import '../models/map_data_model.dart';

class MapDataService {
  late final List<MapData> _data;
  late final List<MetaData> _metaDataList;

  MapDataService() {
    _data = [
      MapData(id: 1, value: 'Location A'),
      MapData(id: 2, value: 'Location B'),
      MapData(id: 3, value: 'Location C'),
    ];
    _metaDataList = [
      // MetaData for the first MapData (0 entry)
      MetaData(id: 1, mapData: _data[0], value1: 'Meta 1A', value2: 'Meta 1B'),
      MetaData(id: 2, mapData: _data[0], value1: 'Meta 2A', value2: 'Meta 2B'),
      MetaData(id: 3, mapData: _data[0], value1: 'Meta 3A', value2: 'Meta 3B'),
      // MetaData for the second MapData (1 entry)
      MetaData(id: 4, mapData: _data[1], value1: 'Meta 4A', value2: 'Meta 4B'),
      // No MetaData for the third MapData
    ];
  }

  // Get all MapData
  Future<List<MapData>> getAll() async {
    return Future.value(List.unmodifiable(_data));
  }

  // Add a new MapData
  Future<void> add(MapData mapData) async {
    _data.add(mapData);
  }

  // Remove a MapData by ID
  Future<void> remove(int id) async {
    _data.removeWhere((item) => item.id == id);
  }

  // Update an existing MapData
  Future<void> update(int id, String newValue) async {
    final index = _data.indexWhere((item) => item.id == id);
    if (index != -1) {
      _data[index] = MapData(id: id, value: newValue);
    }
  }

  // Get all MetaData by MapData ID
  Future<List<MetaData>> getByMapData(int mapDataId) async {
    return Future.value(
      _metaDataList.where((meta) => meta.mapData.id == mapDataId).toList(),
    );
  }

  // Get MetaData by ID
  Future<MetaData?> getById(int id) async {
    try {
      return Future.value(_metaDataList.firstWhere((meta) => meta.id == id));
    } catch (e) {
      return null;
    }
  }

  // Create new MetaData
  Future<void> createMetaData(MetaData metaData) async {
    _metaDataList.add(metaData);
  }

  // Update existing MetaData
  Future<void> updateMetaData(
    int id,
    String newValue1,
    String newValue2,
  ) async {
    final index = _metaDataList.indexWhere((meta) => meta.id == id);
    if (index != -1) {
      _metaDataList[index] = MetaData(
        id: id,
        mapData: _metaDataList[index].mapData,
        value1: newValue1,
        value2: newValue2,
      );
    }
  }

  // Remove MetaData by ID
  Future<void> removeMetaData(int id) async {
    _metaDataList.removeWhere((meta) => meta.id == id);
  }
}
