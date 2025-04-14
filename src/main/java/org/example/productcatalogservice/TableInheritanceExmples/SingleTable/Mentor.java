package org.example.productcatalogservice.TableInheritanceExmples.SingleTable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity(name="sc_mentor")
@DiscriminatorValue(value="2")
public class Mentor extends User {
    Double ratings;
}
