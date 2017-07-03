package com.code;

public class HammingDistance {

    public static void main(String[] args) {
	    int x = 93, y = 73;
	    int dist = hammingDistance(x,y);
        System.out.print(dist);
    }

    static int hammingDistance(int x, int y) {
        int count = 0, i=0;
        String xStr = Integer.toBinaryString(x);
        String yStr = Integer.toBinaryString(y);

        if(xStr.length() > yStr.length()) {
            int diff = xStr.length() - yStr.length();

            for(int j=0;j<diff;j++) {
                yStr = "0"+yStr;
            }
        }
        if(yStr.length() > xStr.length()) {
            int diff = yStr.length() - xStr.length();

            for(int j=0;j<diff;j++) {
                xStr = "0"+xStr;
            }
        }
        while(i < xStr.length()) {
            if(xStr.charAt(i) != yStr.charAt(i))
                count++;

            i++;
        }

        return count;
    }
}
