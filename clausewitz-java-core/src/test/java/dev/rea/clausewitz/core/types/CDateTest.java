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

package dev.rea.clausewitz.core.types;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class CDateTest {

    static Stream<Arguments> parseStringTestSource() {
        return Stream.of(
                Arguments.of("2.13.24.2", new ClausewitzDate(2, 13, 24, 2)),
                Arguments.of("3.3.3", new ClausewitzDate(3, 3, 3)),
                Arguments.of("1.2.3.4.5.6.7.8.9", new ClausewitzDate(1, 2, 3, 4, 5, 6, 7, 8, 9)),
                Arguments.of("2.11.12452", new ClausewitzDate(2, 11, 12452))
        );
    }

    @ParameterizedTest
    @MethodSource("parseStringTestSource")
    void parseTest(String string, ClausewitzDate expected) {
        Assertions.assertEquals(expected, ClausewitzDate.parseDate(string));
    }
}
