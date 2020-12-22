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

package dev.rea.clausewitz.core.types;

import java.util.Arrays;
import java.util.Objects;

public class ClausewitzDate {

    private int day;
    private int month;
    private int year;
    private int[] opt;

    public static ClausewitzDate parseDate(String string) {
        String[] parts = string.split("\\.");
        int d = Integer.parseInt(parts[0]);
        int m = Integer.parseInt(parts[1]);
        int y = Integer.parseInt(parts[2]);

        int[] optArr = new int[parts.length - 3];
        for (int i = 3; i < parts.length; i++) {
            optArr[i - 3] = Integer.parseInt(parts[i]);
        }

        return new ClausewitzDate(d, m, y, optArr);
    }

    public ClausewitzDate(int day, int month, int year, int... optional) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.opt = optional;
    }

    public void set(int day, int month, int year, int... optional) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.opt = optional;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public int[] getOpt() {
        return opt;
    }

    @Override
    public String toString() {
        String date = String.format("%d.%d.%d", year, month, day);
        for (int t : opt) {
            date = date.concat("." + t);
        }
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClausewitzDate that = (ClausewitzDate) o;
        return day == that.day &&
                month == that.month &&
                year == that.year &&
                Arrays.equals(opt, that.opt);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(day, month, year);
        result = 31 * result + Arrays.hashCode(opt);
        return result;
    }
}
