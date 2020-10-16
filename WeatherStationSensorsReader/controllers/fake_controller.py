from controllers.controller import Controller
from dao.fake_dao import FakeDao
from sensors.fake_sensor import FakeSensor


class FakeController(Controller):
    """ Class with a controller without any sensor """

    def __init__(self, server, database, user, password):
        super(FakeController, self).__init__(dao=FakeDao(server=server,
                                                         database=database,
                                                         user=user,
                                                         password=password),
                                             sensor=FakeSensor())
