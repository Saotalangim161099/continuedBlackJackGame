import java.util.Deque;

public class MaxWindow {
    public static void printMax(int[] arr, int n, int k) {
        // Create a queue to store all the USEFUL indexes for the max length of the window in decreasing order from front to rear
        Dequeue dequeue = new Dequeue();

        // Doing the first window
        int i;
        for (i = 0; i < k; i++) {

            // If the queue is not empty and the new element is larger than the rear of the queue( which is the smallest element)
            // In the queue, index of element is inserted based on the value, but in order of index
            // The index at the front will be the oldest
            while (!dequeue.isEmpty() && arr[i] >= arr[dequeue.peakRear()]) {
                dequeue.removeRear();
            }

            // Add the new element to the rear of the queue
            // USEFUL INDEXES mean that you save only the indexes of elements that is larger than the current element
            dequeue.insertLast(i);
        }

        // Process the rest of elements from arr[k] to arr[n-1]
        for (; i < n; ++i) {

            // Print out the max element in the first sliding window which is the front of the queue so far
            System.out.println(arr[dequeue.peakFront()] + " ");

            // Remove all unrelated indexes from the previous window from the front of the queue
            while (!dequeue.isEmpty() && dequeue.peakRear() <= i - k) {
                dequeue.removeFront();
            }

            // Loop: remove all the indexes of elements which are smaller than the current element
            // Doing from the rear of the queue
            while (!dequeue.isEmpty() && arr[i] >= arr[dequeue.peakRear()]) {
                dequeue.removeRear();
            }

            // Add index of new element into the queue from the rear
            dequeue.insertLast(i);
            System.out.println(arr[dequeue.peakFront()]);
        }
    }
