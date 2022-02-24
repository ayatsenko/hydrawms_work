package com.idltd.hydramob.utils.filehandler.handler;


import com.idltd.hydramob.utils.filehandler.FileUploadResult;
import com.idltd.hydramob.utils.filehandler.exception.UnsupportedFileFormatException;

import java.io.IOException;

public interface IFileUploadHandler {

    // проверка формата файла
    public void validate() throws UnsupportedFileFormatException;
    // записать разобрать и записать в базу
    public FileUploadResult upload() throws IOException;
    // записать в Log
    public void savelog(FileLogStatusEnum status, String body);
    // записать разбор строки из загружаемого файла в Log
    public void saveatomlog(FileLogStatusEnum status, String atom, String error);
    //проверить формат файла
    public boolean validatefileformat(byte[] body);

}
