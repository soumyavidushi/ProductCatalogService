package org.example.productcatalogservice.TableInheritanceExmples.SingleTable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity(name="sc_instructor")
@DiscriminatorValue(value="1")
public class Instructor extends User {
    String company;
}
