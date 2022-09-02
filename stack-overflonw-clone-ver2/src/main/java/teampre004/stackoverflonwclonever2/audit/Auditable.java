package teampre004.stackoverflonwclonever2.audit;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable<U> {

    @ManyToOne
    @JsonIgnore
    @CreatedBy
    @JoinColumn(name = "createBy")
    protected U createBy;
    @Column(name = "createDate")
    protected String createDate;
    @Column(name = "modDate")
    protected String modDate;

    @PrePersist
    protected void onPrePersist() {
        this.createDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.modDate = this.createDate;
    }

    @PreUpdate
    protected void onPreUpdate(){
        this.modDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }


}
