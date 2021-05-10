-- 최초 실습용 그리드 추가 ########################################
-- 메뉴 그룹 - 실습용
INSERT INTO MENU_M (MENU_ID, MENU_GRP_CD, MENU_NM, MULTI_LANGUAGE, PARENT_ID, LEVEL, SORT, PROG_CD, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY )
VALUES (300, 'SYSTEM_MANAGER', '실습용 샘플', '{"ko":"실습용","en":"실습용"}',  null, 0, 99, null, sysdate(), 'system', sysdate(), 'system');

-- 실습용 &그리드 추가
-- 프로그램 생성
INSERT INTO PROG_M (PROG_CD, PROG_NM, PROG_PH, TARGET, AUTH_CHECK, SCH_AH, SAV_AH )
VALUES ('edu-web-test', '실습용 &그리드', '/jsp/edu/edu-web-test.jsp', '_self', 'Y', 'Y', 'Y');
-- 메뉴 생성
INSERT INTO MENU_M (MENU_ID, MENU_GRP_CD, MENU_NM, MULTI_LANGUAGE, PARENT_ID, LEVEL, SORT, PROG_CD, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY )
VALUES (301, 'SYSTEM_MANAGER', '실습용 &그리드', '{"ko":"실습용 &그리드","en":"실습용 &그리드"}',  300, 1, 1, 'edu-web-test', sysdate(), 'system', sysdate(), 'system');
-- 메뉴 권한
INSERT INTO AUTH_GROUP_MAP_M (GRP_AUTH_CD , MENU_ID, SCH_AH, SAV_AH, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY )
VALUES ('S0001', '301',  'Y', 'Y', sysdate(), 'system', sysdate(), 'system');


-- 실습용 그리드 &폼 추가 ########################################
-- 프로그램 생성
INSERT INTO PROG_M (PROG_CD, PROG_NM, PROG_PH, TARGET, AUTH_CHECK, SCH_AH, SAV_AH, DEL_AH )
VALUES ('edu-web-test-grid-form', '실습용 그리드&폼', '/jsp/edu/edu-web-test-grid-form.jsp', '_self', 'Y', 'Y', 'Y', 'Y');
INSERT INTO PROG_M (PROG_CD, PROG_NM, PROG_PH, TARGET, AUTH_CHECK, SCH_AH, SAV_AH, DEL_AH )
VALUES ('edu-web-test-grid-form-without-tags', '실습용 그리드 &폼(tag 사용안함)', '/jsp/edu/edu-web-test-grid-form-without-tags.jsp', '_self', 'Y', 'Y', 'Y', 'Y');
-- 메뉴 생성
INSERT INTO MENU_M (MENU_ID, MENU_GRP_CD, MENU_NM, MULTI_LANGUAGE, PARENT_ID, LEVEL, SORT, PROG_CD, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY )
VALUES (302, 'SYSTEM_MANAGER', '실습용 그리드 &폼', '{"ko":"실습용 그리드 &폼","en":"실습용 그리드 &폼"}',  300, 1, 2, 'edu-web-test-grid-form', sysdate(), 'system', sysdate(), 'system');
INSERT INTO MENU_M (MENU_ID, MENU_GRP_CD, MENU_NM, MULTI_LANGUAGE, PARENT_ID, LEVEL, SORT, PROG_CD, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY )
VALUES (303, 'SYSTEM_MANAGER', '실습용 그리드 &폼(tag 사용안함)', '{"ko":"실습용 그리드 &폼(tag 사용안함)","en":"실습용 그리드 &폼(tag 사용안함)"}',  300, 1, 3, 'edu-web-test-grid-form-without-tags', sysdate(), 'system', sysdate(), 'system');
-- 메뉴 권한
INSERT INTO AUTH_GROUP_MAP_M (GRP_AUTH_CD , MENU_ID, SCH_AH, SAV_AH, DEL_AH, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY )
VALUES ('S0001', '302',  'Y', 'Y', 'Y', sysdate(), 'system', sysdate(), 'system');
INSERT INTO AUTH_GROUP_MAP_M (GRP_AUTH_CD , MENU_ID, SCH_AH, SAV_AH, DEL_AH, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY )
VALUES ('S0001', '303',  'Y', 'Y', 'Y', sysdate(), 'system', sysdate(), 'system');


-- 실습용 그리드 &모달, &엑셀 추가 ########################################
-- 프로그램 생성
INSERT INTO PROG_M (PROG_CD, PROG_NM, PROG_PH, TARGET, AUTH_CHECK, SCH_AH, SAV_AH)
VALUES ('edu-web-test-grid-modal', '실습용 그리드 &모달', '/jsp/edu/edu-web-test-grid-modal.jsp', '_self', 'Y', 'Y', 'N');
INSERT INTO PROG_M (PROG_CD, PROG_NM, PROG_PH, TARGET, AUTH_CHECK, SCH_AH, SAV_AH)
VALUES ('edu-web-test-excel', '실습용 &엑셀', '/jsp/edu/edu-web-test-excel.jsp', '_self', 'Y', 'Y', 'Y');
-- 메뉴 생성
INSERT INTO MENU_M (MENU_ID, MENU_GRP_CD, MENU_NM, MULTI_LANGUAGE, PARENT_ID, LEVEL, SORT, PROG_CD, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY )
VALUES (304, 'SYSTEM_MANAGER', '실습용 그리드 &모달', '{"ko":"실습용 그리드 &모달","en":"실습용 그리드 &모달"}',  300, 1, 4, 'edu-web-test-grid-modal', sysdate(), 'system', sysdate(), 'system');
INSERT INTO MENU_M (MENU_ID, MENU_GRP_CD, MENU_NM, MULTI_LANGUAGE, PARENT_ID, LEVEL, SORT, PROG_CD, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY )
VALUES (310, 'SYSTEM_MANAGER', '실습용 &엑셀', '{"ko":"실습용 &엑셀","en":"실습용 &엑셀"}',  300, 1, 10, 'edu-web-test-excel', sysdate(), 'system', sysdate(), 'system');
-- 메뉴 권한
INSERT INTO AUTH_GROUP_MAP_M (GRP_AUTH_CD , MENU_ID, SCH_AH, SAV_AH, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY )
VALUES ('S0001', '304',  'Y', 'N', sysdate(), 'system', sysdate(), 'system');
INSERT INTO AUTH_GROUP_MAP_M (GRP_AUTH_CD , MENU_ID, SCH_AH, SAV_AH, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY )
VALUES ('S0001', '310',  'Y', 'Y', sysdate(), 'system', sysdate(), 'system');