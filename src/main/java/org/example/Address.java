package org.example;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Address {
    private int streetNo;
    private String street;
    private String city;
    private Province province;
    private String postalCode;

    public enum Province {
        AL, BC, MA, NB, NL, NS, NT, NU, ON, PE, QC, SK, YU
    }

    /**
     * checks if postal code is valid
     * @param postalCode postal code input
     * @return if postal code is not of length 6 or is not CDCDCD then false, else is true
     */
    public static boolean isPostalCodeValid(String postalCode) {
        if (postalCode.length() != 6)
        {
            return false;
        }

        for (int i = 0; i < 6; i++) {
            char character = postalCode.charAt(i);
            if (i % 2 == 0)
            {
                if (!Character.isLetter(character))
                {
                    return false;
                }
            }
            else
            {
                if (!Character.isDigit(character))
                {
                    return false;
                }
            }
        }
        return true;
    }

    public Address(int streetNo, String street, String city, Province province, String postalCode) {
        if (isPostalCodeValid(postalCode))
        {
            this.streetNo = streetNo;
            this.street = street;
            this.city = city;
            this.province = province;
            this.postalCode = postalCode.toUpperCase();
        }
        else {
            this.streetNo = 0;
            this.street = null;
            this.city = null;
            this.province = null;
            this.postalCode = null;
        }
    }
}
