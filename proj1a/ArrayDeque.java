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

        if(size+1>capacity-1){
            T[] newarray = (T[]) new Object[capacity*2];
            if(first<=last){
                System.arraycopy(array,first,newarray,0,size);//注意循环变量first,last的维护
                first = 0;
                last = size;
            }
            else{
                System.arraycopy(array,0,newarray,0,last);
                System.arraycopy(array,first,newarray,capacity*2-(size-last),size-last);
                first = capacity*2-(size-last);
            }
            array = newarray;
            capacity*=2;
        }
        first = Math.floorMod(first-1,capacity);
        array[first] = item;
        size+=1;
        UsageUpdated();
    }
    public void addLast(T item){

        if(size+1>capacity-1){
            T[] newarray = (T[]) new Object[capacity*2];
            if(first<=last){
                System.arraycopy(array,first,newarray,0,size);
                first = 0;
                last = size;
            }
            else{
                System.arraycopy(array,0,newarray,0,last);
                System.arraycopy(array,first,newarray,capacity*2-(size-last),size-last);
                first = capacity*2-(size-last);
            }
            array = newarray;
            capacity*=2;
        }
        array[last] = item;
        last = Math.floorMod(last+1,capacity);
        size+=1;
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
            System.out.println(array[Math.floorMod(i,capacity)]);
        }
    }
    public T removeFirst(){
        if(size==0)return null;

        if(1.0*(size-1)/capacity<0.25&&capacity>=16){
            T[] newarray = (T[]) new Object[capacity/2];
            if(first<=last){
                System.arraycopy(array,first,newarray,0,size);
                first = 0;
                last = size;
            }
            else{
                System.arraycopy(array,0,newarray,0,last);
                System.arraycopy(array,first,newarray,capacity/2-(size-last),size-last);
                first = capacity/2-(size-last);
            }
            array = newarray;
            capacity/=2;
        }
        T item = array[first];
        first = Math.floorMod(first+1,capacity);
        size-=1;
        UsageUpdated();
        return item;
    }
    public T removeLast(){
        if(size==0){
            return null;
        }

        if(1.0*(size-1)/capacity<0.25&&capacity>=16){
            T[] newarray = (T[]) new Object[capacity/2];
            if(first<=last){
                System.arraycopy(array,first,newarray,0,size);
                first = 0;
                last = size;
            }
            else{
                System.arraycopy(array,0,newarray,0,last);
                System.arraycopy(array,first,newarray,capacity/2-(size-last),size-last);
            }
            array = newarray;
            capacity/=2;
        }
        last = Math.floorMod(last-1,capacity);
        T item = array[last];
        size-=1;
        UsageUpdated();
        return item;
    }
    public T get(int index){
        return array[Math.floorMod(first+index,capacity)];
    }
}