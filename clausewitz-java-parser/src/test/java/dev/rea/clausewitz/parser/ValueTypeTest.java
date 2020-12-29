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

import dev.rea.clausewitz.entries.ClausewitzMapParsedEntry;
import dev.rea.clausewitz.entries.ClausewitzParsedEntry;
import dev.rea.clausewitz.interfaces.Result;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class ValueTypeTest {

    @Test
    void ValueTypeAssignmentTest() {
        String toParse = "first = 100 \n" + "second = 200%" + "third = {\n" +
                "childOfThird = {0 0 0}\n " + "}";

        Result<ArrayList<ClausewitzParsedEntry>> result = ClausewitzFileParser.parse(toParse);
        Assertions.assertTrue(result.getMessage().isEmpty());
        Assertions.assertTrue(result.getResult().isPresent());
        var entries = result.getResult().get();

        Assertions.assertSame(ValueType.INT, entries.get(0).valueType);
        Assertions.assertSame(ValueType.PERCENT, entries.get(1).valueType);
        Assertions.assertSame(ValueType.CLAUSE, entries.get(2).valueType);

        ClausewitzMapParsedEntry map = (ClausewitzMapParsedEntry) entries.get(2);
        Assertions.assertEquals(1, map.getChildren().size());
        Assertions.assertSame(ValueType.ARRAY, map.getChild("childOfThird").valueType);
    }
}
