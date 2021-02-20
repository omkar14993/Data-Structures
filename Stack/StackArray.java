/** Stack implemented using Arrays */
/**
 * Pros: 
 *       - easy to implement
 *       - complexity is O(1) for all operations
 * Cons:
 *       - Fixed size
 */
public class StackArray {
    int top;
    int stackSize = 100;
    int stack[] = new int[stackSize];

    StackArray() {
        this.top = -1;
    }

    public void push(int value) {
        if(top < 0) {
            System.out.println("Stack is empty, creating a new Stack......");
            stack[++top] = value;
            return;
        } else if(top > stackSize - 1) {
            System.out.println("Stack overflow...");
        } else 
            stack[++top] = value;
    }

    public void pop() {
        if(top < 0) {
            System.out.println("Stack is empty");
        }
        top--;
    }

    public int peek() {
        return stack[top];
    }

    public boolean isEmpty() {
        if(top < 0) 
            return true;
        else 
            return false;
    }

    public static void main(String[] args) {
        StackArray s = new StackArray();
        s.push(2);
        s.push(1);
        s.push(12);
        s.push(11);
        s.push(51);
        System.out.println(s.peek());
        s.pop();
        System.out.println(s.peek());
    }
}