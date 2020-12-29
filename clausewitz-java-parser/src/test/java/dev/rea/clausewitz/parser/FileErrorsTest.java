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

class FileErrorsTest {

    static Stream<Arguments> fileStringSource() {
        return Stream.of(
                Arguments.of("u?"), //error here
                Arguments.of("name = u sr"),
                Arguments.of("first <= {\n" +
                        "test1 >= { 0 0 0 \n" + //error here
                        "}\n" +
                        "second > {\n" +
                        "test2 < { 0 0 0 }\n" +
                        "}")
        );
    }

    @ParameterizedTest
    @MethodSource("fileStringSource")
    void errorFileTest(String string) {
        var result = ClausewitzFileParser.parse(string);
        Assertions.assertTrue(result.getResult().isPresent());
        Assertions.assertTrue(result.getMessage().isPresent());
        System.out.println(result.getMessage().get());
    }

}
