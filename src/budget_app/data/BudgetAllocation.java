package budget_app.data;

public class BudgetAllocation {
    private double groceries;
    //housing will contain internet electricity, water and other maintenance;
    private double housing;
    private double basicUtilities;
    private double transport;
    private double insurance;

    public BudgetAllocation() {
    }

    public BudgetAllocation(double groceries, double housing, double basicUtilities, double transport, double insurance) {
        this.groceries = groceries;
        this.housing = housing;
        this.basicUtilities = basicUtilities;
        this.transport = transport;
        this.insurance = insurance;
    }

    public double getGroceries() {
        return groceries;
    }
    public void setGroceries(double groceries) {
        this.groceries = groceries;
    }

    public double getHousing() {
        return housing;
    }
    public void setHousing(double housing) {
        this.housing = housing;
    }

    public double getBasicUtilities() {
        return basicUtilities;
    }
    public void setBasicUtilities(double basicUtilities) {
        this.basicUtilities = basicUtilities;
    }

    public double getTransport() {
        return transport;
    }
    public void setTransport(double transport) {
        this.transport = transport;
    }

    public double getInsurance() {
        return insurance;
    }
    public void setInsurance(double insurance) {
        this.insurance = insurance;
    }

    @Override
    public String toString() {
        return "BudgetAllocation{" +
                "groceries=" + groceries +
                ", housing=" + housing +
                ", basicUtilities=" + basicUtilities +
                ", transport=" + transport +
                ", insurance=" + insurance +
                '}';
    }
}
