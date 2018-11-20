package Graph;

public class Rate {

    private static final int CONST = 0;
    private static final int PERCENT = 1;

    Vertex vertexOut;
    double rateValue;
    int rateProvisionType;
    double rateProvision;

    Rate(Vertex vartexOut, double rateValue, String rateProvisionType, double rateProvision) {
        this.vertexOut = vartexOut;
        this.rateValue = rateValue;
        if ("PROC".equals(rateProvisionType)) {
            this.rateProvisionType = PERCENT;
        } else {
            this.rateProvisionType = CONST;
        }
        this.rateProvision = rateProvision;
    }

    double getProvision(double inValue) {
        
        if (rateProvisionType == PERCENT){
            return inValue * rateProvision ;
        } else{
            return rateProvision;
        }
    }

}
