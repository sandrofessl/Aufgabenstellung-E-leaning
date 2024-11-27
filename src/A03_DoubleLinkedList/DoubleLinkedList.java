//Sandro Fessl
package A03_DoubleLinkedList;

public class DoubleLinkedList<T> {

    private Node<T> first;
    private Node<T> last;
    private Node<T> current;

    private int count;

    /**
     * Einfügen einer neuen <T>
     *
     * @param a <T>
     */
    public void add(T a) {
        Node<T> newNode = new Node<>(a);
        if (first == null & last == null) {
            first = last = newNode;
//            current = first; Entfernen
        } else {
            newNode.setPrevious(last);
            last.setNext(newNode);
            last = newNode;
        }
        count++;
    }

    /**
     * Internen Zeiger für next() zurücksetzen
     */
    public void reset() {
        current = getFirst();
    }

    /**
     * analog zur Funktion reset()
     */
    public void resetToLast() {
        current = getLast();
    }

    /**
     * Liefert erste Node der Liste retour oder null, wenn Liste leer
     *
     * @return Node|null
     */
    public Node<T> getFirst() {
        return first;
    }

    /**
     * Liefert letzte Node der Liste retour oder null, wenn Liste leer
     *
     * @return Node|null
     */
    public Node<T> getLast() {
        return last;
    }

    /**
     * Gibt aktuelle <T> zurück und setzt internen Zeiger weiter.
     * Falls current nicht gesetzt, wird null retourniert.
     *
     * @return <T>|null
     */
    public T next() {
        if (current == null)
            return null;
        T currentTemp = current.getData();
        current = current.getNext();
        return currentTemp;
    }

    /**
     * analog zur Funktion next()
     *
     * @return <T>|null
     */
    public T previous() {
        if (current == null)
            return null;
        T currentTemp = current.getData();
        current = current.getPrevious();
        return currentTemp;
    }

    /**
     * Current-Pointer auf nächste <T> setzen (aber nicht auslesen).
     * Ignoriert still, dass current nicht gesetzt ist.
     */
    public void moveNext() {
        if (current == null) {

        } else {
            current = current.getNext();
        }
    }

    /**
     * Analog zur Funktion moveNext()
     */
    public void movePrevious() {
        if (current == null) {

        } else {
            current = current.getPrevious();
        }
    }

    /**
     * Retourniert aktuelle (current) <T>, ohne Zeiger zu ändern
     *
     * @return <T>
     * @throws CurrentNotSetException
     */
    public T getCurrent() throws CurrentNotSetException {
        if (current == null)
            throw new CurrentNotSetException();
        return current.getData();
    }

    /**
     * Gibt <T> an bestimmter Position zurück
     *
     * @param pos Position, Nummerierung startet mit 1
     * @return <T>|null
     */
    public T get(int pos) {
        Node<T> currentNode = first;
        for (int i = 1; i < pos; i++) {
            currentNode = currentNode.getNext();
        }
        return currentNode.getData();
    }

    /**
     * Entfernen des Elements an der angegebenen Position.
     * Falls das entfernte Element das aktuelle Element ist, wird current auf null gesetzt.
     *
     * @param pos
     */
    public void remove(int pos) throws CurrentNotSetException {
        Node<T> nodeToDelete = first;
        if (count == 0) {
            return;
        }
        for (int i = 1; i < pos; i++) {
            nodeToDelete = nodeToDelete.getNext();
        }

        Node<T> previous = nodeToDelete.getPrevious();
        Node<T> next = nodeToDelete.getNext();

        if (nodeToDelete.equals(first)) {
            first = next;
            next.setPrevious(null);
        } else if (nodeToDelete.equals(last)) {
            last = previous;
            previous.setNext(null);
        } else {
            previous.setNext(next);
            next.setPrevious(previous);
        }

        if (nodeToDelete.equals(current)) {
            current = null;
        }
        count--;
    }

    /**
     * Entfernt das aktuelle Element.
     * Als neues aktuelles Element wird der Nachfolger gesetzt oder
     * (falls kein Nachfolger) das vorhergehende Element
     *
     * @throws CurrentNotSetException
     */
    public void removeCurrent() throws CurrentNotSetException {
        if (current == null)
            throw new CurrentNotSetException();

        Node<T> previous = current.getPrevious();
        Node<T> next = current.getNext();

        if (current.getNext() == null & current.getPrevious() == null) {
            first = current = last = null;
        } else if (current.equals(first)) {
            first = current = next;
            next.setPrevious(null);
        } else if (current.equals(last)) {
            last = current = previous;
            previous.setNext(null);
        } else {
            previous.setNext(next);
            next.setPrevious(previous);
            current = next;
        }
        count--;
    }

    /**
     * Die Methode fügt die übergebene <T> nach der aktuellen (current) ein
     * und setzt dann die neu eingefügte <T> als aktuelle (current) <T>.
     *
     * @throws CurrentNotSetException
     */
    public void insertAfterCurrentAndMove(T a) throws CurrentNotSetException {
        if (current == null)
            throw new CurrentNotSetException();

        Node<T> newNode = new Node<>(a);
        Node<T> next = current.getNext();

        if (current.equals(last)) {
            newNode.setPrevious(current);
            newNode.setNext(null);
            last = newNode;
            current.setNext(newNode);
            moveNext();
        } else {
            newNode.setPrevious(current);
            newNode.setNext(next);
            current.setNext(newNode);
            next.setPrevious(newNode);
            moveNext();
        }
        count++;
    }
}
