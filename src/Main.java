import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {

//        Test1_3nTiling sol1 = new Test1_3nTiling();
//        int[] testInputs = {2, 4, 6, 8, 10, 20, 500, 5000};
//        for (int n : testInputs) {
//            System.out.println("입력 n = " + n);
//            int result = sol1.solution(n);
//            System.out.println("f(" + n + ") = " + result);
//            System.out.println("-----------------------");
//        }
//
//        Test2_openChat sol2 = new Test2_openChat();
//        String[] testInput2 = {"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"};
//        for (String n : sol2.solution(testInput2)) {
//            System.out.println(n);
//        }
//
//        Test3_engEnding sol3 = new Test3_engEnding();
//        int nSample2 = 5;
//        String[] wordsSample2 = {"hello", "observe", "effect", "take", "either", "recognize", "encourage", "ensure", "establish", "hang", "gather", "refer", "reference", "estimate", "executive"};
//        System.out.println("result: ");
//        for (int item : sol3.solution(nSample2,wordsSample2)) {
//            System.out.println(item);
//        }
//
//        Test4_stackQueue sol4 = new Test4_stackQueue();
//        String s = "()()";
//        System.out.println(sol4.solution(s));
//        String s2 = "(())()";
//        System.out.println(sol4.solution(s2));
//        String s3 = ")()(";
//        System.out.println(sol4.solution(s3));
//        String s4 = ")))(";
//        System.out.println(sol4.solution(s4));

        int[][] a = new int[][]{{0, 3}, {1, 9}, {3, 5}};
        Test5_diskController sol5 = new Test5_diskController();
        System.out.println(sol5.solution(a));

    }
}