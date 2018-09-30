package com.leetcode;

import java.util.Arrays;
/**
 * 合并排序
 * @author wukai
 *
 */
public class MergeSort {

	public void sort(int[] a, int low, int high) {
		if (low < high) {
			int mid = (low + high) / 2;
			sort(a, low, mid);
			sort(a, mid + 1, high);
			
			// 合并排序
			int i = low;
			int j = mid + 1;
			int k = 0;
			int[] tmp = new int[high - low + 1];
			for (; i <= mid && j <= high; k++) {
				if (a[i] > a[j]) {
					tmp[k] = a[j++];
				} else {
					tmp[k] = a[i++];
				}
			}
			
			// 将多余的部分加入tmp中
			while (i <= mid) {
				tmp[k++] = a[i++];
			}
			while (j <= high) {
				tmp[k++] = a[j++];
			}

			for (int l = 0; l < tmp.length; l++)
				a[low + l] = tmp[l];
		}

	}
	
	public static void main(String[] args) {
		MergeSort ms = new MergeSort();

		int[] a = new int[] { 19, 2, 1, 5, 5, 4, 0, 19, 13 };
		ms.sort(a, 0, a.length - 1);

		System.out.println(Arrays.toString(a));

	}
	
	
	
	
	
}
