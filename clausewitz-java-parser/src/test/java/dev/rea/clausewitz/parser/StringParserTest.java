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

import dev.rea.clausewitz.entries.ClausewitzEntry;
import dev.rea.clausewitz.entries.ClausewitzMapEntry;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

 class StringParserTest {

    static Stream<Arguments> stringSource() {
        return Stream.of(
                Arguments.of("", 0, 0),
                Arguments.of("e_empire_tier = {\n" +
                        "       number = 12\n" +
                        "       text = \"some text\"\n" +
                        "       capital = c_county_tier\n" +
                        "   \n" +
                        "       k_kingdom_tier = {\n" +
                        "           percent_norm = 12%\n" +
                        "           percent = 121%\n" +
                        "           date = 11.11.23.4.2.1\n" +
                        "   \n" +
                        "           d_duchy_tier = {\n" +
                        "               color = { 0 0 0 }\n" +
                        "               date = { 255 255 255 }\n" +
                        "               capital = c_county_tier\n" +
                        "   \n" +
                        "               c_county_tier = {\n" +
                        "                   color = { 0 0 0 }\n" +
                        "                   color2 = { text text2 text3 }\n" +
                        "   \n" +
                        "                   b_barony_tier = {\n" +
                        "                       color = { 0 0 0 }\n" +
                        "                       color2 = { 255 255 255 }\n" +
                        "                       province = id\n" +
                        "                   }\n" +
                        "               }\n" +
                        "           }\n" +
                        "       }\n" +
                        "   }", 1, 4),
                Arguments.of("first <= {\n" +
                        "test1 >= { 0 0 0 }\n" +
                        "}\n" +
                        "second > {\n" +
                        "test2 < { 0 0 0 }\n" +
                        "}", 2, 0)
        );
    }

    @ParameterizedTest
    @MethodSource("stringSource")
    void parseString(String text, int count, int depth) {
        Assertions.assertDoesNotThrow(() -> {
            ClausewitzStringParser parser = new ClausewitzStringParser(text);
            List<ClausewitzEntry> list = parser.parse();
            Assertions.assertEquals(count, list.size());

            int maxDepth = 0;
            for (var entry : list) {
                if (entry instanceof ClausewitzMapEntry) {
                    maxDepth = Math.max(maxDepth, findMaxDepth((ClausewitzMapEntry) entry));
                }
            }

            Assertions.assertEquals(depth, maxDepth);
        });
    }

    int findMaxDepth(ClausewitzMapEntry entry) {
        int maxDepth = entry.getDepth();
        for (var child : entry.getChildren()) {
            if (child instanceof ClausewitzMapEntry) {
                maxDepth = Math.max(maxDepth, findMaxDepth((ClausewitzMapEntry) child));
            }
        }
        return maxDepth;
    }
}
