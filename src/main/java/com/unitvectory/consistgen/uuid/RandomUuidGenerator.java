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

import java.util.UUID;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Generates a UUID whose value is random.
 * 
 * @author Jared Hatfield (UnitVectorY Labs)
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RandomUuidGenerator implements UuidGenerator {

    private static final RandomUuidGenerator INSTANCE = new RandomUuidGenerator();

    /**
     * Gets the instance of the RandomUuidGenerator.
     * 
     * @return the instance
     */
    public static RandomUuidGenerator getInstance() {
        return INSTANCE;
    }

    @Override
    public String generateUuid() {
        return UUID.randomUUID().toString();
    }
}
