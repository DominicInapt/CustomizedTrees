import java.util.ArrayList;

public class Heap<E extends Comparable<E>> {
    private Comparable<E>[] array;
    private int DEFAULT_CAPACITY=10;
    private int capacity;
    private int len;

    //Smallest at top.


    public Heap(){
        array= new Comparable[DEFAULT_CAPACITY];
        capacity= DEFAULT_CAPACITY;

    }

    public void add(E item){
        if(item ==null){
            throw new NullPointerException();
        }
        array[len]=item;
        siftUp(len);
        len++;
        if(len>=array.length){
            expand();
        }
    }

    public boolean remove(E item){
        if(item==null){
            return false;
        }

        int index=0;
        while(!array[index].equals(item)){
            index++;
            if(index>len){
                return false;
            }
        }
        array[index]=array[len-1];
        array[len-1]=null;
        len--;
        siftDown(index);
        return true;
    }

    private void siftUp(int index){

        E val= (E) array[index];

        while(index!=0 && array[(index-1)/2].compareTo(val)>0){
            array[index]=array[(index-1)/2];//Swap it down.
            index=(index-1)/2;
        }
        array[index]=val;
    }

    private void siftDown(int index){
        E val = (E) array[index];
        //comparations.

        // False, false, break
        // True,false fine---ish
        //False,True impossible
        //True,true fine.

        //3 cases.
        //A left child
        //No children
        //Compare the children.

        //There is at least one safe comparison
        while( 2*index+1<len){
            int smolIndex;
            if(2*index+2<len &&
                    array[2*index+1].compareTo((E) array[2*index+2])>0){
                smolIndex=2*index+2;
            }else{
                smolIndex=2*index+1;
            }

            if(array[smolIndex].compareTo(val)<0){
                array[index]=array[smolIndex];
                index=smolIndex;
            }else{
                break;
            }

        }

        array[index]=val;
    }

    private void expand( int increase){
        Comparable<E>[] newArray= new Comparable[array.length+increase];

        int i=0;
        while(i<len){
            newArray[i]=array[i];
            i++;
        }
        array=newArray;
    }
    private void expand(){
        expand(DEFAULT_CAPACITY);
    }


    public String toString(){
        StringBuilder str= new StringBuilder();
        str.append('[');
        for(int i=0;i<len;i++){
            str.append(" "+array[i].toString());
            if(i!=len-1){
                str.append(',');
            }
        }
        str.append(" ]");
        return str.toString();
    }

    public int size(){
        return len;
    }

    public int height(){
        if(len==0){
            return 0;
        }

        int l=len;
        int log=0;
        while(l!=0){
            l/=2;
            log++;
        }
        return log;

    }
    public E get(int index){
        if(index<0 || index> len){
            return null;
        }
        return (E) array[index];
    }
    public E getChild(int index, boolean left){
        if(left){
            return get(2*index+1);
        }else{
            return get(2*index+2);
        }
    }

}
