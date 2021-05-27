<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ax" tagdir="/WEB-INF/tags" %>

<ax:set key="title" value="${pageName}"/>
<ax:set key="page_desc" value="${PAGE_REMARK}"/>
<ax:set key="page_auto_height" value="true"/>

<ax:layout name="base">
    <jsp:attribute name="script">
        <script type="text/javascript" src="<c:url value='/assets/js/view/pms/book/state.js' />"></script>
    </jsp:attribute>
    <jsp:body>

        <div data-page-buttons="">
            <div class="button-warp">
                    <button type="button" class="btn btn-default" data-page-btn="search"><i class="cqc-magnifier"></i> 검색 </button>
                    <button type="button" class="btn btn-default" data-search-view-btn="form-clear"> 검색선택 초기화</button>
                    <button type="button" class="btn btn-default" data-page-btn="excel"><i class="cqc-file-excel-o"></i> 엑셀 다운로드</button>
            </div>
        </div>


        <div role="page-header">
            <ax:form name="searchView0">
                <ax:tbl clazz="ax-search-tbl" minWidth="500px">
                    <ax:tr>
                        <ax:td label='검색어' width="350px">
                            <input type="text" class="js-filter form-control" name ="filter"/>
                        </ax:td>
                        <ax:td label='예약번호' width="350px">
                            <input type="text" class="js-rsvNum form-control" />
                        </ax:td>
                        <ax:td label='예약일' width="350px">
                            <div class="input-group" data-ax5picker="rsvDt">
                                <input type="text" name="rsvStDt" class="js-rsvDtSt form-control" placeholder="YYYY-MM-DD" data-ax5formatter="date">
                                <span class="input-group-addon">~</span>
                                <input type="text"  name="rsvEndDt"class="js-rsvDtEnd form-control" placeholder="YYYY-MM-DD" data-ax5formatter="date">
                                <span class="input-group-addon"><i class="cqc-calendar"></i></span>
                            </div>                       
                         </ax:td>
                    </ax:tr>
                    <ax:tr>
                        <ax:td label='객실타입' width="350px">
                            <ax:common-code groupCd="ROOM_TYPE" id="roomType" dataPath="roomTypCd"
                            clazz="js-roomTypCd " emptyText="전체" />
                    </ax:td>
                        <ax:td label='도착일' width="350px">
                            <div class="input-group" data-ax5picker="arrDt">
                                <input type="text" name ="arrStDt" class="js-arrStDt form-control" placeholder="YYYY-MM-DD" data-ax5formatter="date">
                                <span class="input-group-addon">~</span>
                                <input type="text" name ="arrEndDt" class="js-arrEndDt form-control" placeholder="YYYY-MM-DD" data-ax5formatter="date">
                                <span class="input-group-addon"><i class="cqc-calendar"></i></span>
                            </div>                             </ax:td>
                        <ax:td label='출발일' width="350px">
                            <div class="input-group" data-ax5picker="depDt">
                                <input type="text" name ="depStDt" class="js-depStDt form-control"placeholder="YYYY-MM-DD" data-ax5formatter="date">
                                <span class="input-group-addon">~</span>
                                <input type="text"  name ="depEndDt" class="js-depEndDt form-control"placeholder="YYYY-MM-DD" data-ax5formatter="date">
                                <span class="input-group-addon"><i class="cqc-calendar"></i></span>
                            </div>
                        </ax:td>
                    </ax:tr>
                    <ax:tr>
                        <ax:td label='상태' width="1000px">
                            <label class="checkbox-inline"><input type="checkbox" class="js-sttusCd-all" value="">전체 </label>
                            <ax:common-code groupCd="STAY_STATUS" name="sttusCd"  type ="checkbox"/>
                         </ax:td>
                    </ax:tr>
                </ax:tbl>
            </ax:form>
            <div class="H10"></div>
        </div>

        <ax:split-layout name="ax1" orientation="horizontal">
            <ax:split-panel width="*" style="">

                <!-- 목록 -->
                <div class="ax-button-group" data-fit-height-aside="grid-view-01">
                    <div class="left">
                        <h2><i class="cqc-list"></i>예약목록 </h2>
                    </div>
                    <div class="right" style="width:200px">
                        <div style="display: inline-block ">
                        <ax:common-code groupCd="STAY_STATUS"  id="sttusCd" clazz="js-sttusCd " /> 
                       </div>
                        <button type="button" class="btn btn-default" data-grid-view-01-btn="sstus"><i class="cqc-circle-with-plus"></i> 상태변경</button>
                    </div>
                </div>
                <div data-ax5grid="grid-view-01" data-fit-height-content="grid-view-01" style="height: 300px;"></div>

            </ax:split-panel>
        </ax:split-layout>

    </jsp:body>
</ax:layout>