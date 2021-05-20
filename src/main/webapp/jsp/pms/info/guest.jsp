<%@ page contentType="text/html; charset=UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="ax" tagdir="/WEB-INF/tags" %>

            <ax:set key="title" value="${pageName}" />
            <ax:set key="page_desc" value="${PAGE_REMARK}" />
            <ax:set key="page_auto_height" value="true" />

            <ax:layout name="base">
                <jsp:attribute name="script">
                    <script type="text/javascript" src="<c:url value='/assets/js/view/pms/info/guest.js' />"></script>
                </jsp:attribute>
                <jsp:body>

                    <ax:page-buttons></ax:page-buttons>


                    <div role="page-header">
                        <ax:form name="searchView0">
                            <ax:tbl clazz="ax-search-tbl" minWidth="500px">
                                <ax:tr>
                                    <ax:td label='이름' width="300px">
                                        <input type="text" class="js-guestNm form-control" />
                                    </ax:td>
                                    <ax:td label='전화번호' width="300px">
                                        <input type="text" class="js-guestTel form-control" />
                                    </ax:td>
                                    <ax:td label='이메일' width="300px">
                                        <input type="text" class="js-email form-control" />
                                    </ax:td>
                                </ax:tr>
                            </ax:tbl>
                        </ax:form>
                        <div class="H10"></div>
                    </div>

                    <ax:split-layout name="ax1" orientation="vertical">
                        <ax:split-panel width="600" style="padding-right: 10px;">
                            <!-- 목록 -->
                            <div class="ax-button-group" data-fit-height-aside="grid-view-01">
                                <div class="left">
                                    <h2><i class="cqc-list"></i>투숙객 목록
                                </div>

                            </div>
                            <div data-ax5grid="grid-view-01" data-fit-height-content="grid-view-01"
                                style="height: 300px;"></div>
                        </ax:split-panel>
                        <ax:splitter></ax:splitter>
                        <ax:split-panel width="*" style="padding-left: 10px;" scroll="scroll">
                            <!-- 폼 -->
                            <div class="ax-button-group" role="panel-header">
                                <div class="left">
                                    <h2><i class="cqc-news"></i>투숙객 정보
                                    </h2>
                                </div>

                            </div>
                            <form name="formView01" class ="js-form">
                                <ax:tbl clazz="ax-form-tbl" minWidth="500px">
                                    <ax:tr>
                                        <ax:td label="이름" width="300px">
                                            <input type="text" id="guestNm" data-ax-path="guestNm" class="js-guestNm form-control" />
                                        </ax:td>
                                        <ax:td label="영문" width="300px">
                                            <input type="text" id="guestNmEng" data-ax-path="guestNmEng"
                                                class="js-guestNmEng form-control"/>
                                        </ax:td>
                                    </ax:tr>
                                    <ax:tr>
                                        <ax:td label="연락처" width="300px">
                                            <input type="text" id="guestTel" data-ax-path="guestTel"
                                                title="연락처" class="js-guestTel form-control"/>
                                        </ax:td>
                                        <ax:td label="이메일" width="300px">
                                            <input type="text" id="email" data-ax-path="email" title="이메일" class="email form-control" />
                                        </ax:td>
                                    </ax:tr>
                                    <ax:tr>
                                        <ax:td label="언어" width="300px">
                                            <ax:common-code groupCd="LANG" id="langCd" dataPath="langCd"
                                                clazz="js-langCd  " emptyText="전체" />
                                        </ax:td>
                                        <ax:td label="생년월일" width="40%">
                                            <div class="input-group" data-ax5picker="date">
                                                <input type="text" name="brth" id="brth" data-ax-path="brth"
                                                    class="js-brth form-control" placeholder="YYYY-MM-DD"  data-ax5formatter="date" />
                                                <span class="input-group-addon"><i class="cqc-calendar"></i></span></div>
                                        </ax:td>
                                        <span>
                                            <label><input type="radio" id = "gender" data-ax-path="gender" name="gender" value="M">남
                                            </label>
                                            <label><input type="radio" id ="gender" data-ax-path="gender" name="gender" value="F">여
                                            </label>
                                        </span>

                                    </ax:tr>
                                    <ax:tr>
                                        <ax:td label="비고" width="100%">
                                            <textarea id="rmk" data-ax-path="rmk" class="form-control"></textarea>
                                        </ax:td>
                                    </ax:tr>
                                </ax:tbl>

                                <div class="H5"></div>
                                <div class="ax-button-group">
                                    <div class="left">
                                        <h2>
                                            <i class="cqc-list"></i>투숙객 이력
                                        </h2>
                                    </div>
                                </div>
                                <div data-ax5grid="grid-view-02" style="height: 300px;"></div>

                            </form>
                        </ax:split-panel>
                    </ax:split-layout>
                </jsp:body>
            </ax:layout>