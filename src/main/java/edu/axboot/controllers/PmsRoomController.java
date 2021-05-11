package edu.axboot.controllers;

import com.chequer.axboot.core.api.response.Responses;
import com.chequer.axboot.core.controllers.BaseController;
import com.chequer.axboot.core.parameter.RequestParams;
import edu.axboot.controllers.dto.PmsRoomListDto;
import edu.axboot.controllers.dto.PmsRoomSaveDto;
import edu.axboot.controllers.dto.PmsRoomUpdateDto;
import edu.axboot.utils.MiscUtils;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import com.chequer.axboot.core.api.response.ApiResponse;
import org.springframework.web.bind.annotation.*;
import edu.axboot.domain.pms.room.PmsRoom;
import edu.axboot.domain.pms.room.PmsRoomService;

import javax.inject.Inject;
import java.util.List;

@RequiredArgsConstructor
@RestController
@Controller
//@RequestMapping(value = "/api/v1/pmsroom")
public class PmsRoomController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(PmsRoomController.class);

    @Inject
    private final PmsRoomService pmsRoomService;
    //Dto----------------------------------------------------------------------
    @PostMapping("/api/v1/pmsroom/dto")
    public ApiResponse save(@RequestBody PmsRoomSaveDto requestDto){
        pmsRoomService.saveDto(requestDto);
        return ok();
    }
    @PutMapping("/api/v1/pmsroom/dto/{id}")
    public ApiResponse update(@PathVariable Long id, @RequestBody PmsRoomUpdateDto requestDto) {
        pmsRoomService.update(id, requestDto);
        return ok();
    }
    @GetMapping("/api/v1/pmsroom/dto")
     public Responses.ListResponse list(String roomNum, String roomTypCd, String dndYn,
                                        String ebYn, String roomSttusCd,
                                        String clnSttusCd, String svcSttusCd){
             List<PmsRoomListDto> list = pmsRoomService.findByAll( roomNum,  roomTypCd,  dndYn,
                     ebYn,roomSttusCd,clnSttusCd,  svcSttusCd);
            return Responses.ListResponse.of(list);
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



