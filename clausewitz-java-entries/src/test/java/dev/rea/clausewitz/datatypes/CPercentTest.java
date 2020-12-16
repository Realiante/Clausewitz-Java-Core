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

package dev.rea.clausewitz.datatypes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.of;

class CPercentTest {

    static Stream<Arguments> parseTestSource() {
        return Stream.of(
                of("1%", new ClausewitzPercent(1)),
                of("2%", new ClausewitzPercent(2)),
                of("155%", new ClausewitzPercent(155))
        );
    }

    @ParameterizedTest
    @MethodSource("parseTestSource")
    void parseTest(String string, ClausewitzPercent expected) {
        Assertions.assertEquals(expected, ClausewitzPercent.parsePercent(string));
    }
}
