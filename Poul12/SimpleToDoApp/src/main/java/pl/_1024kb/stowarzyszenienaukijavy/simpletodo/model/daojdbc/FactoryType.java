package pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.daojdbc;

public enum FactoryType
{
    MYSQL_DAO(1);

    private int numberType;

    FactoryType(int numberType) {
        this.numberType = numberType;
    }

    public int getNumberType() {
        return numberType;
    }
}
