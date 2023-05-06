package pl.sda.bankapp.mapper;

public class CSVMapper {

    public static final String DELIMITER = ";";

    public final static String ACCOUNTS_HEADER =
            "ID; Account Number; Currency; Account Type; Current Amount; Customer ID";

    public final static String CUSTOMERS_HEADER =
            "ID; Name; Surname; Phone; Email; Pesel; Age; Date of birth; City; Street; Post code";

    public static class AccountColumns {
        private static int index = 0;

        public static int ID = index++;
        public static int ACCOUNT_NUMBER = index++;
        public static int CURRENCY = index++;
        public static int ACCOUNT_TYPE = index++;
        public static int CURRENT_AMOUNT = index++;
        public static int CUSTOMER_ID = index;
    }

    public static class CustomerColumns {
        private static int index = 0;

        public static int ID = index++;
        public static int NAME = index++;
        public static int SURNAME = index++;
        public static int PHONE = index++;
        public static int EMAIL = index++;
        public static int PESEL = index++;
        public static int AGE = index++;
        public static int DATE_OF_BIRTH = index++;
        public static int CITY = index++;
        public static int STREET = index++;
        public static int POST_CODE = index;
    }

}
