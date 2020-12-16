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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Objects;

class FileParserTest {

    @Test
    void parseFile() throws URISyntaxException, IOException {
        File file = new File(Objects.requireNonNull(
                getClass().getClassLoader().getResource("test.txt")).toURI());
        ClausewitzFileParser parser = new ClausewitzFileParser(file);
        ArrayList<ClausewitzEntry> list = parser.parse();

        Assertions.assertEquals(1, list.size());
        ClausewitzEntry entry = list.get(0);

        String entryString = entry.toString();
        ClausewitzStringParser stringParser = new ClausewitzStringParser(entryString);
        list = stringParser.parse();

        Assertions.assertEquals(1, list.size());
        ClausewitzEntry entryFromString = list.get(0);

        Assertions.assertEquals(entry.toString(), entryFromString.toString());
    }
}
