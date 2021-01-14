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

package dev.rea.clausewitz.parser.entries.builders;

import dev.rea.clausewitz.parser.entries.ClauseParsedEntry;
import dev.rea.clausewitz.parser.entries.ParsedEntry;

import java.util.ArrayList;

public class ClauseEntryBuilder extends BaseEntryBuilder {

    private final ArrayList<ParsedEntry> children = new ArrayList<>();

    public ClauseEntryBuilder(ClauseEntryBuilder parent, String name, String valueOperator) {
        super(parent, name, valueOperator);
    }

    public void addChild(ParsedEntry child) {
        children.add(child);
    }


    @Override
    public ParsedEntry build() {
        ClauseParsedEntry clause = new ClauseParsedEntry(this.name, this.valueOperator);
        clause.setChildren(children);
        return clause;
    }
}
