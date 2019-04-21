/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentlist;

public class Address {

    String room;
    String streetNumber;
    String street;
    String city;
    String province;
    String postalCode;

    @Override
    public String toString() {
        return room + ", " + streetNumber + " " + street + ", " + city + ", " + province + ", " + postalCode;
    }

    public void deserialize(String s) {
        // TODO Auto-generated method stub
        String[] fields = s.split(",");
        this.room = fields[0];
        String[] subfields = fields[1].split(" ");
        this.streetNumber = subfields[1];
        this.street = subfields[2];
        this.city = fields[2];
        this.province = fields[3];
        this.postalCode = fields[4];

    }

}
