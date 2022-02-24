package com.idltd.hydramob.utils.filehandler.exception;


import com.idltd.hydramob.utils.filehandler.handler.FileTypeEnum;

public class UnsupportedFileTypeException extends Exception{
    public UnsupportedFileTypeException(FileTypeEnum fte) {
        super("Unsupported format "+fte.name());
    }
}
