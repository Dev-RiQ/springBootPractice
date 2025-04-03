import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name="lockers")
@ToString(exclude = "student")
//@ToString
public class Locker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="locker_id")
    private Long id;
    private int lockNo;

    // 관계형 데이터베이스 mysql 에서는 student 생성이 안된다
    @OneToMany(mappedBy = "locker")
    private List<Student> student = new ArrayList<>();

    public Locker(int lockNo) {
        this.lockNo = lockNo;
    }
}