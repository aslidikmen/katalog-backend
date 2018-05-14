/*
 * Created by Batuhan Kök on 14/5/2018.
 * Copyright (c) D-Teknoloji 2018.
 */

package invendolab.katalog.helpers;

import java.util.UUID;

public class RandomGenerator {

    private String key;

    public RandomGenerator() {
        this.key = UUID.randomUUID().toString();
    }

    public String getKey() {
        return key;
    }
}
