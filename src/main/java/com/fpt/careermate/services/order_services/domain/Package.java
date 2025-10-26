package com.fpt.careermate.services.order_services.domain;

import com.fpt.careermate.services.profile_services.domain.Candidate;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Entity(name = "packages")
public class Package {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;
    Long price;
    int durationDays;

    @OneToMany(mappedBy = "currentPackage")
    List<Candidate> candidates;
}
