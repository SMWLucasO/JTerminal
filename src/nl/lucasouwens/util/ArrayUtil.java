package nl.lucasouwens.util;

import  java.util.ArrayList;

public class ArrayUtil {

    /**
     * A method to join arrays together
     * @param arrays The arrays you wish to merge
     * @return String[]
     */
    public static String[] join(String[]... arrays) {
        int size = 0;
        for (String[] array : arrays) {
            size += array.length;
        }

        // create list of appropriate size
        java.util.List list = new ArrayList(size);

        // add arrays
        for (String[] array : arrays) {
            list.addAll(java.util.Arrays.asList(array));
        }

        // create and return final array
        return (String[]) list.toArray(new String[size]);
    }

}
