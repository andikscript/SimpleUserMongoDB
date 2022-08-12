package com.andikscript.simpleusermongodb.model.postgresql;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue
    @Column(name = "id", length = 16)
    private UUID id;

    @NotNull
    @Column(name = "nama", nullable = false, length = 512)
    private String nama;

    @NotNull
    @Column(name = "gaji", nullable = false)
    private Integer gaji;

    public UUID getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Integer getGaji() {
        return gaji;
    }

    public void setGaji(Integer gaji) {
        this.gaji = gaji;
    }
}
