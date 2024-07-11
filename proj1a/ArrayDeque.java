public class ArrayDeque<T> {
    private int first;
    private int last;
    private int size;
    private int capacity;
    private T[] array;
    private double UsageFactor;
    private void UsageUpdated(){
        UsageFactor = size/capacity;
    }
    public ArrayDeque(){
        size = 0;
        capacity = 8;
        array = (T[]) new Object[8];
        first = 0;
        last = 0;
        UsageUpdated();
    }
    public void addFirst(T item){
        size+=1;
        if(size>capacity-1){
            T[] newarray = (T[]) new Object[capacity*2];
            if(first<=last)System.arraycopy(array,first,newarray,0,size);
            else{
                System.arraycopy(array,0,newarray,0,last+1);
                System.arraycopy(array,first,newarray,capacity*2-(size-last-1),size-last-1);
            }
            array = newarray;
            capacity*=2;
        }
        first = (first-1)%capacity;
        array[first] = item;
        UsageUpdated();
    }
    public void addLast(T item){
        size+=1;
        if(size>capacity-1){
            T[] newarray = (T[]) new Object[capacity*2];
            if(first<=last)System.arraycopy(array,first,newarray,0,size);
            else{
                System.arraycopy(array,0,newarray,0,last+1);
                System.arraycopy(array,first,newarray,capacity*2-(size-last-1),size-last-1);
            }
            array = newarray;
            capacity*=2;
        }
        array[last] = item;
        last = (last+1)%capacity;
        UsageUpdated();
    }
    public boolean isEmpty(){
        return (first==last);
    }
    public int size(){
        return size;
    }
    public void printDeque(){
        for(int i=0;i<size;i=i+1){
            System.out.println(array[(first+i)%capacity]);
        }
    }
    public T removeFirst(){
        size-=1;
        UsageUpdated();
        if(UsageFactor<0.25&&capacity>=16){
            T[] newarray = (T[]) new Object[capacity/2];
            if(first<=last)System.arraycopy(array,first,newarray,0,size);
            else{
                System.arraycopy(array,0,newarray,0,last+1);
                System.arraycopy(array,first,newarray,capacity/2-(size-last-1),size-last-1);
            }
            array = newarray;
            capacity/=2;
        }
        T item = array[first];
        first = (first+1)%capacity;
        return item;
    }
    public T removeLast(){
        size-=1;
        UsageUpdated();
        if(UsageFactor<0.25){
            T[] newarray = (T[]) new Object[capacity/2];
            if(first<=last)System.arraycopy(array,first,newarray,0,size);
            else{
                System.arraycopy(array,0,newarray,0,last+1);
                System.arraycopy(array,first,newarray,capacity/2-(size-last-1),size-last-1);
            }
            array = newarray;
            capacity/=2;
        }
        T item = array[(last-1)%capacity];
        last = (last-1)%capacity;
        return item;
    }
    public T get(int index){
        return array[first+index];
    }
}