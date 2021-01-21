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
import dev.rea.clausewitz.ClausewitzParser.PairContext;
import dev.rea.clausewitz.ClausewitzParser.ValueContext;
import dev.rea.clausewitz.interfaces.val.ValueType;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;

import java.util.ArrayList;

public abstract class ClausewitzAbstractListener extends ClausewitzBaseListener {
    private final ClausewitzLexer lexer;
    private final ArrayList<String> errors = new ArrayList<>();

    private ParserRuleContext lastVisited;
    private String lastErrPairText;

    protected ClausewitzAbstractListener(ClausewitzLexer lexer) {
        this.lexer = lexer;
    }

    @Override
    public void enterEveryRule(ParserRuleContext ctx) {
        lastVisited = ctx;
    }

    @Override
    public void visitErrorNode(ErrorNode node) {
        super.visitErrorNode(node);
        String lastVisitedText = lastVisited.getText();
        String errorText = node.getText();

        String error;
        if (lastErrPairText != null) {
            error = String.format("Invalid input: %s%n%s after %s", lastErrPairText, errorText, lastVisitedText);
            lastErrPairText = null;
        } else {
            error = String.format("Invalid input: %s %s", lastVisitedText, errorText);
        }

        errors.add(error);
    }

    /**
     * This will extract a value type from a context if one is present
     *
     * @param context Pair context to extract value type from
     * @return value type
     */
    protected ValueType getValueType(PairContext context) {
        ValueContext valueContext = context.value();
        if (valueContext != null && !valueContext.isEmpty()) {
            return getValueType(valueContext);
        } else {
            lastErrPairText = context.getText();
            return null;
        }
    }

    protected ValueType getValueType(ValueContext context) {
        if (context.getChildCount() != 0) {
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
        return null;
    }

    public ArrayList<String> getErrors() {
        return errors;
    }

    protected void addError(String message) {
        errors.add(message);
    }
}
