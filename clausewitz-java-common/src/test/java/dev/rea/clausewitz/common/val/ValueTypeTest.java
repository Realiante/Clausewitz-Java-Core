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

package dev.rea.clausewitz.common.val;

import dev.rea.clausewitz.common.values.Clause;
import dev.rea.clausewitz.common.values.ClausewitzDate;
import dev.rea.clausewitz.common.values.ClausewitzPercent;
import dev.rea.clausewitz.common.values.ValueType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.stream.Stream;

import static dev.rea.clausewitz.common.values.ValueType.*;
import static org.junit.jupiter.params.provider.Arguments.of;

class ValueTypeTest {

    static Stream<Arguments> valueStringSource() {
        return Stream.of(of(STRING, STRING.getSymbolic(), "String"),
                of(INT, INT.getSymbolic(), 12),
                of(FLOAT, FLOAT.getSymbolic(), 12.3f),
                of(PERCENT, PERCENT.getSymbolic(), new ClausewitzPercent(11)),
                of(DATE, DATE.getSymbolic(), new ClausewitzDate(11, 3, 4)),
                of(CLAUSE, CLAUSE.getSymbolic(), new Clause()),
                of(ARRAY, ARRAY.getSymbolic(), new ArrayList<>()),
                of(ARRAY, ARRAY.getSymbolic(), new LinkedList<>())
        );
    }

    @ParameterizedTest
    @MethodSource("valueStringSource")
    void getValueTypeTest(ValueType expected, String symbolic, Object obj) {
        Assertions.assertSame(expected, ValueType.getBySymbolic(symbolic));
        Assertions.assertSame(expected, ValueType.getType(obj));
    }

    @Test
    void nullValueTypeTest() {
        Assertions.assertNull(ValueType.getType(new Object()));
    }


}