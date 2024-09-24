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

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Provides the epoch time from a settable value.
 * 
 * Defaults to a value of 0.
 * 
 * @author Jared Hatfield (UnitVectorY Labs)
 */
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SettableEpochTimeProvider implements EpochTimeProvider {

    /**
     * The epoch time in milliseconds.
     */
    private long epochTimeMilliseconds = 0;

    @Override
    public long epochTimeMilliseconds() {
        return this.epochTimeMilliseconds;
    }

    /**
     * Sets the epoch time in seconds.
     * 
     * @param epochTimeSeconds the epoch time in seconds
     */
    public void setEpochTimeSeconds(long epochTimeSeconds) {
        this.epochTimeMilliseconds = epochTimeSeconds * 1000;
    }
}
