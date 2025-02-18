import common.BaseClass;

import java.util.HashSet;
import java.util.Set;


public class Test3_engEnding extends BaseClass {
    public int[] solution(int n, String[] words) {
        /**
         * input 값들의 sort 가 되어있는 상태(1번부터 번호 순서대로)
         * + 마지막 사람이 말하고 다시 1번부터 진행.(ex. 3명이서 진행할 경우 words 배열은 3개 단위로 순서 loop)
         * words array 는  n size 의 block 이 존재한다고 볼 수 있음.
         * 중복 단어 x, 한 글자 단어 x
         *
         * n 은 2 이상 10 미만의 자연수
         * 탈락자의 [번호,차례] 형태로 return
         * */
        int[] answer = new int[2];

        //중복체크를 위한 Set 사용
        Set<String> wordSet = new HashSet<>();

        for (int i = 0; i < words.length; i++) {
            //현재 단어
            String nowWord = words[i];

            //첫번째 단어는 이전 단어가 없으므로 패스
            if (i > 0) {
                String preWord = words[i - 1];

                //rule1. 끝말잇기 정합성 체크
                if (nowWord.charAt(0) != preWord.charAt(preWord.length() - 1)) {
                    //탈락자 번호 - ex. n = 3,  index 0 -> 1 , 1 -> 2, 2 -> 3, 3 -> 0
                    answer[0] = (i % n) + 1;
                    //탈락 차례
                    answer[1] = (i / n) + 1;
                    return answer;
                }
            }

            if (!wordSet.add(nowWord)) {
                answer[0] = (i % n) + 1;
                answer[1] = (i / n) + 1;
                return answer;
            }
        }

        answer[0] = 0;
        answer[1] = 0;

        return answer;

        //version1 - 틀린 답
//        int[] answer = new int[2];
//
//        // 탈락한 턴(차례)
//        int eliminatedTurn = 0;
//        // 탈락자(번호)
//        int eliminatedPersonNumber = 0;
//
//        List<String> wordsList = new ArrayList<>(Arrays.asList(words));
//
//        for (int i = wordsList.size()-1; i > 0; i--) {
//            // 규칙1. 실제 끝말잇기와 동일한 방식으로 틀린 사람 찾기 O(n)
//            // 현재 단어가 규칙1을 준수하는지 체크
//            String nowWord = wordsList.get(i);
//            String bfWord = wordsList.get(i-1);
//            if (!nowWord.startsWith(bfWord.substring(bfWord.length() - 1))) {
//                eliminatedTurn = Math.ceilDiv((i+1),n);
//                eliminatedPersonNumber = (i%n)+1;
//                break;
//            }
//
//            // 사이즈 조절
//            wordsList.remove(i);
//
//            // 규칙2. 중복된 단어인 경우 탈락 대상
//            if (wordsList.contains(nowWord)) {
//                eliminatedTurn = Math.ceilDiv((i+1),n);
//                eliminatedPersonNumber = (i%n)+1;
//                break;
//            }
//        }
//        answer[0] = eliminatedPersonNumber;
//        answer[1] = eliminatedTurn;
//
//        return answer;
    }
}
