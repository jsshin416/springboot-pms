package edu.axboot.domain.pms.info.guest;

import edu.axboot.AXBootApplication;
import edu.axboot.controllers.dto.GuestListResponseDto;
import edu.axboot.controllers.dto.GuestSaveRequestDto;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AXBootApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class GuestTest {
    @Autowired
    private GuestService guestService;

    //public static List<Long> ids;
    public static long ids =0;
    @Test
    public void test1_게스트_저장(){
        //given
        GuestSaveRequestDto dtos = GuestSaveRequestDto.builder()
                .id(null)
                .guestNm("김여빈")
                .guestNmEng("song")
                .guestTel("1111")
                .gender("남")
                .build();
        //when
        ids = this.guestService.save(dtos);
        //then
        assertTrue(ids >0);

  }
//  @Test
//  public void test2_게스트_불러오기(){
//       //given
//      String guestNm="김여빈";
//      String
//
//      //when
//      List<GuestListResponseDto> result = guestService.findByL(guestNm);
//      //then
//  }





}
