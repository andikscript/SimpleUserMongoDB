package com.andikscript.simpleusermongodb.model.h2;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "nama", nullable = false, length = 512)
    private String nama;

    @NotNull
    @Column(name = "gaji", nullable = false)
    private Integer gaji;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
