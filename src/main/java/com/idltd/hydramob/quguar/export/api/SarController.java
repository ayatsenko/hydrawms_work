package com.idltd.hydramob.quguar.export.api;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.idltd.hydramob.quguar.export.api.response.ApiResponse;
import com.idltd.hydramob.quguar.export.sar.Sar;

import javax.ws.rs.core.MediaType;

@RestController
@RequestMapping("/api/sar")
public class SarController {

    @RequestMapping(value = "/add",  method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON)
    public ApiResponse add(@RequestBody Sar sar){
        ApiResponse result=new ApiResponse();
        result.setSource(sar);
        result.setResult("Done");
        return result;
    }

}
