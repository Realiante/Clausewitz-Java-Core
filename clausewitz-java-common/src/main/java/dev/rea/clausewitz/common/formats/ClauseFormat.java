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

package dev.rea.clausewitz.common.formats;

import dev.rea.clausewitz.common.values.Clause;
import dev.rea.clausewitz.common.values.ValueType;

public final class ClauseFormat extends AbstractValueFormat<Clause> {

    @Override
    protected Clause buildObject(String string) {
        //TODO: When clause factory is in
        return null;
    }

    @Override
    public ValueType getValueType() {
        return ValueType.CLAUSE;
    }
}
