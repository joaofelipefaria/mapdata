class MapData {
  final int id;
  final String value;

  MapData({required this.id, required this.value});

  factory MapData.fromJson(Map<String, dynamic> json) {
    return MapData(id: json['id'] as int, value: json['value'] as String);
  }

  Map<String, dynamic> toJson() {
    return {'id': id, 'value': value};
  }
}
