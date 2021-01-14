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

package dev.rea.clausewitz.parser.listeners;

import dev.rea.clausewitz.ClausewitzLexer;
import dev.rea.clausewitz.ClausewitzParser.PairContext;
import dev.rea.clausewitz.interfaces.val.ValueType;
import dev.rea.clausewitz.parser.entries.ParsedEntry;
import dev.rea.clausewitz.parser.entries.builders.ClauseEntryBuilder;
import dev.rea.clausewitz.parser.entries.builders.SingleEntryBuilder;

import java.util.ArrayList;

/**
 * Listener that will create a list of entries.
 */
public final class ClausewitzFileListener extends ClausewitzAbstractListener {
    private final ArrayList<ParsedEntry> file = new ArrayList<>();

    private ClauseEntryBuilder currentMap;

    public ClausewitzFileListener(ClausewitzLexer lexer) {
        super(lexer);
    }


    @Override
    public void enterPair(PairContext ctx) {
        super.enterPair(ctx);
        if (ctx.value() != null && ctx.value().clause() != null) {
            currentMap = new ClauseEntryBuilder(currentMap,
                    ctx.STRING().getText(), ctx.VALUE_OPERATOR().getText());
        }
    }

    @Override
    public void exitPair(PairContext ctx) {
        super.exitPair(ctx);
        ParsedEntry entry = null;
        ValueType type = getValueType(ctx);
        if (type == ValueType.CLAUSE) {
            ClauseEntryBuilder parent = currentMap.parent;
            entry = currentMap.build();
            currentMap = parent;
        } else if (type != null) {
            entry = new SingleEntryBuilder(
                    currentMap, ctx.STRING().getText(),
                    ctx.VALUE_OPERATOR().getText(),
                    ctx.value(), type).build();
        }

        if (entry != null) {
            if (currentMap == null) {
                file.add(entry);
            } else {
                currentMap.addChild(entry);
            }
        }
    }

    public ArrayList<ParsedEntry> getFileEntriesList() {
        return new ArrayList<>(file);
    }

}

