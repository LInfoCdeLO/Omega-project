public class ressort {
    private int positionx1;
    private int positionx2;
    private int positiony1;
    private int positiony2;
    private int raideur;
    private char accroche1;
    private char accroche2;
    private double vitessex1;
    private double vitessey1;
    private double vitessex2;
    private double vitessey2;
    
    
    
    public ressort( int x1, int y1, int x2, int y2, int raideurdef) {
        positionx1=x1;
        positionx2=x2;
        positiony1=y1;
        positiony2=y2;
        raideur=raideurdef;
        accroche1='f';
        accroche2='f';
    }
}
