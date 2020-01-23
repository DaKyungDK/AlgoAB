package 이산수학;

import java.util.Scanner;


//class ListNode {
//    int data;
//    ListNode prev;
//    ListNode next;
//     
//    public ListNode()
//    {
//        data = 0;
//        prev = this;
//        next = this;
//    }
// 
//    public static ListNode appendListNode(ListNode head, int data)
//    {
//        ListNode node = new ListNode();
//        node.data = data;
//        if (head == null)
//        {
//            head = node;
//        }
//        else
//        {
//            ListNode last = head.prev;
//            last.next = node;
//            head.prev = node;
//            node.prev = last;
//            node.next = head;
//        }
//        return head;
//    }
//     
//    public static ListNode removeListNode(ListNode head, ListNode node)
//    {
//        if (head == head.next)
//        {
//            return null;
//        }
//        else
//        {
//            ListNode prevNode = node.prev;
//            ListNode nextNode = node.next;
//            prevNode.next = nextNode;
//            nextNode.prev = prevNode;
//            return (head == node) ? nextNode : head;
//        }
//         
//    }
//    
//}
     
 
class Solution_LinkedList
{
    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int test_case = 1; test_case <= T; test_case++)
        {
            ListNode head = null;
            int N = sc.nextInt();
            for (int i = 0; i < N; i++)
            {
                int data = sc.nextInt();
                head = ListNode.appendListNode(head, data);
            }
            ListNode node = head;
            while(head != head.next)
            {
                ListNode nextNode = node.next;
                head = ListNode.removeListNode(head, node);
                node = nextNode.next.next;
            }
            System.out.printf("#%d %d\n", test_case, head.data);
        }
        sc.close();
    }
}