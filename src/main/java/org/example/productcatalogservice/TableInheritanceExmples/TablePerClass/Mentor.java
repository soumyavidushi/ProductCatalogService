package org.example.productcatalogservice.TableInheritanceExmples.TablePerClass;

import jakarta.persistence.Entity;

@Entity(name="tpc_mentor")
public class Mentor extends User{
    Double ratings;
}
