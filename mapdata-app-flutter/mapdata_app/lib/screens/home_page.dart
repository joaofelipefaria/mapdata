import 'package:flutter/material.dart';
import '../models/map_data_model.dart';
import '../models/meta_data_model.dart' as app_meta;
import '../services/map_data_service.dart';

class HomePage extends StatefulWidget {
  const HomePage({super.key});

  @override
  _HomePageState createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  final MapDataService _mapDataService = MapDataService();
  List<MapData> mapData = []; // List to store MapData items
  MapData? _selectedMapData; // Stores the currently selected MapData item
  List<app_meta.MetaData> metaDataList = []; // List to store MetaData items

  @override
  void initState() {
    super.initState();
    _loadData(); // Load initial data when the widget is initialized
  }

  // Loads all MapData items from the service
  Future<void> _loadData() async {
    try {
      final data = await _mapDataService.getAll();
      setState(() {
        mapData = data;
        metaDataList = []; // Clear MetaData list when data is reloaded
        _selectedMapData = null; // Reset the selected MapData
      });
    } catch (e) {
      // Handle any errors that occur during data loading
      ScaffoldMessenger.of(
        context,
      ).showSnackBar(SnackBar(content: Text('Failed to load data: $e')));
    }
  }

  // Opens a dialog to create a new MapData item
  void _createData() {
    TextEditingController controller = TextEditingController();
    showDialog(
      context: context,
      builder:
          (context) => AlertDialog(
            title: const Text('Create MapData'),
            content: TextField(
              controller: controller,
              decoration: const InputDecoration(labelText: 'Enter Value'),
            ),
            actions: [
              TextButton(
                onPressed: () => Navigator.pop(context), // Close the dialog
                child: const Text('Cancel'),
              ),
              TextButton(
                onPressed: () async {
                  if (controller.text.isNotEmpty) {
                    await _mapDataService.add(
                      MapData(id: 0, value: controller.text),
                    ); // Create new MapData
                    await _loadData(); // Reload the data after creation
                  }
                  Navigator.pop(context); // Close the dialog
                },
                child: const Text('Create'),
              ),
            ],
          ),
    );
  }

  // Updates the MetaData list based on the selected MapData
  Future<void> _showMetadataForMapData(MapData mapDataItem) async {
    try {
      // Fetch MetaData for the selected MapData
      final fetchedMetaData = await _mapDataService.getByMapData(
        mapDataItem.id,
      );

      setState(() {
        _selectedMapData = mapDataItem;
        metaDataList = fetchedMetaData; // Update the MetaData list

        // If no MetaData is found, clear the list
        if (metaDataList.isEmpty) {
          print('No MetaData found for MapData ID: ${mapDataItem.id}');
        }
      });
    } catch (e) {
      // Handle any errors that occur during the fetch
      print('Error fetching MetaData for MapData ID: ${mapDataItem.id}, $e');
      ScaffoldMessenger.of(
        context,
      ).showSnackBar(SnackBar(content: Text('Failed to load MetaData: $e')));

      // Clear the MetaData list in case of an error
      setState(() {
        metaDataList = [];
      });
    }
  }

  // Deletes a MapData item by its ID
  Future<void> _deleteItem(int id) async {
    await _mapDataService.remove(id); // Perform the deletion
    await _loadData(); // Reload the MapData list after deletion
  }

  // Deletes a MetaData item by its ID
  Future<void> _deleteMetaDataItem(int id) async {
    try {
      // Perform the deletion
      await _mapDataService.removeMetaData(id);

      // Check if a MapData is selected
      if (_selectedMapData != null) {
        // Reload the MetaData list for the selected MapData
        final updatedMetaDataList = await _mapDataService.getByMapData(
          _selectedMapData!.id,
        );

        // Print the size of the updated MetaData list
        print('Updated MetaData List Size: ${updatedMetaDataList.length}');

        // Update the state with the new list
        setState(() {
          metaDataList = updatedMetaDataList;
        });
      }
    } catch (e) {
      // Handle any errors that occur during deletion
      ScaffoldMessenger.of(
        context,
      ).showSnackBar(SnackBar(content: Text('Failed to delete MetaData: $e')));
    }
  }

