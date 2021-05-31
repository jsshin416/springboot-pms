package edu.axboot.domain.pms.book.booking;

import com.chequer.axboot.core.annotations.ColumnPosition;
import edu.axboot.controllers.dto.MemoSaveRequestDto;
import edu.axboot.domain.BaseJpaModel;
import edu.axboot.domain.SimpleJpaModel;
import edu.axboot.domain.pms.book.memo.Memo;
import edu.axboot.domain.pms.info.guest.Guest;
import lombok.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.Alias;
import org.bouncycastle.pqc.math.linearalgebra.IntUtils;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import com.chequer.axboot.core.annotations.Comment;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "PMS_CHK")
public class Booking extends BaseJpaModel<Long> {

	@Id
	@Column(name = "ID", precision = 19, nullable = false)
	@Comment(value = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ColumnPosition(1)
	private Long id;

	@Column(name = "RSV_DT", length = 10, nullable = false)
	@Comment(value = "예약 일자")
	@ColumnPosition(2)
	private String rsvDt;

	@Column(name = "SNO", precision = 10, nullable = false)
	@Comment(value = "일련번호")
	@ColumnPosition(3)
	private Integer sno;

	@Column(name = "RSV_NUM", length = 20, nullable = false)
	@Comment(value = "예약 번호")
	@ColumnPosition(4)
	private String rsvNum;

	@Column(name = "GUEST_ID", precision = 19)
	@Comment(value = "투숙객 ID")
	@ColumnPosition(5)
	private Long guestId;

	@Column(name = "GUEST_NM", length = 100)
	@Comment(value = "투숙객 명")
	@ColumnPosition(6)
	private String guestNm;

	@Column(name = "GUEST_NM_ENG", length = 200)
	@Comment(value = "투숙객 명 영어")
	@ColumnPosition(7)
	private String guestNmEng;

	@Column(name = "GUEST_TEL", length = 18)
	@Comment(value = "투숙객 전화")
	@ColumnPosition(8)
	private String guestTel;

	@Column(name = "EMAIL", length = 100)
	@Comment(value = "이메일")
	@ColumnPosition(9)
	private String email;

	@Column(name = "LANG_CD", length = 20)
	@Comment(value = "언어 CD")
	@ColumnPosition(10)
	private String langCd;

	@Column(name = "ARR_DT", length = 10, nullable = false)
	@Comment(value = "도착 일자")
	@ColumnPosition(11)
	private String arrDt;

	@Column(name = "ARR_TIME", length = 8)
	@Comment(value = "도착 시간")
	@ColumnPosition(12)
	private String arrTime;

	@Column(name = "DEP_DT", length = 10, nullable = false)
	@Comment(value = "출발 일자")
	@ColumnPosition(13)
	private String depDt;

	@Column(name = "DEP_TIME", length = 8)
	@Comment(value = "출발 시간")
	@ColumnPosition(14)
	private String depTime;

	@Column(name = "NIGHT_CNT", precision = 10, nullable = false)
	@Comment(value = "숙박 수")
	@ColumnPosition(15)
	private Integer nightCnt;

	@Column(name = "ROOM_TYP_CD", length = 20, nullable = false)
	@Comment(value = "객실 타입 CD")
	@ColumnPosition(16)
	private String roomTypCd;

	@Column(name = "ROOM_NUM", length = 10)
	@Comment(value = "객실 번호")
	@ColumnPosition(17)
	private String roomNum;

	@Column(name = "ADULT_CNT", precision = 10, nullable = false)
	@Comment(value = "성인 수")
	@ColumnPosition(18)
	private Integer adultCnt;

	@Column(name = "CHLD_CNT", precision = 10, nullable = false)
	@Comment(value = "아동 수")
	@ColumnPosition(19)
	private Integer chldCnt;

	@Column(name = "SALE_TYP_CD", length = 20, nullable = false)
	@Comment(value = "판매 유형 CD")
	@ColumnPosition(20)
	private String saleTypCd;

	@Column(name = "STTUS_CD", length = 20, nullable = false)
	@Comment(value = "상태 CD")
	@ColumnPosition(21)
	private String sttusCd;

	@Column(name = "SRC_CD", length = 20, nullable = false)
	@Comment(value = "소스 CD")
	@ColumnPosition(22)
	private String srcCd;

	@Column(name = "BRTH", length = 10)
	@Comment(value = "생일")
	@ColumnPosition(23)
	private String brth;

	@Column(name = "GENDER", length = 20)
	@Comment(value = "성별")
	@ColumnPosition(24)
	private String gender;

	@Column(name = "PAY_CD", length = 20)
	@Comment(value = "결제 CD")
	@ColumnPosition(25)
	private String payCd;

	@Column(name = "ADVN_YN", length = 1, nullable = false)
	@Comment(value = "선수금 여부")
	@ColumnPosition(26)
	private String advnYn;

	@Column(name = "SALE_PRC", precision = 18, scale = 0)
	@Comment(value = "판매 가격")
	@ColumnPosition(27)
	private BigDecimal salePrc;

	@Column(name = "SVC_PRC", precision = 18, scale = 0)
	@Comment(value = "서비스 가격")
	@ColumnPosition(28)
	private BigDecimal svcPrc;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "RSV_NUM", referencedColumnName = "RSV_NUM", insertable = false, updatable = false)
	private List<Memo> memoList;



