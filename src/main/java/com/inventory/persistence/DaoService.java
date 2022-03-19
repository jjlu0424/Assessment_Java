package com.inventory.persistence;

import java.util.HashMap;
import java.util.Map;

public class DaoService {

    private  Map<String, Dao> daoMap;
    private static DaoService instance;


    public static void createInstance()
    {
        if (instance != null)
        {
            return;
        }
        instance = new DaoService();
    }

    public static DaoService getInstance() {
        return instance;
    }

    private DaoService()
    {
        daoMap = new HashMap<String, Dao>();
    }

    public void createNewDao(String name, Dao dao)
    {
        if (daoMap.containsKey(name.toLowerCase())){
            return;
        }
        daoMap.put(name.toLowerCase(), dao);
    }

    public Dao getDao(String name)
    {
        if (daoMap.containsKey(name.toLowerCase())){
            return daoMap.get(name.toLowerCase());
        }
        return null;
    }

    public boolean configureDao(String daoName, String dbUrl){
        if (daoMap.containsKey(daoName.toLowerCase())){
            return daoMap.get(daoName.toLowerCase()).configure(dbUrl);
        }
        return false;
    }
}
