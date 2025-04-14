package org.example.productcatalogservice.TableInheritanceExmples.TablePerClass;

import jakarta.persistence.Entity;

@Entity(name="tpc_ta")
public class Ta extends User{
    int noOfHelpRequests;
}
