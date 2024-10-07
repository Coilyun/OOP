import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HeapSortTest {

    @Test
    public void testHeapSort() {
        int[] input = {5, 4, 3, 2, 1};
        int[] expected = {1, 2, 3, 4, 5};
        HeapSort.heapSort(input);
        assertArrayEquals(expected, input);
    }

    @Test
    public void testHeapSortEmptyArray() {
        int[] input = {};
        int[] expected = {};
        HeapSort.heapSort(input);
        assertArrayEquals(expected, input);
    }

    @Test
    public void testHeapSortSingleElement() {
        int[] input = {42};
        int[] expected = {42};
        HeapSort.heapSort(input);
        assertArrayEquals(expected, input);
    }

    @Test
    public void testHeapSortAlreadySorted() {
        int[] input = {1, 2, 3, 4, 5};
        int[] expected = {1, 2, 3, 4, 5};
        HeapSort.heapSort(input);
        assertArrayEquals(expected, input);
    }
}
