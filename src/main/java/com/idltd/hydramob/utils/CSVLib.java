package com.idltd.hydramob.utils;

public class CSVLib {

   public static char getCvsColumnSeparator(Long csvscheme) {
        char separator;
        switch (csvscheme.intValue()) {
            case 1:  separator = ',';
                break;
            case 2:  separator = ';';
                break;
            default: separator = ',';
                break;
        }
        return separator;
    }
}
