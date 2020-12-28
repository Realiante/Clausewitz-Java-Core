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

import dev.rea.clausewitz.ClausewitzLexer;
import dev.rea.clausewitz.ClausewitzParser;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;

interface ClausewitzBaseFileParser {

    default FileParseResult parseAsFile() throws IOException {
        CommonTokenStream tokenStream = new CommonTokenStream(buildLexer());
        ClausewitzParser parser = new ClausewitzParser(tokenStream);

        ParseTreeWalker walker = new ParseTreeWalker();
        ClausewitzListenerImpl listener = new ClausewitzListenerImpl();

        walker.walk(listener, parser.file());
        return new FileParseResult(listener.getErrors(), listener.getFileEntriesList());
    }

    ClausewitzLexer buildLexer() throws IOException;
}
