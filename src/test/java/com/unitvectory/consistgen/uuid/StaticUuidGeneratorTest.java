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
 * Test the StaticUuidGenerator class.
 * 
 * @author Jared Hatfield (UnitVectorY Labs)
 */
class StaticUuidGeneratorTest {

    @Test
    void testGenerateUuid() {
        UuidGenerator generator = StaticUuidGenerator.builder().build();
        assertEquals("00000000-0000-0000-0000-000000000000", generator.generateUuid());
    }

    @Test
    void testGenerateUuidSpecific() {
        UuidGenerator generator = StaticUuidGenerator.builder().uuid("11111111-1111-1111-1111-111111111111")
                .build();
        assertEquals("11111111-1111-1111-1111-111111111111", generator.generateUuid());
    }
}
