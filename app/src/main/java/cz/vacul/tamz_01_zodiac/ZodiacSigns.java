package cz.vacul.tamz_01_zodiac;

import android.widget.ImageView;
import android.widget.TextView;

import java.util.Date;

/**
 * Created by smuggler on 15.09.15.
 */
public enum ZodiacSigns {
    Aries(new Date(1970, 2, 21), new Date(1970, 3, 19), R.drawable.p1),
    Taurus(new Date(1970, 3, 20), new Date(1970, 4, 20), R.drawable.p2),
    Gemini(new Date(1970, 4, 21), new Date(1970, 5, 20), R.drawable.p3),
    Cancer(new Date(1970, 5, 21), new Date(1970, 6, 22), R.drawable.p4),
    Leo(new Date(1970, 6, 23), new Date(1970, 7, 22), R.drawable.p5),
    Virgo(new Date(1970, 7, 23), new Date(1970, 8, 22), R.drawable.p6),
    Libra(new Date(1970, 8, 23), new Date(1970, 9, 22), R.drawable.p7),
    Scorpio(new Date(1970, 9, 23), new Date(1970, 10, 21), R.drawable.p8),
    Sagittarius(new Date(1970, 10, 22), new Date(1970, 11, 21), R.drawable.p9),
    Capricorn(new Date(1970, 11, 22), new Date(1970, 11, 31), R.drawable.p10),
    Capricorn2(new Date(1970, 0, 1), new Date(1970, 0, 19), R.drawable.p10),
    Aquarius(new Date(1970, 0, 20), new Date(1970, 1, 18), R.drawable.p11),
    Pisces(new Date(1970, 1, 19), new Date(1970, 2, 20), R.drawable.p12);

    private Date startDate, endDate;
    private int drawable;

    private ZodiacSigns(Date startDate, Date endDate, int drawable){
        this.startDate = startDate;
        this.endDate = endDate;
        this.drawable = drawable;
    }

    private boolean isSign(Date d){
        return !(d.before(startDate) || d.after(endDate));
    }

    public static int getSign(Date bday){
        ZodiacSigns[] values = ZodiacSigns.values();
        for (int i = 0; i < values.length; i++){
            values[i].startDate.setYear(bday.getYear());
            values[i].endDate.setYear(bday.getYear());
            if (values[i].isSign(bday)) {
                return values[i].equals(Capricorn2) ? i - 1 : i;
            }
        }
        return 0;
    }

    public static void setSign(Date bday, TextView tv, ImageView iv){
        for (ZodiacSigns zs : ZodiacSigns.values()){
            zs.startDate.setYear(bday.getYear());
            zs.endDate.setYear(bday.getYear());
            if (zs.isSign(bday)) {
                tv.setText(zs.equals(Capricorn2) ? zs.name().substring(0, zs.name().length() - 1) : zs.name());
                iv.setImageResource(zs.drawable);
            }
        }
    }
}
