package edu.axboot.domain.pms.book.memo;

import com.chequer.axboot.core.annotations.ColumnPosition;
import edu.axboot.domain.SimpleJpaModel;
import edu.axboot.domain.pms.book.booking.Booking;
import lombok.*;
import org.apache.ibatis.type.Alias;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import com.chequer.axboot.core.annotations.Comment;
import javax.persistence.*;
import java.sql.Timestamp;

@Setter
@Getter
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "PMS_CHK_MEMO")
public class Memo extends SimpleJpaModel<Long> {

	@Id
	@Column(name = "ID", precision = 19, nullable = false)
	@Comment(value = "ID")
	private Long id;

	@Column(name = "RSV_NUM", length = 20)
	@Comment(value = "예약번호")
	private String rsvNum;

	@Column(name = "SNO", precision = 10, nullable = false)
	@Comment(value = "일련번호")
	private Integer sno;

	@Column(name = "MEMO_CN", length = 4000, nullable = false)
	@Comment(value = "메모 내용")
	private String memoCn;

	@Column(name = "MEMO_DTTI", nullable = false)
	@Comment(value = "메모 일시")
	private Timestamp memoDtti;

	@Column(name = "MEMO_MAN", length = 100, nullable = false)
	@Comment(value = "메모 자")
	private String memoMan;

	@Column(name = "DEL_YN", length = 1, nullable = false)
	@Comment(value = "삭제 여부")
	private String delYn;


	@Override
    public Long getId() {
        return id;
    }

    @Builder
	public Memo(Long id, String rsvNum, Integer sno, String memoCn, Timestamp memoDtti, String memoMan, String delYn){
    	this.id =id;
    	this.rsvNum = rsvNum;
    	this.sno =sno;
    	this.memoCn =memoCn;
    	this.memoDtti = memoDtti;
    	this.memoMan =memoMan;
    	this.delYn =delYn;
	}
}