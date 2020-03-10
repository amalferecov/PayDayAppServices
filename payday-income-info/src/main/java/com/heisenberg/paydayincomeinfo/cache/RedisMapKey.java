package com.heisenberg.paydayincomeinfo.cache;

public enum RedisMapKey {
    MAP_OF_INCOME ("MAP_OF_INCOME");


    private String mapName;

    RedisMapKey(String mapName) {
        this.mapName = mapName;
    }

    public String getMapName() {
        return mapName;
    }
}
