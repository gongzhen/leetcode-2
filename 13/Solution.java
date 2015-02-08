public class Solution
{

  static final char[] ROMAN_LETTER = new char[]{'I', 'V', 'X', 'L', 'C', 'D', 'M', '?', '?'};

  static final String[] NUMBER_PATTERN = new String[]{"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

  boolean isMatch (String s, String pattern, char one, char five, char ten)
  {
    if (s.length() < pattern.length())
      return false;
    for (int i = 0; i < pattern.length(); ++i)
    {
      char c1 = s.charAt(i), c2 = pattern.charAt(i);
      if (c2 == 'I' && c1 != one)
        return false;
      if (c2 == 'V' && c1 != five)
        return false;
      if (c2 == 'X' && c1 != ten)
        return false;
    }
    return true;
  }

  int matchPattern (String s, char one, char five, char ten)
  {
    for (int i = 8; i >= 0; --i)
    {
      if (isMatch(s, NUMBER_PATTERN[i], one, five, ten))
        return i;
    }
    return -1;
  }

  public int romanToInt (String s)
  {
    int num = 0;
    int start = 0;
    int unit = 1000;
    int letterIdx = 6;
    while (unit > 0)
    {
      int patternIdx = matchPattern(s.substring(start), ROMAN_LETTER[letterIdx], ROMAN_LETTER[letterIdx + 1], ROMAN_LETTER[letterIdx + 2]);
      if (patternIdx != -1)
      {
        num += (patternIdx + 1) * unit;
        start += NUMBER_PATTERN[patternIdx].length();
      }
      unit /= 10;
      letterIdx -= 2;
    }
    if (start < s.length())
      throw new RuntimeException();
    return num;
  }

}
