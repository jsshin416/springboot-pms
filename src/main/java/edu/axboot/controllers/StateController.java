package edu.axboot.controllers;

import com.chequer.axboot.core.api.response.ApiResponse;
import com.chequer.axboot.core.controllers.BaseController;
import edu.axboot.controllers.dto.BookingSaveRequestDto;
import edu.axboot.domain.pms.book.booking.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;

@RequiredArgsConstructor
@Controller
@RequestMapping(value = "/api/v1/state")
public class StateController extends BaseController {




}