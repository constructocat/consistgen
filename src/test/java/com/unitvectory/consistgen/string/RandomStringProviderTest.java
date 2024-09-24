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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;

import org.junit.jupiter.api.Test;

/**
 * Test the RandomStringProvider class.
 * 
 * @author Jared Hatfield (UnitVectorY Labs)
 */
class RandomStringProviderTest {

    private boolean isLimitedToAlphabet(String result, String alphabet) {
        for (char c : result.toCharArray()) {
            if (alphabet.indexOf(c) < 0) {
                return false;
            }
        }

        return true;
    }

    @Test
    void testDefaultAlphabet() {
        Random random = new Random(0);
        RandomStringProvider provider = RandomStringProvider.builder().random(random).build();
        for (int i = 1; i < 100; i++) {
            String result = provider.generate(i);
            assertEquals(i, result.length());
            assertTrue(isLimitedToAlphabet(result, RandomStringProvider.ALPHABET));
        }
    }

    @Test
    void testCustomAlphabet() {
        Random random = new Random(0);
        String customAlphabet = "abc123";
        RandomStringProvider provider = RandomStringProvider.builder().alphabet(customAlphabet).random(random).build();
        for (int i = 1; i < 100; i++) {
            String result = provider.generate(i);
            assertEquals(i, result.length());
            assertTrue(isLimitedToAlphabet(result, customAlphabet));
        }
    }

    @Test
    void testDefaultRandom() {
        RandomStringProvider provider = RandomStringProvider.getInstance();
        assertEquals(10, provider.generate(10).length());
    }

    @Test
    void testNullAlphabetAndRandom() {
        RandomStringProvider provider = RandomStringProvider.builder().alphabet(null).random(null).build();
        assertEquals(10, provider.generate(10).length());
    }

    @Test
    void testGenerateWithEmptyAlphabet() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            RandomStringProvider.builder().alphabet("").build();
        });
        assertEquals("alphabet must not be empty", exception.getMessage());
    }

    @Test
    void testNegativeLength() {
        RandomStringProvider provider = RandomStringProvider.builder().build();
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> provider.generate(-1),
                "Expected generate() to throw on negative length");
        assertEquals("length must be greater than 0", thrown.getMessage());
    }

    @Test
    void testZeroLength() {
        RandomStringProvider provider = RandomStringProvider.builder().build();
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> provider.generate(0),
                "Expected generate() to throw on negative length");
        assertEquals("length must be greater than 0", thrown.getMessage());
    }
}
