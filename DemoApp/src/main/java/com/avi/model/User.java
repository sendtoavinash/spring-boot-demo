package com.avi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.avi.audit.Auditable;
import lombok.Data;

import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "user_entity", schema = "authentication")
@Inheritance(strategy = InheritanceType.JOINED)
public class User extends Auditable<Integer> implements Serializable {
    // Common Fields in User-Registration :: Address State/UT Email-ID Mobile No
    // District PIN Code .
    // Fields in Admin : First Name Last Name Employee ID E-MailId Mobile Number

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer entityid;

    @Column(name = "name_of_Entity")
    private String name_of_Entity;

    @Column(name = "name", nullable = true)
    private String name;

    @Column(length = 50, nullable = true)
    private String firstName;

    @Column(length = 50, nullable = true)
    private String middleName;

    @Column(length = 50, nullable = true)
    private String lastName;

    @Column(name = "address", length = 1000)
    private String address;

    @Column(name = "email", unique = true)
    // @Email(message = "Email can not be empty")
    private String email;

    @Column(name = "mobile")
    // @Pattern(regexp = "^(\\+91[\\-\\s]?)?[0]?(91)?[6789]\\d{9}$")
    private String mobile;

    @Column(name = "mobilenumber")
    private String mobileNumber;

    @Column(name = "state_ut", nullable = true)
    private String state_ut;

    @Column(name = "district", nullable = true)
    private String district;

    @Column(name = "pincode", nullable = true)
    // @Pattern(regexp = "^[1-9]{1}[0-9]{2}[0-9]{3}$")
    private String pincode;

    @Column(name = "password", nullable = true)
    @JsonIgnore
    private String password;

    @Column(name = "user_type", nullable = true)
    private String user_type;

    @JsonIgnore
    @Column(name = "usertype", nullable = true)
    private String userType;

    @JsonIgnore
    @Column(name = "status", nullable = true)
    private String status;

    @JsonIgnore
    @Column(name = "ip_address", nullable = true)
    private String ip_address;

    @JsonIgnore
    @Column(nullable = true)
    private Boolean isActive;

    @Column(nullable = true)
    @JsonProperty(access = Access.READ_ONLY)
    private Integer failedAttemptCount;

    @Column(nullable = true)
    private String emailId;

    @Column(name = "is_active")
    private Boolean is_active;

    @Column(name = "is_adhaar_verified", nullable = true)
    private Boolean is_adhaar_verified;

    @JsonIgnore
    @Column(name = "is_delete")
    private Boolean is_delete;

    @Column(name = "security_question", nullable = true)
    private String security_question;

    @Column(name = "security_answer", nullable = true)
    private String security_answer;

    @Column(name = "createddate", nullable = true)
    @JsonProperty(access = Access.READ_ONLY)
    private Date createdDate;

    @Column(nullable = true)
    private Integer selected_office;

    @Column(nullable = true)
    private Integer selected_role;

    @Column(nullable = true)
    private Integer selected_sector;

    public User() {
        super();
        this.status = "active";
        this.createdDate = new Date();
        this.failedAttemptCount = 0;
        this.is_active = true;
        this.is_delete = false;
        this.isActive = false;
        this.is_adhaar_verified = false;
    }

    public User(Integer id, String name_of_Entity, String address, String email, String mobile, String state_ut,
                String district, String pincode, String userType, HttpServletRequest request) {
        super();
        this.entityid = id;
        this.name_of_Entity = name_of_Entity;
        this.user_type = userType;
        this.userType = userType;
        this.firstName = name_of_Entity;
        this.name = name_of_Entity;
        this.address = address;
        this.email = email.trim();
        this.mobile = mobile;
        this.mobileNumber = mobile;
        this.state_ut = state_ut;
        this.district = district;
        this.pincode = pincode;
        this.status = "active";
        this.failedAttemptCount = 0;
        this.createdDate = new Date();
        this.ip_address = request.getRemoteAddr() != null ? request.getRemoteAddr() : null;
        this.is_active = true;
        this.is_delete = false;
        this.isActive = false;
    }

    public User(Integer entityid, String name_of_Entity) {
        this.entityid = entityid;
        this.name_of_Entity = name_of_Entity;
    }

}
