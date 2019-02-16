package pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.daojdbc;

import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.api.TaskDao;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.api.UserDao;

public class MysqlDaoFactory extends DaoFactory
{
    @Override
    public UserDao getUserDao() {
        return new MysqlUserDao();
    }

    @Override
    public TaskDao getTaskDao() {
        return new MysqlTaskDao();
    }
}
