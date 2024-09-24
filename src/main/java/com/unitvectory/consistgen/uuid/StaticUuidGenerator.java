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

import lombok.Builder;

/**
 * Generates a UUID whose value is always the same.
 * 
 * @author Jared Hatfield (UnitVectorY Labs)
 */
public class StaticUuidGenerator implements UuidGenerator {

    /**
     * The UUID.
     */
    private final String uuid;

    /**
     * Creates a new StaticUuidGenerator.
     * 
     * @param uuid the UUID
     */
    @Builder
    public StaticUuidGenerator(String uuid) {
        if (uuid == null) {
            this.uuid = "00000000-0000-0000-0000-000000000000";
        } else {
            this.uuid = uuid;
        }
    }

    @Override
    public String generateUuid() {
        return uuid;
    }

}
