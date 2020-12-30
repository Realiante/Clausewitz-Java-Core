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

package dev.rea.clausewitz.parser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class ValueErrorsTest {

    static Stream<Arguments> valueStringSource() {
        return Stream.of(
                Arguments.of("1string", true),
                Arguments.of("\"notClosed", true),
                Arguments.of("{ notClosed = string", true),
                Arguments.of("normal", false)
        );
    }

    @ParameterizedTest
    @MethodSource("valueStringSource")
    void errorTest(String string, boolean expectingErrors) {
        var result = ClausewitzValueParser.parse(string);
        Assertions.assertTrue(result.getResult().isPresent());
        Assertions.assertEquals(expectingErrors, result.getMessage().isPresent());

        if (result.getMessage().isPresent()) {
            System.out.println(result.getMessage().get());
        } else {
            System.out.println("No errors!");
        }
    }

}
