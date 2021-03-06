# 最长回文子串

## 暴力法

遍历每个可能的字符串，如果字符串不相等则进行下一次遍历。

* 时间复杂度：O(n^3)
* 空间复杂度：O(1)

``` java
	/**
	 * 字符串回文（暴力法）
	 * 
	 * @param s
	 * @return
	 */
	public String longestPalindrome1(String s) {
		int len = s.length();
		int maxLen = 0;
		String ans = s;
		for (int i = 0; i < len; i++) {
			for (int j = i; j < len; j++) {
				int low = i;
				int high = j;
				boolean b = true;
				while (low <= high) {
					if (s.charAt(low) != s.charAt(high)) {
						b = false;
						break;
					}
					low++;
					high--;
				}
				if (b) {
					if (maxLen < j - i + 1) {
						maxLen = j - i + 1;
						ans = s.substring(i, j + 1);
					}
				}
			}
		}
		return ans;
	}
```

## 对称法

遍历每个字符串，由于回文左右对称的特性，通过判断当前字符左右是否相同即可。

* 时间复杂度：O(n^2)
* 空间复杂度：O(1)

``` java
	/**
	 * 字符串回文（对称算法）
	 * 
	 * @param s
	 * @return
	 */
	public String longestPalindrome2(String s) {
		int len = s.length();
		if (len == 0 || len == 1) {
			return s;
		}
		int start = 0;
		int end = 0;
		int maxLen = 0;
		for (int i = 0; i < s.length(); i++) {
			int l1 = expendAndCompare(s, i, i);
			int l2 = expendAndCompare(s, i, i + 1);
			if (l1 > l2) {
				if (l1 > maxLen) {
					maxLen = l1;
					start = i - l1 / 2;
					end = i + l1 / 2;
				}
			} else {
				if (l2 > maxLen) {
					maxLen = l2;
					start = i - l2 / 2 + 1;
					end = i + l2 / 2;
				}
			}
		}
		return s.substring(start, end + 1);
	}
	
	private int expendAndCompare(String s, final int left, final int right) {
		int l = left;
		int r = right;
		while (l >= 0 && r < s.length()) {
			if (s.charAt(l) != s.charAt(r)) {
				break;
			}
			l--;
			r++;
		}
		return r - l - 1;
	}
```

## 马拉车算法

[算法原理](https://articles.leetcode.com/longest-palindromic-substring-part-ii/)

* 时间复杂度：O(n)

``` java
	/**
	 * 马拉车算法
	 * @param s
	 * @return
	 */
	public String longestPalindrome3(String s) {
		if (s == null) {
			return "";
		}
		int len = s.length();
		if (len == 0 || len == 1) {
			return s;
		}
		// 1.预处理，在字符串中插入'#',开头加入
		s = processStr(s);
		int[] P = new int[s.length()];
		int mx = 1, id = 1;
		int max = 0;
		int maxId = 0;
		for (int i = 1; i < s.length(); i++) {
			P[i] = mx > i ? Math.min(P[2 * id - i], mx - i) : 1;
			while (i + P[i] < s.length() && i - P[i] >= 0 && s.charAt(i + P[i]) == s.charAt(i - P[i])) {
				P[i]++;
			}
			if (i + P[i] > mx) {
				mx = i + P[i];
				id = i;
			}
			if (P[i] > max) {
				max = P[i];
				maxId = i;
			}
		}
		return s.substring(maxId - P[maxId] + 1, maxId + P[maxId]).replaceAll("#", "");
	}
	
	private String processStr(String s) {
		StringBuilder sb = new StringBuilder("$#");
		for (int i = 0; i < s.length(); i++) {
			sb.append(s.charAt(i));
			sb.append("#");
		}
		return sb.toString();
	}
```
