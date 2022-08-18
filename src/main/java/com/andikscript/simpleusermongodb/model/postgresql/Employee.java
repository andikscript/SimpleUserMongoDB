package com.andikscript.simpleusermongodb.model.postgresql;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue
    @Column(name = "id", length = 16)
    private UUID id;

    @NotBlank(message = "name is mandatory")
    @Column(name = "nama", nullable = false, length = 512)
    private String nama;

    @NotNull(message = "gaji is mandatory")
    @Min(0)
    @Column(name = "gaji", nullable = false)
    private Integer gaji;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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
