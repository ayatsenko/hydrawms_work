package com.idltd.hydramob.Sheduler;

import com.idltd.hydramob.repository.sheduler.MailTaskRepository;
import com.idltd.hydramob.utils.mail.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShedulerMail {

    private final MailTaskRepository mailTaskRepository;

    @Autowired
    private EmailServiceImpl javaMailSender;

    public ShedulerMail(MailTaskRepository mailTaskRepository) {
        this.mailTaskRepository = mailTaskRepository;
    }

//    @Scheduled(fixedRate = 10000)
//    public void mailAll(){
//        List<MailTask> mailTaskList = mailTaskRepository.queryByStateisNull();
//        for ( MailTask mt:mailTaskList) {
//            String result = javaMailSender.sendSimpleMessage(mt.mt_to,mt.mt_subject,mt.mt_text);
//            if (result.equalsIgnoreCase("done")) {
//                mt.mt_state = "done";
//                mt.mt_error = null;
//            }
//            else{
//                mt.mt_state = "error";
//                mt.mt_error = result;
//            }
//
//            mt.mt_done=new Date();
//            mailTaskRepository.save(mt);
//        }
//    }
}
