package lifeprotect.mock.forms;

public class MockForm {
    private int nbresident;
    private int bloodPressurepPb;
    private int diabeticPb;
    private int stepsPb;
    private int nbAthletic;

    public MockForm(int nbResident, int bloodPressurepPb, int diabeticPb, int stepsPb, int nbAthletic){
        this.nbresident = nbResident;
        this.bloodPressurepPb = bloodPressurepPb;
        this.diabeticPb = diabeticPb;
        this.stepsPb = stepsPb;
        this.nbAthletic = nbAthletic;
    }


    public MockForm(){

    }

    public int getNbresident() {
        return nbresident;
    }

    public void setNbresident(int nbresident) {
        this.nbresident = nbresident;
    }

    public int getBloodPressurepPb() {
        return bloodPressurepPb;
    }

    public void setBloodPressurepPb(int bloodPressurepPb) {
        this.bloodPressurepPb = bloodPressurepPb;
    }

    public int getDiabeticPb() {
        return diabeticPb;
    }

    public void setDiabeticPb(int diabeticPb) {
        this.diabeticPb = diabeticPb;
    }

    public int getStepsPb() {
        return stepsPb;
    }

    public void setStepsPb(int stepsPb) {
        this.stepsPb = stepsPb;
    }

    public int getNbAthletic() {
        return nbAthletic;
    }

    public void setNbAthletic(int nbAthletic) {
        this.nbAthletic = nbAthletic;
    }
}


