package com.fpo.web.utils;

import java.text.Format;
import java.util.Locale;

import com.ibm.icu.math.BigDecimal;
import com.ibm.icu.text.DecimalFormat;

public class ApplicationStringUtils {

    // convert INR Format to Amount

    public static String convertINRFormatToAmount(String strInputAmount) {
        String amount = strInputAmount.replaceAll(",", "");
        return amount;
    }

    public static String convertAmountToINRFormat(String strInputAmount)
    {
        if(strInputAmount.equals(""))
            strInputAmount = "0.00";
        String amountToBeConverted = "";
        String finalFormattedAmount = "";
        String decimalValue = "";

        DecimalFormat df1 = new DecimalFormat("#");
        df1.setMaximumFractionDigits(2);
        strInputAmount = df1.format(Double.parseDouble(strInputAmount));

        if(strInputAmount.indexOf(".") != -1)
        {
            amountToBeConverted = strInputAmount.substring(0,strInputAmount.indexOf("."));
            decimalValue = strInputAmount.substring(strInputAmount.indexOf(".")+1);
            if(decimalValue.length()==0)	decimalValue = decimalValue+"00";
            if(decimalValue.length()==1)	decimalValue = decimalValue+"0";
        }
        else
        {
            amountToBeConverted = strInputAmount;
            decimalValue = "00";
        }

        StringBuilder stringBuilder = new StringBuilder();
        char amountArray[] = amountToBeConverted.toCharArray();
        int length1= 0, length2=0;
        for (int i=amountArray.length-1; i>=0; i--)
        {
            if (length1 < 3)
            {
                stringBuilder.append(amountArray[i]);
                length1++;
            }
            else if (length2 < 2)
            {
                if (length2 == 0)
                {
                    stringBuilder.append(",");
                    stringBuilder.append(amountArray[i]);
                    length2++;
                }
                else
                {
                    stringBuilder.append(amountArray[i]);
                    length2 = 0;
                }
            }
        }

        finalFormattedAmount = stringBuilder.reverse().append(".").append(decimalValue).toString();

        if(foundScientificNotation(finalFormattedAmount))
        {
            finalFormattedAmount = new BigDecimal(finalFormattedAmount).toString();
            Format format = com.ibm.icu.text.NumberFormat.getCurrencyInstance(new Locale("en", "in"));
            finalFormattedAmount = format.format(new BigDecimal(finalFormattedAmount));
        }
        if(finalFormattedAmount.contains("Rs."))
            finalFormattedAmount = finalFormattedAmount.replaceAll("Rs.", "");

        return finalFormattedAmount.trim();
    }

    public static boolean foundScientificNotation(String numberString)
    {
        try
        {
            new BigDecimal(numberString);
        }
        catch (NumberFormatException e)
        {
            return false;
        }
        return numberString.toUpperCase().contains("E");
    }

//	public static String convertDoubleToTwoDecimal(Double dblAmountToConvert)
//	{
//		String decimalValue = "";
//		String strAmountToConvert = "";
//
//		if(ConvertAmountToWords.isScientificNotation(dblAmountToConvert.toString())){
//			strAmountToConvert = new BigDecimal(dblAmountToConvert.toString()).toString();
//		}else{
//			strAmountToConvert = String.valueOf(dblAmountToConvert);
//		}
//
//		if(strAmountToConvert.indexOf(".") > -1)
//		{
//			decimalValue = strAmountToConvert.substring(strAmountToConvert.indexOf(".")+1);
//			if(decimalValue.length()==1)
//			{
//				strAmountToConvert = strAmountToConvert + "0";
//			}
//		}
//		else
//			strAmountToConvert = strAmountToConvert + ".00";
//
//		return strAmountToConvert;
//	}

}
