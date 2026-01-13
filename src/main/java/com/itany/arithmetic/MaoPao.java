package com.itany.arithmetic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author koala
 * @version 1.0
 * @date 2025/05/09/11:59
 * @description
 */
public class MaoPao {
    List<Integer> list = new ArrayList<>();
    static int[] arr = new int[]{19, 11, 8, 6, 5, 2, 7, 1, 12};
    public static void main(String[] args) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length -i - 1; j++) {
                if (arr[j] > arr[j+1]) {
                    int temp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    private static void bubbleSort1(int[] arr) {
        // 先找最小值
        int n = arr.length;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    int temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                }
            }
        }
    }

    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        //外部循环控制排序的趟数。冒泡排序的每一趟会将最大的元素"冒泡"到数组的末尾，因此需要执行 n-1 趟，其中 n 是元素的总数
        for (int i = 0; i < n - 1; i++) {
            //内循环控制每趟比较的次数。由于每一趟都会将一个最大的元素沉到数组末尾，所以内循环次数逐渐减小。
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // 交换arr[j]和arr[j+1]
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            System.out.println("第"+(i+1)+"趟："+Arrays.toString(arr));
        }
    }
}
