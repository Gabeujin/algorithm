import common.BaseClass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test2_openChat extends BaseClass {
    //version 2
    public String[] solution(String[] record) {
        //uid - nickname 저장 map
        Map<String, String> uidNickNameMap = new HashMap<>();
        //출력 이벤트 로그 (enter, leave) 저장 [uid,event]
        List<String[]> uidEventList = new ArrayList<>();

        //레코드 순회하여 최종닉네임 및 이벤트 저장
        for (String piece : record) {
            //"Enter uid1234 Muzi" -> ["Enter", "uid1234", "Muzi"]
            // pieceInfo[0] = event
            // pieceInfo[1] = uid
            // pieceInfo[2] = nickName (+Optional)
            String[] pieceInfo = piece.split(" ");

            if (pieceInfo[0].equals("Enter")) {
                //입장 시 UID에 따른 닉네임 및 이벤트 저장
                uidNickNameMap.put(pieceInfo[1], pieceInfo[2]);
                uidEventList.add(new String[]{pieceInfo[1],"님이 들어왔습니다."});
            } else if (pieceInfo[0].equals("Leave")) {
                //퇴장 시 닉네임 로그가 없으므로 UID에 따른 이벤트만 저장
                uidEventList.add(new String[]{pieceInfo[1],"님이 나갔습니다."});
            } else if (pieceInfo[0].equals("Change")) {
                //변경 시 UID에 따른 닉네임 저장
                uidNickNameMap.put(pieceInfo[1], pieceInfo[2]);
            }
        }
        //이벤트만큼 return array 사이즈 조정
        String[] answer = new String[uidEventList.size()];
        //loop
        int idx = 0;

        //event 별 출력문자열 생성 및 answer 에 저장
        for (String[] event : uidEventList) {
            answer[idx++] = uidNickNameMap.get(event[0]) + event[1];
        }

        return answer;
    }




    // version 1
//    public String[] solution(String[] record) {
//        Map<String,String> uids = new HashMap<>();
//        int resultLength = 0;
//        int resultIndex = 0;
//        for(int i = 0 ; i < record.length ; i++)
//        {//uid 를 기준으로 leave,change 등의 상태를 체크해 최종 닉네임을 정한다.
//            //실제 화면에 출력될 데이터의 상태값음 Enter,Leave 임을 참고
//
//            //입장 또는 퇴장 상태 확인
//            if(record[i].startsWith("Enter") || record[i].startsWith("Leave"))
//                resultLength++;
//
//            //입장 또는 변경시 uid 의 최종 닉네임 보관
//            //putAll 로 uid 별 최종닉네임 저장
//            if(record[i].startsWith("Enter") || record[i].startsWith("Change")) {
//                uids.putAll(userInfo(record[i]));
//            }
//
//        }//end for
//
//        String[] answer = new String[resultLength];
//
//        for(int i = 0 ; i < record.length ; i++)
//        {//출력문 생성
//            if(record[i].startsWith("Enter") || record[i].startsWith("Leave"))
//            //입장 또는 퇴장 상태 확인
//            {
//                answer[resultIndex++] = uids.get(record[i].split(" ")[1]) + "님이 "
//                        + (record[i].startsWith("Enter") ? "들어왔습니다." : "나갔습니다.");
//            }//end if
//        }//end for
//
//        return answer;
//        // return answer;
//    }
//
//    public Map<String,String> userInfo(String item){
//        String[] infoSplit = item.split(" ");
//        Map<String,String> info = new HashMap<String,String>();
//        info.put(infoSplit[1], infoSplit[2]);
//        return info;
//    }
}
