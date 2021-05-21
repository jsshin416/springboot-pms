package edu.axboot.controllers;

import com.chequer.axboot.core.controllers.BaseController;
import edu.axboot.controllers.dto.BookingSaveRequestDto;
import edu.axboot.domain.pms.book.memo.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import com.chequer.axboot.core.api.response.ApiResponse;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import edu.axboot.domain.pms.book.booking.BookingService;

import javax.inject.Inject;

@RequiredArgsConstructor
@Controller
@RequestMapping(value = "/api/v1/booking")
public class BookingController extends BaseController {

    @Inject
    private final BookingService bookingService;
    //private final GuestService guestService;
    //private final MemoService memoService;


    @RequestMapping(method = RequestMethod.POST, produces = APPLICATION_JSON)
    public ApiResponse save(@RequestBody BookingSaveRequestDto request){
        bookingService.save(request);
        return ok();
    }

    /*@RequestMapping(method = RequestMethod.POST, produces = APPLICATION_JSON)
    public ApiResponse save(@RequestBody GuestSaveRequestDto request){
        guestService.save(request);
        return ok();
    }*/
    /*@RequestMapping(method = RequestMethod.POST, produces = APPLICATION_JSON)
    public ApiResponse memoSave(@RequestBody MemoSaveRequestDto request){
        memoService.save(request);
        return ok();
    }*/



}