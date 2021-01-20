/*
 *  Copyright 2020 Daniel "Realiant" Fedotov
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package dev.rea.clausewitz.core.exceptions;

import java.util.Properties;

/**
 * This exception is thrown when something truly unexpected occurs.
 * Due to the nature of this exception, contact information is attached in the message.
 * The data is read from info/info.properties resource.
 * If the repository changes ownership, it is important to update that resource.
 */
public class UnexpectedException extends RuntimeException {

    /**
     * @param error Error message describing the unexpected situation.
     */
    public UnexpectedException(String error) {
        super(getErrorMessage(error));
    }

    /**
     * @param error Error message provided in the constructor.
     * @return Formatted error message with contact information attached.
     */
    private static String getErrorMessage(String error) {
        String base = String.format("Unexpected error has occurred: %s", error);
        var inputStream = UnexpectedException.class.getResourceAsStream("info.properties");
        Properties info = new Properties();
        try {
            info.load(inputStream);
        } catch (Exception e) {
            return base;
        }
        return String.format("%s%nPlease post an issue an issue at %s or contact the developer at %s",
                base, info.getProperty("git-source"), info.getProperty("owner"));

    }
}
