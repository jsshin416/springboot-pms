<%@ page import="com.chequer.axboot.core.utils.RequestUtils" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ax" tagdir="/WEB-INF/tags" %>
<%
    RequestUtils requestUtils = RequestUtils.of(request);
    request.setAttribute("id", requestUtils.getString("id"));
    request.setAttribute("modalTyp", requestUtils.getString("modalTyp"));
%>
            <ax:set key="page_auto_height" value="true" />

            <ax:layout name="modal">
                <jsp:attribute name="script">
                    <ax:script-lang key="ax.script" />
                    <script>
                        var modalParams = {id: "${id}", modalTyp: "${modalTyp}"};
                    </script>
                    <script type="text/javascript" src="<c:url value='/assets/js/view/pms/book/state-modal.js' />"></script>
                    <ax:script-lang key="ax.script" var="LANG" />
                    <ax:script-lang key="ax.base" var="COL" />
                </jsp:attribute>

                <jsp:body>
                    <div data-fit-height-aside="form-view-01">
                         <form name="formView01" class="js-form">
                                <div data-ax-tr class="left">
                                    <div style="font-weight: bold; display: inline-block; margin: 0px 5px">예약번호 : <input
                                            style="border: 0; background: none; width: 100px;" data-ax-path="rsvNum" name="rsvNum" class="js-rsvNum"
                                            readonly="readonly"></div>
                                    <div style="width: 200px; display: inline-block;">
                                        <div style="display: inline-block">
                                            <c:if test ="${modalTyp = All}">
                                            <ax:common-code groupCd="STAY_STATUS" id="sttusCd" dataPath ="sttusCd" clazz="js-sttusCd "/>
                                            </c:if>
                                            <c:if test ="${modalTyp = ChkIn || modalTyp = InHouse}">
                                                <div style="display: inline-block ">
                                                    <input
                                                    style="border: 0; background: none; width: 100px;" data-ax-path="roomNum" name="roomNum" class="js-roomNum"
                                                    readonly="readonly">
                                                   </div>
                                                    <button type="button" class="btn btn-default" data-grid-view-01-btn="room"><i class="cqc-circle-with-plus"></i> 객실배정</button>
                                            </c:if>
                                            <c:if test ="${modalTyp = ChkOut}">
                                                <div style="display: inline-block ">
                                                    <input
                                                    style="border: 0; background: none; width: 100px;" data-ax-path="roomNum" name="roomNum" class="js-roomNum"
                                                    readonly="readonly">
                                                   </div>
                                            </c:if>
                                        </div>
                                        </div>
                                    <div style="float: right; margin-top: 10px; padding-right: 10px;">
                                        <span style="color: red;">* </span>표시는 필수 항목 체크 부분
                                    </div>
                                </div>
                                <ax:tbl clazz="ax-form-tbl" minWidth="500px">
                                    <div data-ax-tr>
                                        <div data-ax-td style="width:35%">
                                            <div data-ax-td-label style="width:120px"><span class="def-ask-s" style="color: red;">*</span>도착일</div>
                                            <div data-ax-td-wrap>

                                                <div class="input-group" data-ax5picker="date">
                                                    <input type="text" name="arrDt" id="arrDt" data-ax-path="arrDt"
                                                        class="js-arrDt form-control" data-ax5formatter="date"
                                                        placeholder="YYYY-MM-DD">
                                                    <span class="input-group-addon"><i class="cqc-calendar"></i></span>
                                                </div>

                                            </div>
                                        </div>
                                        <div data-ax-td style="width:30%">
                                            <div data-ax-td-label style="width:120px"><span class="def-ask-s" style="color: red;">*</span>숙박수</div>
                                            <div data-ax-td-wrap>
                                                <input type="text" name="nightCnt" id="nightCnt" data-ax-path="nightCnt"
                                                    class="js-nightCnt form-control">

                                            </div>
                                        </div>

                                        <div data-ax-td style="width:35%">
                                            <div data-ax-td-label style="width:120px"> <span class="def-ask-s" style="color: red;">*</span>출발일</div>
                                            <div data-ax-td-wrap>
                                                <div class="input-group" data-ax5picker="date">
                                                    <input type="text" name="depDt" id="depDt" data-ax-path="depDt"
                                                        class="js-depDt form-control" data-ax5formatter="date"
                                                        placeholder="YYYY-MM-DD">
                                                    <span class="input-group-addon"><i class="cqc-calendar"></i></span>
                                                </div>


                                            </div>
                                        </div>
                                    </div>
                                    <div data-ax-tr >
                                        <div data-ax-td style="width:35%">
                                            <div data-ax-td-label style="width:120px"><span class="def-ask-s" style="color: red;">*</span>객실타입</div>
                                            <div data-ax-td-wrap>
                                                <ax:common-code groupCd="ROOM_TYPE" id="roomType" dataPath="roomTypCd" clazz="js-roomTypCd "
                                                    emptyText="전체" />
                                            </div>
                                        </div>
                                        <div data-ax-td style="width:30%">
                                            <div data-ax-td-label style="width:120px"><span class="def-ask-s" style="color: red;">*</span>성인수</div>
                                            <div data-ax-td-wrap>
                                                <input type="text" name="adultCnt" id="adultCnt" data-ax-path="adultCnt" min="1"
                                                    class="js-adultCnt form-control">
                                            </div>
                                        </div>
                                        <div data-ax-td style="width:35%">
                                            <div data-ax-td-label style="width:120px"><span class="def-ask-s" style="color: red;">*</span>아동수</div>
                                            <div data-ax-td-wrap>
                                                <input type="text" name="chldCnt" id="chldCnt" data-ax-path="chldCnt" min="0"
                                                    class="js-chldCnt form-control">
                                            </div>
                                        </div>
                                    </div>


                                    <div data-ax-tr>
                                        <div data-ax-td style="width:100%">
                                            <div data-ax-td-label style="width:120px;">투숙객
                                                <button type="button" class="btn btn-default" id="sch_m"
                                                data-form-view-01-btn="guestModal">
                                                    <i class="cqc-magnifier"></i>검색
                                                </button>
                                            </div>
                                            <div data-ax-td-wrap id = "guestId">
                                                <div data-ax-tr>
                                                    <div data-ax-td style="width:50%">
                                                        <div data-ax-td-label
                                                            style="width:120px; background-color: #fff; background-image: none;">
                                                            이름</div>
                                                        <div data-ax-td-wrap>
                                                            <input type="text" name="guestNm" id="guestNm"
                                                                data-ax-path="guestNm"
                                                                class="js-guestNm form-control"  />
                                                        </div>
                                                    </div>
                                                    <div data-ax-td style="width:50%">
                                                        <div data-ax-td-label
                                                            style="width:120px; background-color: #fff; background-image: none;">
                                                            영문</div>
                                                        <div data-ax-td-wrap>
                                                            <input type="text" name="guestNmEng" id="guestNmEng"
                                                            data-ax-path="guestNmEng" class="js-guestNmEng form-control"  />
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
                                                                data-ax-path="guestTel" data-ax5formatter="guestTel"
                                                                class="js-guestTel form-control"
                                                                placeholder="000-000-000"  />
                                                        </div>
                                                    </div>
                                                    <div data-ax-td style="width:50%">
                                                        <div data-ax-td-label
                                                            style="width:120px; background-color: #fff; background-image: none;">
                                                            이메일</div>
                                                        <div data-ax-td-wrap>
                                                            <input type="text" name="email" id="email"
                                                                data-ax-path="email" class="js-email form-control"  />
                                                        </div>
                                                    </div>
                                                </div>
                                                <div data-ax-tr>
                                                    <div data-ax-td style="width:50%">
                                                        <div data-ax-td-label
                                                            style="width:120px; background-color: #fff; background-image: none;">
                                                            언어</div>
                                                        <div data-ax-td-wrap>
                                                            <ax:common-code groupCd="LANG" id="js-langCd"
                                                                dataPath="langCd" clazz="js-langCd" emptyText="전체"  />
                                                        </div>
                                                    </div>
                                                    <div data-ax-td style="width:40%">
                                                        <div data-ax-td-label
                                                            style="width:120px; background-color: #fff; background-image: none;">
                                                            생년월일</div>
                                                        <div data-ax-td-wrap>
                                                            <div class="input-group" data-ax5picker="date">
                                                                <input type="text" name="brth" id="brth"
                                                                    data-ax-path="brth" class="js-brth form-control"
                                                                    placeholder="YYYY-MM-DD" data-ax5formatter="date"  />
                                                                <span class="input-group-addon"><i
                                                                        class="cqc-calendar"></i></span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <span>
                                                        <label><input type="radio" data-ax-path="gender" name="gender"
                                                                value="M" >남
                                                        </label>
                                                        <label><input type="radio" data-ax-path="gender" name="gender"
                                                                value="F" >여
                                                        </label>
                                                    </span>

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
                                                            <span class="def-ask-s" style="color: red;">*</span>판매유형</div>
                                                        <div data-ax-td-wrap>
                                                            <ax:common-code groupCd="SALE_TYPE" id="saleTypCd"
                                                                dataPath="saleTypCd" clazz="js-saleTypCd"
                                                                emptyText="전체" />
                                                        </div>
                                                    </div>
                                                    <div data-ax-td style="width:50%">
                                                        <div data-ax-td-label
                                                            style="width:120px; background-color: #fff; background-image: none;">
                                                            <span class="def-ask-s" style="color: red;">*</span>예약경로</div>
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
                                                            <ax:common-code groupCd="PAY_METHOD" id="payCd"
                                                                dataPath="payCd" clazz="js-payCd" emptyText="전체" />
                                                        </div>
                                                    </div>
                                                    <div data-ax-td style="width:50%">
                                                        <div data-ax-td-label
                                                            style="width:120px; background-color: #fff; background-image: none;">
                                                            <span class="def-ask-s" style="color: red;">*</span>선수금 여부</div>
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
                                                                data-ax-path="salePrc" class="js-salePrc form-control"
                                                                data-ax5formatter="money" />
                                                        </div>
                                                    </div>
                                                    <div data-ax-td style="width:50%">
                                                        <div data-ax-td-label
                                                            style="width:120px; background-color: #fff; background-image: none;">
                                                            서비스 금액</div>
                                                        <div data-ax-td-wrap>
                                                            <input type="text" name="svcPrc" id="svcPrc"
                                                                data-ax-path="svcPrc" class="js-svcPrc form-control"
                                                                data-ax5formatter="money" />
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
                                                        <div class="ax-button-group"
                                                            data-fit-height-aside="grid-view-01">
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
                                                            data-fit-height-content="grid-view-01"
                                                            style="height: 300px;">
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                </ax:tbl>
                            </form>
                        
                    </div>
                   
                    <div class="ax-button-group">
                        <div class="auto" style="padding-left:380px;">
                            <button type="button" class="btn btn-default" data-page-btn="save">저장 </button>
                            <button type="button" class="btn btn-default" data-page-btn="close">닫기</button>
                        </div>
                    </div>
                </jsp:body>

            </ax:layout>