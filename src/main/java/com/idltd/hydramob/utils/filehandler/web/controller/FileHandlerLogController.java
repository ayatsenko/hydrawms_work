package com.idltd.hydramob.utils.filehandler.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.idltd.hydramob.utils.JSONDatatable;
import com.idltd.hydramob.utils.filehandler.FileUploadResult;
import com.idltd.hydramob.utils.filehandler.FileUploadService;
import com.idltd.hydramob.utils.filehandler.handler.FileTypeEnum;
import com.idltd.hydramob.utils.filehandler.repository.FileHandlerLogRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.IOException;

import static com.idltd.hydramob.utils.StaticUtils.ConvertTraceExceptionToText;

@RestController
@RequestMapping("/filehandlerlog")
public class FileHandlerLogController {

    private final FileHandlerLogRepository fileHandlerLogRepository;

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private FileUploadService fileUploadService;

    public FileHandlerLogController(FileHandlerLogRepository fileHandlerLogRepository, EntityManager entityManager) {
        this.fileHandlerLogRepository = fileHandlerLogRepository;
        this.entityManager = entityManager;
    }


    @RequestMapping(value = {"","/", "/index"}, method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView index(ModelAndView mav
    ){
        mav.setViewName("/filehandlerlog/cover");
        return mav;
    }

    @PostMapping("/gettable")
    public JSONDatatable gettable() {
        JSONDatatable result = new JSONDatatable();
        result.setData(fileHandlerLogRepository.getAll());
        return result;
    }

    class TDownloadfileResult {
        public FileUploadResult fileUploadResult;
    }
    @RequestMapping("/downloadfile")
    public ResponseEntity<?> uploadfile(
            @RequestParam(name = "format_id") Long format_id,
            @RequestParam(name = "file_incoming", required = false) MultipartFile file_incoming,
            @RequestParam(name = "file_consumption", required = false) MultipartFile file_consumption

    ) {
        ResponseEntity result = null;

        TDownloadfileResult ufr = new TDownloadfileResult();
            try {
                switch(format_id.intValue()) {
                    case 5 :
                        ufr.fileUploadResult=fileUploadService.upload(FileTypeEnum.BAT,file_incoming,file_consumption);
                        break;
                    case 16 :
                        ufr.fileUploadResult=fileUploadService.upload(FileTypeEnum.BAT_NGP,file_incoming,file_consumption);
                        break;
                    case 6 :
                        ufr.fileUploadResult=fileUploadService.upload(FileTypeEnum.EGM,file_incoming,file_consumption);
                        break;
                    case 7 :
                        ufr.fileUploadResult=fileUploadService.upload(FileTypeEnum.GLO,file_incoming,file_consumption);
                        break;
                    case 8 :
                        ufr.fileUploadResult=fileUploadService.upload(FileTypeEnum.NMC,file_incoming,file_consumption);
                        break;
                    case 9 :
                        ufr.fileUploadResult=fileUploadService.upload(FileTypeEnum.DSV,file_incoming,file_consumption);
                        break;
                    case 10 :
                        ufr.fileUploadResult=fileUploadService.upload(FileTypeEnum.RBL,file_incoming,file_consumption);
                        break;
                    case 11 :
                        ufr.fileUploadResult=fileUploadService.upload(FileTypeEnum.LDA,file_incoming,file_consumption);
                        break;
                    case 12 :
                        ufr.fileUploadResult=fileUploadService.upload(FileTypeEnum.SMS,file_incoming,file_consumption);
                        break;
                    case 13 :
                        ufr.fileUploadResult=fileUploadService.upload(FileTypeEnum.SOL,file_incoming,file_consumption);
                        break;
                    case 14 :
                        ufr.fileUploadResult=fileUploadService.upload(FileTypeEnum.EKL,file_incoming,file_consumption);
                        break;
                    case 15 :
                        ufr.fileUploadResult=fileUploadService.upload(FileTypeEnum.PMU,file_incoming,file_consumption);
                        break;
                    default :
                        throw new Exception("I do not know format_id ="+format_id);
                }
                result = ResponseEntity.ok(ufr);
            } catch (IOException e) {
//                e.printStackTrace();
                result = new ResponseEntity<>(ConvertTraceExceptionToText(e), HttpStatus.INTERNAL_SERVER_ERROR);
            } catch (Exception e) {
//                e.printStackTrace();
                result = new ResponseEntity<>(ConvertTraceExceptionToText(e), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        return result;
    }
}
