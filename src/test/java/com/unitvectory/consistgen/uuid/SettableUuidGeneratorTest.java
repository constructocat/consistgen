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
package com.unitvectory.consistgen.uuid;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 * Test the SettableUuidGenerator class.
 * 
 * @author Jared Hatfield (UnitVectorY Labs)
 */
class SettableUuidGeneratorTest {

    @Test
    void testDefaultUuid() {
        SettableUuidGenerator generator = SettableUuidGenerator.builder().build();
        assertEquals("00000000-0000-0000-0000-000000000000", generator.generateUuid());
    }

    @Test
    void testConstructorWithUuid() {
        String customUuid = "12345678-1234-1234-1234-123456789abc";
        SettableUuidGenerator generator = SettableUuidGenerator.builder().uuid(customUuid).build();
        assertEquals(customUuid, generator.generateUuid());
    }

    @Test
    void testUuidSetter() {
        SettableUuidGenerator generator = SettableUuidGenerator.builder().build();
        String firstUuid = "11111111-1111-1111-1111-111111111111";
        generator.setUuid(firstUuid);
        assertEquals(firstUuid, generator.generateUuid());

        String secondUuid = "22222222-2222-2222-2222-222222222222";
        generator.setUuid(secondUuid);
        assertEquals(secondUuid, generator.generateUuid());

        generator.setUuid(null);
        assertEquals("00000000-0000-0000-0000-000000000000", generator.generateUuid());
    }
}