<%@ page contentType="text/html; charset=UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="ax" tagdir="/WEB-INF/tags" %>

          
           
            <ax:set key="page_auto_height" value="true" />

            <ax:layout name="modal">
                <jsp:attribute name="script">
                    <ax:script-lang key="ax.script" />
                    <script type="text/javascript" src="<c:url value='/assets/js/view/pms/book/booking.js' />"></script>
                    <ax:script-lang key="ax.script" var="LANG" />
                    <ax:script-lang key="ax.base" var="COL" />   
                </jsp:attribute>
            

                <jsp:body>


                    <div data-fit-height-aside="form-view-01">
                        <div class="ax-button-group">
                            <div class="left">
                                <h1><i class="cqc-news"></i> 예약등록 </h1>
                            </div>
                            <div class="right">
                                <a style="padding-right:10px;">*표시는 필수항목 체크 부분</a>
                                <button type="button" class="btn btn-default" data-page-btn="save">저장</button>
                                <button type="button" class="btn btn-default" data-form-view-01-btn="form-clear">
                                    <i class="cqc-erase"></i>
                                    <ax:lang id="ax.admin.clear" />
                                </button>
                            </div>
                        </div>
                        <div><div class="left" name= rsvNum>
                            <h4> 예약번호: </h4> </div></div>

                        <form name="formView01" class="js-form">
                            <ax:tbl clazz="ax-form-tbl" minWidth="500px">
                                <ax:tr labelWidth="120px">
                                    <ax:td label="*도착일" width="35%">
                                        <div class="input-group" data-ax5picker="date">
                                            <input type="text" name="arrDt" id="arrDt" data-ax-path="arrDt"
                                                class="js-arrDt form-control" data-ax5formatter="arrDt" placeholder="YYYY-MM-DD" />
                                            <span class="input-group-addon"><i class="cqc-calendar"></i></span>
                                        </div>
                                    </ax:td>
                                    <ax:td label="*숙박수" width="30%">
                                        <input type="text" name="nightCnt" id="nightCnt" data-ax-path=""
                                            class="js-nightCnt form-control" />

                                    </ax:td>
                                    <ax:td label="*출발일" width="35%">
                                        <div class="input-group" data-ax5picker="date">
                                            <input type="text" name="depDt" id="depDt" data-ax-path="depDt"
                                                class="js-depDt form-control" data-ax5formatter="depDt" placeholder="YYYY-MM-DD" />
                                            <span class="input-group-addon"><i class="cqc-calendar"></i></span>
                                        </div>

                                    </ax:td>
                                </ax:tr>

                                <ax:tr labelWidth="120px">
                                    <ax:td label="객실타입" width="35%">
                                        <ax:common-code groupCd="ROOM_TYPE" id="roomType" dataPath="roomTypCd"
                                            clazz="js-roomTypCd " emptyText="전체" />
                                    </ax:td>
                                    <ax:td label="성인수" width="30%">
                                        <input type="text" name="adultCnt" id="adultCnt" data-ax-path="adultCnt"
                                            class="js-adultCnt form-control" />

                                    </ax:td>
                                    <ax:td label="아동수" width="35%">
                                        <input type="text" name="chldCnt" id="chldCnt" data-ax-path="chldCnt"
                                            class="js-chldCnt form-control" />

                                    </ax:td>
                                </ax:tr>


                                <div data-ax-tr>
                                    <div data-ax-td style="width:100%">
                                        <div data-ax-td-label style="width:120px;">투숙객
                                            <button type="button" class="btn btn-default" id="sch_m" data-searchview-btn="modal">
                                                <i class="cqc-magnifier"></i>검색
                                            </button>
                                        </div>
                                        <div data-ax-td-wrap>
                                            <div data-ax-tr >
                                                <div data-ax-td style="width:50%">
                                                    <div data-ax-td-label
                                                        style="width:120px; background-color: #fff; background-image: none;">
                                                        이름</div>
                                                    <div data-ax-td-wrap>
                                                        <input type="text" name="guestNm" id="guestNm"
                                                            data-ax-path="guestNm" class="js-guestNm form-control" />
                                                    </div>
                                                </div>
                                                <div data-ax-td style="width:50%">
                                                    <div data-ax-td-label
                                                        style="width:120px; background-color: #fff; background-image: none;">
                                                        영문</div>
                                                    <div data-ax-td-wrap>
                                                        <input type="text" naem="guestNmEng" id="guestNmEng"
                                                            data-ax-path="guestNmEng"
                                                            class="js-guestNmEng form-control" />
                                                    </div>
                                                </div>
                                            </div>
                                            <div data-ax-tr>
                                                <div data-ax-td style="width:50%">
                                                    <div data-ax-td-label
                                                        style="width:120px; background-color: #fff; background-image: none;">
                                                        연락처</div>
                                                    <div data-ax-td-wrap>
                                                        <input type="text" name="guestTel" id="guestTel"
                                                            data-ax-path="guestTel" data-ax5formatter="guestTel" class="js-guestTel form-control" placeholder="000-000-000" />
                                                    </div>
                                                </div>
                                                <div data-ax-td style="width:50%">
                                                    <div data-ax-td-label
                                                        style="width:120px; background-color: #fff; background-image: none;">
                                                        이메일</div>
                                                    <div data-ax-td-wrap>
                                                        <input type="text" name="email" id="email" data-ax-path="email"
                                                            class="js-email form-control" />
                                                    </div>
                                                </div>
                                            </div>
                                            <div data-ax-tr>
                                                <div data-ax-td style="width:50%">
                                                    <div data-ax-td-label
                                                        style="width:120px; background-color: #fff; background-image: none;">
                                                        언어</div>
                                                    <div data-ax-td-wrap>
                                                        <ax:common-code groupCd="LANG" id="js-langCd" dataPath="langCd"
                                                            clazz="js-langCd" emptyText="전체" />
                                                    </div>
                                                </div>
                                                <div data-ax-td style="width:50%">
                                                    <div data-ax-td-label
                                                        style="width:120px; background-color: #fff; background-image: none;">
                                                        생년월일</div>
                                                    <div data-ax-td-wrap>
                                                        <div data-ax-td>
                                                            <div class="input-group" data-ax5picker="date">
                                                                <input type="text" name="brth" id="brth"
                                                                    data-ax-path="brth" class="js-brth form-control"
                                                                    placeholder="YYYY-MM-DD" />
                                                                <span class="input-group-addon"><i
                                                                        class="cqc-calendar"></i></span>
                                                            </div>
                                                        </div>
                                                        <div data-ax-td-wrap>
                                                            남 <input type="radio" name="gender" value="남" checked>
                                                            여 <input type="radio" name="gender" value="여">

                                                        </div>
                                                    </div>


                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div data-ax-tr>
                                    <div data-ax-td style="width:100%">
                                        <div data-ax-td-label style="width:120px;">판매/결제</div>
                                        <div data-ax-td-wrap>
                                            <div data-ax-tr>
                                                <div data-ax-td style="width:50%">
                                                    <div data-ax-td-label
                                                        style="width:120px; background-color: #fff; background-image: none;">
                                                        판매유형</div>
                                                    <div data-ax-td-wrap>
                                                        <ax:common-code groupCd="SALE_TYPE" id="saleTypCd"
                                                            dataPath="saleTypCd" clazz="js-saleTypCd" emptyText="전체" />
                                                    </div>
                                                </div>
                                                <div data-ax-td style="width:50%">
                                                    <div data-ax-td-label
                                                        style="width:120px; background-color: #fff; background-image: none;">
                                                        예약경로</div>
                                                    <div data-ax-td-wrap>
                                                        <ax:common-code groupCd="RESERVATION_ROUTE" id="srcCd"
                                                            dataPath="srcCd" clazz="js-srcCd" emptyText="전체" />
                                                    </div>
                                                </div>
                                            </div>
                                            <div data-ax-tr>
                                                <div data-ax-td style="width:50%">
                                                    <div data-ax-td-label
                                                        style="width:120px; background-color: #fff; background-image: none;">
                                                        결제방법</div>
                                                    <div data-ax-td-wrap>
                                                        <ax:common-code groupCd="PAY_METHOD" id="payCd" dataPath="payCd"
                                                            clazz="js-payCd" emptyText="전체" />
                                                    </div>
                                                </div>
                                                <div data-ax-td style="width:50%">
                                                    <div data-ax-td-label
                                                        style="width:120px; background-color: #fff; background-image: none;">
                                                        선수금 여부</div>
                                                    <div data-ax-td-wrap>
                                                        <input type="checkbox" name="advnYn" id="advnYn"
                                                            data-ax-path="advnYn" value="Y" class="js-advnYn" />
                                                    </div>
                                                </div>
                                            </div>
                                            <div data-ax-tr>
                                                <div data-ax-td style="width:50%">
                                                    <div data-ax-td-label
                                                        style="width:120px; background-color: #fff; background-image: none;">
                                                        결제금액</div>
                                                    <div data-ax-td-wrap>
                                                        <input type="text" name="salePrc" id="salePrc"
                                                            data-ax-path="salePrc" class="js-salePrc form-control" />
                                                    </div>
                                                </div>
                                                <div data-ax-td style="width:50%">
                                                    <div data-ax-td-label
                                                        style="width:120px; background-color: #fff; background-image: none;">
                                                        서비스 금액</div>
                                                    <div data-ax-td-wrap>
                                                        <input type="text" name="svcPrc" id="svcPrc"
                                                            data-ax-path="svcPrc" class="js-svcPrc form-control" />
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div data-ax-tr>
                                    <div data-ax-td style="width:100%">
                                        <div data-ax-td-label style="width:120px;">비고</div>
                                        <div data-ax-td-wrap>
                                            <div data-ax-tr>
                                                <div data-ax-td style="width:100%">
                                                    <!-- 목록 -->
                                                    <div class="ax-button-group" data-fit-height-aside="grid-view-01">
                                                        <div class="left">
                                                            <h2><i class="cqc-list"></i>
                                                                투숙메모 </h2>
                                                        </div>
                                                        <div class="right">
                                                            <button type="button" class="btn btn-default"
                                                                data-grid-view-01-btn="add"><i
                                                                    class="cqc-circle-with-plus"></i> 추가</button>
                                                            <button type="button" class="btn btn-default"
                                                                data-grid-view-01-btn="delete"><i
                                                                    class="cqc-circle-with-minus"></i> 삭제</button>
                                                        </div>
                                                    </div>
                                                    <div data-ax5grid="grid-view-01"
                                                        data-fit-height-content="grid-view-01" style="height: 300px;">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                            </ax:tbl>
                        </form>
                    </div>
                </jsp:body>
            </ax:layout>