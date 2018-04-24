package Main;

public class FixedElements {
	final static public double TAXRATE = 0.08875;
	final static public double PSCSTAX = 1.2;
	
	// categories
	final static public String REFILL = "Refill";
	final static public String ACTIVATION = "Activation";
	final static public String SERVICE = "Service";
	final static public String DEVICE = "Device";
	final static public String ACCESSORIES = "Accessories";
	final static public String PAYBILL = "PayBill";
	final static public String[] CATEGORIES = {REFILL, ACTIVATION, SERVICE, DEVICE, ACCESSORIES, PAYBILL};
	
	// carriers 
	final static public String LYCA = "Lyca Mobile";
	final static public String ULTRA = "Ultra Mobile";
	final static public String SIMPLE = "Simple Mobile";
	final static public String FLEX = "Flex";
	final static public String H2O = "H2O";
	final static public String TM = "T-Mobile";
	final static public String ATT = "AT&T";
	final static public String ROK = "ROK Mobile";
	final static public String TELCEL = "TelCel";
	final static public String GOSMART = "GoSmart";
	final static public String EASYGO = "EasyGo";
	final static public String NET10 = "Net10";
	final static public String CT = "CT Mobile";
	final static public String EOT = "EOT";
	final static public String REDPOCKET = "Red Pocket";
	final static public String OTHER = "OTHER";
	final static public String[] CARRIERS= {LYCA, ULTRA, SIMPLE,FLEX, H2O, TM, ATT, ROK, TELCEL, GOSMART, EASYGO, NET10, CT, EOT, REDPOCKET, OTHER};
	
	// group
	final static public String NORMAL = "Normal";
	final static public String SWAPF = "Swap Family";
	final static public String FLEXM = "Flex Mix";
	final static public String FLEXF = "Flex Family";
	final static public String GV = "GV";
	final static public String GVF = "GV Family";
	final static public String[] GROUP = {NORMAL, SWAPF, FLEX,FLEXF, FLEXM, GV, GVF}; 
	
	// group title
	final static public String PRIME = "Prime";
	final static public String PARENT = "Parent";
	final static public String CHILD = "Child";
	final static public String[] GROUPTITLE = {PRIME, PARENT, CHILD}; 
	
	// plan
	final static public String P$10 = "$10";
	final static public String P$15 = "$15";
	final static public String P$19 = "$19";
	final static public String P$20 = "$20";
	final static public String P$23 = "$23";
	final static public String P$25 = "$25";
	final static public String P$29 = "$29";
	final static public String P$30 = "$30";
	final static public String P$34 = "$34";
	final static public String P$35 = "$35";
	final static public String P$39 = "$39";
	final static public String P$40 = "$40";
	final static public String P$45 = "$45";
	final static public String P$49 = "$49";
	final static public String P$50 = "$50";
	final static public String P$55 = "$55";
	final static public String P$60 = "$60";
	final static public String P$65 = "$65";
	final static public String P$70 = "$70";
	final static public String P$75 = "$75";
	final static public String PSYG = "PayAsYouGo";
	final static public String UNKNOWN = "Unknown Plan";
	final static public String[] PLAN = {P$10, P$15, P$19,P$20, P$23, P$25, P$29, P$30, P$34, P$35, P$39, P$40, P$45, P$49, P$50,P$55, P$60,P$65,P$70,P$75, PSYG, UNKNOWN};
	
	// order status
	final static public String ORDERCOMPLETE = "Complete";
	final static public String REFUND = "Refund";
	final static public String UNPAID = "Unpaid";
	final static public String[] ORDERSTATUS = {ORDERCOMPLETE, REFUND, UNPAID};
	
	// activation status
	final static public String WAITING = "Waiting";
	final static public String INPROGRESS = "In Progress";
	final static public String PORTERROR = "Port Error";
	final static public String INCORRECTINFO = "Incorrect Info";
	final static public String COMPLETE = "Complete";
	final static public String CANCEL = "Cancel";
	final static public String[] ACTIVATIONSTATUS = {WAITING, INPROGRESS, PORTERROR, INCORRECTINFO, COMPLETE, CANCEL, REFUND}; 
	
	// payment methods
	final static public String CASH = "Cash";
	final static public String CREDIT = "Credit/Debit";
	final static public String BENEFIT = "Benefit Card";
	final static public String[] PAYMENTMETHOD = {CASH, CREDIT, BENEFIT, UNPAID};
	
	// action
	final static public String[] ACTION = {REFILL, ACTIVATION, CANCEL};
	
	// USA states
	final public static String[] STATES = {"Alaska",
											"Alabama",
											"Arkansas",
											"American Samoa",
            								"Arizona",
            								"California",
            								"Colorado",
            								"Connecticut",
            								"District of Columbia",
            								"Delaware",
            								"Florida",
            								"Georgia",
            								"Guam",
            								"Hawaii",
            								"Iowa",
            								"Idaho",
            								"Illinois",
            								"Indiana",
            								"Kansas",
            								"Kentucky",
            								"Louisiana",
            								"Massachusetts",
            								"Maryland",
            								"Maine",
            								"Michigan",
            								"Minnesota",
            								"Missouri",
            								"Mississippi",
            								"Montana",
            								"North Carolina",
            								" North Dakota",
            								"Nebraska",
            								"New Hampshire",
            								"New Jersey",
            								"New Mexico",
            								"Nevada",
            								"New York",
            								"Ohio",
            								"Oklahoma",
            								"Oregon",
            								"Pennsylvania",
            								"Puerto Rico",
            								"Rhode Island",
            								"South Carolina",
            								"South Dakota",
            								"Tennessee",
            								"Texas",
            								"Utah",
            								"Virginia",
            								"Virgin Islands",
            								"Vermont",
            								"Washington",
            								"Wisconsin",
            								"West Virginia",
											"Wyoming"};
}
