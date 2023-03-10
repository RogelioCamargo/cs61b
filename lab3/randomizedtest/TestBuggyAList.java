package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove() {
        BuggyAList<Integer> buggyList = new BuggyAList<>();
        AListNoResizing<Integer> listNoResizing = new AListNoResizing<>();

        buggyList.addLast(4);
        listNoResizing.addLast(4);
        buggyList.addLast(5);
        listNoResizing.addLast(5);
        buggyList.addLast(6);
        listNoResizing.addLast(6);

        assertEquals(listNoResizing.size(), buggyList.size());
        assertEquals(listNoResizing.removeLast(), buggyList.removeLast());
        assertEquals(listNoResizing.removeLast(), buggyList.removeLast());
        assertEquals(listNoResizing.removeLast(), buggyList.removeLast());
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> B = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                B.addLast(randVal);

            } else if (operationNumber == 1) {
                assertEquals(L.size(),  B.size());

            } else if (operationNumber == 2) {
                if (L.size() == 0 || B.size() == 0) {
                    continue;
                }

                assertEquals(L.getLast(), B.getLast());
            } else if (operationNumber == 3) {
                if (L.size() == 0 || B.size() == 0) {
                    continue;
                }

                assertEquals(L.removeLast(), B.removeLast());
            }
        }
    }
}
