import common.BaseClass;

public class Test1_3nTiling extends BaseClass {
        public int solution(int n) {
        /**
         * 1. 패턴 찾기 및 단순 점화식 가정(조합결과의 세로길이는 3으로 고정)
         * 가로길이 2, 세로길이 1 타일로 가로길이 2에 해당되는 조합이 가능한 경우의 수 : 3
         * f(2) = 3
         *
         * 가로길이가 홀수(odd number)인 경우 조합 불가능, 경우의 수 : 0
         *
         * 가로길이가 4 일 때,
         * f(4) = f(2) 의 확장
         * f(2) 에서 발생할 수 있는 모든 경우의 수에 3가지의 새로운 방법 추가로 가능
         * f(2) * 3 = 9
         *
         * 3 * 2 보드에 타일 경계가 이어지도록 붙이는 방식이 1가지 더 존재하는 경우
         * f(2) * 4 = 12
         * 하지만 실제 모형을 만들어보면 11 개가 만들어지므로 - x 값이 필요함.
         * f(n) = 4 * f(n-2) + x
         *
         * ex. n = 6 (실제 만들어지는 경우의 수 : 41)
         * f(6) = 4 * f(4) + x
         * 41 = 4 * 11 + x
         * -3 = x
         *
         * x 값을 -3이라고 가정한 다음 f(4)에 대입 해보면 값이 9가 나온다.
         * f(6) 수식을 참고해서 x 값을 f(n-4) 로 지정하고 점화식을 아래와 같이 구성하면
         * f(n) = 4 * f(n-2) - f(n-4) 로 확인할 수 있는데
         * 그렇다면 f(0) = 1 이 성립되어야 한다.(아무것도 놓지않는 경우 : 1)
         *
         *
         * */

        // 홀수일 때는 타일링이 불가능하므로 0 리턴
        if (n % 2 == 1) return 0;

        // n이 짝수일 때만 경우의 수를 담는 배열에 저장
        // ex. 타일링의 가로길이는 m * 2
        int mod = 1_000_000_007; // 경우의 수(result) 를 나눠 나머지 값을 구하기 위한 상수

        if (n == 0) return 1;
        if (n == 2) return 3;
        if (n == 4) return 11;

        int m = n / 2;

        // 초기값(디버깅으로 찾은 값):
        // dp[1] = f(2) = 3, dp[2] = f(4) = 11, dp[3] = f(6) = 41
        // 가로길이 매개변수로 인해 발생되는 경우의 수 배열
        // overflow 방지를 위해 long 배열
        long[] dp = new long[m + 1];
        dp[0] = 1L; // 아무것도 놓지 않는 경우 : 1
        dp[1] = 3L;
        dp[2] = 11L;

        // 점화식: f(n) = 4 × f(n–2) – f(n–4) (n이 짝수일 때만 적용)
        // 가로길이는 2 * 인덱스
        // n-2 는 현재 index-1, n-4 는 현재 index-2
        for (int i = 3; i <= m; i++) {
                //모듈러 분배법칙 적용
                dp[i] = ((4 * dp[i-1])%mod - dp[i-2]%mod + mod)%mod;//음수 방지
        }

        // 모듈러(1_000_000_007) 값은 10억 미만으로 최종 결과는 모듈러 값 미만이기 때문에
        // int 의 범위 (약 -21~+21억) 에 포함되므로 int 로 DownCasting 해도 문제 없음
        return (int)dp[m];
    }
}
