import os

app_env = os.environ.get("APP_ENV", "not set")
print(f"APP_ENV = {app_env}")
