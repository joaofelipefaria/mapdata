from flask import Blueprint, jsonify, request
from .models import DataEntity, MetadataEntity

main = Blueprint('main', __name__)

# ===========================
# Routes for DataEntity
# ===========================

@main.route('/api/mapdata/all', methods=['GET'])
def get_all_data():
    """
    Retrieve all DataEntity entries.
    """
    data = DataEntity.query.all()
    return jsonify([d.to_dict() for d in data]), 200

@main.route('/api/mapdata', methods=['POST'])
def create_data():
    """
    Create a new DataEntity entry.
    """
    data = request.json
    if 'value' not in data:
        return jsonify({'error': 'Missing "value" field'}), 400

    new_data = DataEntity(value=data['value'])
    db.session.add(new_data)
    db.session.commit()
    return jsonify(new_data.to_dict()), 201

@main.route('/api/mapdata/<int:data_id>', methods=['GET'])
def get_data_by_id(data_id):
    """
    Retrieve a single DataEntity entry by ID.
    """
    data = DataEntity.query.get(data_id)
    if not data:
        return jsonify({'error': 'DataEntity not found'}), 404
    return jsonify(data.to_dict()), 200

@main.route('/api/mapdata/<int:data_id>', methods=['PUT'])
def update_data(data_id):
    """
    Update a DataEntity entry by ID.
    """
    data = DataEntity.query.get(data_id)
    if not data:
        return jsonify({'error': 'DataEntity not found'}), 404

    update_data = request.json
    if 'value' in update_data:
        data.value = update_data['value']
    db.session.commit()
    return jsonify(data.to_dict()), 200

@main.route('/api/mapdata/<int:data_id>', methods=['DELETE'])
def delete_data(data_id):
    """
    Delete a DataEntity entry by ID.
    """
    data = DataEntity.query.get(data_id)
    if not data:
        return jsonify({'error': 'DataEntity not found'}), 404

    db.session.delete(data)
    db.session.commit()
    return jsonify({'message': 'DataEntity deleted successfully'}), 200

# ===========================
# Routes for MetadataEntity
# ===========================

@main.route('/api/mapdata/metadata/all', methods=['GET'])
def get_all_metadata():
    """
    Retrieve all MetadataEntity entries.
    """
    metadata = MetadataEntity.query.all()
    return jsonify([m.to_dict() for m in metadata]), 200

@main.route('/api/mapdata/metadata', methods=['POST'])
def create_metadata():
    """
    Create a new MetadataEntity entry.
    """
    data = request.json
    if 'data_id' not in data or 'value1' not in data or 'value2' not in data:
        return jsonify({'error': 'Missing required fields: "data_id", "value1", "value2"'}), 400

    new_metadata = MetadataEntity(
        data_id=data['data_id'],
        value1=data['value1'],
        value2=data['value2']
    )
    db.session.add(new_metadata)
    db.session.commit()
    return jsonify(new_metadata.to_dict()), 201

@main.route('/api/mapdata/metadata/<int:metadata_id>', methods=['GET'])
def get_metadata_by_id(metadata_id):
    """
    Retrieve a single MetadataEntity entry by ID.
    """
    metadata = MetadataEntity.query.get(metadata_id)
    if not metadata:
        return jsonify({'error': 'MetadataEntity not found'}), 404
    return jsonify(metadata.to_dict()), 200

@main.route('/api/mapdata/metadata/<int:metadata_id>', methods=['PUT'])
def update_metadata(metadata_id):
    """
    Update a MetadataEntity entry by ID.
    """
    metadata = MetadataEntity.query.get(metadata_id)
    if not metadata:
        return jsonify({'error': 'MetadataEntity not found'}), 404

    update_data = request.json
    if 'value1' in update_data:
        metadata.value1 = update_data['value1']
    if 'value2' in update_data:
        metadata.value2 = update_data['value2']
    db.session.commit()
    return jsonify(metadata.to_dict()), 200

@main.route('/api/mapdata/metadata/<int:metadata_id>', methods=['DELETE'])
def delete_metadata(metadata_id):
    """
    Delete a MetadataEntity entry by ID.
    """
    metadata = MetadataEntity.query.get(metadata_id)
    if not metadata:
        return jsonify({'error': 'MetadataEntity not found'}), 404

    db.session.delete(metadata)
    db.session.commit()
    return jsonify({'message': 'MetadataEntity deleted successfully'}), 200