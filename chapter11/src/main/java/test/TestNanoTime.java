package test;

/**
 * Created by shliangyan on 2017/9/29.
 */
public class TestNanoTime {

    public static void main(String[] args) {
        String[] a = new String[]{"aaa", "bbb", "ccc"};
        String[] b = new String[]{"aaa", "aaa", "ccc"};
    }

    public static boolean equals(String[] a, String[] b) {
        if (a.length != b.length) {
            return false;
        }
        int n = a[0].hashCode() ^ b[0].hashCode();
        for (int i = 1; i < a.length; i++) {
            n ^= a[i].hashCode() ^ b[i].hashCode();
        }
        if (n == 0) {
            return true;
        }
        return false;
    }
}
