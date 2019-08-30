package com.tangu.tangucore.tcmts2.util.excel;

import org.apache.log4j.Logger;

import java.text.DecimalFormat;
import java.util.LinkedList;

public class FormulaParser {
	static Logger logger = Logger.getLogger(FormulaParser.class);
	private static int index = -1;

	public static void main(String[] args) {
		String  ss="[bb]hello,world[ss]";
		String[] fieldArray=ss.split("\\[[.]+\\]");
		for(int i=0;i<fieldArray.length;i++){
			logger.info(""+fieldArray[i]);
		}
//		logger.info(formularResultDouble("3.2*(2+3)/5-(1-2)*3 "));
//		String[] fieldArray=parseFieldArray("productName+productCode*totalQuantity-(totalRevenue-2)+3 ");
//		for(int i=0;i<fieldArray.length;i++){
//			logger.info(""+fieldArray[i]);
//		}
//		logger.info(formularResultDouble("1100.0+0.0-550.0"));
		
		DecimalFormat formater = new DecimalFormat("#0.##");
		 logger.info(formater.format(0.0897456));
	}
	public static String[] parseFieldArray(String fieldName){
		String ret="";
		if(fieldName.indexOf("+")>=0||fieldName.indexOf("-")>=0||fieldName.indexOf("*")>=0
				||fieldName.indexOf("/")>=0||fieldName.indexOf("(")>=0||fieldName.indexOf(")")>=0){
			ret=fieldName.replaceAll("[*+-/()]+", "##");
		}
		String[] fieldArray=ret.split("##");
		return fieldArray;
	}

