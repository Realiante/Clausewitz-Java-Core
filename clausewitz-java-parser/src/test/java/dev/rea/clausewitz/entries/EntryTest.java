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

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static dev.rea.clausewitz.datatypes.ClausewitzValueOperator.EQUAL;

class EntryTest {

    @Test
    void EntryNameThrowTest() {
        Assertions.assertThrows(
                IllegalClausewitzEntryNameException.class,
                () -> new ClausewitzObjectLine("", EQUAL, 123));

        Assertions.assertThrows(
                IllegalClausewitzEntryNameException.class,
                () -> new ClausewitzObjectLine(null, EQUAL, 123));
    }

    @Test
    void findableFromParentTest() {
        ClausewitzMapLine parent = new ClausewitzMapLine("Parent", EQUAL);
        ClausewitzObjectLine entry = new ClausewitzObjectLine(parent, "FIRST", EQUAL, "Object");

        Assertions.assertTrue(entry.getParent().isPresent());
        Assertions.assertEquals(CEntryNameKey.findKeyByName("FIRST"), entry.getKey());
        Assertions.assertTrue(Optional.ofNullable(parent.getChild("FIRST")).isPresent());

        entry.setName("SECOND");

        Assertions.assertEquals(CEntryNameKey.findKeyByName("SECOND"), entry.getKey());
        Assertions.assertFalse(Optional.ofNullable(parent.getChild("FIRST")).isPresent());
        Assertions.assertTrue(Optional.ofNullable(parent.getChild("SECOND")).isPresent());
        Assertions.assertEquals(parent.getChild("SECOND").getName(), entry.getName());
    }
}
