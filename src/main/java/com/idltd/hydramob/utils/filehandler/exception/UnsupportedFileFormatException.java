package com.idltd.hydramob.utils.filehandler.exception;


import com.idltd.hydramob.utils.filehandler.handler.FileTypeEnum;

public class UnsupportedFileFormatException extends Exception{
    public UnsupportedFileFormatException(String filename, FileTypeEnum fte) {
        super("Format "+filename+" is not valid "+fte.name()+" format");
    }
}
