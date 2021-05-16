-- 숙박메모
DROP TABLE IF EXISTS PMS_CHK_MEMO RESTRICT;

-- 숙박메모
CREATE TABLE PMS_CHK_MEMO (
	ID         BIGINT        NOT NULL COMMENT 'ID', -- ID
	RSV_NUM    VARCHAR(20)   NULL     COMMENT '예약번호', -- 예약번호
	SNO        INT           NOT NULL DEFAULT 0 COMMENT '일련번호', -- 일련번호
	MEMO_CN    VARCHAR(4000) NOT NULL COMMENT '메모 내용', -- 메모 내용
	MEMO_DTTI  TIMESTAMP     NOT NULL COMMENT '메모 일시', -- 메모 일시
	MEMO_MAN   VARCHAR(100)  NOT NULL COMMENT '메모 자', -- 메모 자
	DEL_YN     VARCHAR(1)    NOT NULL DEFAULT 'N' COMMENT '삭제 여부', -- 삭제 여부
	CREATED_AT TIMESTAMP     NULL     COMMENT '등록일', -- 등록일
	CREATED_BY VARCHAR(100)  NULL     COMMENT '등록자', -- 등록자
	UPDATED_AT TIMESTAMP     NULL     COMMENT '변경일', -- 변경일
	UPDATED_BY VARCHAR(100)  NULL     COMMENT '변경자' -- 변경자
);

-- 숙박메모
ALTER TABLE PMS_CHK_MEMO
	ADD CONSTRAINT PK_cust_memo_sttmt -- 고객 메모 내역 기본키
		PRIMARY KEY (
			ID -- ID
		);

-- 숙박메모 인덱스
CREATE INDEX IX_PMS_CHK_MEMO
	ON PMS_CHK_MEMO( -- 숙박메모
	);
