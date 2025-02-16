public class Main {
    public static void main(String[] args) {

        Test1_3nTiling sol1 = new Test1_3nTiling();
        // 다양한 입력값을 테스트합니다.
        int[] testInputs = {2, 4, 6, 8, 10, 20, 500, 5000};
        for (int n : testInputs) {
            System.out.println("입력 n = " + n);
            int result = sol1.solution(n);
            System.out.println("f(" + n + ") = " + result);
            System.out.println("===================================");
        }

        Test2_openChat sol2 = new Test2_openChat();
        String[] testInput2 = {"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"};

        System.out.println("==========openChat Log============");
        for (String n : sol2.solution(testInput2)) {
            System.out.println(n);
        }
        System.out.println("==================================");
    }
}