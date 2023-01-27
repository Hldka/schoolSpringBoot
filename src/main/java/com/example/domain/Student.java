package com.example.domain;

import lombok.*;
/*
Spring Boot Okul Yönetim Sistemi Projesi
1. Her ogrenci kendi numarasini girerek kendi notunu görüntüleyebilsin.(HALİDE )
2. Her ögretmen, (User1)
   a)ögrencilerin sadece listesini görebilsin.Isim ve not(Tüm bilgilerini görmesin) ----> BETÜL
   b)ögrencilerin notlarini degistirebilsin   ----->AYŞE
3.Her yönetici,(User2)
   a)Tüm ogrencileri tüm bilgileriyle listeleyebilsin, ---->FATİH HAYYAR
   b)Ogrenci ekleyebilsin,  ---->betül***
   c)ogrenci silebilsin, ---->     kamuran
   d)Ogrenci bilgilerini update edebilsin,  --->ömer
   e)Istedigi ozellik ile siralama yapabilsin(Isme gore siralama) ---->yavuz selim
   f)istedigi ozelliklerdeki ogrencileri filtreleyebilsin(Notu 70 den fazla olanlar) ---->????
 */
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
@NoArgsConstructor
@Getter
@Setter
@Entity
@AllArgsConstructor

public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)//Id setlenmemeli
    private Long id;
    @NotNull(message = "First name can not null")
    @NotBlank(message = "First bame can not bull")
    @Column(nullable = false)
    private String name;
    @NotNull(message = "last name can not be null!")
    @NotBlank(message = "last name can not be white space")
    @Column(nullable = false)
    private String lastName;


    @NotNull
    @NotBlank
    @Column(unique = true)
    private String studentNo;


    private String grade;

}
