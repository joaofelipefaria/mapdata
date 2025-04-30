from .. import db

class DataEntity(db.Model):
    """
    Represents the Data entity.
    This model maps to the 'data' table in the database.
    """
    __tablename__ = 'mapdata'

    id = db.Column(db.Integer, primary_key=True)  # Primary key
    value = db.Column(db.String(255), nullable=False)  # String value

    # Relationship with Metadata
    metadataEntity = db.relationship('MetadataEntity', backref='data', lazy=True)

    def __repr__(self):
        """
        String representation of the Data object.
        """
        return f"<DataEntity id={self.id} value={self.value}>"

    def to_dict(self):
        """
        Converts the DataEntity object to a dictionary.
        """
        return {
            'id': self.id,
            'value': self.value,
            'metadataEntity': [m.to_dict() for m in self.metadataEntity]
        }