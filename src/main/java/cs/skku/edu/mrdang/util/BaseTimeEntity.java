package cs.skku.edu.mrdang.util;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@SuperBuilder
@MappedSuperclass
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {

    @CreatedDate
    protected LocalDateTime createdDate;

    @LastModifiedDate
    protected LocalDateTime modifiedDate;
}
