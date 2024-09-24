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
 * Provides a string that is based on the given alphabet.
 * 
 * The alphabet must be set for this provider with a given alphabet. A given
 * string of the provided length will generate the same string given the
 * provided alphabet using the same rules as implemented in
 * StaticStringProvider. However, the alphabet here can be changed at any time
 * allowing for variance in the output.
 * 
 * The default alphabet is
 * "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".
 * 
 * For example, if the alphabet is "abc" and the length is 5, the generated
 * string would be "abcab".
 * 
 * @see StaticStringProvider
 * 
 * @author Jared Hatfield (UnitVectorY Labs)
 */
public class SettableStringProvider implements StringProvider {

    /**
     * The alphabet to use for generating the string.
     */
    private String alphabet;

    /**
     * Create a new SettableStringProvider.
     * 
     * @param alphabet the alphabet to use for generating the string
     */
    @Builder
    public SettableStringProvider(String alphabet) {
        if (alphabet == null) {
            this.alphabet = RandomStringProvider.ALPHABET;
        } else if (alphabet.isEmpty()) {
            throw new IllegalArgumentException("alphabet must not be empty");
        } else {
            this.alphabet = alphabet;
        }
    }

    /**
     * Generate a string of the given length.
     * 
     * @param length the length of the string to generate
     * @return the generated string
     */
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
     * Set the alphabet to use for generating the string.
     * 
     * Sertting the alphabet to null will set the alphabet to the default alphabet.
     * 
     * @param alphabet the alphabet to use for generating the string
     */
    public void setAlphabet(String alphabet) {
        if (alphabet == null) {
            this.alphabet = RandomStringProvider.ALPHABET;
        } else if (alphabet.isEmpty()) {
            throw new IllegalArgumentException("alphabet must not be empty");
        } else {
            this.alphabet = alphabet;
        }
    }
}
