package com.ecommerce.admin.fileHandling;

import com.ecommerce.admin.audit.InitiatorAudit;
import com.ecommerce.admin.entity.enums.EFileSizeType;
import com.ecommerce.admin.entity.enums.EfileStatus;
import javax.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "files", uniqueConstraints = {@UniqueConstraint(columnNames = "path")})
public class File extends InitiatorAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="path")
    private String path;

    @Transient
    private String url;

    @Column(name="size")
    private int size;

    @Column(name="size_type")
    @Enumerated(EnumType.STRING)
    private EFileSizeType sizeType;

    @Column(name="type")
    private String type;

    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private EfileStatus status;

    public File(String directory, String fileName, String extension, String fileBaseName) {
        super();
    }


    public String getUrl() {
        return "http://localhost:8090/api/v1/product/load-file/" + this.getName();
    }
}
