package edu.axboot.controllers;

import com.chequer.axboot.core.api.response.Responses;
import com.chequer.axboot.core.controllers.BaseController;
import com.chequer.axboot.core.utils.DateUtils;
import com.chequer.axboot.core.utils.ExcelUtils;
import com.wordnik.swagger.annotations.ApiOperation;
import edu.axboot.controllers.dto.GuestListResponseDto;
import edu.axboot.controllers.dto.GuestResponseDto;
import edu.axboot.controllers.dto.GuestSaveRequestDto;
import edu.axboot.controllers.dto.PmsRoomListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import com.chequer.axboot.core.api.response.ApiResponse;
import org.springframework.web.bind.annotation.*;
import edu.axboot.domain.pms.info.guest.GuestService;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping(value = "/api/v1/guest")
public class GuestController extends BaseController {

    @Inject
    private final GuestService guestService;

    @RequestMapping( method = RequestMethod.GET, produces = APPLICATION_JSON)
    public Responses.ListResponse list( @RequestParam(value = "guestNm",required = false) String guestNm,
                                        @RequestParam(value = "guestTel", required = false) String guestTel,
                                       @RequestParam (value ="email",required = false) String email){
        List<GuestListResponseDto> list = guestService.findByL(guestNm,guestTel,email );
        return Responses.ListResponse.of(list);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = APPLICATION_JSON)
    public GuestResponseDto findById(@PathVariable Long id){
        return guestService.findById(id);
    }

    @RequestMapping(method = RequestMethod.POST, produces = APPLICATION_JSON)
    public ApiResponse save(@RequestBody GuestSaveRequestDto request){
        guestService.save(request);
        return ok();

    }
    @RequestMapping(method = {RequestMethod.PUT}, produces = APPLICATION_JSON)
    public ApiResponse update(@RequestBody GuestSaveRequestDto requestDto) {
        guestService.update(requestDto);
        return ok();
    }

    @ApiOperation(value = "엑셀다운로드", notes = "/resources/excel/pms_guest.xlsx")
    @RequestMapping(value = "/exceldown", method = {RequestMethod.POST}, produces = APPLICATION_JSON)
    public void excelDown(@RequestParam(value = "guestNm", required = false) String guestNm,
                          @RequestParam(value = "guestTel", required = false) String guestTel,
                          @RequestParam(value = "email", required = false) String email,
                          HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<GuestListResponseDto> list = guestService.findByL(guestNm, guestTel, email);
        ExcelUtils.renderExcel("/excel/pms_guest.xlsx", list, "Guest_" + DateUtils.getYyyyMMddHHmmssWithDate(), request, response);
    }

}