from pydantic_settings import BaseSettings, SettingsConfigDict 

class Settings(BaseSettings): 
    app_name: str = "MapData API" 
    host: str = "0.0.0.0"   
    port: int = 8000 
    database_url: str 
    model_config = SettingsConfigDict(
        env_file=".env", 
        case_sensitive=False, 
        extra="ignore", 
    ) 

settings = Settings()
