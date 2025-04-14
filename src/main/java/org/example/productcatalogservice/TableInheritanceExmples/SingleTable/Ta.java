package org.example.productcatalogservice.TableInheritanceExmples.SingleTable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity(name="sc_ta")
@DiscriminatorValue(value="0")
public class Ta extends User {
    int noOfHelpRequests;
}
