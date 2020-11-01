package com.IceHockeyLeague.LeagueManager.Player;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class RandomChance implements IRandomChance {
    private Random random;

    public RandomChance(Random random) {
        this.random = random;
    }

    @Override
    public float getRandomFloatNumber(int lowerValue, int upperValue) {
        if(upperValue < lowerValue) {
            return -1;
        }
        return (random.nextFloat() * (upperValue - lowerValue)) + lowerValue;
    }

    @Override
    public float roundFloatNumber(float number, int noOfDecimalPlaces) {
        BigDecimal bigDecimal = new BigDecimal(Float.toString(number)).setScale(noOfDecimalPlaces, RoundingMode.CEILING);
        return bigDecimal.floatValue();
    }

    @Override
    public int getRandomIntegerNumber(int lowerValue, int upperValue) {
        if (upperValue < lowerValue) {
            return -1;
        }
        // Adding 1 as specified value is exclusive in number generation
        int randomNumber = random.nextInt(upperValue+1);

        if(randomNumber < lowerValue) {
            randomNumber += lowerValue;
        }

        return randomNumber;
    }
}
