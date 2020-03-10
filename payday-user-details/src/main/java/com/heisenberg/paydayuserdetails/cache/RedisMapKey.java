package com.heisenberg.paydayuserdetails.cache;

public enum RedisMapKey {
    MAP_OF_USER_DETAILS ("MAP_OF_USER_DETAILS");


    private String mapName;

    RedisMapKey(String mapName) {
        this.mapName = mapName;
    }

    public String getMapName() {
        return mapName;
    }
}