  // Opens a dialog to edit a MapData item
  void _editItem(int id) {
    // Encontra o item MapData pelo ID
    final mapDataItem = mapData.firstWhere((item) => item.id == id);

    // Preenche o controlador com o valor atual do MapData
    TextEditingController controller = TextEditingController(
      text: mapDataItem.value,
    );

    showDialog(
      context: context,
      builder:
          (context) => AlertDialog(
            title: const Text('Edit MapData'),
            content: TextField(
              controller: controller,
              decoration: const InputDecoration(labelText: 'New Value'),
            ),
            actions: [
              TextButton(
                onPressed: () => Navigator.pop(context), // Fecha o diálogo
                child: const Text('Cancel'),
              ),
              TextButton(
                onPressed: () async {
                  if (controller.text.isNotEmpty) {
                    await _mapDataService.update(
                      id,
                      controller.text,
                    ); // Atualiza o item
                    await _loadData(); // Recarrega os dados após a atualização
                  }
                  Navigator.pop(context); // Fecha o diálogo
                },
                child: const Text('Save'),
              ),
            ],
          ),
    );
  }

  // Opens a dialog to edit a MetaData item
  Future<void> _editMetaDataItem(int id) async {
    TextEditingController controller1 = TextEditingController();
    TextEditingController controller2 = TextEditingController();
    showDialog(
      context: context,
      builder:
          (context) => AlertDialog(
            title: const Text('Edit MetaData'),
            content: Column(
              mainAxisSize: MainAxisSize.min,
              children: [
                TextField(
                  controller: controller1,
                  decoration: const InputDecoration(labelText: 'New Value1'),
                ),
                TextField(
                  controller: controller2,
                  decoration: const InputDecoration(labelText: 'New Value2'),
                ),
              ],
            ),
            actions: [
              TextButton(
                onPressed: () => Navigator.pop(context), // Close the dialog
                child: const Text('Cancel'),
              ),
              TextButton(
                onPressed: () async {
                  await _mapDataService.updateMetaData(
                    id,
                    controller1.text,
                    controller2.text,
                  ); // Update the MetaData item
                  if (_selectedMapData != null) {
                    metaDataList = await _mapDataService.getByMapData(
                      _selectedMapData!.id,
                    );
                  }
                  setState(() {});
                  Navigator.pop(context); // Close the dialog
                },
                child: const Text('Save'),
              ),
            ],
          ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('MapData List')),
      body: Column(
        children: [
          // Button to reload data
          Padding(
            padding: const EdgeInsets.all(8.0),
            child: ElevatedButton(
              onPressed: _loadData,
              child: const Text('Query Data'),
            ),
          ),

          // List of MapData items
          Expanded(
            flex: 3,
            child: ListView.builder(
              itemCount: mapData.length,
              itemBuilder: (context, index) {
                final mapDataItem = mapData[index];
                return ListTile(
                  leading: CircleAvatar(
                    child: Text(mapDataItem.id.toString()), // Display ID
                  ),
                  title: Text(mapDataItem.value), // Display value
                  trailing: Row(
                    mainAxisSize: MainAxisSize.min,
                    children: [
                      // Info button to select a MapData item
                      IconButton(
                        icon: const Icon(Icons.info, color: Colors.blue),
                        onPressed: () => _showMetadataForMapData(mapDataItem),
                      ),
                      // Edit button to edit a MapData item
                      IconButton(
                        icon: const Icon(Icons.edit, color: Colors.orange),
                        onPressed: () => _editItem(mapDataItem.id),
                      ),
                      // Delete button to delete a MapData item
                      IconButton(
                        icon: const Icon(Icons.delete, color: Colors.red),
                        onPressed: () => _deleteItem(mapDataItem.id),
                      ),
                    ],
                  ),
                );
              },
            ),
          ),

          // Button to create new MapData
          Padding(
            padding: const EdgeInsets.all(8.0),
            child: ElevatedButton(
              onPressed: _createData,
              child: const Text('Create Data'),
            ),
          ),

          // List of MetaData items (displayed directly below MapData list)
          if (metaDataList.isNotEmpty)
            Flexible(
              flex: 2,
              child: ListView.builder(
                itemCount: metaDataList.length,
                itemBuilder: (context, index) {
                  final meta = metaDataList[index];
                  return ListTile(
                    leading: CircleAvatar(
                      child: Text(meta.id.toString()), // Display MetaData ID
                    ),
                    title: Text('Value1: ${meta.value1}'), // Display Value1
                    subtitle: Text('Value2: ${meta.value2}'), // Display Value2
                    trailing: Row(
                      mainAxisSize: MainAxisSize.min,
                      children: [
                        // Edit button to edit a MetaData item
                        IconButton(
                          icon: const Icon(Icons.edit, color: Colors.orange),
                          onPressed: () => _editMetaDataItem(meta.id),
                        ),
                        // Delete button to delete a MetaData item
                        IconButton(
                          icon: const Icon(Icons.delete, color: Colors.red),
                          onPressed: () => _deleteMetaDataItem(meta.id),
                        ),
                      ],
                    ),
                  );
                },
              ),
            ),
        ],
      ),
    );
  }
}
