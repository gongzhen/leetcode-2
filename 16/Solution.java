public class Solution
{

  int N;
  int[] num;

  int next (int i)
  {
    int j = i + 1;
    while (j < N && num[j] == num[i])
      ++j;
    return j;
  }

  int findBefore (int x, int start)
  {
    int end = N;
    while (end - start > 1)
    {
      int mid = (start + end) / 2;
      if (num[mid] <= x)
        start = mid;
      else
        end = mid;
    }
    return start;
  }

  public int threeSumClosest (int[] num, int target)
  {
    N = num.length;
    this.num = num;
    Arrays.sort(num);
    int minDiff = Integer.MAX_VALUE;
    int closestSum = -1;
    for (int i = 0; i + 2 < N; i = next(i))
      for (int j = i + 1; j + 1 < N; j = next(j))
      {
        int x = target - (num[i] + num[j]);
        int k = findBefore(x, j + 1);
        if (num[k] <= x && k + 1 < N && num[k + 1] - x < x - num[k])
          ++k;
        int sum = num[i] + num[j] + num[k];
        int diff = Math.abs(target - sum);
        if (diff < minDiff)
        {
          minDiff = diff;
          closestSum = sum;
        }
      }
    return closestSum;
  }

}
