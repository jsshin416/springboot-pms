<%@ page import="com.chequer.axboot.core.utils.RequestUtils" %>
    <%@ page contentType="text/html; charset=UTF-8" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
            <%@ taglib prefix="ax" tagdir="/WEB-INF/tags" %>

                <ax:set key="page_auto_height" value="true" />

                <ax:layout name="modal">
                    <jsp:attribute name="script">
                        <ax:script-lang key="ax.script" />
                        <script type="text/javascript" src="<c:url value='/assets/js/view/pms/book/book-modal.js' />"></script>
                            <ax:script-lang key="ax.script" var="LANG" />
                        <ax:script-lang key="ax.base" var="COL" />
                    </jsp:attribute>

                    <jsp:body>
                        <ax:split-panel width="730px" style="padding-left: 0;" scroll="scroll">
                            <div class="ax-button-group" role="panel-header">
                                <div class="left">
                                    <h2><i class="cqc-news"></i>  투숙객 목록  </h2>
                                </div>
                            </div>
                            <div data-ax5grid="grid-view-01" style="height: 300px;" ></div>
                           <!-- 폼 -->
                         
                           <form name="formView01" class ="js-form" style="padding-top: 10px;">
                                <ax:tbl clazz="ax-form-tbl" minWidth="500px" >
                                    <ax:tr>
                                        <ax:td label="이름" width="300px">
                                            <input type="text" id="guestNm" data-ax-path="guestNm" class="form-control"
                                                data-ax-validate="required"  readonly />
                                        </ax:td>
                                        <ax:td label="영문" width="300px">
                                            <input type="text" id="guestNmEng" data-ax-path="guestNmEng"
                                                class="form-control" data-ax-validate="required"   readonly/>
                                        </ax:td>
                                    </ax:tr>
                                    <ax:tr>
                                        <ax:td label="연락처" width="300px">
                                            <input type="text" id="guestTel" data-ax-path="guestTel"
                                                class="form-control"   readonly />
                                        </ax:td>
                                        <ax:td label="이메일" width="300px">
                                            <input type="text" id="email" data-ax-path="email" class="form-control"   readonly  />
                                        </ax:td>
                                    </ax:tr>
                                    <ax:tr>
                                        <ax:td label="언어" width="300px">
                                            <input type="text" id="langCd" data-ax-path="langCd" class="form-control"   readonly  />

                                        </ax:td>
                                        <ax:td label="생년월일" width="40%">
                                            <input type="text" id="brth" data-ax-path="brth" class="form-control"   readonly  />
                                        </ax:td>
                                        <span>
                                            <label><input type="radio" data-ax-path="gender" name="gender" value="M"  >남
                                            </label>
                                            <label><input type="radio" data-ax-path="gender" name="gender" value="F"  >여
                                            </label>
                                        </span>

                                    </ax:tr>
                                    <ax:tr>
                                        <ax:td label="비고" width="100%">
                                            <textarea id="rmk" data-ax-path="rmk" class="form-control"  readonly></textarea>
                                        </ax:td>
                                    </ax:tr>
                                </ax:tbl>
                           </form>
                           <div class="ax-button-group">
                            <div class="auto">
                                <button type="button" class="btn btn-default" data-page-btn="choice">선택 </button>
                                <button type="button" class="btn btn-default" data-page-btn="close">닫기</button>
                            </div>
                        </div>
                       </ax:split-panel>

                    </jsp:body>
                </ax:layout>