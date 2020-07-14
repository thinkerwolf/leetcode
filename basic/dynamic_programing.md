# 动态规划

## 思路

1. 找到求解的关键点f(i)

2. 写出动态转移方程f(i)和f(i-1)

## 最大子序和

f(i)表示数组以第i位结尾的子数组最大和，也就是`max{f(i)}  0<=i<=n-1`

状态转移方程为 `max{{f(i-1), f(i)}, max}`

``` go
func maxSubArray(nums []int) int {
    max := nums[0]
    for i := 1; i < len(nums); i++ {
        if nums[i] + nums[i-1] > nums[i] {
            nums[i] += nums[i-1]
        }
        if (nums[i] > max) {
            max = nums[i]
        }
        return max
    }
}
```

## 打家劫舍
