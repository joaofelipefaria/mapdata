from .. import db

class MetadataEntity(db.Model):
    """
    Represents the Metadata entity.
    This model maps to the 'metadata' table in the database.
    """
    __tablename__ = 'metadata'

    id = db.Column(db.Integer, primary_key=True)  # Primary key
    idmapdata = db.Column(db.Integer, db.ForeignKey('mapdata.id'), nullable=False)  # Foreign key to Data
    value1 = db.Column(db.String(255), nullable=False)  # First string value
    value2 = db.Column(db.String(255), nullable=False)  # Second string value

    def __repr__(self):
        """
        String representation of the MetadataEntity object.
        """
        return f"<MetadataEntity id={self.id} data_id={self.data_id} value1={self.value1} value2={self.value2}>"

    def to_dict(self):
        """
        Converts the MetadataEntity object to a dictionary.
        """
        return {
            'id': self.id,
            'data_id': self.idmapdata,
            'value1': self.value1,
            'value2': self.value2
        }