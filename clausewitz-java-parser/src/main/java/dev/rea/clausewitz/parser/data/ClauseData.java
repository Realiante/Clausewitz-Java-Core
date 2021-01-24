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

package dev.rea.clausewitz.parser.data;

import dev.rea.clausewitz.common.values.ValueType;

import java.util.ArrayList;

public final class ClauseData implements CollectionData {
    public final ArrayList<EntryData> entries = new ArrayList<>();

    public ClauseData() {
    }

    @Override
    public ValueType getType() {
        return ValueType.CLAUSE;
    }

    @Override
    public boolean addData(Data data) {
        if (!data.isValueType()) {
            entries.add((EntryData) data);
            return true;
        }
        return false;
    }
}
