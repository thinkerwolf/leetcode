# 两个排序数组的中位数
## 数组拼接排序
比较无脑的算法，先把两个数组拼接成一个新数组，然后进行排序，找出中位数
* 时间复杂度：O(m+n)

``` java
 public double findMedianSortedArrays(int[] nums1, int[] nums2) {
      int len = nums1.length + nums2.length;
		int[] nums = new int[len];
		for (int i = 0; i < nums1.length; i++) {
			nums[i] = nums1[i];
		}
		for (int i = 0; i < nums2.length; i++) {
			nums[i + nums1.length] = nums2[i];
		}
		Arrays.sort(nums);
		if (len % 2 != 0) {
			return nums[len / 2];
		} else {
			return (nums[len / 2] + nums[len / 2 - 1]) / 2.0;
		}       
 }
```

## 最小长度数组轮训法（递归法）
* 时间复杂度：O(log(min(m,n)))
* 空间复杂度：O(1)

``` java
public double findMedianSortedArrays(int[] A, int[] B) {
      int m = A.length;
		int n = B.length;
		if (m > n) {
			int[] temp = A;
			A = B;
			B = temp;
			int tmp = m;
			m = n;
			n = tmp;
		}
		int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
		while (iMin <= iMax) {
			int i = (iMin + iMax) / 2;
			int j = halfLen - i;
			if (i < iMax && A[i - 1] > B[j]) {
				// 太大，A偏左，B偏右
				iMax--;
			} else if (j > iMin && B[j - 1] > A[i]) {
				// 太小，A偏右，A偏左
				iMin++;
			} else {
				// 正好
				int maxLeft = 0;
				if (A[i - 1] >= B[j - 1]) {
					maxLeft = A[i - 1];
				} else if (A[i - 1] < B[j - 1]) {
					maxLeft = B[j - 1];
				}
				if ((m + n) % 2 == 1) {
					return maxLeft;
				}
				int minRight = 0;
				if (A[i] >= B[j]) {
					minRight = B[j];
				} else {
					minRight = A[i];
				}
				return (maxLeft + minRight) / 2.0;
			}
		}
		return 0.0;   
    }

```
