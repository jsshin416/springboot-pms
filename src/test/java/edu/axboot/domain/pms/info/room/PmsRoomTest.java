package edu.axboot.domain.pms.info.room;


import edu.axboot.AXBootApplication;
import edu.axboot.controllers.dto.PmsRoomListResponseDto;
import edu.axboot.controllers.dto.PmsRoomSaveRequestDto;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;



@RunWith(SpringRunner.class)
@SpringBootTest(classes = AXBootApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PmsRoomTest {

    @Autowired
    private PmsRoomService pmsRoomService;

    public static List<Long> ids;

    @Test
    public void test1_객실_저장하기() {
        //given
        List<PmsRoomSaveRequestDto> dtos = new ArrayList<PmsRoomSaveRequestDto>();
        dtos.add(PmsRoomSaveRequestDto.builder()
                .id(null)
                .roomNum("001")
                .roomTypCd("AA")
                .dndYn("N")
                .ebYn("N")
                .__created__(true)
                .__modified__(false)
                .__deleted__(false)
                .build());
        //when
        ids = pmsRoomService.save(dtos);
        //then
        assertTrue(ids.size() == dtos.size());
    }
    @Test
    public void test2_객실_불러오기() {
        //given
        String roomType = "AA";

        //when
        List<PmsRoomListResponseDto> result = pmsRoomService.findByL(roomType);

        //then
        assertTrue(result.size() > 0);
    }
    @Test
    public void test3_객실_수정하기() {
        //given
        List<PmsRoomSaveRequestDto> dtos = new ArrayList<PmsRoomSaveRequestDto>();
        for (Long id: ids) {
            dtos.add(PmsRoomSaveRequestDto.builder()
                    .id(id)
                    .roomNum("000")
                    .roomTypCd("BB")
                    .dndYn("Y")
                    .ebYn("Y")
                    .__created__(false)
                    .__modified__(true)
                    .__deleted__(false)
                    .build());
        }

        //when
        ids = pmsRoomService.save(dtos);

        //then
        assertTrue(ids.size() == dtos.size());
    }

    @Test
    public void test4_객실_삭제하기() {
        //given
        List<PmsRoomSaveRequestDto> dtos = new ArrayList<PmsRoomSaveRequestDto>();
        for (Long id: ids) {
            dtos.add(PmsRoomSaveRequestDto.builder()
                    .id(id)
                    .roomNum("000")
                    .roomTypCd("BB")
                    .dndYn("N")
                    .ebYn("N")
                    .__created__(false)
                    .__modified__(false)
                    .__deleted__(true)
                    .build());
        }

        //when
        ids = pmsRoomService.save(dtos);

        //then
        assertTrue(ids.size() == dtos.size());
    }

}
