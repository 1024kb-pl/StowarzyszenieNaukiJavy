package pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.daojdbc;

import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.api.TaskDao;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.api.UserDao;

public abstract class DaoFactory
{
    public static final int MYSQL_DAO = 1;

    public abstract UserDao getUserDao();

    public abstract TaskDao getTaskDao();

    private static DaoFactory instance;

    public static DaoFactory getDaoFactory(int factoryType)
    {
        if(instance == null)
        {
            if(factoryType == MYSQL_DAO)
                instance = new MysqlDaoFactory();
        }

        return instance;
    }
}
