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
import dev.rea.clausewitz.ClausewitzParser.ValueContext;
import dev.rea.clausewitz.parser.ValueType;

/**
 * Listener that will only assign a type to a first encountered value.
 * Meant as an error checker.
 */
public final class ClausewitzErrorListener extends ClausewitzAbstractListener {

    private ValueType firstValueType;

    public ClausewitzErrorListener(ClausewitzLexer lexer) {
        super(lexer);
    }

    @Override
    public void enterValue(ValueContext ctx) {
        super.enterValue(ctx);

        if (firstValueType == null) {
            firstValueType = getValueTypeOrErr(ctx);
        }
    }

    public ValueType getFirstValueType() {
        return firstValueType;
    }
}
