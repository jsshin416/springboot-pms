package edu.axboot.controllers;

import com.chequer.axboot.core.api.response.Responses;
import com.chequer.axboot.core.controllers.BaseController;
import com.chequer.axboot.core.parameter.RequestParams;
import org.springframework.stereotype.Controller;
import com.chequer.axboot.core.api.response.ApiResponse;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import edu.axboot.domain.pms.room.PmsRoom;
import edu.axboot.domain.pms.room.PmsRoomService;

import javax.inject.Inject;
import java.util.List;

@Controller
@RequestMapping(value = "/api/v1/pmsroom")
public class PmsRoomController extends BaseController {

    @Inject
    private PmsRoomService pmsRoomService;

    @RequestMapping(method = RequestMethod.GET, produces = APPLICATION_JSON)
    public Responses.ListResponse list(RequestParams<PmsRoom> requestParams) {
        List<PmsRoom> list = pmsRoomService.gets(requestParams);
        return Responses.ListResponse.of(list);
    }

    @RequestMapping(method = {RequestMethod.PUT}, produces = APPLICATION_JSON)
    public ApiResponse save(@RequestBody List<PmsRoom> request) {
        pmsRoomService.save(request);
        return ok();
    }

//QueryDsl--------------------------------------------------------------------------

    @RequestMapping(value = "/querydsl", method = RequestMethod.GET, produces = APPLICATION_JSON)
    public Responses.ListResponse listUsingQuery(RequestParams<PmsRoom> requestParams) {
        List<PmsRoom> list = pmsRoomService.getUsingQuery(requestParams);
        return Responses.ListResponse.of(list);
    }

    @RequestMapping(value = "/querydsl", method = {RequestMethod.PUT}, produces = APPLICATION_JSON)
    public ApiResponse saveUsingQuery(@RequestBody List<PmsRoom> request) {
        pmsRoomService.saveUsingQuery(request);
        return ok();
    }

}