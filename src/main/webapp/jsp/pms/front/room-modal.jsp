<%@ page import="com.chequer.axboot.core.utils.RequestUtils" %>
    <%@ page contentType="text/html; charset=UTF-8" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
            <%@ taglib prefix="ax" tagdir="/WEB-INF/tags" %>
            <%
            RequestUtils requestUtils = RequestUtils.of(request);
            request.setAttribute("roomTypCd", requestUtils.getString("roomTypCd"));
            request.setAttribute("modalView", requestUtils.getString("modalView"));
        %>

<ax:set key="title" value="${pageName}"/>
<ax:set key="page_desc" value="${PAGE_REMARK}"/>
<ax:set key="page_auto_height" value="true"/>

<ax:layout name="modal">
    <jsp:attribute name="script">
        <script>
            var modalParams = {roomTypCd: "${roomTypCd}",  modalView: "${modalView}"};
        </script>
        <script type="text/javascript" src="<c:url value='/assets/js/view/pms/front/room-modal.js' />"></script>
        <ax:script-lang key="ax.script" var="LANG" />
        <ax:script-lang key="ax.base" var="COL" />
    </jsp:attribute>
    <jsp:body>
       
            <ax:split-panel width="500px" style="padding-left: 0;" scroll="scroll">
                <div class="ax-button-group" role="panel-header">
                    <div class="left">
                        <h2><i class="cqc-news"></i>  객실 목록  </h2>
                    </div>
                </div>
                <div data-ax5grid="grid-view-01" data-fit-height-content="grid-view-01" style="height: 300px;"></div>
                <div class="ax-button-group">
                    <div class="auto">
                        <button type="button" class="btn btn-default" data-page-btn="choice"> 선택</button>
                        <button type="button" class="btn btn-default"  data-page-btn="close">닫기</button>
                    </div>
                </div>
            </ax:split-panel>
        

    </jsp:body>
</ax:layout>