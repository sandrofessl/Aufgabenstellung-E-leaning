//Sandro Fessl
package A02_Queue;

public class Queue<T> {
    private Node<T> first;

    private Node<T> last;

    private int cnt;

    /**
     * Das vorderste (=erste) Element aus der Queue entfernen und zurückliefern.
     * Existiert kein Element, wird eine Exception ausgelöst.
     *
     * @throws QueueEmptyException
     */
    public T dequeue() throws QueueEmptyException {
        if(cnt==0)
            throw new QueueEmptyException();

        T data = first.getData();
        first = first.getNext();
        cnt--;
        return data;
    }

    /**
     * Übergebenen Integer am Ende der Queue anhängen.
     *
     * @param i Zahl
     */
    public void enqueue(T i) {
        Node<T> newNode = new Node<T>(i);
        if (cnt == 0) {
            first = newNode;
            last = newNode;
        } else {
            last.setNext(newNode);
            last = newNode;
        }

        cnt++;
    }

    /**
     * Liefert die Anzahl der Elemente im Stack
     *
     * @return
     */
    public int getCount() {
        return cnt;
    }
}
