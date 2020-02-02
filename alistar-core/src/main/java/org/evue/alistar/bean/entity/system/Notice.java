package org.evue.alistar.bean.entity.system;

import lombok.Data;
import org.evue.alistar.bean.entity.BaseEntity;
import org.hibernate.annotations.Table;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;

/**
 * Created  on 2018/4/2 0002.
 *
 * @author enilu
 */

@Entity(name = "t_sys_notice")
@Table(appliesTo = "t_sys_notice", comment = "通知")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Notice extends BaseEntity {
    @Column
    private String title;
    @Column
    private Integer type;
    @Column
    private String content;

}
