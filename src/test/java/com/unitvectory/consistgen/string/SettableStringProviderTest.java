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

import org.junit.jupiter.api.Test;

/**
 * Test the SettableStringProvider class.
 * 
 * @author Jared Hatfield (UnitVectorY Labs)
 */
class SettableStringProviderTest {

    @Test
    void testGenerateValidLength() {
        SettableStringProvider provider = SettableStringProvider.builder().alphabet("abc").build();
        String result = provider.generate(7);
        assertEquals("abcabca", result);
    }

    @Test
    void testGenerateZeroLength() {
        SettableStringProvider provider = SettableStringProvider.builder().build();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            provider.generate(0);
        });
        assertEquals("length must be greater than 0", exception.getMessage());
    }

    @Test
    void testGenerateNegativeLength() {
        SettableStringProvider provider = SettableStringProvider.builder().alphabet("abc").build();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            provider.generate(-1);
        });
        assertEquals("length must be greater than 0", exception.getMessage());
    }

    @Test
    void testGenerateWithNullAlphabet() {
        SettableStringProvider provider = SettableStringProvider.builder().alphabet(null).build();
        String result = provider.generate(62);
        assertEquals(RandomStringProvider.ALPHABET.length(), result.length());
        assertEquals(RandomStringProvider.ALPHABET, result);
    }

    @Test
    void testGenerateSetToNullAlphabet() {
        SettableStringProvider provider = SettableStringProvider.builder().alphabet("a").build();
        String initialResult = provider.generate(10);
        assertEquals("aaaaaaaaaa", initialResult);

        provider.setAlphabet(null);

        String result = provider.generate(62);
        assertEquals(RandomStringProvider.ALPHABET.length(), result.length());
        assertEquals(RandomStringProvider.ALPHABET, result);
    }

    @Test
    void testGenerateWithEmptyAlphabet() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            SettableStringProvider.builder().alphabet("").build();
        });
        assertEquals("alphabet must not be empty", exception.getMessage());
    }

    @Test
    void testSetEmptyAlphabet() {
        SettableStringProvider provider = SettableStringProvider.builder().build();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            provider.setAlphabet("");
        });
        assertEquals("alphabet must not be empty", exception.getMessage());
    }

    @Test
    void testFullAlphabet() {
        SettableStringProvider provider = SettableStringProvider.builder().build();
        String result = provider.generate(124);
        assertEquals(RandomStringProvider.ALPHABET.length() * 2, result.length());
        assertEquals(RandomStringProvider.ALPHABET + RandomStringProvider.ALPHABET, result);
    }

    @Test
    void testSetAlphabet() {
        SettableStringProvider provider = SettableStringProvider.builder().build();

        provider.setAlphabet("a");
        String result = provider.generate(10);
        assertEquals("aaaaaaaaaa", result);

        provider.setAlphabet("b");
        result = provider.generate(20);
        assertEquals("bbbbbbbbbbbbbbbbbbbb", result);

        provider.setAlphabet("c");
        result = provider.generate(30);
        assertEquals("cccccccccccccccccccccccccccccc", result);
    }
}
