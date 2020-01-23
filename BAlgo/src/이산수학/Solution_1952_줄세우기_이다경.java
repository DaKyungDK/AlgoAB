package 이산수학;
//LinkedList, Queue 구현
import java.util.Scanner;

class ListNode {
    int data;
    ListNode prev;
    ListNode next;
     
    public ListNode()
    {
        data = 0;
        prev = this;
        next = this;
    }
 
    public static ListNode appendListNode(ListNode head, int data)
    {
        ListNode node = new ListNode();
        node.data = data;
        if (head == null)
        {
            head = node;
        }
        else
        {
            ListNode last = head.prev;
            last.next = node;
            head.prev = node;
            node.prev = last;
            node.next = head;
        }
        return head;
    }
     
    public static ListNode removeListNode(ListNode head, ListNode node)
    {
        if (head == head.next)
        {
            return null;
        }
        else
        {
            ListNode prevNode = node.prev;
            ListNode nextNode = node.next;
            prevNode.next = nextNode;
            nextNode.prev = prevNode;
            return (head == node) ? nextNode : head;
        }
         
    }
    
}
class Queue {
	 
    static final int MAX_N = 50009;
 
    static int front;
    static int rear;
    static int queue[] = new int[MAX_N];
 
    static void queueInit()
    {
        front = 0;
        rear = 0;
    }
 
    static boolean queueIsEmpty()
    {
        return (front == rear);
    }
 
    static boolean queueIsFull()
    {
        if ((rear + 1) % MAX_N == front)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
 
    static boolean queueEnqueue(int value)
    {
        if (queueIsFull())
        {
            System.out.print("queue is full!");
            return false;
        }
        queue[rear] = value;
        rear++;
        if (rear == MAX_N)
        {
            rear = 0;
        }
 
        return true;
    }
 
    static Integer queueDequeue()
    {
        if (queueIsEmpty()) 
        {
            System.out.print("queue is empty!");
            return null;
        }
         
        Integer value = new Integer(queue[front]);
 
        front++;
        if (front == MAX_N)
        {
            front = 0;
        }
        return value;
    }
}
public class Solution_1952_줄세우기_이다경 {
	static int N,M,a,b;
	static ListNode[] adjlist = new ListNode[50009];
	static int[] indegree = new int[50009];
	static int[] sortResult= new int[50009];
	static Queue qu = new Queue();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			M = sc.nextInt();

			for (int i = 1; i <= N; i++) {
				indegree[i] = 0;
				sortResult[i] = 0;
			}//초기화
			
			for (int i = 1; i <= M; i++) {
				a = sc.nextInt();
				b = sc.nextInt();
				adjlist[a] = adjlist[a].appendListNode(adjlist[a], b);
				indegree[b]++;
			}
			
			for (int i = 1; i <= N; i++) {
				if(indegree[i] == 0 ) {
					qu.queueEnqueue(i);
				}
			}
			
			int n = 1;
			while(!qu.queueIsEmpty()) {
				int cur = qu.queueDequeue();
				sortResult[n++] = cur;
				int i=0;
				
				while(adjlist[cur]!=null) {
					if(adjlist[cur] == adjlist[cur].next) {
						i = adjlist[cur].data;
						adjlist[cur] = null;
						indegree[i]--;
						if(indegree[i]==0) qu.queueEnqueue(i);
						break;
					}
					i = adjlist[cur].next.data;
					adjlist[cur].removeListNode(adjlist[cur], adjlist[cur].next);
					indegree[i]--;
					if(indegree[i]==0) qu.queueEnqueue(i);
				}
			}//end while..

			
			
			System.out.print("#"+tc);
			for (int i = 1; i <=N ; i++) {
				System.out.print(" "+sortResult[i]);
			}
			System.out.println();
		}//end tc..
	}

}
