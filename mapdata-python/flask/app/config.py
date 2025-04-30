import os

class Config:
    """Configuration class for Flask application."""
    
    # Secret key for session management and CSRF protection
    SECRET_KEY = os.environ.get('SECRET_KEY', 'your_secret_key')

    # Database configuration
    SQLALCHEMY_DATABASE_URI = os.environ.get(
        'DATABASE_URL',
        'postgresql://joaofaria_user:my_pwd_123@localhost:5432/mapdata_db'
    )
    SQLALCHEMY_TRACK_MODIFICATIONS = False

    # SQLAlchemy settings for debugging
    SQLALCHEMY_ECHO = True  # Show SQL queries in the console (similar to spring.jpa.show-sql=true)