# 最长无重复子串

``` java
public int lengthOfLongestSubstring(String s) {
     	  Map<Character, Integer> map = new HashMap<>();
        int x = 0;
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
           char c = s.charAt(i);
           if (map.containsKey(c)) {
              x = Math.max(map.get(c), x);
           } 
           res = Math.max(i - x + 1, res);
           map.put(c, i + 1);
        }
        return res;
}
```