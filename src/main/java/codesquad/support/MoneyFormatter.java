package codesquad.support;

import java.text.NumberFormat;
import java.util.Locale;

public class MoneyFormatter {
    NumberFormat formatter;
    public MoneyFormatter(){
        formatter = NumberFormat.getInstance();
    }
    public String intToMoney(int money){
        return formatter.format(money);
    }
    public String longToMoney(long money){
        return formatter.format(money);
    }
}
