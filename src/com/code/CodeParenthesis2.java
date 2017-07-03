package com.code;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Animesh on 7/3/17.
 */
public class CodeParenthesis2 {

    public static void main(String[] args) {
        String input = ")))";
        for(String ans:removeInvalidParentheses(input)) {
            System.out.println(ans);
        }
    }

    public static List<String> removeInvalidParentheses(String s) {
        List<String> ans = new ArrayList<>();
        remove(s, ans, 0, 0, new char[]{'(', ')'});
        return ans;
    }

    public static void remove(String s, List<String> ans, int last_i, int last_j, char[] par) {
        int stack = 0, i;
        // Search for mismatch
        for (i = last_i; i < s.length(); ++i) {
            if (s.charAt(i) == par[0]) stack++;
            if (s.charAt(i) == par[1]) stack--;
            if (stack < 0) break;
        }
        // Remove a parenthesis
        if (stack < 0) {
            for (int j = last_j; j <= i; ++j) {
                if (s.charAt(j) == par[1] && (j == last_j || s.charAt(j - 1) != par[1])) {
                    String candidate = s.substring(0, j) + s.substring(j + 1, s.length());
                    remove(candidate, ans, i, j, par);
                }
            }
            return;
        }
        // If no mismatch, try reverse the string
        String reversed = new StringBuilder(s).reverse().toString();
        if (par[0] == '(')
            remove(reversed, ans, 0, 0, new char[]{')', '('});
        else
            ans.add(reversed); // both sides are finished, got an answer
    }
}
