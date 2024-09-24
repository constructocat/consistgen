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
package com.unitvectory.consistgen.epoch;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 * Test the SettableEpochTimeProvider class.
 * 
 * @author Jared Hatfield (UnitVectorY Labs)
 */
class SettableEpochTimeProviderTest {

    @Test
    void testDefaultConstructor() {
        SettableEpochTimeProvider provider = new SettableEpochTimeProvider();
        assertEquals(0, provider.epochTimeMilliseconds());
    }

    @Test
    void testParameterizedConstructor() {
        long expectedTime = 5000;
        SettableEpochTimeProvider provider = new SettableEpochTimeProvider(expectedTime);
        assertEquals(expectedTime, provider.epochTimeMilliseconds());
    }

    @Test
    void testSetEpochTimeMilliseconds() {
        SettableEpochTimeProvider provider = new SettableEpochTimeProvider();
        long newTime = 10000;
        provider.setEpochTimeMilliseconds(newTime);
        assertEquals(newTime, provider.epochTimeMilliseconds());
    }

    @Test
    void testsetEpochTimeSeconds() {
        SettableEpochTimeProvider provider = new SettableEpochTimeProvider();
        long newTimeSeconds = 10;
        provider.setEpochTimeSeconds(newTimeSeconds);
        assertEquals(newTimeSeconds * 1000, provider.epochTimeMilliseconds());
    }

    @Test
    void testsetEpochTimeSecondsMultiple() {
        SettableEpochTimeProvider provider = new SettableEpochTimeProvider();
        long newTimeSeconds = 10;
        provider.setEpochTimeSeconds(newTimeSeconds);
        assertEquals(newTimeSeconds * 1000, provider.epochTimeMilliseconds());
        newTimeSeconds = 20;
        provider.setEpochTimeSeconds(newTimeSeconds);
        assertEquals(newTimeSeconds * 1000, provider.epochTimeMilliseconds());
    }
}