	public static double formularResultDouble(String s) {
		LinkedList<Token> oper = new LinkedList<Token>();
		oper.addFirst(new Token('#', -1));
		LinkedList<Double> num = new LinkedList<Double>();
		String t = " ";
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(') {
				if (" ".equals(t) != true) {
					num.addFirst(new Double(t));
					t = " ";
				}
				oper.addFirst(new Token('(', 0));
			}
			if (s.charAt(i) == ')') {
				if (" ".equals(t) != true) {
					num.addFirst(new Double(t));
					t = " ";
				}
				while (true) {
					Token cur = oper.removeFirst();
					if (cur.c == '(') {
						break;
					}
					double d2 = num.removeFirst();
					double d1 = num.removeFirst();
					if (cur.c == '+') {
						num.addFirst(d1 + d2);
					}
					if (cur.c == '-') {
						num.addFirst(d1 - d2);
					}
					if (cur.c == '*') {
						num.addFirst(d1 * d2);
					}
					if (cur.c == '/') {
						if (d2 == 0) {
//							logger.info("除数为0 ");
							return 0;
//							System.exit(1);
						}
						num.addFirst(d1 / d2);
					}
				}
			}
			if (s.charAt(i) == '+') {
				if (" ".equals(t) != true) {
					num.addFirst(new Double(t));
					t = " ";
				}
				Token tnew = new Token('+', 1);
				while (true) {
					Token cur = oper.removeFirst();
					if (tnew.level > cur.level) {
						oper.addFirst(cur);
						oper.addFirst(tnew);
						break;
					} else {
						double d2 = num.removeFirst();
						double d1 = num.removeFirst();
						if (cur.c == '+') {
							num.addFirst(d1 + d2);
						}
						if (cur.c == '-') {
							num.addFirst(d1 - d2);
						}
						if (cur.c == '*') {
							num.addFirst(d1 * d2);
						}
						if (cur.c == '/') {
							if (d2 == 0) {
								logger.info("除数为0 ");
								return 0;
//								System.exit(1);
							}
							num.addFirst(d1 / d2);
						}
					}
				}
			}
			if (s.charAt(i) == '-') {
				if (" ".equals(t) != true) {
					num.addFirst(new Double(t));
					t = " ";
				}
				Token tnew = new Token('-', 1);
				while (true) {
					Token cur = oper.removeFirst();
					if (tnew.level > cur.level) {
						oper.addFirst(cur);
						oper.addFirst(tnew);
						break;
					} else {
						double d2 = num.removeFirst();
						double d1 = num.removeFirst();
						if (cur.c == '+') {
							num.addFirst(d1 + d2);
						}
						if (cur.c == '-') {
							num.addFirst(d1 - d2);
						}
						if (cur.c == '*') {
							num.addFirst(d1 * d2);
						}
						if (cur.c == '/') {
							if (d2 == 0) {
								logger.info("除数为0 ");
								return 0;//System.exit(1);
							}
							num.addFirst(d1 / d2);
						}
					}
				}

			}
			if (s.charAt(i) == '*') {
				if (" ".equals(t) != true) {
					num.addFirst(new Double(t));
					t = " ";
				}
				Token tnew = new Token('*', 2);
				while (true) {
					Token cur = oper.removeFirst();
					if (tnew.level > cur.level) {
						oper.addFirst(cur);
						oper.addFirst(tnew);
						break;
					} else {
						double d2 = num.removeFirst();
						double d1 = num.removeFirst();
						if (cur.c == '+') {
							num.addFirst(d1 + d2);
						}
						if (cur.c == '-') {
							num.addFirst(d1 - d2);
						}
						if (cur.c == '*') {
							num.addFirst(d1 * d2);
						}
						if (cur.c == '/') {
							if (d2 == 0) {
								logger.info("除数为0 ");
								return 0;//System.exit(1);
							}
							num.addFirst(d1 / d2);
						}
					}
				}

			}
			if (s.charAt(i) == '/') {
				if (" ".equals(t) != true) {
					num.addFirst(new Double(t));
					t = " ";
				}
				Token tnew = new Token('/', 2);
				while (true) {
					Token cur = oper.removeFirst();
					if (tnew.level > cur.level) {
						oper.addFirst(cur);
						oper.addFirst(tnew);
						break;
					} else {
						double d2 = num.removeFirst();
						double d1 = num.removeFirst();
						if (cur.c == '+') {
							num.addFirst(d1 + d2);
						}
						if (cur.c == '-') {
							num.addFirst(d1 - d2);
						}
						if (cur.c == '*') {
							num.addFirst(d1 * d2);
						}
						if (cur.c == '/') {
							if (d2 == 0) {
								logger.info("除数为0 ");
								return 0;//System.exit(1);
							}
							num.addFirst(format(d1/d2));
						}
					}
				}

			}
			if (s.charAt(i) >= '0' && s.charAt(i) <= '9' || s.charAt(i) == '.') {
				t += s.charAt(i);
			}

		}
		if (" ".equals(t) != true) {
			num.addFirst(new Double(t));
		}
		while (oper.size() > 1) {
			Token cur = oper.removeFirst();
			double d2 = num.removeFirst();
			double d1 = num.removeFirst();
			if (cur.c == '+') {
				logger.info("**"+(d1 + d2));
				num.addFirst(d1 + d2);
			}
			if (cur.c == '-') {
				num.addFirst(d1 - d2);
			}
			if (cur.c == '*') {
				num.addFirst(d1 * d2);
			}
			if (cur.c == '/') {
				if (d2 == 0) {
					//logger.info("除数为0 ");
					return 0;//System.exit(1);
				}
				num.addFirst(format(d1/d2));
			}

		}

		return num.getFirst();

	}
	
	public static Double format(Double num){
		DecimalFormat formater = new DecimalFormat("#0.##");
		return Double.valueOf(formater.format(num));
	}
}

class Token {
	public char c;
	public int level;// 运算优先级 ：(:0 +:1 -:1 *:2 /:2 ):3

	public Token(char c, int level) {
		this.c = c;
		this.level = level;
	}

	@Override
	public String toString() {
		return " " + c + "   " + level;
	}

}
