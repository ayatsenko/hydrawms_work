package com.idltd.hydramob.Sheduler;

import com.idltd.hydramob.repository.sheduler.MailTaskRepository;
import org.springframework.stereotype.Component;

@Component
public class ShedulerForexFile {

    private final MailTaskRepository mailTaskRepository;

    public ShedulerForexFile(
            MailTaskRepository mailTaskRepository
    ) {
        this.mailTaskRepository = mailTaskRepository;
    }


}
