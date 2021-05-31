package edu.axboot.controllers;

import com.chequer.axboot.core.api.response.Responses;
import com.chequer.axboot.core.controllers.BaseController;
import edu.axboot.controllers.dto.PmsRoomListResponseDto;
import edu.axboot.controllers.dto.PmsRoomSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import com.chequer.axboot.core.api.response.ApiResponse;
import org.springframework.web.bind.annotation.*;
import edu.axboot.domain.pms.info.room.PmsRoomService;

import javax.inject.Inject;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping(value = "/api/v1/pmsroom")
public class PmsRoomController extends BaseController {

    @Inject
    private final PmsRoomService pmsRoomService;
    //Dto----------------------------------------------------------------------
    @RequestMapping( method = RequestMethod.GET, produces = APPLICATION_JSON)
    public Responses.ListResponse list( @RequestParam(value = "roomTypCd",required = false) String roomTypCd){
             List<PmsRoomListResponseDto> list = pmsRoomService.findByL(roomTypCd);
            return Responses.ListResponse.of(list);
            }

    @RequestMapping(method = RequestMethod.POST, produces = APPLICATION_JSON)
    public ApiResponse save(@RequestBody List<PmsRoomSaveRequestDto> request){
        pmsRoomService.save(request);
        return ok();
    }

}




//QueryDsl--------------------------------------------------------------------------
   /* @RequestMapping( method = RequestMethod.GET, produces = APPLICATION_JSON)
    public Responses.ListResponse listUsingQuery(RequestParams<PmsRoom> requestParams) {
    List<PmsRoom> list = pmsRoomService.getUsingQuery(requestParams);
    return Responses.ListResponse.of(list);
}
   *//* @RequestMapping( method = RequestMethod.GET, produces = APPLICATION_JSON)
    public Responses.PageResponse listUsingQuery(RequestParams<PmsRoom> requestParams) {
        List<PmsRoom> list = pmsRoomService.getUsingQuery(requestParams);
        Page<PmsRoom> page = MiscUtils.toPage(list, requestParams.getPageable());
        return Responses.PageResponse.of(page);
    }*//*

    @RequestMapping( method = {RequestMethod.PUT}, produces = APPLICATION_JSON)
    public ApiResponse saveUsingQuery(@RequestBody List<PmsRoom> request) {
        pmsRoomService.saveUsingQuery(request);
        return ok();
    }*/

/*
    @RequestMapping(method = RequestMethod.POST, produces = APPLICATION_JSON)
    public ApiResponse saveUsingQueryDsl(@RequestBody PmsRoom request) {
        pmsRoomService.saveUsingQueryDsl(request);
    return ok();
    }

    @RequestMapping( method = RequestMethod.DELETE, produces = APPLICATION_JSON)
    public ApiResponse deleteUsingQueryDsl(@RequestParam List<Long> ids) {
        pmsRoomService.deleteUsingQueryDsl(ids);
        return ok();
    }
*/



