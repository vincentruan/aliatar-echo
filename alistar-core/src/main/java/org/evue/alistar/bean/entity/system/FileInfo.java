package org.evue.alistar.bean.entity.system;

import lombok.Data;
import org.evue.alistar.bean.entity.BaseEntity;
import org.hibernate.annotations.Table;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Transient;

@Data
@Entity(name = "t_sys_file_info")
@Table(appliesTo = "t_sys_file_info", comment = "文件")
@EntityListeners(AuditingEntityListener.class)
public class FileInfo extends BaseEntity {
    @Column
    private String originalFileName;
    @Column
    private String realFileName;
    @Transient
    private String ablatePath;

}
