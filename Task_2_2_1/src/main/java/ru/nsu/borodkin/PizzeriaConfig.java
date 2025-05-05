package ru.nsu.borodkin;


import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class PizzeriaConfig {
    public int[] bakers;
    public int[] couriers;
    public int warehousesCapacity;
    public int workTime;

    public static PizzeriaConfig load(String path) throws Exception {
        try (Reader reader = new InputStreamReader(PizzeriaConfig.class.getResourceAsStream(path))) {
            return new Gson().fromJson(reader, PizzeriaConfig.class);
        }
    }
}
