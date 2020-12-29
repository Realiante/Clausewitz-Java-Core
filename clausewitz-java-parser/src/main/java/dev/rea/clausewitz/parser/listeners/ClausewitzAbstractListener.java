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

import dev.rea.clausewitz.ClausewitzBaseListener;
import dev.rea.clausewitz.ClausewitzLexer;
import dev.rea.clausewitz.ClausewitzParser;
import dev.rea.clausewitz.ClausewitzParser.ValueContext;
import dev.rea.clausewitz.parser.ValueType;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.tree.ErrorNode;

import java.util.ArrayList;

public abstract class ClausewitzAbstractListener extends ClausewitzBaseListener {
    private final ClausewitzLexer lexer;
    private final ArrayList<String> errors = new ArrayList<>();

    protected ClausewitzAbstractListener(ClausewitzLexer lexer) {
        this.lexer = lexer;
    }

    @Override
    public void visitErrorNode(ErrorNode node) {
        super.visitErrorNode(node);
        errors.add(node.getText());
    }

    protected ValueType getValueTypeOrErr(ValueContext context) {
        if (context != null && !context.isEmpty() && context.getChildCount() != 0) {
            return getValueType(context);
        } else {
            return ValueType.ERROR;
        }
    }

    private ValueType getValueType(ValueContext context) {
        Object payload = context.getChild(0).getPayload();

        if (payload instanceof ClausewitzParser.ArrayContext) {
            return ValueType.ARRAY;
        } else if (payload instanceof ClausewitzParser.ClauseContext) {
            return ValueType.CLAUSE;
        } else {
            CommonToken token = (CommonToken) payload;
            int type = token.getType();
            String symbolic = lexer.getVocabulary().getSymbolicName(type);
            return ValueType.getBySymbolic(symbolic);
        }
    }

    public ArrayList<String> getErrors() {
        return errors;
    }
}
