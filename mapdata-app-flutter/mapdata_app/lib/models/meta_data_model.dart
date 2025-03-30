import 'package:mapdata_app/models/map_data_model.dart';

class MetaData {
  final int id;
  final MapData mapData;
  final String value1;
  final String value2;

  MetaData({
    required this.id,
    required this.mapData,
    required this.value1,
    required this.value2,
  });

  factory MetaData.fromJson(Map<String, dynamic> json) {
    return MetaData(
      id: json['id'] as int,
      mapData: MapData.fromJson(json['mapdata']),
      value1: json['value1'] as String,
      value2: json['value2'] as String,
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'id': id,
      'mapDataId': mapData.id, // Only the ID of the MapData is sent
      'value1': value1,
      'value2': value2,
    };
  }
}
