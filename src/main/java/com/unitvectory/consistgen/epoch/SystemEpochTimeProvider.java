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

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Provides the epoch time from the system.
 * 
 * @author Jared Hatfield (UnitVectorY Labs)
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SystemEpochTimeProvider implements EpochTimeProvider {

    private static final SystemEpochTimeProvider INSTANCE = new SystemEpochTimeProvider();

    /**
     * Gets the instance of the SystemEpochTimeProvider.
     * 
     * @return the detailt instance of the SystemEpochTimeProvider
     */
    public static SystemEpochTimeProvider getInstance() {
        return INSTANCE;
    }

    @Override
    public long epochTimeMilliseconds() {
        return System.currentTimeMillis();
    }
}
