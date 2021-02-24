import java.util.Queue;

/** Queue implemented using Static Array */
public class QueueArray {
 public int head;
 public int tail;
 public int[] queue;

   // constructor for creating fixed sized Queue
   QueueArray() {
       this.queue = new int[100];
       this.head = 0;
       this.tail = 0;
   }

   // constructor for creating dynamic sized Queue,
   // Note: size cannot expand once created.
    QueueArray(int size) {
        this.queue = new int[size];
        this.head = 0; 
        this.tail = 0;
    }

    /** Insert a new value in the end */
    /** complexity:- O(1) */
    public void enqueue(int value) {
        if(this.tail == this.queue.length)
            throw new RuntimeException("Queue has reached max capacity, cannot enqueue....");
        else if(this.head == 0 && this.tail == 0)
        {
         System.out.println("Queue is empty, creating a new Queue....");
         this.queue[tail] = value;
         this.tail++;
        } else {
            this.queue[tail] = value;
            this.tail++;
        }

        System.out.println("Enqueued, Head is"+this.head+" and Tail is"+this.tail);
    }


    /** Delete the head(top) value from queue */
    /** Complexity:- O(1) */
    public void dequeue() {
        if(this.head == -1)
            throw new RuntimeException("Queue is empty......");
        else if(this.head == this.tail) {
            System.out.println("Queue only contains 1 value, deleting Queue...");
            this.head = -1;
            this.tail = -1;
        } else {
            this.head++;
        }
        System.out.println("Dequeued, Head is"+this.head+" and Tail is"+this.tail);
    }

    /**Print all values in Queue from Head ---> Tail */
    /** Complexity O(n) */
    public void printValues() {
        for(int i = this.head; i < this.tail; i++) {
            System.out.println(this.queue[i]);
        }
    }

    public static void main(String[] args) {
        QueueArray q = new QueueArray(4);
        q.enqueue(1);
        q.enqueue(41);
        q.enqueue(31);
        q.enqueue(12);
        q.printValues();
        q.dequeue();
        q.printValues();
    }    
}
