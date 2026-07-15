from sqlalchemy import create_engine 
from sqlalchemy.orm import DeclarativeBase 
from sqlalchemy.orm import sessionmaker 
from app.config.settings import settings 

engine = create_engine(
    settings.database_url, 
    echo=False, 
    pool_pre_ping=True, 
) 

SessionLocal = sessionmaker(
    bind=engine, 
    autoflush=False, 
    autocommit=False, 
    expire_on_commit=False, 
) 

class Base(DeclarativeBase): 
    pass 

def get_session(): 
    session = SessionLocal() 
    try: 
        yield session 
    finally: 
        session.close()