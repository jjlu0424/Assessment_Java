package com.inventory.persistence;

import java.util.HashMap;
import java.util.Map;

/**
 * Singleton manager for using and creating DAO
 * @author  Mei-Hung Lu
 * @version 1.0
 * @since   21-03-2022
 */
public class DaoService {

    private  Map<String, Dao> daoMap;
    private static DaoService instance;

    private DaoService()
    {
        daoMap = new HashMap<String, Dao>();
    }

    /**
     * Creates the singleton instance
     */
    public static void createInstance()
    {
        if (instance != null)
        {
            return;
        }
        instance = new DaoService();
    }

    /**
     * Gets the singleton instance
     * @return DaoService The instance
     */
    public static DaoService getInstance() {
        return instance;
    }


    /**
     * Adds a new DAO into pool of DAOs
     * @param name The name of the DAO
     * @param dao The DAO to be added
     */
    public void createNewDao(String name, Dao dao)
    {
        if (daoMap.containsKey(name.toLowerCase())){
            return;
        }
        daoMap.put(name.toLowerCase(), dao);
    }

    /**
     * Gets a DAO with name
     * @param name The name of the DAO
     * @return Dao The DAO that the name refers to
     */
    public Dao getDao(String name)
    {
        if (daoMap.containsKey(name.toLowerCase())){
            return daoMap.get(name.toLowerCase());
        }
        return null;
    }

    /**
     * Configures a DAO with a database path
     * @param daoName The name of the DAO
     * @param dbUrl The path to the database
     * @return boolean Whether configuration is successful
     */
    public boolean configureDao(String daoName, String dbUrl){
        if (daoMap.containsKey(daoName.toLowerCase())){
            return daoMap.get(daoName.toLowerCase()).configure(dbUrl);
        }
        return false;
    }
}
