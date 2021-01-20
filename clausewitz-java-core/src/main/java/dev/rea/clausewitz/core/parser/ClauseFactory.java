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

import dev.rea.clausewitz.interfaces.ClausewitzEntry;
import dev.rea.clausewitz.interfaces.Result;
import dev.rea.clausewitz.interfaces.val.Clause;
import dev.rea.clausewitz.parser.entries.ParsedEntry;

import java.util.ArrayList;
import java.util.Set;

public final class ClauseFactory {

    private ClauseFactory() {
        //static class
    }

    public final Result<Clause> parse(String clauseAsString) {
        //todo
        return null;
    }

    public final Clause build(Set<ClausewitzEntry> entries) {
        //todo
        return null;
    }

    protected final Clause build(ArrayList<ParsedEntry> entries) {

        //todo
        return null;
    }
}
