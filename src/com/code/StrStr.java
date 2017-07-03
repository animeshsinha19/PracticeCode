package com.code;

public class StrStr {

    public static void main(String[] args) {

        String haystack = "";
        String needle = "Ria";
        int index = strStr(haystack,needle);
        System.out.println(index);
    }

    static int strStr(String haystack, String needle) {

        if(needle.equals("") && haystack.equals(""))
            return 0;

        if(haystack.equals(""))
            return -1;

        if(needle.equals(""))
            return 0;

        String temp = "";
        for(int i=0;i<haystack.length()-needle.length()+1;i++) {
            if(haystack.charAt(i) == needle.charAt(0)) {
                temp = haystack.substring(i,i+needle.length());
                if(temp.equals(needle))
                    return i;
            }
        }


        return -1;
    }
}
