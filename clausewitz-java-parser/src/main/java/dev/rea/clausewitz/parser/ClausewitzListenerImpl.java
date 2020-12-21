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

import dev.rea.clausewitz.ClausewitzBaseListener;
import dev.rea.clausewitz.ClausewitzParser.ArrayContext;
import dev.rea.clausewitz.ClausewitzParser.FileContext;
import dev.rea.clausewitz.ClausewitzParser.PairContext;
import dev.rea.clausewitz.datatypes.ClausewitzDate;
import dev.rea.clausewitz.datatypes.ClausewitzPercent;
import dev.rea.clausewitz.datatypes.ClausewitzValueOperator;
import dev.rea.clausewitz.entries.ClausewitzLine;
import dev.rea.clausewitz.entries.ClausewitzMapLine;
import dev.rea.clausewitz.entries.ClausewitzObjectLine;

import java.util.ArrayList;
import java.util.List;

import static dev.rea.clausewitz.ClausewitzParser.ValueContext;

final class ClausewitzListenerImpl extends ClausewitzBaseListener {

    private final List<ClausewitzLine> file = new ArrayList<>();
    private boolean reachedFileEnd = false;
    private MapBuilder currentMap;

    @Override
    public void enterPair(PairContext ctx) {
        super.enterPair(ctx);
        if (ctx.value().clause() != null) {
            currentMap = new MapBuilder(currentMap,
                    ctx.STRING().getText(), ClausewitzValueOperator.get(ctx.VALUE_OPERATOR().getText()));
        }
    }

    @Override
    public void exitPair(PairContext ctx) {
        super.exitPair(ctx);

        ClausewitzLine entry;
        if (ctx.value().clause() != null) {
            MapBuilder parent = currentMap.parent;
            entry = currentMap.build();
            currentMap = parent;
        } else {
            entry = new SingleEntryBuilder(currentMap, ctx.STRING().getText(),
                    ClausewitzValueOperator.get(ctx.VALUE_OPERATOR().getText()),
                    ctx.value()).build();
        }

        if (currentMap == null) {
            file.add(entry);
        } else {
            currentMap.addChild(entry);
        }
    }

    @Override
    public void exitFile(FileContext ctx) {
        super.exitFile(ctx);
        reachedFileEnd = true;
    }

    public ArrayList<ClausewitzLine> getFileEntriesList() {
        return new ArrayList<>(file);
    }

    public boolean haveReachedEndOfFile() {
        return reachedFileEnd;
    }

    private abstract class BaseEntryBuilder {
        protected final MapBuilder parent;
        protected final String name;
        protected final ClausewitzValueOperator valueOperator;

        public BaseEntryBuilder(MapBuilder parent, String name, ClausewitzValueOperator valueOperator) {
            this.parent = parent;
            this.name = name;
            this.valueOperator = valueOperator;
        }

        public abstract ClausewitzLine build();
    }

    private class MapBuilder extends BaseEntryBuilder {
        private final ArrayList<ClausewitzLine> children = new ArrayList<>();

        public MapBuilder(MapBuilder parent, String name, ClausewitzValueOperator valueOperator) {
            super(parent, name, valueOperator);
        }

        public void addChild(ClausewitzLine child) {
            children.add(child);
        }

        @Override
        public ClausewitzLine build() {
            ClausewitzMapLine map = new ClausewitzMapLine(this.name, this.valueOperator);
            map.setChildren(children);
            return map;
        }
    }

    private class SingleEntryBuilder extends BaseEntryBuilder {
        private final ValueContext valueContext;

        public SingleEntryBuilder(MapBuilder parent, String name, ClausewitzValueOperator valueOperator, ValueContext context) {
            super(parent, name, valueOperator);
            this.valueContext = context;
        }

        @Override
        public ClausewitzLine build() {
            parseValue(valueContext);
            return new ClausewitzObjectLine(this.name, this.valueOperator, parseValue(valueContext));
        }

        private Object parseValue(ValueContext valueContext) {
            //todo: This seems gross!
            //Most likely ANTLR allows for a ... smarter solution, but currently i dont know it.
            //Will look back on this todo in the future and unfuck this.
            if (valueContext.INT() != null) {
                return Integer.parseInt(valueContext.INT().getText());
            } else if (valueContext.FLOAT() != null) {
                return Double.parseDouble(valueContext.FLOAT().getText());
            } else if (valueContext.STRING() != null) {
                return valueContext.STRING().getText();
            } else if (valueContext.DATE() != null) {
                return ClausewitzDate.parseDate(valueContext.DATE().getText());
            } else if (valueContext.PERCENT() != null) {
                return ClausewitzPercent.parsePercent(valueContext.PERCENT().getText());
            } else {
                return buildArray(valueContext.array());
            }
        }

        private ArrayList<Object> buildArray(ArrayContext arrayContext) {
            ArrayList<Object> list = new ArrayList<>();
            arrayContext.value().forEach(arrayValueContext ->
                list.add(parseValue(arrayValueContext))
            );
            return list;
        }
    }
}

