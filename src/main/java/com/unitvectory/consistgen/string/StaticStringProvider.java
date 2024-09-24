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

import lombok.Builder;

/**
 * Provides a string that is static based on the alphabet provided.
 * 
 * The same string will always be generated for the specified length. The
 * alphabet is repeated in order to generate the string if the string is longer
 * than the alphabet.
 * 
 * The default alphabet is
 * "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".
 * 
 * For example, if the alphabet is "abc" and the length is 5, the generated
 * string would be "abcab".
 * 
 * @author Jared Hatfield (UnitVectorY Labs)
 */
public class StaticStringProvider implements StringProvider {

    /*
     * The default instance of the StaticStringProvider.
     */
    private static final StaticStringProvider INSTANCE = StaticStringProvider.builder().build();

    /**
     * The alphabet to use for generating the string.
     */
    private final String alphabet;

    /**
     * Creates a new static string provider.
     * 
     * @param alphabet the alphabet to use for generating the string
     */
    @Builder
    public StaticStringProvider(String alphabet) {
        if (alphabet == null) {
            this.alphabet = RandomStringProvider.ALPHABET;
        } else if (alphabet.isEmpty()) {
            throw new IllegalArgumentException("alphabet must not be empty");
        } else {
            this.alphabet = alphabet;
        }
    }

    @Override
    public String generate(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("length must be greater than 0");
        }

        StringBuilder builder = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            builder.append(alphabet.charAt(i % alphabet.length()));
        }

        return builder.toString();
    }

    /**
     * Get the default instance of the StaticStringProvider.
     * 
     * @return the default instance of the StaticStringProvider
     */
    public static StaticStringProvider getInstance() {
        return INSTANCE;
    }
}
