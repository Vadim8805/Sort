import java.util.Arrays;
import java.util.Date;

public class Sort {
    public static void main(String[] args) {
        int[] numbers = new int[100000];
        fillArray(numbers);
        int[] bubbleSortArray = new int[numbers.length];
        System.arraycopy(numbers, 0, bubbleSortArray, 0, numbers.length);
        int[] insertionSortArray = new int[numbers.length];
        System.arraycopy(numbers, 0, insertionSortArray, 0, numbers.length);
        int[] selectionSortArray = new int[numbers.length];
        System.arraycopy(numbers, 0, selectionSortArray, 0, numbers.length);
        int[] mergeSortArray = new int[numbers.length];
        System.arraycopy(numbers, 0, mergeSortArray, 0, numbers.length);
        int[] heapSortArray = new int[numbers.length];
        System.arraycopy(numbers, 0, heapSortArray, 0, numbers.length);
        int[] quickSortArray = new int[numbers.length];
        System.arraycopy(numbers, 0, quickSortArray, 0, numbers.length);

//        System.out.println("Default array:");
//        outArray(numbers);

//        bubbleSort(bubbleSortArray);
//        outArray(bubbleSortArray);
//
//        insertionSort(insertionSortArray);
//        outArray(insertionSortArray);
//
//        selectionSort(selectionSortArray);
//        outArray(selectionSortArray);

        mergeSort(mergeSortArray);
//        outArray(mergeSortArray);

        heapSort(heapSortArray);
//        outArray(heapSortArray);

        quickSort(quickSortArray);
//        outArray(heapSortArray);

        defaultSort(numbers);
//        outArray(numbers);
    }

    public static void defaultSort(int[] array) {
        Date timeBefore = new Date();
        long before = timeBefore.getTime();
        Arrays.sort(array);
        Date timeAfter = new Date();
        long after = timeAfter.getTime();
        long timeToSort = after - before;
        System.out.println("Arrays.sort() time: " + timeToSort + " milliseconds");
    }

    public static void bubbleSort(int[] array) {
        int[] a = new int[array.length];
        Date timeBefore = new Date();
        long before = timeBefore.getTime();
        boolean sorted = false;
        while (!sorted) {
            sorted = true;
            for (int i = 0; i < a.length - 1; i++) {
                if (array[i] > array[i + 1]) {
                    int temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                    sorted = false;
                }
            }
        }
        Date timeAfter = new Date();
        long after = timeAfter.getTime();
        long timeToSort = after - before;
        System.out.println("bubbleSort time: " + timeToSort + " milliseconds");
    }

    public static void insertionSort(int[] array) {
        Date timeBefore = new Date();
        long before = timeBefore.getTime();
        for (int i = 1; i < array.length; i++) {
            for (int j = i; j > 0; j--) {
                if (array[j] < array[j - 1]) {
                    int temp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = temp;
                }
            }
        }
        Date timeAfter = new Date();
        long after = timeAfter.getTime();
        long timeToSort = after - before;
        System.out.println("insertionSort time: " + timeToSort + " milliseconds");
    }

