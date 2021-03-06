/*
 * Copyright 2020 Hochschule Luzern Informatik.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ch.hslu.ad.sw12.exercise.n4.quicksort;

import ch.hslu.ad.sw10.Sort;

import java.util.concurrent.RecursiveAction;

/**
 * Codevorlage zu RecursiveAction für die Sortierung eines int-Arrays.
 */
@SuppressWarnings("serial")
public final class QuicksortTask extends RecursiveAction {

    private static final int THRESHOLD = 1000;
    private final int[] array;
    private final int min;
    private final int max;

    /**
     * Erzeugt einen Array-Sortier Task.
     *
     * @param array Interger-Array.
     */
    public QuicksortTask(int[] array) {
        this(array, 0, array.length - 1);
    }

    private QuicksortTask(final int[] array, final int min, final int max) {
        this.array = array;
        this.min = min;
        this.max = max;
    }

    @Override
    protected void compute() {
        if ((this.max - this.min) <= THRESHOLD) {
            Sort.insertionSort(array, min, max);
        } else {
            int up = this.min;
            int down = this.max - 1;
            int separator = array[this.max];
            boolean allChecked = false;
            do {
                while (array[up] < separator) {
                    up++;
                }
                while ((array[down] > separator) && (down > up)) {
                    down--;
                }
                if (down > up) {
                    exchange(array, up, down);
                    up++;
                    down--;
                } else {
                    allChecked = true;
                }
            } while (!allChecked);
            exchange(array, up, this.max);

            QuicksortTask left = null;
            if (this.min < (up - 1)) {
                left = new QuicksortTask(array, this.min, (up - 1));
                left.fork();
            }
            QuicksortTask right = null;
            if ((up + 1) < this.max) {
                right = new QuicksortTask(array, (up + 1), this.max);
                right.fork();
            }
            if (left != null) left.join();
            if (right != null) right.join();
        }
    }

    private static void exchange(final int[] arr, final int firstIndex, final int secondIndex) {
        int temp;
        temp = arr[firstIndex];
        arr[firstIndex] = arr[secondIndex];
        arr[secondIndex] = temp;
    }
}
