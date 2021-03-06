package com.idltd.hydramob.utils.filehandler;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.idltd.hydramob.utils.filehandler.entity.FilehandlerLog;
import com.idltd.hydramob.utils.filehandler.exception.UnsupportedFileFormatException;
import com.idltd.hydramob.utils.filehandler.exception.UnsupportedFileTypeException;
import com.idltd.hydramob.utils.filehandler.handler.FileLogStatusEnum;
import com.idltd.hydramob.utils.filehandler.handler.FileTypeEnum;
import com.idltd.hydramob.utils.filehandler.handler.FileUploadHandlerBAT;
import com.idltd.hydramob.utils.filehandler.handler.IFileUploadHandler;
import com.idltd.hydramob.utils.filehandler.repository.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.IOException;
import java.sql.Date;

import static com.idltd.hydramob.utils.StaticUtils.GetUserName;


@Service
public class FileUploadService {

    private final FileHandlerBufferRepository fileHandlerBufferRepository;
    private final FileHandlerLogRepository fileHandlerLogRepository;
    private final FileHandlerDetailLogRepository fileHandlerDetailLogRepository;
    private final FileHandlerAtomLogRepository fileHandlerAtomLogRepository;
    private final LoadBatRepository loadBatRepository;


    @PersistenceContext
    private EntityManager entityManager;

    public FileUploadService(FileHandlerBufferRepository fileHandlerBufferRepository, FileHandlerLogRepository fileHandlerLogRepository, FileHandlerDetailLogRepository fileHandlerDetailLogRepository, FileHandlerAtomLogRepository fileHandlerAtomLogRepository, EntityManager entityManager, LoadBatRepository loadBatRepository) {
        this.fileHandlerBufferRepository = fileHandlerBufferRepository;
        this.fileHandlerLogRepository = fileHandlerLogRepository;
        this.fileHandlerDetailLogRepository = fileHandlerDetailLogRepository;
        this.fileHandlerAtomLogRepository = fileHandlerAtomLogRepository;
        this.loadBatRepository = loadBatRepository;
        this.entityManager = entityManager;
    }

    private void savelog(FilehandlerLog fhl, FileLogStatusEnum flse, String body){

        if (flse==FileLogStatusEnum.SUCCESS) { fhl.setFhl_body(body);
        } else {
            fhl.setFhl_errorbody(body);
        }

        fhl.setFhl_status(flse.toString());
        fileHandlerLogRepository.save(fhl);
    }

    //    ???????????????? ?????????? ?????????????? ?? ????????
    public FileUploadResult upload(FileTypeEnum fte,
                                   MultipartFile file_incoming,
                                   MultipartFile file_consumption) throws UnsupportedFileFormatException, UnsupportedFileTypeException, IOException {

        FilehandlerLog fhl_incoming = new FilehandlerLog();
        FilehandlerLog fhl_consumption = new FilehandlerLog();
        fhl_incoming.setFhl_user(GetUserName());
        fhl_consumption.setFhl_user(GetUserName());

        //?????????????? ?? ???????? ???????????? ?????????? ???????????? ?? ?????? ????????????????????
        fhl_incoming.setFhl_startdate(new Date(System.currentTimeMillis()));
        fhl_consumption.setFhl_startdate(new Date(System.currentTimeMillis()));
        savelog(fhl_incoming,FileLogStatusEnum.SUCCESS,"Load file:"+file_incoming.getOriginalFilename());
        savelog(fhl_consumption,FileLogStatusEnum.SUCCESS,"Load file:"+file_consumption.getOriginalFilename());

        FileUploadResult result0;
        FileUploadResult result;

        try {
            //???????????????????? ?? ???????? ?????????????????????? ????????

            IFileUploadHandler fuh_incoming = null;
            IFileUploadHandler fuh_consumption = null;
            //    ???????????????? ???? ???????????? ?????????? ?? ???????????????????? ????????????????????
            switch (fte) {
                case BAT :
                case EGM :
                case GLO :
                case NMC :
                case DSV :
                case RBL :
                case LDA :
                case SMS :
                case SOL :
                case EKL :
                case PMU :
                case BAT_NGP:
                    fuh_incoming=new FileUploadHandlerBAT(fte,0,fhl_incoming,file_incoming,fileHandlerBufferRepository,fileHandlerLogRepository,fileHandlerDetailLogRepository,fileHandlerAtomLogRepository,
                                                            entityManager,
                                                            loadBatRepository);
                    fuh_consumption=new FileUploadHandlerBAT(fte,1,fhl_consumption,file_consumption,fileHandlerBufferRepository,fileHandlerLogRepository,fileHandlerDetailLogRepository,fileHandlerAtomLogRepository,
                                                            entityManager,
                                                            loadBatRepository);
                    break;

                default:    throw new UnsupportedFileTypeException(fte);
            }
            //?????????????????? ?? ?????????????????? ??????????????
            result0 = fuh_incoming.upload();
            result = fuh_consumption.upload();

            //???????????????????? ???????????????? ???????????????? ?? ??????
            fhl_incoming.setFhl_enddate(new Date(System.currentTimeMillis()));
            savelog(fhl_incoming,FileLogStatusEnum.SUCCESS,"Load file:"+file_incoming.getOriginalFilename());
            fhl_consumption.setFhl_enddate(new Date(System.currentTimeMillis()));
            savelog(fhl_consumption,FileLogStatusEnum.SUCCESS,"Load file:"+file_consumption.getOriginalFilename());

        } catch ( //UnsupportedFileTypeException |
//                 UnsupportedFileFormatException |
                 Exception e) {
            //???????????????????? ???????????? ?? ??????
            fhl_incoming.setFhl_enddate(new Date(System.currentTimeMillis()));
            savelog(fhl_incoming, FileLogStatusEnum.ERROR, e.getMessage());
            fhl_consumption.setFhl_enddate(new Date(System.currentTimeMillis()));
            savelog(fhl_consumption, FileLogStatusEnum.ERROR, e.getMessage());
            throw e;
        }
        return result;
    }

}
