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

package dev.rea.clausewitz.core.result;

import dev.rea.clausewitz.interfaces.Result;

import java.util.Optional;

public final class BooleanResult implements Result<Boolean> {

    private final String message;
    private final boolean result;

    public BooleanResult(boolean result) {
        this.result = result;
        this.message = null;
    }

    public BooleanResult(String message, boolean result) {
        this.message = message;
        this.result = result;
    }

    @Override
    public Optional<String> getMessage() {
        return Optional.ofNullable(message);
    }

    @Override
    public Optional<Boolean> getResult() {
        return Optional.of(result);
    }
}
