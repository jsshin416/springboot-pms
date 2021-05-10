package edu.axboot.domain.pms.room;


import edu.axboot.AXBootApplication;
import lombok.SneakyThrows;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@Log
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AXBootApplication.class)

public class PmsRoomTest {
    private QPmsRoom qPmsRoom;

    @Autowired
    private PmsRoomService pmsRoomService;

    @SneakyThrows   //명시적인 예외처리 생략
    @Test
    public void testsavequery(){
       /* //given

        List<PmsRoom> pmsRoomList = new ArrayList<>();

        //when

        //then*/

        //given



    }

}
