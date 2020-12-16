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

package dev.rea.clausewitz.reader;

import dev.rea.clausewitz.entries.ClausewitzEntry;
import dev.rea.clausewitz.parser.ClausewitzFileParser;
import dev.rea.clausewitz.parser.ClausewitzStringParser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public final class ClausewitzFileReader {

    private ClausewitzFileReader() {
        //static class
    }

    public static ArrayList<ClausewitzEntry> read(File file) throws IOException {
        ClausewitzFileParser parser = new ClausewitzFileParser(file);
        return parser.parse();
    }

    public static ArrayList<ClausewitzEntry> read(String string) throws IOException {
        ClausewitzStringParser parser = new ClausewitzStringParser(string);
        return parser.parse();
    }
}
