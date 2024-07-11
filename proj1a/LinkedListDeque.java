public class LinkedListDeque<T> {
    private int size;
    private Node sentinal;
    public class Node{
        public T item;
        public Node next;
        public Node prev;
        public Node(T myitem,Node myprev,Node mynext){
            item = myitem;
            next = mynext;
            prev = myprev;
        }
        public Node(Node myprev,Node mynext) {
            next = mynext;
            prev = myprev;
        }
        public T GetNodeRecursive(int index){
            if(index==0)return this.item;
            return this.next.GetNodeRecursive(index-1);
        }
    }
    private void addNode (T item,Node prev,Node next) {
        Node newnode = new Node(item, prev, next);
        prev.next = newnode;
        next.prev = newnode;
    }
    private T removeNode(Node p){
        p.prev.next = p.next;
        p.next.prev = p.prev;
        return p.item;
    }
    public LinkedListDeque(){
        size = 0;
        sentinal = new Node(null,null);
        sentinal.next = sentinal;
        sentinal.prev = sentinal;
    }
    public void addFirst(T item){
        size+=1;
        addNode(item,sentinal,sentinal.next);
    }/*size为何不加上this.*/
    public void addLast(T item){
        size+=1;
        addNode(item,sentinal.prev,sentinal);
    }
    public boolean isEmpty(){
        return size==0;
    }
    public int size(){
        return size;
    }
    public void printDeque(){
        Node p = sentinal.next;
        while(p!=sentinal){
            System.out.println(p.item);
            p = p.next;
        }
    }
    public T removeFirst(){
        size-=1;
        return removeNode(sentinal.next);
    }
    public T removeLast(){
        size-=1;
        return removeNode(sentinal.prev);
    }
    public T get(int index){
        Node p = sentinal.next;
        for(int cnt=0;cnt<index;cnt+=1){
            p = p.next;
        }
        return p.item;
    }
    public T getRecursive(int index){
        return sentinal.next.GetNodeRecursive(index);
    }
}