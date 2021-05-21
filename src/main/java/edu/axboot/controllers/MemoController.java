package edu.axboot.controllers;

import com.chequer.axboot.core.controllers.BaseController;
import org.springframework.stereotype.Controller;
import com.chequer.axboot.core.api.response.ApiResponse;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import edu.axboot.domain.pms.book.memo.MemoService;

import javax.inject.Inject;

@Controller
@RequestMapping(value = "/api/v1/memo")
public class MemoController extends BaseController {

    @Inject
    private MemoService memoService;



}