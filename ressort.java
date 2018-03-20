public class ressort {
    public int positionx1;
    public int positionx2;
    public int positiony1;
    public int positiony2;
    public int raideur;
    public char accroche1;
    public char accroche2;
    public int[] point1=new int [2];
    public int[] point2=new int [2];
    private double vitessex1;
    private double vitessey1;
    private double vitessex2;
    private double vitessey2;
    
    
    
    public ressort( int x1, int y1, int x2, int y2, int raideurdef) {
        positionx1=x1-8;
        positionx2=x2-8;
        positiony1=y1-50;
        positiony2=y2-50;
        point1[0]=positionx1;point1[1]=positiony1;
        point2[0]=positionx2;point2[1]=positiony2;
        raideur=raideurdef;
        accroche1='e';
        accroche2='e';
    }
}
