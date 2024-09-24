/*
 * Copyright 2024 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.unitvectory.consistgen.string;

import java.security.SecureRandom;
import java.util.Random;

import lombok.Builder;

/**
 * Provides a string that is randomly generated from an alphabet of a given
 * length.
 * 
 * The default alphabet is
 * "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".
 * 
 * The alphabet can be overridden at construction time by specifying a string
 * with the desired list of characters to be randomly selected from.
 * 
 * By default, a SecureRandom is used to generate the random numbers. If desired
 * an alternate implementation of Random can be provided.
 * 
 * @see SecureRandom
 * 
 * @author Jared Hatfield (UnitVectorY Labs)
 */
public class RandomStringProvider implements StringProvider {

    /**
     * The default instance of the RandomStringProvider.
     */
    private static final RandomStringProvider INSTANCE = RandomStringProvider.builder().build();

    /**
     * The default alphabet.
     */
    static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    /**
     * The alphabet to use for generating the random string.
     */
    private final String alphabet;

    /**
     * The random number generator to use for generating the random string.
     */
    private final Random random;

    /**
     * Creates a new RandomStringProvider.
     * 
     * @param alphabet the alphabet to use for generating the random string
     * @param random   the random number generator to use
     */
    @Builder
    public RandomStringProvider(String alphabet, Random random) {
        if (alphabet == null) {
            this.alphabet = ALPHABET;
        } else if (alphabet.isEmpty()) {
            throw new IllegalArgumentException("alphabet must not be empty");
        } else {
            this.alphabet = alphabet;
        }

        if (random == null) {
            this.random = new SecureRandom();
        } else {
            this.random = random;
        }
    }

    @Override
    public String generate(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("length must be greater than 0");
        }

        StringBuilder builder = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            builder.append(alphabet.charAt(random.nextInt(alphabet.length())));
        }

        return builder.toString();
    }

    /**
     * Gets the instance of the RandomStringProvider.
     * 
     * The default implementation utilizes a SecureRandom for generating random numbers.
     * 
     * The default alphabet is "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".
     * 
     * @return the default instance of the RandomStringProvider
     */
    public static RandomStringProvider getInstance() {
        return INSTANCE;
    }
}
