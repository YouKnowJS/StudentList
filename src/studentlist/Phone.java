/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentlist;

public class Phone {

    String countryCode = "001";
    String areaCode = "647";
    String code = "0000000";

    @Override
    public String toString() {
        return countryCode + "-" + "(" + areaCode + ")" + code;
    }

    void deserialize(String s) {
        this.countryCode = s.substring(0, 3);
        this.areaCode = s.substring(5, 8);
        this.code = s.substring(9, 16);

    }

}
