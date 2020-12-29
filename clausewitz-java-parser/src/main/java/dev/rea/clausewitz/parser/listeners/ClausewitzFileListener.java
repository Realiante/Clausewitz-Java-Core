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
import dev.rea.clausewitz.entries.ClausewitzMapParsedEntry;
import dev.rea.clausewitz.entries.ClausewitzParsedEntry;
import dev.rea.clausewitz.entries.ClausewitzSingleParsedEntry;
import dev.rea.clausewitz.parser.ValueType;

import java.util.ArrayList;

import static dev.rea.clausewitz.ClausewitzParser.ValueContext;

/**
 * Listener that will create a list of entries.
 */
public final class ClausewitzFileListener extends ClausewitzAbstractListener {
    private final ArrayList<ClausewitzParsedEntry> file = new ArrayList<>();

    private MapBuilder currentMap;

    public ClausewitzFileListener(ClausewitzLexer lexer) {
        super(lexer);
    }


    @Override
    public void enterPair(PairContext ctx) {
        super.enterPair(ctx);
        if (ctx.value() != null && ctx.value().clause() != null) {
            currentMap = new MapBuilder(currentMap,
                    ctx.STRING().getText(), ctx.VALUE_OPERATOR().getText());
        }
    }

    @Override
    public void exitPair(PairContext ctx) {
        super.exitPair(ctx);
        ClausewitzParsedEntry entry = null;
        ValueType type = getValueTypeOrErr(ctx.value());
        if (type == ValueType.CLAUSE) {
            MapBuilder parent = currentMap.parent;
            entry = currentMap.build();
            currentMap = parent;
        } else if (type != ValueType.ERROR) {
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

    public ArrayList<ClausewitzParsedEntry> getFileEntriesList() {
        return new ArrayList<>(file);
    }

    private abstract class BaseEntryBuilder {
        protected final MapBuilder parent;
        protected final String name;
        protected final String valueOperator;

        public BaseEntryBuilder(MapBuilder parent, String name, String valueOperator) {
            this.parent = parent;
            this.name = name;
            this.valueOperator = valueOperator;
        }

        public abstract ClausewitzParsedEntry build();
    }

    private class MapBuilder extends BaseEntryBuilder {
        private final ArrayList<ClausewitzParsedEntry> children = new ArrayList<>();

        public MapBuilder(MapBuilder parent, String name, String valueOperator) {
            super(parent, name, valueOperator);
        }

        public void addChild(ClausewitzParsedEntry child) {
            children.add(child);
        }

        @Override
        public ClausewitzParsedEntry build() {
            ClausewitzMapParsedEntry map = new ClausewitzMapParsedEntry(this.name, this.valueOperator);
            map.setChildren(children);
            return map;
        }
    }

    private class SingleEntryBuilder extends BaseEntryBuilder {
        private final ValueContext valueContext;
        private final ValueType valueType;

        public SingleEntryBuilder(MapBuilder parent, String name, String valueOperator, ValueContext context, ValueType valueType) {
            super(parent, name, valueOperator);
            this.valueContext = context;
            this.valueType = valueType;
        }

        @Override
        public ClausewitzParsedEntry build() {
            return new ClausewitzSingleParsedEntry(this.name, this.valueOperator, valueContext.getText(), valueType);
        }
    }
}

