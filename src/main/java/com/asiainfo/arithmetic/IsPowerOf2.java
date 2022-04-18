package com.asiainfo.arithmetic;

import java.util.Scanner;

/**
 * @author Administrator 求一个数是否是偶数以及是否是2的次幂 2的次幂1,2,4,8,16...
 */
public class IsPowerOf2 {

	public static void main(String[] args) {
		int param = new Scanner(System.in).nextInt();
		// isEven(13);
		boolean flag = isPowerOf2(param);
		if(flag){
			System.out.println(param+"是2的次幂数");
		}else
			System.out.println(param+"不是2的次幂数");
	}

	private static boolean isPowerOf2(int num) {
		if (num == 1) {
			return true;
		}
		else if (isEven(num) && num >= 2) {
			return isPowerOf2(num / 2);//递归调用
		}
		return false;
	}

	private static boolean isEven(int num) {
		if (num % 2 != 0) {
			return false;
		}
		return true;
	}
}
