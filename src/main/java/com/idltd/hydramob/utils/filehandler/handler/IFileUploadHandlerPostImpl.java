package com.idltd.hydramob.utils.filehandler.handler;

import org.springframework.web.multipart.MultipartFile;
import com.idltd.hydramob.utils.filehandler.entity.FilehandlerLog;
import com.idltd.hydramob.utils.filehandler.exception.UnsupportedFileFormatException;
import com.idltd.hydramob.utils.filehandler.repository.FileHandlerAtomLogRepository;
import com.idltd.hydramob.utils.filehandler.repository.FileHandlerBufferRepository;
import com.idltd.hydramob.utils.filehandler.repository.FileHandlerDetailLogRepository;
import com.idltd.hydramob.utils.filehandler.repository.FileHandlerLogRepository;

import java.io.IOException;

public abstract class IFileUploadHandlerPostImpl extends IFileUploadHandlerImpl {

    protected final Long dis_id;
    protected final Long service_id;
    protected final Long type_id;
    protected final String scountry_iso2;
    protected final String rcountry_iso2;

    public IFileUploadHandlerPostImpl(FileTypeEnum fte, FilehandlerLog fhl, MultipartFile file, FileHandlerBufferRepository fileHandlerBufferRepository, FileHandlerLogRepository fileHandlerLogRepository, FileHandlerDetailLogRepository fileHandlerDetailLogRepository, FileHandlerAtomLogRepository fileHandlerAtomLogRepository,
                                      Long dis_id, Long service_id, Long type_id, String scountry_iso2, String rcountry_iso2)
            throws UnsupportedFileFormatException, NullPointerException, IOException {
        super(fte, fhl, file, fileHandlerBufferRepository, fileHandlerLogRepository, fileHandlerDetailLogRepository, fileHandlerAtomLogRepository);

        if (dis_id == null) throw new NullPointerException("dis_id can not be null"); //если нет депеши то некуда грузить
        if (service_id == null) throw new NullPointerException("service_id can not be null");
        if (type_id == null) throw new NullPointerException("type_id can not be null");
        if (scountry_iso2 == null) throw new NullPointerException("scountry_iso2 can not be null");
        if (rcountry_iso2 == null) throw new NullPointerException("rcountry_iso2 can not be null");

        this.dis_id=dis_id;
        this.service_id = service_id;
        this.type_id = type_id;
        this.scountry_iso2 = scountry_iso2;
        this.rcountry_iso2 = rcountry_iso2;
    }


}
