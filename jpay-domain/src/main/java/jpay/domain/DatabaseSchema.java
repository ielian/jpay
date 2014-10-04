package jpay.domain;

import java.util.Arrays;

/**
 * DatabaseSchema
 *
 * enumeration of tables and columns for use with jdbc impl.
 */
public class DatabaseSchema
{
    public static enum CreditCardTable {
        TYPE_ID("type_id"),
        SLICE_START_TIME("slice_start_time"),
        EVENT_TIME("event_time"),
        EVENT_ID("event_id"),
        EVENT_PAYLOAD("event_payload");

        public final static String TABLE_NAME = "CREDIT_CARDS";
        public final String COLUMN_NAME;
        CreditCardTable (String name) {COLUMN_NAME = name;}
        public String toString () {return COLUMN_NAME;}
        public static String[] names () {
            return Arrays.toString(values()).replaceAll("\\[|]", "").split(", ");
        }
        public static String columns () {
            return Arrays.toString(values()).replaceAll("\\[|]", "");
        }
    }

    public static void main (String[] args)
    {
        int[] values = {1,2,3,4,5};
        String insertStmt = String.format("insert into %s(%s) values(%s)", CreditCardTable.TABLE_NAME, CreditCardTable.columns(), Arrays.toString(values).replaceAll("\\[|]",""));
        System.out.println(insertStmt);
    }

}