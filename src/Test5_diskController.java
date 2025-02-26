import common.BaseClass;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

class Job{
    int reqTime;//작업요청시점
    int duration;//작업소요시간
    int idx;//작업번호

    public Job(int requestTime, int duration, int index) {
        this.reqTime = requestTime;
        this.duration = duration;
        this.idx = index;
    }
}

public class Test5_diskController extends BaseClass {
    public int solution(int[][] jobs) {
        int n = jobs.length;

        //version 1
        /*
        Job 객체배열 생성 (for i)
        Job[] jobArr = new Job[n];
        int jobArrIdx = 0;
        for (int i = 0; i < jobs.length; i++) {
            //작업요청시점,작업소요시간,작업번호로 Job 객체 생성
            jobArr[i] = new Job(jobs[i][0], jobs[i][1], i);
        }
        // 작업요청시점을 기준으로 오름차순 정렬

        // 1. lambda 표현식 사용 with Comparator - jdk1.8~)
//        Arrays.sort(jobArr, Comparator.comparingInt(a -> a.requestTime));
        /*
        2. lambda 표현식(To be removed in a future release, 향후 제거 예정)
        Arrays.sort(jobArr, (a, b) -> a.requestTime - b.requestTime);
        3. legacy한 표현방식 (new Comparator 사용하여 compare() method overriding)
        Arrays.sort(jobArr, new Comparator<Job>() {
            @Override
            public int compare(Job o1, Job o2) {
                //작업요청시간이 짧은 경우 배열의 앞에 위치, 긴 경우 뒤에 위치
                return o1.requestTime - o2.requestTime;
            }
        });
        */

        // 결과가 음수이면 a가 b보다 앞에 위치, 양수이면 b가 a보다 앞에 위치
        //Comparator.comparingInt()

        // version 2
        // 1.Job 객체 배열 생성(Stream API 사용)
        // 1-1.IntStream.range(0,n): 0부터 n-1 까지의 숫자 생성
        // 1-2.mapToObj: IntStream을 Stream으로 변환하여 Job 객체 인스턴스 생성
        // 1-3.sorted: Comparator.comparingInt()를 사용해서 Tim Sort로 Quick Sort와 Merge Sort를 혼합해서 정렬
        // 1-4.toArray: Stream을 배열로 전환. 더블콜론 연산자를 활용해 Job class 의 생성자 활용하여 배열 길이 자동 조절 및 다형성 활용
        Job[] jobArr = IntStream.range(0, n)
                .mapToObj(i -> new Job(jobs[i][0], jobs[i][1], i))
                .sorted(Comparator.comparingInt(a -> a.reqTime))
                .toArray(Job[]::new);

        // version 1
        // 작업 소요시간을 기준으로 PriorityQueue 생성(우선순위 큐)
        // 위와 마찬가지로 lambda 표현식을 사용해 comparator.comparingInt() 사용하여 정렬
/*        PriorityQueue<Job> pq = new PriorityQueue<>(new Comparator<Job>() {
            @Override
            public int compare(Job o1, Job o2) {
                //알고리즘 우선순위 요구사항에 맞게 정렬
                //1.작업소요시간이 짧은 것부터 PQueue 의 앞에 배치되도록
                if (o1.duration != o2.duration) return o1.duration - o2.duration;
                //2.작업요청시점이 짧은 것부터 PQueue 의 앞에 배치되도록
                if (o1.requestTime != o2.requestTime) return o1.requestTime - o2.requestTime;
                //3.작업번호가 작은 것부터 PQueue 의 앞에 배치되도록
                return o1.index - o2.index;
            }
        });*/

        //version 2
        //알고리즘 우선순위 요구사항에 맞게 정렬

        PriorityQueue<Job> pq = new PriorityQueue<>(Comparator
                //1.작업소요시간이 짧은 것부터 PQueue 의 앞에 배치되도록
                .comparingInt((Job a) -> a.duration)
                //2.작업요청시점이 짧은 것부터 PQueue 의 앞에 배치되도록
                .thenComparingInt(a -> a.reqTime)
                //3.작업번호가 작은 것부터 PQueue 의 앞에 배치되도록
                .thenComparingInt(a -> a.idx));


        //현재 작업시간
        int time = 0,
        //처리된 작업
        count = 0,
        //현재 작업 인덱스(작업요청시간이 짧은 순서대로 정렬되어 있음)
        idx = 0,
        //전체작업시간(반환값)
        totalProcessingTime = 0;

        while (count < n) {
            //현재 작업시간에 들어온 요청을 우선순위 큐에 넣어줌
            while (idx < n && jobArr[idx].reqTime <= time) {
                pq.add(jobArr[idx++]);
            }

            //PQueue 가 비어있지 않은 경우
            if (!pq.isEmpty()) {
                // version 1 : O(n log n) + O(n log n)
                //현재시간 = 현재시간 + 작업소요시간
                /*time += pq.peek().duration;
                //전체 작업시간 = 전체 작업시간 + (현재시간 - 작업요청시점)
                totalProcessingTime += time - pq.peek().reqTime;

                //작업이 끝나면 PQueue 에서 제거 후 작업건수 증가
                pq.poll();
                count++;*/

                // version 2 : heap 에서 작업을 꺼내서 처리하여 불필요한 연산 overhead 를 줄임
                // O(n log n)
                Job job = pq.poll();
                time += job.duration;
                totalProcessingTime += time - job.reqTime;
                count++;
            } else {
                //queue 가 비어있는 경우, 현재 PQueue head 에 있는 작업의 요청시간을 현재시간으로 변경
                time = jobArr[idx].reqTime;
            }

        }
        return totalProcessingTime / n;
    }
}
