package leetcode.tree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangyu
 **/


public class FindAllAnagrams438 {
    @Test
    public void testFindAllAnagrams() {
        String s = "baa";
        String p = "aa";
        List<Integer> list = findAllAnagrams(s, p);
        System.out.println(list);
    }

    /**
     * @param s 字符串s
     * @param p 字符串p
     * @return 链表
     */
    private List<Integer> findAllAnagrams(String s, String p) {
        List<Integer> list = new ArrayList<>();
        int i = 0;
        int j = p.length();
        while (j <= s.length()) {
            String subString = s.substring(i, j);
            if (equalsIgnoreOrderString1(subString, p)) {
                list.add(i);
            }
            i++;
            j++;
        }
        return list;
    }

    /**
     * @param s 字符串s
     * @param p 字符串p
     * @return 链表
     */
    public boolean equalsIgnoreOrderString1(String s, String p) {
        if (s.length() == 0 || p.length() == 0) {
            return false;
        }
        if (s.length() != p.length()) {
            return false;
        }
        char[] chsp = p.toCharArray();
        for (char ch : chsp) {
            if (s.contains(String.valueOf(ch))) {
                s = s.replaceFirst(String.valueOf(ch), "@");
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * @param s 字符串s
     * @param p 字符串p
     * @return 链表
     */
    public List<Integer> findAnagrams3(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        int sl = s.length(), pl = p.length();
        if (sl < pl) {
            return ans;
        }
        int[] pa = new int[26];
        // 让这些字符都映射在这26个数字中
        for (char c : p.toCharArray()) {
            System.out.println(pa[c - 'a']++);
        }

        int[] sa = new int[26];
        char[] sc = s.toCharArray();
        for (int i = 0; i < pl; i++) {
            sa[sc[i] - 'a']++;
        }
        if (isSame(sa, pa)) {
            ans.add(0);
        }
        for (int i = pl; i < sl; i++) {
            sa[sc[i] - 'a']++;
            sa[sc[i - pl] - 'a']--;
            if (isSame(sa, pa)) ans.add(i - pl + 1);
        }
        return ans;
    }

    private boolean isSame(int[] sa, int[] pa) {
        for (int i = 0; i < 26; i++) {
            if (sa[i] != pa[i]) return false;
        }
        return true;
    }
}
