package user.domain;


import common.buisness.domain.BaseDomain;
import order.domain.Order;

public class User extends BaseDomain {
    private String name;
    private String last;

    public long getPassportID() {
        return passportID;
    }

    public void setPassportID(long passportID) {
        this.passportID = passportID;
    }

    private long passportID;
    private Passport passport;
    private ClientType clientType;

    private Order[] orders;

    public User(){}

    public User(String name, String last, Passport passport) {
        this.name = name;
        this.last = last;
        this.passport = passport;
    }

    public User(String name, String last){
        this.name = name;
        this.last = last;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    public void setClientType(ClientType clientType) {
        this.clientType = clientType;
    }

    public ClientType getClientType() {
        return clientType;
    }

    @Override
    public String toString() {
        if(passport != null) {
            return "name: " + name + "\nlast name: " + last + "\n" + passport;
        }else{
            return "name: " + name + "\nlast name: " + last;
        }
    }
}

class Passport{
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private String series;
    private String number;

    public Passport(String series, String number) {
        this.series = series;
        this.number = number;
    }


    @Override
    public String toString() {
        return "Passport: serial = " + series + ", number = " + number;
    }
}
