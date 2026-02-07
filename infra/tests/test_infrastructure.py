import requests
import sys

# Configuration
SPARK_MASTER_UI = 'http://localhost:8080'
SPARK_WORKER_UI = 'http://localhost:8081'


def print_status(service, status, message=""):
    color = "\033[92m" if status == "PASS" else "\033[91m"
    reset = "\033[0m"
    print(f"[{color}{status}{reset}] {service}: {message}")


def test_spark_ui():
    print("\nTesting Spark UI Reachability...")
    success = True

    # Check Master
    try:
        response = requests.get(SPARK_MASTER_UI)
        if response.status_code == 200:
            print_status("Spark Master UI", "PASS", "Reachable")
        else:
            print_status("Spark Master UI", "FAIL",
                         f"Status Code: {response.status_code}")
            success = False
    except Exception as e:
        print_status("Spark Master UI", "FAIL", str(e))
        success = False

    # Check Worker
    try:
        response = requests.get(SPARK_WORKER_UI)
        if response.status_code == 200:
            print_status("Spark Worker UI", "PASS", "Reachable")
        else:
            print_status("Spark Worker UI", "FAIL",
                         f"Status Code: {response.status_code}")
            success = False
    except Exception as e:
        print_status("Spark Worker UI", "FAIL", str(e))
        success = False

    return success


def main():
    print("Starting Infrastructure Health Check...")
    print("="*40)

    spark_ok = test_spark_ui()

    print("="*40)
    if spark_ok:
        print("\033[92mAll systems operational!\033[0m")
        sys.exit(0)
    else:
        print("\033[91mSome systems failed check.\033[0m")
        sys.exit(1)


if __name__ == "__main__":
    main()
