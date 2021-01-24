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

import dev.rea.clausewitz.common.values.ValueOperator;

/**
 * Mimics the structure of the common entry.
 */
public final class EntryData {

    public final String name;
    public final ValueOperator operator;
    public final ValueData value;

    public EntryData(String name, ValueOperator operator, ValueData value) {
        this.name = name;
        this.operator = operator;
        this.value = value;
    }
}
