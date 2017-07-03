package com.code;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Animesh on 7/1/17.
 */
public class CorrectParentheses {

    public static void main(String[] args) {
        String input = "(a(b(c)";

        input = removeAllLastOpenParen(input);
        input = removeAllFirstCloseParen(input);

        List<String> myStr = new ArrayList<>();
        int countOpenParen = countParen(input,0,'o');
        int countCloseParen = countParen(input,0,'c');

        if(countOpenParen == 0 && countCloseParen == 0) {
            myStr.add(input);
        } else if(verifyString(input)) {
            myStr.add(input);
        } else {
            remParen(input,myStr,countOpenParen,countCloseParen);
            if(myStr.size() == 0) {
                input = removeConflictingCloseParens(input);
                countOpenParen = countParen(input,0,'o');
                countCloseParen = countParen(input,0,'c');
                remParen(input,myStr,countOpenParen,countCloseParen);
            }

            if(myStr.size() == 0) {
                myStr.add("");
            }
        }
        for(String s : myStr) {
            System.out.println(s);
        }

    }

    static String removeConflictingCloseParens(String input) {
        int count = 0;
        List<Integer> conflictingCloseParenIndices = new ArrayList<>();

        for(int i=0;i<input.length();i++) {
            if(input.charAt(i) == '(') {
                if(count < 0) {
                    count = 1;
                } else {
                    count++;
                }

            } else if(input.charAt(i) == ')') {
                count--;
            }

            if(count < 0)
                conflictingCloseParenIndices.add(i);
        }

        if(conflictingCloseParenIndices.size() == 0) {
            return input;
        }

        int size = conflictingCloseParenIndices.size();
        int[] indices = new int[size];
        for(int i=0;i<conflictingCloseParenIndices.size();i++) {
            indices[i] = conflictingCloseParenIndices.get(i);
        }

        return removeChars(input,indices);

    }

    static String removeAllLastOpenParen(String input) {
        int indexLastOpenParen = input.lastIndexOf('(');
        int indexLastCloseParen = input.lastIndexOf(')');
        String newInput = input;
        if(indexLastCloseParen < indexLastOpenParen) {
            newInput = removeChars(input,new int[]{indexLastOpenParen});
            while(true) {
                indexLastOpenParen = newInput.lastIndexOf('(');
                indexLastCloseParen = newInput.lastIndexOf(')');
                if(indexLastCloseParen > indexLastOpenParen) {
                    break;
                } else {
                    if(indexLastOpenParen != -1) {
                        newInput = removeChars(newInput,new int[]{indexLastOpenParen});
                    } else { // there are no open parens to work with
                        break;
                    }
                }
            }
        }

        return newInput;
    }

    static String removeAllFirstCloseParen(String input) {
        int indexFirstCloseParen = input.indexOf(')');
        int indexFirstOpenParen = input.indexOf('(');
        String newInput = input;
        if(indexFirstOpenParen > indexFirstCloseParen) {
            newInput = removeChars(input,new int[]{indexFirstCloseParen});
            while(true) {
                indexFirstCloseParen = newInput.indexOf(')');
                indexFirstOpenParen = newInput.indexOf('(');
                if(indexFirstOpenParen < indexFirstCloseParen) {
                    break;
                } else {
                    if(indexFirstCloseParen != -1) {
                        newInput = removeChars(newInput,new int[]{indexFirstCloseParen});
                    } else { // there are no close parens to work with
                        break;
                    }
                }
            }
        }

        return newInput;
    }

    static void remParen(String input, List<String> myStr, int countOpenParen, int countCloseParen) {
        int removeParen = countCloseParen - countOpenParen;
        int absRemoveParen = Math.abs(removeParen);


        List<int[]> myList;
        if(countCloseParen > countOpenParen) {
            int[] closeParenIndices = parenIndices(input,0,countCloseParen,'c');
            myList = combinationsIndices(closeParenIndices,absRemoveParen);
            for(int[] indexCombination:myList) {
                String output = removeChars(input,indexCombination);
                if(verifyString(output) && !isDuplicate(myStr,output)) {
                    myStr.add(output);
                }
            }


        } else if(countOpenParen > countCloseParen) {
            int[] openParenIndices = parenIndices(input,0,countOpenParen,'o');
            myList =  combinationsIndices(openParenIndices, absRemoveParen);
            for(int[] indexCombination:myList) {
                String output = removeChars(input,indexCombination);
                if(verifyString(output) && !isDuplicate(myStr,output)) {
                    myStr.add(output);
                }
            }

        }



    }

    static List<int[]> combinationsIndices(int[] indices, int parenCount) {
        int len = indices.length;
        int[] data = new int[parenCount];
        List<int[]> indicesCombinations = new ArrayList<>();
        combinationsHelper(indices,data,0,len-1,0,parenCount,indicesCombinations);
        return indicesCombinations;
    }

    static void combinationsHelper(int[] indices, int[] data, int start, int end, int index, int r, List<int[]> indicesCombinations) {

        if(index == r) {
            int[] newData = new int[r];
            for(int j=0;j<r;j++) {
                newData[j] = data[j];
            }
            indicesCombinations.add(newData);
            return;
        }

        for(int i = start; i<=end && end-i+1>= r - index; i++) {
            data[index] = indices[i];
            combinationsHelper(indices,data,i+1,end, index+1,r,indicesCombinations);
        }
    }

    static int countParen(String input, int startIndex, char parenType) {
        int parenFound = 0;
        for(int i=startIndex;i<input.length();i++) {
            if(input.charAt(i) == '(' && parenType == 'o') {
                    parenFound++;
            }
            else if(input.charAt(i) == ')' && parenType == 'c') {
                parenFound++;
            }
        }
        return parenFound;

    }

    static String removeChars(String input, int[] indices) {
        char[] charInput = input.toCharArray();
        char[] output = new char[input.length()-indices.length];
        String out = "";
        int j=0,k=0;
        for(int i=0;i<charInput.length;i++) {
            if(j<indices.length && i==indices[j]) {
                j++;
            } else {
                output[k] = charInput[i];
                k++;
            }
        }

        for(int i=0;i<output.length;i++) {
            out += output[i];
        }

        return out;
    }

    static boolean isDuplicate(List<String> myStr, String s) {
        for(String str : myStr) {
            if(str.equals(s)) {
                return true;
            }
        }
        return false;
    }

    static boolean verifyString(String s) {
        int count = 0;
        for(int i=0;i<s.length();i++) {
            if(s.charAt(i) == '(') {
                count++;
            } else if(s.charAt(i) == ')') {
                count--;
            }

            if(count < 0)
                return false;
        }
        if(count == 0)
            return true;
        else
            return false;
    }

    static int[] parenIndices(String input, int startIndex, int parenCount, char parenType) {
        int[] indices = new int[parenCount];
        int iIndices = 0;
        for(int i=startIndex;i<input.length();i++) {
            if(parenType == 'c' && input.charAt(i) == ')') {
                indices[iIndices] = i;
                iIndices++;
                parenCount--;
            } else if(parenType == 'o' && input.charAt(i) == '(') {
                indices[iIndices] = i;
                iIndices++;
                parenCount--;
            }
            if(parenCount == 0)
                break;
        }
        return indices;
    }
}
