package edu.axboot.domain.pms.room;


import com.chequer.axboot.core.annotations.ColumnPosition;
import edu.axboot.domain.BaseJpaModel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import com.chequer.axboot.core.annotations.Comment;
import javax.persistence.*;

@NoArgsConstructor
@Setter
@Getter
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "PMS_ROOM")
public class PmsRoom extends BaseJpaModel<Long> {//**simple->base

	@Id
	@Column(name = "ID", precision = 19, nullable = false)
	@Comment(value = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "ROOM_NUM", length = 10, nullable = false)
	@Comment(value = "객실 번호")
	private String roomNum;

	@Column(name = "ROOM_TYP_CD", length = 20, nullable = false)
	@Comment(value = "객실 타입 CD")
	private String roomTypCd;

	@Column(name = "DND_YN", length = 1, nullable = false)
	@Comment(value = "DND 여부")
	private String dndYn;

	@Column(name = "EB_YN", length = 1, nullable = false)
	@Comment(value = "EB 여부")
	private String ebYn;

	@Column(name = "ROOM_STTUS_CD", length = 20)
	@Comment(value = "객실 상태 CD")
	private String roomSttusCd;

	@Column(name = "CLN_STTUS_CD", length = 20)
	@Comment(value = "청소 상태 CD")
	private String clnSttusCd;

	@Column(name = "SVC_STTUS_CD", length = 20)
	@Comment(value = "서비스 상태 CD")
	private String svcSttusCd;


@Override
public Long getId() {
return id;
}

@Builder
	public PmsRoom(Long id, String roomNum, String roomTypCd, String dndYn,
				   String ebYn, String roomSttusCd,
				   String clnSttusCd, String svcSttusCd,
				   boolean isCreated,boolean isModified, boolean isDeleted){
	this.id = id;
	this.roomNum = roomNum;
	this.roomTypCd = roomTypCd;
	this.dndYn = dndYn;
	this.ebYn =ebYn;
	this.roomSttusCd = roomSttusCd;
	this.clnSttusCd = clnSttusCd;
	this.svcSttusCd =svcSttusCd;
	this.__created__ = isCreated;
	this.__modified__ = isModified;
	this.__deleted__ = isDeleted;
	}


}