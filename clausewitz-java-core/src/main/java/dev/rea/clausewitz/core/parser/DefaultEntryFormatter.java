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

package dev.rea.clausewitz.core.parser;

import dev.rea.clausewitz.core.format.*;
import dev.rea.clausewitz.interfaces.Format;
import dev.rea.clausewitz.interfaces.Value;
import dev.rea.clausewitz.interfaces.val.ClausewitzDate;
import dev.rea.clausewitz.interfaces.val.ClausewitzPercent;

import java.util.ArrayList;

public class DefaultEntryFormatter extends EntryFormatter {

    @Override
    protected Format<Integer> integerFormat() {
        return new IntegerFormat();
    }

    @Override
    protected Format<ClausewitzPercent> percentFormat() {
        return new PercentFormat();
    }

    @Override
    protected Format<Float> floatFormat() {
        return new FloatFormat();
    }

    @Override
    protected Format<ClausewitzDate> dateFormat() {
        return new DateFormat();
    }

    @Override
    protected Format<String> stringFormat() {
        return new StringFormat();
    }

    @Override
    protected Format<ArrayList<Value<?>>> arrayFormat() {
        return new ArrayFormat();
    }
}