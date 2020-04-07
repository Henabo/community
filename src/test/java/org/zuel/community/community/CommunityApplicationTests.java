package org.zuel.community.community;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class CommunityApplicationTests {

    @Test
    void contextLoads() {
        int[][] grid = {{1,2},{1,2}};
        int N = grid.length;
        int[][] temp = new int[N+1][N+1];
        for(int i = 0; i < N; i ++){
            for(int j = 0; j < N; j++){
                temp[i][j] = grid [i][j];
            }
        }
        System.out.println(temp);
    }

    @Test
    void duckTest(){
        int[] duck = {1,2,3,4};
        //计数，hashMap计数法
        Map<Integer, Integer> map = new HashMap<>();
        for(int num : duck){
            if(map.containsKey(num)){
                map.put(num, map.get(num) + 1);
            }else{
                map.put(num, 1);
            }
        }
        map.size();
        //计数，数组计数法
        int[] count = new int[duck.length];
        for(int num : duck){
            count[num]++;
        }

    }

    @Test
    void reverseList(){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
    }
}
