import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1654 {
    static int[] arr;               // 원래 랜선들의 길이
    static int n;                   // 필요한 랜선의 개수
    static long result;             // 필요한 개수이상 랜선을 만들 때 랜선의 최대 길이
    static int max;                 // 랜선들 중 가장 긴 랜선의 길이

    public static void binarySearch(long start, long end){
        long cnt = 0;
        long mid = (end-start)/2+start;         // 중간 값 찾기
        if(start > end){                        // 기저 조건 : 시작 값이 끝 값을 넘어서면 멈추기
            System.out.println(result);
            return;
        }
        for(int len: arr){
            cnt += len/mid;                       // 랜선들을 중간 값으로 잘랐을 때 랜선의 개수 구하기
        }
        if(cnt < n){                                // 만약, 랜선의 개수가 필요한 랜선의 개수보다 작다면
            binarySearch(start, mid-1);             // 시작 길이(0) ~ 중간 값(mid) 사이 길이로 다시 재 탐색
        }
        else{
            result = mid;                           // 랜선의 최대 길이를 중간 값으로 설정
            binarySearch(mid+1, end);               // 중간 값(mid) ~ 끝 길이(max) 값까지 재 탐색
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine()," ");
        int k = Integer.parseInt(st.nextToken());               // 원래 랜선의 개수
        n = Integer.parseInt(st.nextToken());
        arr = new int[k];
        max = 0;

        for(int i=0; i<k; i++){
            arr[i] = Integer.parseInt(in.readLine());
            if(max < arr[i])    max = arr[i];                   // 주어진 랜선의 최대 길이를 max에 담기
        }

        binarySearch(1, max);           // start를 1로 하지 않으면 mid가 0이되면서 에러 발생. 어차피 길이는 자연수의 최소값인 1임
    }
}
