package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
@XStreamAlias("contact")
@Entity
@Table(name = "addressbook")
public class ContactData {
    @Id
            @Column(name = "id")
    int id;
    @Transient
    private String allPhones;
    @Expose
    @Column(name = "firstname")
    private String firstname;
    @Expose
    @Column(name = "lastname")
    private String lastname;
    @Transient
    private String nickname;
    @Transient
    private String company;
    @Transient
    private String address;
    @Expose
    @Column(name = "mobile")
    @Type(type = "text")
    private String mobilePhone;
    @Column(name ="work")
    @Type(type = "text")
    private String workPhone;
    @Column(name = "home")
    @Type(type = "text")
    private String homePhone;
    @Transient
    private String group;
    @Expose
    @Column(name = "email")
    @Type(type = "text")
    private String email;
    @Column(name = "email2")
    @Type(type = "text")
    private String email2;
    @Column(name = "email3")
    @Type(type = "text")
    private String email3;
    @Transient
    private String fullInfo;

    @Column(name = "photo")
    @Type(type = "text")
    private String photo;
    @Transient
    private String allEmails;


    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", allPhones='" + allPhones + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", nickname='" + nickname + '\'' +
                ", company='" + company + '\'' +
                ", address='" + address + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", workPhone='" + workPhone + '\'' +
                ", homePhone='" + homePhone + '\'' +
                ", group='" + group + '\'' +
                ", email='" + email + '\'' +
                ", email2='" + email2 + '\'' +
                ", email3='" + email3 + '\'' +
                ", fullInfo='" + fullInfo + '\'' +
                ", photo='" + photo + '\'' +
                ", allEmails='" + allEmails + '\'' +
                '}';
    }


    public String getAllEmails() {
        return allEmails;
    }




    public ContactData withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public ContactData withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public ContactData withNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public ContactData withCompany(String company) {
        this.company = company;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactData withMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }

    public ContactData withWorkPhone(String workPhone) {
        this.workPhone = workPhone;
        return this;
    }
    public ContactData withHomePhone(String homePhone) {
        this.homePhone = homePhone;
        return this;
    }

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }


    public ContactData(String firstname, String lastname, String nickname, String company, String address, String mobilePhone, String workPhone, String email, String group) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.nickname = nickname;
        this.company = company;
        this.address = address;
        this.mobilePhone = mobilePhone;
        this.workPhone = workPhone;
        this.email = email;
        this.group = group;
    }

    public ContactData() {
    }



    public int getId() {
        return id;
    }

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData data = (ContactData) o;

        if (id != data.id) return false;
        if (firstname != null ? !firstname.equals(data.firstname) : data.firstname != null) return false;
        return lastname != null ? lastname.equals(data.lastname) : data.lastname == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        return result;
    }

    /*public ContactData(int id, String firstname, String lastname) {
          this.id = id;

          this.firstname = firstname;
          this.lastname = lastname;
        }
      */
    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getNickname() {
        return nickname;
    }

    public String getCompany() {
        return company;
    }

    public String getAddress() {
        return address;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public String getEmail() {
        return email;
    }

    public String getGroup() {
        return group;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public ContactData withAllPhones(String phones) {
        this.allPhones = phones;
        return this;

    }

    public String getAllPhones() {
        return allPhones;
    }

    public ContactData withAllEmails(String emails) {
        this.allEmails = emails;
        return this;

    }

    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public String getEmail2() {
        return email2;
    }

    public ContactData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public String getEmail3() {
        return email3;
    }

    public ContactData withFullInfo(String fullinfo) {
        this.fullInfo = fullinfo;
        return this;
    }

    public String getFullInfo() {
        return fullInfo;
    }
    public File getPhoto() {
        return new File(photo);
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }
}

