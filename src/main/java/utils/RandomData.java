package utils;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomData {

    /**
     * 
     * @return a random email as a string
     */
    public String generateRandomEmail() {
        return RandomStringUtils.randomAlphanumeric(8) + "@gmail.com";
    }

}


