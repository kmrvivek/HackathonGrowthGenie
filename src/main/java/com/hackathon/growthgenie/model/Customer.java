package com.hackathon.growthgenie.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "customers")
public class Customer implements Serializable {
  
  private static final long serialVersionUID = 1L;
  
  @Id
  @Column(name="CUSTOMERID")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer customerId;
  
  @Column(name="FIRSTNAME")
  private String firstName;

  @Column(name="LASTNAME")
  private String lastName;

  @Column(name="DATEOFBIRTH")
  private String dob;

  @Column(name="GENDER")
  private String gender;

  @Column(name="ADDRESS")
  private String address;
   
  @Column(name="EMAIL")
  private String email;
  
  @Column(name="PHONE")
  private String phone;
  
  @Column(name="UID")
  private String uid;
  
}
