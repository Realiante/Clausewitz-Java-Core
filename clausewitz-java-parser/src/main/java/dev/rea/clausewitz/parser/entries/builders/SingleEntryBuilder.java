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

import dev.rea.clausewitz.ClausewitzParser;
import dev.rea.clausewitz.interfaces.val.ValueType;
import dev.rea.clausewitz.parser.entries.ParsedEntry;
import dev.rea.clausewitz.parser.entries.SingleParsedEntry;

public class SingleEntryBuilder extends BaseEntryBuilder {
    private final ClausewitzParser.ValueContext valueContext;
    private final ValueType valueType;

    public SingleEntryBuilder(ClauseEntryBuilder parent, String name, String valueOperator, ClausewitzParser.ValueContext context, ValueType valueType) {
        super(parent, name, valueOperator);
        this.valueContext = context;
        this.valueType = valueType;
    }

    @Override
    public ParsedEntry build() {
        return new SingleParsedEntry(this.name, this.valueOperator, valueContext.getText(), valueType);
    }
}
