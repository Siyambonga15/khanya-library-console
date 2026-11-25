package za.co.khanya.library.domain;

public class MembershipType {

    private int memberLimit;


    public MembershipType( String membershipType) {

        if (membershipType.equals ("Standard")){
            memberLimit = 1;
        }else if (membershipType.equals ("Premium")){
            memberLimit = 3;
        }


    }


public int getMemberLimit() {

    return memberLimit;

    }
}
