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

package dev.rea.clausewitz.entries;

public class ClausewitzSingleEntry extends ClausewitzEntry {

    private final String entry;

    public ClausewitzSingleEntry(ClausewitzMapEntry parent, String name, String valueOperator, String entry) {
        super(parent, name, valueOperator);
        this.entry = entry;
    }

    public ClausewitzSingleEntry(String name, String valueOperator, String entry) {
        super(name, valueOperator);
        this.entry = entry;
    }

    public String getEntry() {
        return entry;
    }

    @Override
    public String valueToString() {
        return entry;
    }
}
