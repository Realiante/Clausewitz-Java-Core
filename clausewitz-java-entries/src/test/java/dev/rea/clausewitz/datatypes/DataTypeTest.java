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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Stream;

import static dev.rea.clausewitz.datatypes.ClausewitzDataType.*;
import static org.junit.jupiter.params.provider.Arguments.of;

class DataTypeTest {

    static Stream<Arguments> dataTypeMatchSource() {
        return Stream.of(
                of(INTEGER, 123, true),
                of(DOUBLE, 143, false),
                of(DOUBLE, 12.34, true),
                of(STRING, "String", true),
                of(PERCENT, new ClausewitzPercent(12), true),
                of(DATE, new ClausewitzDate(1, 2, 3, 4), true),
                of(MAP, new HashMap<String, Object>(), true),
                of(ARRAY, new ArrayList<>(), true),
                of(ANY, "String", true),
                of(ANY, new Object(), true)
        );
    }

    static Stream<Arguments> getTypeSource() {
        return Stream.of(
                of(INTEGER, 123, "INT"),
                of(DOUBLE, 12.34, "DOUBLE"),
                of(STRING, "String", "STRING"),
                of(PERCENT, new ClausewitzPercent(12), "PERCENT"),
                of(DATE, new ClausewitzDate(1, 2, 3, 4), "DATE"),
                of(MAP, new HashMap<String, Object>(), "MAP"),
                of(ARRAY, new ArrayList<>(), "ARRAY")
        );
    }

    @ParameterizedTest
    @MethodSource("dataTypeMatchSource")
    void matchTest(ClausewitzDataType type, Object obj, boolean expectedResult) {
        Assertions.assertEquals(expectedResult, ClausewitzDataType.matches(type, obj));
    }

    @ParameterizedTest
    @MethodSource("getTypeSource")
    void getTest(ClausewitzDataType expectedType, Object obj, String key) {
        Assertions.assertEquals(expectedType, getType(obj));
        Assertions.assertEquals(expectedType, get(key));
    }
}