	@Override
    public Long getId() {
        return id;
    }
	@Builder
	public Booking(Long id,String rsvDt, Integer sno, String rsvNum,
				   Long guestId, String guestNm,
				   String guestNmEng, String guestTel,
				   String email, String langCd, String aarDt, String arrTime,
				   String depDt, String depTime, Integer nightCnt, String roomTypCd,
				   String roomNum, Integer adultCnt, Integer chldCnt, String saleTypCd,
				   String sttusCd, String srcCd, String brth, String gender, String payCd, String advnYn,
				   BigDecimal salePrc, BigDecimal svcPrc,
				   boolean isCreated,boolean isModified, boolean isDeleted){

		this.id = id;
		this.rsvDt = rsvDt;
		this.sno =sno;
		this.rsvNum= rsvNum;
		this.guestId =guestId;
		this.guestNm = guestNm;
		this.guestNmEng =guestNmEng;
		this.guestTel = guestTel;
		this.email =email;
		this.langCd =langCd;
		this.arrDt = aarDt;
		this.arrTime =arrTime;
		this.depDt = depDt;
		this.depTime = depTime;
		this.nightCnt = nightCnt;
		this.roomTypCd = roomTypCd;
		this.roomNum = roomNum;
		this.adultCnt =adultCnt;
		this.chldCnt = chldCnt;
		this.saleTypCd = saleTypCd;
		this.sttusCd =sttusCd;
		this.srcCd =srcCd;
		this.brth = brth;
		this.gender =gender;
		this.payCd =payCd;
		this.advnYn =advnYn;
		this.salePrc =salePrc;
		this.svcPrc =svcPrc;
		this.__created__ = isCreated;
		this.__modified__ = isModified;
		this.__deleted__ = isDeleted;

	}

	public void 예약번호생성(String rsvDt, int sno) {
		this.rsvDt = rsvDt;
		this.sno = sno;
		this.rsvNum = "R" + rsvDt.replaceAll("-", "") + StringUtils.leftPad(Integer.toString(sno),3,'0');
		this.sttusCd ="RSV_01";
	}

	public void 투숙번호갱신(Long guestId){
		this.guestId =guestId;
	}
	public void 예약수정 (Long guestId, String arrDt, Integer nightCnt, String depDt,
					  String roomTypCd, Integer adultCnt,Integer chldCnt,
					  String guestNm,String guestNmEng, String guestTel,
					  String email, String langCd, String brth, String gender,
					  String saleTypCd, String srcCd,String payCd, String advnYn,
					  BigDecimal salePrc, BigDecimal svcPrc){
		this.guestId =guestId;
		this.guestNm = guestNm;
		this.guestNmEng =guestNmEng;
		this.guestTel = guestTel;
		this.email =email;
		this.langCd =langCd;
		this.arrDt = arrDt;
		this.depDt = depDt;
		this.nightCnt = nightCnt;
		this.roomTypCd = roomTypCd;
		this.adultCnt =adultCnt;
		this.chldCnt = chldCnt;
		this.saleTypCd = saleTypCd;
		this.srcCd =srcCd;
		this.brth = brth;
		this.gender =gender;
		this.payCd =payCd;
		this.advnYn =advnYn;
		this.salePrc =salePrc;
		this.svcPrc =svcPrc;

	}
	public  void 예약상태변경(String sttusCd){
		this.sttusCd = sttusCd;
	}
	public  void 객실배정(String roomNum){ this.roomNum = roomNum; }

}