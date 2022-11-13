package application.account.adapter.out.persistence;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table (name = "activity")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ActivityJpaEntity {
    @Id
    @GeneratedValue
    @Column (name = "id")
    private Integer id;

    @Column (name = "time_stamp")
    private Timestamp timeStamp;

    @Column (name = "owner_account_id")
    private Integer ownerAccountId;

    @Column (name = "source_account_id")
    private Integer sourceAccountId;

    @Column (name = "destination_account_id")
    private Integer destinationAccountId;

    @Column (name = "amount")
    private Long amount;

}
