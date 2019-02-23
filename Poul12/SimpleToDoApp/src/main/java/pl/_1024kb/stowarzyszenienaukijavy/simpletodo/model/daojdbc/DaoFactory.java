package pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.daojdbc;

import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.api.TaskDao;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.api.UserDao;

public abstract class DaoFactory
{
    public abstract UserDao getUserDao();

    public abstract TaskDao getTaskDao();

    private static DaoFactory instance;

    public static DaoFactory getDaoFactory(FactoryType factoryType)
    {
        if(instance == null)
        {
            switch(factoryType)
            {
                case MYSQL_DAO:
                    instance = new MysqlDaoFactory();
                    break;
            }
        }

        return instance;
    }
}
