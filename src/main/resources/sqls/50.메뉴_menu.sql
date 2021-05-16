-- 최초 실습용 그리드 추가 ########################################
-- 메뉴 그룹 - 예약 실습용
INSERT INTO MENU_M (MENU_ID, MENU_GRP_CD, MENU_NM, MULTI_LANGUAGE, PARENT_ID, LEVEL, SORT, PROG_CD, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY )
VALUES (200, 'SYSTEM_MANAGER', '예약', '{"ko":"예약","en":"예약"}',  null, 0, 99, null, sysdate(), 'system', sysdate(), 'system');

-- 예약등록, 예약현황 추가
-- 프로그램 생성
INSERT INTO PROG_M (PROG_CD, PROG_NM, PROG_PH, TARGET, AUTH_CHECK, SCH_AH, SAV_AH )
VALUES ('booking', '예약등록', '/jsp/pms/book/booking.jsp', '_self', 'Y', 'Y', 'Y');
INSERT INTO PROG_M (PROG_CD, PROG_NM, PROG_PH, TARGET, AUTH_CHECK, SCH_AH, SAV_AH )
VALUES ('state', '예약현황', '/jsp/pms/book/state.jsp', '_self', 'Y', 'Y', 'Y');

-- 메뉴 생성
INSERT INTO MENU_M (MENU_ID, MENU_GRP_CD, MENU_NM, MULTI_LANGUAGE, PARENT_ID, LEVEL, SORT, PROG_CD, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY )
VALUES (201, 'SYSTEM_MANAGER', '예약등록', '{"ko":"예약등록","en":"예약등록"}',  200, 1, 1, 'booking', sysdate(), 'system', sysdate(), 'system');
INSERT INTO MENU_M (MENU_ID, MENU_GRP_CD, MENU_NM, MULTI_LANGUAGE, PARENT_ID, LEVEL, SORT, PROG_CD, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY )
VALUES (202, 'SYSTEM_MANAGER', '예약현황', '{"ko":"예약현황","en":"예약현황"}',  200, 1, 2, 'state', sysdate(), 'system', sysdate(), 'system');

-- 메뉴 권한
INSERT INTO AUTH_GROUP_MAP_M (GRP_AUTH_CD , MENU_ID, SCH_AH, SAV_AH, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY )
VALUES ('S0001', '201',  'Y', 'Y', sysdate(), 'system', sysdate(), 'system');
INSERT INTO AUTH_GROUP_MAP_M (GRP_AUTH_CD , MENU_ID, SCH_AH, SAV_AH, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY )
VALUES ('S0001', '202',  'Y', 'Y', sysdate(), 'system', sysdate(), 'system');



-- 최초 프론트 추가 ########################################
-- 메뉴 그룹 - 프론트 실습용
INSERT INTO MENU_M (MENU_ID, MENU_GRP_CD, MENU_NM, MULTI_LANGUAGE, PARENT_ID, LEVEL, SORT, PROG_CD, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY )
VALUES (300, 'SYSTEM_MANAGER', '프론트', '{"ko":"프론트","en":"front"}',  null, 0, 99, null, sysdate(), 'system', sysdate(), 'system');

-- 예약등록, 예약현황 추가
-- 프로그램 생성
INSERT INTO PROG_M (PROG_CD, PROG_NM, PROG_PH, TARGET, AUTH_CHECK, SCH_AH, SAV_AH )
VALUES ('check-in', '체크인', '/jsp/pms/front/check-in.jsp', '_self', 'Y', 'Y', 'Y');
INSERT INTO PROG_M (PROG_CD, PROG_NM, PROG_PH, TARGET, AUTH_CHECK, SCH_AH, SAV_AH )
VALUES ('in-house', '인하우스', '/jsp/pms/front/in-house.jsp', '_self', 'Y', 'Y', 'Y');
INSERT INTO PROG_M (PROG_CD, PROG_NM, PROG_PH, TARGET, AUTH_CHECK, SCH_AH, SAV_AH )
VALUES ('check-out', '체크아웃', '/jsp/pms/front/check-out.jsp', '_self', 'Y', 'Y', 'Y');


-- 메뉴 생성
INSERT INTO MENU_M (MENU_ID, MENU_GRP_CD, MENU_NM, MULTI_LANGUAGE, PARENT_ID, LEVEL, SORT, PROG_CD, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY )
VALUES (301, 'SYSTEM_MANAGER', '체크인', '{"ko":"체크인","en":"check-in"}',  300, 1, 1, 'check-in', sysdate(), 'system', sysdate(), 'system');
INSERT INTO MENU_M (MENU_ID, MENU_GRP_CD, MENU_NM, MULTI_LANGUAGE, PARENT_ID, LEVEL, SORT, PROG_CD, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY )
VALUES (302, 'SYSTEM_MANAGER', '인하우스', '{"ko":"인하우스","en":"in-house"}',  300, 1, 2, 'in-house', sysdate(), 'system', sysdate(), 'system');
INSERT INTO MENU_M (MENU_ID, MENU_GRP_CD, MENU_NM, MULTI_LANGUAGE, PARENT_ID, LEVEL, SORT, PROG_CD, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY )
VALUES (303, 'SYSTEM_MANAGER', '체크아웃', '{"ko":"체크아웃","en":"check-out"}',  300, 1, 3, 'check-out', sysdate(), 'system', sysdate(), 'system');


