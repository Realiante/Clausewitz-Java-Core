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

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

final class CEntryNameKey {

    protected static final Map<String, CEntryNameKey> findMap = new HashMap<>();
    private static long lastID = 0;
    private final long id;
    private String currentName;

    public CEntryNameKey(String name) {
        id = lastID++;
        setNewLegalName(name);
    }

    public String getCurrentName() {
        return currentName;
    }

    public void setNewLegalName(String newName) {
        if (!(newName == null || newName.length() == 0) && (this.currentName == null || !this.currentName.equals(newName))) {
            findMap.remove(currentName);
            this.currentName = newName;
            findMap.put(currentName, this);
        } else {
            throw new IllegalClausewitzEntryNameException();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CEntryNameKey that = (CEntryNameKey) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
