import subprocess

def run_script(script_path, args=None):
    try:
        command = [script_path] if args is None else [script_path] + args
        result = subprocess.run(command, check=True, capture_output=True, text=True)
        print(f"Output: {result.stdout}")
        return True
    except subprocess.CalledProcessError as e:
        print(f"Error running script: {e}")
        print(f"Error output: {e.stderr}")
        return False

# Example usage
scripts_to_run = [
    "stop_docker_services.py"
]

for script in scripts_to_run:
    success = run_script(script)
    if success:
        print(f"Successfully ran {script}")
    else:
        print(f"Failed to run {script}")
