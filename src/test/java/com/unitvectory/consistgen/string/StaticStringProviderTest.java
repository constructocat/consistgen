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
 * Test the SystemEpochTimeProvider class.
 * 
 * @author Jared Hatfield (UnitVectorY Labs)
 */
class StaticStringProviderTest {

    @Test
    void testGenerateWithDefaultAlphabet() {
        StaticStringProvider provider = StaticStringProvider.getInstance();
        String result = provider.generate(10);
        assertEquals("abcdefghij", result);
    }

    @Test
    void testGenerateWithCustomAlphabet() {
        StaticStringProvider provider = StaticStringProvider.builder().alphabet("abc").build();
        String result = provider.generate(5);
        assertEquals("abcab", result);
    }

    @Test
    void testGenerateWithEmptyAlphabet() {
        assertThrows(IllegalArgumentException.class, () -> {
            StaticStringProvider.builder().alphabet("").build();
        });
    }

    @Test
    void testGenerateWithNullAlphabet() {
        StaticStringProvider provider = StaticStringProvider.builder().alphabet(null).build();
        String result = provider.generate(10);
        assertEquals("abcdefghij", result);
    }

    @Test
    void testGenerateWithNegativeLength() {
        StaticStringProvider provider = StaticStringProvider.builder().build();
        assertThrows(IllegalArgumentException.class, () -> {
            provider.generate(-1);
        });
    }

    @Test
    void testGenerateWithZeroLength() {
        StaticStringProvider provider = StaticStringProvider.builder().build();
        assertThrows(IllegalArgumentException.class, () -> {
            provider.generate(0);
        });
    }
}
