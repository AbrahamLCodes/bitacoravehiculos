package swtizona.androidapps.bpv.database;

import android.content.Context;

public class TestOperations {


    public void testInsertAuto(DataBaseController db){

        String [] rows = {"Ford","Ranger", "2007","2.3","EBM4789","El clutch llevalo con Carlos el carpa"};
        String [] rows1 = {"Dodge","RAM", "2003","4.7","JKL9890","La bomba del agua esta chingadona"};
        String [] rows2 = {"Chrysler","Town & Country", "2012","3.6","BNM7964","Le falla mucho la transmision"};
        String [] rows3 = {"Mitsubishi","Mirage", "2017","2.30","EMC1023","La burra de geo lo choco"};

        db.insert6Rows("AUTOS", rows);
        db.insert6Rows("AUTOS", rows1);
        db.insert6Rows("AUTOS", rows2);
        db.insert6Rows("AUTOS", rows3);
    }

}