    public static void selectionSort(int[] array) {
        Date timeBefore = new Date();
        long before = timeBefore.getTime();
        for (int i = 0; i < array.length; i++) {
            int min = array[i];
            int indexOfMin = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < min) {
                    min = array[j];
                    indexOfMin = j;
                }
            }
            if (indexOfMin != i) {
                array[indexOfMin] = array[i];
                array[i] = min;
            }
        }
        Date timeAfter = new Date();
        long after = timeAfter.getTime();
        long timeToSort = after - before;
        System.out.println("selectionSort time: " + timeToSort + " milliseconds");
    }

    public static void mergeSort(int[] array) {
        Date timeBefore = new Date();
        long before = timeBefore.getTime();
        merge(array, 0, array.length - 1);
        Date timeAfter = new Date();
        long after = timeAfter.getTime();
        long timeToSort = after - before;
        System.out.println("mergeSort time: " + timeToSort + " milliseconds");
    }

    private static void merge(int[] array, int left, int right) {
        if (right <= left) return;
        int mid = (left + right) / 2;
        merge(array, left, mid);
        merge(array, mid + 1, right);
        merge(array, left, mid, right);
    }

    private static void merge(int[] array, int left, int mid, int right) {
        // вычисляем длину
        int lengthLeft = mid - left + 1;
        int lengthRight = right - mid;

        // создаем временные подмассивы
        int[] leftArray = new int[lengthLeft];
        int[] rightArray = new int[lengthRight];

        // копируем отсортированные массивы во временные
        for (int i = 0; i < lengthLeft; i++)
            leftArray[i] = array[left + i];
        for (int i = 0; i < lengthRight; i++)
            rightArray[i] = array[mid + i + 1];

        // итераторы содержат текущий индекс временного подмассива
        int leftIndex = 0;
        int rightIndex = 0;

        // копируем из leftArray и rightArray обратно в массив
        for (int i = left; i < right + 1; i++) {
            // если остаются нескопированные элементы в R и L, копируем минимальный
            if (leftIndex < lengthLeft && rightIndex < lengthRight) {
                if (leftArray[leftIndex] < rightArray[rightIndex]) {
                    array[i] = leftArray[leftIndex];
                    leftIndex++;
                } else {
                    array[i] = rightArray[rightIndex];
                    rightIndex++;
                }
            }
            // если все элементы были скопированы из rightArray, скопировать остальные из leftArray
            else if (leftIndex < lengthLeft) {
                array[i] = leftArray[leftIndex];
                leftIndex++;
            }
            // если все элементы были скопированы из leftArray, скопировать остальные из rightArray
            else if (rightIndex < lengthRight) {
                array[i] = rightArray[rightIndex];
                rightIndex++;
            }
        }
    }

    public static void heapSort(int[] array) {
        Date timeBefore = new Date();
        long before = timeBefore.getTime();

        if (array.length == 0) return;

        // Строим кучу
        int length = array.length;
        // проходим от первого без ответвлений к корню
        for (int i = length / 2 - 1; i >= 0; i--)
            heap(array, length, i);

        for (int i = length - 1; i >= 0; i--) {
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;

            heap(array, i, 0);
        }

        Date timeAfter = new Date();
        long after = timeAfter.getTime();
        long timeToSort = after - before;
        System.out.println("heapSort time: " + timeToSort + " milliseconds");
    }

    static void heap(int[] array, int length, int i) {
        int leftChild = 2 * i + 1;
        int rightChild = 2 * i + 2;
        int largest = i;

        // если левый дочерний больше родительского
        if (leftChild < length && array[leftChild] > array[largest]) {
            largest = leftChild;
        }

        // если правый дочерний больше родительского
        if (rightChild < length && array[rightChild] > array[largest]) {
            largest = rightChild;
        }

        // если должна произойти замена
        if (largest != i) {
            int temp = array[i];
            array[i] = array[largest];
            array[largest] = temp;
            heap(array, length, largest);
        }
    }

    public static void quickSort(int[] array) {
        Date timeBefore = new Date();
        long before = timeBefore.getTime();
        quick(array, 0, array.length - 1);
        Date timeAfter = new Date();
        long after = timeAfter.getTime();
        long timeToSort = after - before;
        System.out.println("quickSort time: " + timeToSort + " milliseconds");
    }

    public static void quick(int[] array, int begin, int end) {
        if (end <= begin) return;
        int pivot = partition(array, begin, end);
        quick(array, begin, pivot - 1);
        quick(array, pivot + 1, end);
    }

    static int partition(int[] array, int begin, int end) {
        int pivot = end;

        int counter = begin;
        for (int i = begin; i < end; i++) {
            if (array[i] < array[pivot]) {
                int temp = array[counter];
                array[counter] = array[i];
                array[i] = temp;
                counter++;
            }
        }
        int temp = array[pivot];
        array[pivot] = array[counter];
        array[counter] = temp;

        return counter;
    }

    public static void fillArray(int[] n) {
        for (int i = 0; i < n.length; i++) {
            n[i] = (int) (Math.random() * 100);
        }
    }

    public static void outArray(int[] n) {
        for (int value : n) {
            System.out.print(value + " ");
        }
        System.out.println();
    }
}