-- 메뉴 권한
INSERT INTO AUTH_GROUP_MAP_M (GRP_AUTH_CD , MENU_ID, SCH_AH, SAV_AH, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY )
VALUES ('S0001', '301',  'Y', 'Y', sysdate(), 'system', sysdate(), 'system');
INSERT INTO AUTH_GROUP_MAP_M (GRP_AUTH_CD , MENU_ID, SCH_AH, SAV_AH, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY )
VALUES ('S0001', '302',  'Y', 'Y', sysdate(), 'system', sysdate(), 'system');
INSERT INTO AUTH_GROUP_MAP_M (GRP_AUTH_CD , MENU_ID, SCH_AH, SAV_AH, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY )
VALUES ('S0001', '303',  'Y', 'Y', sysdate(), 'system', sysdate(), 'system');


-- 최초 실습용 그리드 추가 ########################################
-- 메뉴 그룹 - 보고서 실습용
INSERT INTO MENU_M (MENU_ID, MENU_GRP_CD, MENU_NM, MULTI_LANGUAGE, PARENT_ID, LEVEL, SORT, PROG_CD, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY )
VALUES (400, 'SYSTEM_MANAGER', '보고서', '{"ko":"보고서","en":"report"}',  null, 0, 99, null, sysdate(), 'system', sysdate(), 'system');

-- 예약등록, 예약현황 추가
-- 프로그램 생성
INSERT INTO PROG_M (PROG_CD, PROG_NM, PROG_PH, TARGET, AUTH_CHECK, SCH_AH, SAV_AH )
VALUES ('sales', '매출현황', '/jsp/pms/report/sales.jsp', '_self', 'Y', 'Y', 'Y');

-- 메뉴 생성
INSERT INTO MENU_M (MENU_ID, MENU_GRP_CD, MENU_NM, MULTI_LANGUAGE, PARENT_ID, LEVEL, SORT, PROG_CD, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY )
VALUES (401, 'SYSTEM_MANAGER', '매출현황', '{"ko":"매출현황","en":"sales"}',  400, 1, 1, 'sales', sysdate(), 'system', sysdate(), 'system');

-- 메뉴 권한
INSERT INTO AUTH_GROUP_MAP_M (GRP_AUTH_CD , MENU_ID, SCH_AH, SAV_AH, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY )
VALUES ('S0001', '401',  'Y', 'Y', sysdate(), 'system', sysdate(), 'system');


-- 최초 실습용 그리드 추가 ########################################
-- 메뉴 그룹 - 기준정보 실습용
INSERT INTO MENU_M (MENU_ID, MENU_GRP_CD, MENU_NM, MULTI_LANGUAGE, PARENT_ID, LEVEL, SORT, PROG_CD, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY )
VALUES (500, 'SYSTEM_MANAGER', '기준정보', '{"ko":"기준정보","en":"info"}',  null, 0, 99, null, sysdate(), 'system', sysdate(), 'system');

-- 예약등록, 예약현황 추가
-- 프로그램 생성
INSERT INTO PROG_M (PROG_CD, PROG_NM, PROG_PH, TARGET, AUTH_CHECK, SCH_AH, SAV_AH )
VALUES ('room', '객실정보', '/jsp/pms/info/room.jsp', '_self', 'Y', 'Y', 'Y');
INSERT INTO PROG_M (PROG_CD, PROG_NM, PROG_PH, TARGET, AUTH_CHECK, SCH_AH, SAV_AH )
VALUES ('guest', '투숙객정보', '/jsp/pms/info/guest.jsp', '_self', 'Y', 'Y', 'Y');

-- 메뉴 생성
INSERT INTO MENU_M (MENU_ID, MENU_GRP_CD, MENU_NM, MULTI_LANGUAGE, PARENT_ID, LEVEL, SORT, PROG_CD, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY )
VALUES (501, 'SYSTEM_MANAGER', '객실정보', '{"ko":"객실정보","en":"room"}',  500, 1, 1, 'room', sysdate(), 'system', sysdate(), 'system');
INSERT INTO MENU_M (MENU_ID, MENU_GRP_CD, MENU_NM, MULTI_LANGUAGE, PARENT_ID, LEVEL, SORT, PROG_CD, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY )
VALUES (502, 'SYSTEM_MANAGER', '투숙객정보', '{"ko":"투숙객정보","en":"guest"}',  500, 1, 2, 'guest', sysdate(), 'system', sysdate(), 'system');

-- 메뉴 권한
INSERT INTO AUTH_GROUP_MAP_M (GRP_AUTH_CD , MENU_ID, SCH_AH, SAV_AH, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY )
VALUES ('S0001', '501',  'Y', 'Y', sysdate(), 'system', sysdate(), 'system');
INSERT INTO AUTH_GROUP_MAP_M (GRP_AUTH_CD , MENU_ID, SCH_AH, SAV_AH, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY )
VALUES ('S0001', '502',  'Y', 'Y', sysdate(), 'system', sysdate(), 'system');



