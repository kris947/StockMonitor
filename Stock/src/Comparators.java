
import java.util.*;

public class Comparators {

    public static Comparator<Transaction> PriceComparator = new Comparator<Transaction>() {
        @Override
        public int compare(Transaction o1, Transaction o2) {
        	if (o1.getprice() == o2.getprice())
        		return 0;
        	else if (o1.getprice() > o2.getprice())
        		return 1;
        	else if (o2.getprice() > o1.getprice())
        		return -1;
        	else
        		return 0;
        }
    };
        public static Comparator<Transaction> TimeComparator = new Comparator<Transaction>() {
            @Override
            public int compare(Transaction o1, Transaction o2) {
            	int b=o1.getTime().compareTo(o2.getTime());
            	if (b==0)
            		return 0;
            	else if (b>0)
            		return 1;
            	else if (b<0)
            		return -1;
            	else
            		return 0;
            }
    };   
}
