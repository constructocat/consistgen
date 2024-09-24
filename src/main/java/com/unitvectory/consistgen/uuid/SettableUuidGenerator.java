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
 * Generates a UUID whose value can be set.
 * 
 * The UUID will default to "00000000-0000-0000-0000-000000000000"
 * 
 * @author Jared Hatfield (UnitVectorY Labs)
 */
public class SettableUuidGenerator implements UuidGenerator {

    /**
     * The default UUID.
     */
    private static final String DEFAULT = "00000000-0000-0000-0000-000000000000";

    /**
     * The UUID.
     */
    private String uuid;

    /**
     * Create a new SettableUuidGenerator.
     * 
     * @param uuid the UUID
     */
    @Builder
    public SettableUuidGenerator(String uuid) {
        if (uuid == null) {
            this.uuid = DEFAULT;
        } else {
            this.uuid = uuid;
        }
    }

    @Override
    public String generateUuid() {
        return this.uuid;
    }

    /**
     * Set the UUID.
     * 
     * If value is set to null default value of
     * "00000000-0000-0000-0000-000000000000" will be used.
     * 
     * @param uuid the UUID
     */
    public void setUuid(String uuid) {
        if (uuid == null) {
            this.uuid = DEFAULT;
        } else {
            this.uuid = uuid;
        }
    }
}
