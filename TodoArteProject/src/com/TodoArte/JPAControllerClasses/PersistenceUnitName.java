package com.TodoArte.JPAControllerClasses;

public class PersistenceUnitName {
    private static String persistenceUnitName = "TodoArtePersistenceUnit";
    
    public static String getPersistenceUnitName(){
        return persistenceUnitName;
    }
}
