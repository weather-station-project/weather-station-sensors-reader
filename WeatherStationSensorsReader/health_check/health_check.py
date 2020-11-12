import logging
import os
import sys

from main.main_class import Main


def configure_critical_level_logging():
    logging.basicConfig(level=logging.CRITICAL, format=Main.LOG_FORMAT)


def main():
    try:
        configure_critical_level_logging()

        main_class = Main(variables=os.environ)
        main_class.validate_environment_variables()

        controllers_enabled = main_class.get_controllers_enabled()

        if not controllers_enabled:
            raise Exception('There is no controller configured on the init. Please, read the documentation available on Github.')

        main_class.execute_controllers_health_check(controllers=controllers_enabled)
    except Exception as e:
        logging.critical('[HEALTH CHECK ERROR]', e)
        return 1

    return 0


if __name__ == '__main__':
    sys.exit(main())
