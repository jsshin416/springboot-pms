<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ax" tagdir="/WEB-INF/tags" %>

<ax:set key="title" value="${pageName}"/>
<ax:set key="page_desc" value="${PAGE_REMARK}"/>
<ax:set key="page_auto_height" value="true"/>

<ax:layout name="base">
    <jsp:attribute name="script">
        <script type="text/javascript" src="<c:url value='/assets/js/view/pms/report/sales.js' />"></script>
    </jsp:attribute>
    <jsp:body>

        <form name="excelForm" class="js-grid" method="post">
            <div data-page-buttons="">
                <div class="button-warp">
                        <button type="button" class="btn btn-default" data-page-btn="search"><i class="cqc-magnifier"></i> 검색 </button>
                        <button type="button" class="btn btn-default" data-page-btn="excel"><i class="cqc-file-excel-o"></i> 엑셀 다운로드</button>
                </div>
            </div>


        <div role="page-header">
            <ax:form name="searchView0">
                <ax:tbl clazz="ax-search-tbl" minWidth="500px">
                    <ax:tr>
                        <ax:td label='조회날짜' width="300px">
                            <button type="button" class="btn btn-default" data-page-btn=""> 오늘 </button>
                            <button type="button" class="btn btn-default" data-page-btn=""> 어제</button>
                            <button type="button" class="btn btn-default" data-page-btn=""> 3일 </button>
                            <button type="button" class="btn btn-default" data-page-btn=""> 7일</button>
                            <button type="button" class="btn btn-default" data-page-btn=""> 1개월 </button>
                            <button type="button" class="btn btn-default" data-page-btn=""> 3개월</button>
                            <button type="button" class="btn btn-default" data-page-btn=""> 6개월 </button>
                            <button type="button" class="btn btn-default" data-page-btn=""> 1년</button>
                        </ax:td>
                        <div style="width: 200px; display: inline-block; margin: 5px;">
                            <div class="input-group" data-ax5picker="rsvDt">
                                <input type="text" name="rsvStDt" class="js-rsvDtSt form-control" placeholder="YYYY-MM-DD" data-ax5formatter="date">
                                <span class="input-group-addon">~</span>
                                <input type="text"  name="rsvEndDt"class="js-rsvDtEnd form-control" placeholder="YYYY-MM-DD" data-ax5formatter="date">
                                <span class="input-group-addon"><i class="cqc-calendar"></i></span>
                            </div>                       
                        </div>
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
                        <h2><i class="cqc-list"></i>
                           보고서 </h2>
                    </div>
                </div>
                <div data-ax5grid="grid-view-01" data-fit-height-content="grid-view-01" style="height: 300px;"></div>

            </ax:split-panel>
        </ax:split-layout>

    </jsp:body>
</ax:layout>