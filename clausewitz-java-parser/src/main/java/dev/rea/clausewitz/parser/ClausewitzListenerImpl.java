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
import dev.rea.clausewitz.ClausewitzParser.FileContext;
import dev.rea.clausewitz.ClausewitzParser.PairContext;
import dev.rea.clausewitz.entries.ClausewitzEntry;
import dev.rea.clausewitz.entries.ClausewitzMapEntry;
import dev.rea.clausewitz.entries.ClausewitzSingleEntry;
import org.antlr.v4.runtime.tree.ErrorNode;

import java.util.ArrayList;

import static dev.rea.clausewitz.ClausewitzParser.ValueContext;

final class ClausewitzListenerImpl extends ClausewitzBaseListener {

    private final ArrayList<ClausewitzEntry> file = new ArrayList<>();
    private final ArrayList<String> errors = new ArrayList<>();

    private boolean reachedFileEnd = false;
    private MapBuilder currentMap;

    @Override
    public void enterPair(PairContext ctx) {
        super.enterPair(ctx);
        if (ctx.value().clause() != null) {
            currentMap = new MapBuilder(currentMap,
                    ctx.STRING().getText(), ctx.VALUE_OPERATOR().getText());
        }
    }

    @Override
    public void exitPair(PairContext ctx) {
        super.exitPair(ctx);

        ClausewitzEntry entry;
        if (ctx.value().clause() != null) {
            MapBuilder parent = currentMap.parent;
            entry = currentMap.build();
            currentMap = parent;
        } else {
            entry = new SingleEntryBuilder(currentMap, ctx.STRING().getText(),
                    ctx.VALUE_OPERATOR().getText(),
                    ctx.value()).build();
        }

        if (currentMap == null) {
            file.add(entry);
        } else {
            currentMap.addChild(entry);
        }
    }

    @Override
    public void visitErrorNode(ErrorNode node) {
        super.visitErrorNode(node);
        errors.add(node.getText());
    }

    @Override
    public void exitFile(FileContext ctx) {
        super.exitFile(ctx);
        reachedFileEnd = true;
    }

    public ArrayList<ClausewitzEntry> getFileEntriesList() {
        return new ArrayList<>(file);
    }

    public ArrayList<String> getErrors() {
        return errors;
    }

    public boolean haveReachedEndOfFile() {
        return reachedFileEnd;
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

        public abstract ClausewitzEntry build();
    }

    private class MapBuilder extends BaseEntryBuilder {
        private final ArrayList<ClausewitzEntry> children = new ArrayList<>();

        public MapBuilder(MapBuilder parent, String name, String valueOperator) {
            super(parent, name, valueOperator);
        }

        public void addChild(ClausewitzEntry child) {
            children.add(child);
        }

        @Override
        public ClausewitzEntry build() {
            ClausewitzMapEntry map = new ClausewitzMapEntry(this.name, this.valueOperator);
            map.setChildren(children);
            return map;
        }
    }

    private class SingleEntryBuilder extends BaseEntryBuilder {
        private final ValueContext valueContext;

        public SingleEntryBuilder(MapBuilder parent, String name, String valueOperator, ValueContext context) {
            super(parent, name, valueOperator);
            this.valueContext = context;
        }

        @Override
        public ClausewitzEntry build() {
            return new ClausewitzSingleEntry(this.name, this.valueOperator, valueContext.getText());
        }
    }
}

