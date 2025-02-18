import common.BaseClass;

import java.util.Stack;

public class Test4_stackQueue extends BaseClass {

    boolean solution(String s) {
        //Stack (FIFO)
        //먼저 "()" 완성되면 OUT, 최종적으로 Stack 이 empty 상태가 되야 함.
        Stack<Character> st = new Stack<Character>();

        for (int i = 0; i < s.length(); i++) {
            char sChar = s.charAt(i);
            if (sChar == '(') {
                // "()" 완성형이 되기 위해선 무조건 stack에 먼저 "(" 문자가 들어와야 한다.
                st.push(sChar);
            } else if (sChar == ')') {
                if (st.isEmpty()) {
                    //비어 있으면 false
                    return false;
                }
                //"()" 조합이 완성되면 pop
                st.pop();
            }
        }
        return st.isEmpty();
    }
}
