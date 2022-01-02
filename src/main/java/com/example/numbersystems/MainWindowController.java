/**
 * Number Systems.
 * Calculator to solve the sum and product of two numbers (in base 2, 10, or 16)
 * and displays the answers in formatted binary, decimal, and hexadecimal. Application is with
 * GUI made with javafx.
 *
 * @author Will Nigel De Jesus
 * @version 1
 */

package com.example.numbersystems;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;


public class MainWindowController implements Initializable{
    // Connect FXML elements to controller.
    @FXML ComboBox<String> cmbBase1, cmbBase2;
    @FXML Button btnSolve;
    @FXML TextField txt1, txt2;
    @FXML Label lblSumBin, lblSumDec, lblSumHex, lblProdBin, lblProdDec, lblProdHex;

    /**
     * Initializes fxml elements and values.
     * @param url url
     * @param reso reso
     */
    @Override
    public void initialize(URL url, ResourceBundle reso) {
        // Set Values of Dropdowns.
        cmbBase1.setItems(FXCollections.observableArrayList("Binary", "Decimal", "Hexadecimal"));
        cmbBase1.getSelectionModel().selectFirst();
        cmbBase2.setItems(FXCollections.observableArrayList("Binary", "Decimal", "Hexadecimal"));
        cmbBase2.getSelectionModel().selectFirst();

        // Calls Solve function on mouse click event/
        btnSolve.setOnMouseClicked(mouseEvent -> solve());
    }

    /**
     * Function dedicated to solving for the sum and product of the inputs.
     */
    public void solve() {
        // Convert inputs to base 10
        String initVal1 = baseConversion(txt1.getText(), getBaseDrp(cmbBase1.getValue()), 10);
        String initVal2 = baseConversion(txt2.getText(), getBaseDrp(cmbBase2.getValue()), 10);

        // Solve for sum in base 10
        String sumDec = Integer.toString(Integer.parseInt(initVal1) + Integer.parseInt(initVal2));
        // Convert sum to base 2
        String sumBin = baseConversion(sumDec, 10, 2);
        // Convert sum to base 16
        String sumHex = baseConversion(sumDec, 10, 16);

        // Solve for product in base 10
        String prodDec = Integer.toString(Integer.parseInt(initVal1) * Integer.parseInt(initVal2));
        // Convert product to base 2
        String prodBin = baseConversion(prodDec, 10, 2);
        // Convert product to base 16
        String prodHex = baseConversion(prodDec, 10, 16);

        //Format Outputs
        sumBin = formatOutput(sumBin, 8);
        sumHex = formatOutput(sumHex, 4);
        prodBin = formatOutput(prodBin, 8);
        prodHex = formatOutput(prodHex, 4);

        // Output to labels
        lblSumBin.setText(sumBin);
        lblSumDec.setText(sumDec);
        lblSumHex.setText(sumHex);
        lblProdBin.setText(prodBin);
        lblProdDec.setText(prodDec);
        lblProdHex.setText(prodHex);

    }

    /**
     * Gets the int value of the chosen option of base on the dropdown.
     * @param base String/word that represents the base the user chose on the dropdown.
     * @return The integer value of the chosen base.
     */
    public int getBaseDrp(String base) {
        // Gets string value of combobox and returns int base
        if(base == "Binary") {
            return 2;
        }
        else if (base == "Decimal") {
            return 10;
        }
        else if (base == "Hexadecimal") {
            return 16;
        }
        else {
            return 0;
        }
    }

    /**
     * Accepts a string number in its indicated base and converts it to target base.
     * @param num String number to be converted
     * @param base_from Initial base of the number to be converted
     * @param base_to Target base of the number to be converted
     * @return The converted number in string.
     */
    public String baseConversion(String num, int base_from, int base_to) {
        if(base_from == 2) {
            //Case Binary
            if(base_to == 2) {
                // Binary to Binary conversion will yield same string
                return num;
            }
            else if(base_to == 10) {
                // Converts Binary to Decimal
                return binaryToDecimal(num);
            }
            else if(base_to == 16) {
                // Converts Binary to Hexadecimal
                return binaryToHexadecimal(num);
            }
        }
        else if(base_from == 10) {
            //Case Decimal
            if(base_to == 2) {
                // Converts Decimal to Binary
                return decimalToBinary(num);
            }
            else if(base_to == 10) {
                // Decimal to Decimal conversion will yield same string
                return num;
            }
            else if(base_to == 16) {
                // Converts Decimal to Hexadecimal
                return decimalToHexadecimal(num);
            }
        }
        else if(base_from == 16) {
            //Case Hexadecimal
            if(base_to == 2) {
                // Converts Hexadecimal to Binary
                return hexadecimalToBinary(num);
            }
            else if(base_to == 10) {
                // Converts Hexadecimal to Decimal
                return hexadecimalToDecimal(num);
            }
            else if(base_to == 16) {
                // Hexadecimal to Hexadecimal will yield same string
                return num;
            }
        }

        // Invalid Case
        return null;
    }

    /**
     * Helper function which converts Binary To Decimal
     * @param num String Binary to be converted to Decimal
     * @return the converted number in string
     */
    public String binaryToDecimal(String num) {
        // Initialize decimal value to 0
        int dec = 0;
        // Sets current exponent to the leftmost digit
        int curPower = num.length() - 1;
        // Loop through the string binary from left to right and perform necessary operation
        for(int i = 0; i < num.length(); i++) {
            // Case where binary digit is 1
            if(num.charAt(i) == '1') {
                dec += Math.pow(2, curPower);
                curPower--;
            }
            // Case where binary digit is 0
            else if(num.charAt(i) == '0') {
                curPower--;
            }
            // Case where binary digit is neither 1 or 0
            else {
                // Invalid Input
                inputError();
            }
        }
        // Converts integer to string to return
        return Integer.toString(dec);
    }

    /**
     * Helper function which converts Binary To Hexadecimal
     * @param num String Binary to be converted to Hexadecimal
     * @return the converted number in string
     */
    public String binaryToHexadecimal(String num) {
        // Convert to Decimal
        num = binaryToDecimal(num);
        // Convert Decimal To Hexadecimal and return
        return decimalToHexadecimal(num);
    }

    /**
     * Helper function which converts Decimal To Binary
     * @param num String Decimal to be converted to Binary
     * @return the converted number in string
     */
    public String decimalToBinary(String num) {
        // Convert string number to integer
        int dec = Integer.parseInt(num);
        // Initialize StringBuilder that will contain final converted number
        StringBuilder newNum = new StringBuilder();
        // Repeatedly divide and convert remainder to string until decimal reaches 0
        // String will be in reverse
        while(dec != 0) {
            newNum.append(dec % 2);
            dec /= 2;
        }
        // Reverses string to achieve correct output before return
        return newNum.reverse().toString();
    }

    /**
     * Helper function which converts Decimal To Hexadecimal
     * @param num String Decimal to be converted to Hexadecimal
     * @return the converted number in string
     */
    public String decimalToHexadecimal(String num) {
        // Convert string number to integer
        int dec = Integer.parseInt(num);
        // Initialize StringBuilder that will contain final converted number
        StringBuilder newNum = new StringBuilder();
        // Repeatedly divide and convert remainder to string until decimal reaches 0
        // String will be in reverse
        while(dec != 0) {
            // Makes use of convertIntToHexChar function to get appropriate hex character from its integer value
            newNum.append(convertIntToHexChar(dec % 16));
            dec /= 16;
        }
        // Reverses string to achieve correct output before return
        return newNum.reverse().toString();
    }

    /**
     * Helper function which converts Hexadecimal To Binary
     * @param num String Hexadecimal to be converted to Binary
     * @return the converted number in string
     */
    public String hexadecimalToBinary(String num) {
        // Convert Hexadecimal to Decimal
        num = hexadecimalToDecimal(num);
        // Convert Decimal to Binary
        return decimalToBinary(num);
    }

    /**
     * Helper function which converts Hexadecimal To Decimal
     * @param num String Hexadecimal to be converted to Decimal
     * @return the converted number in string
     */
    public String hexadecimalToDecimal(String num) {
        // Initialize decimal value to 0
        int dec = 0;
        // Sets current exponent to the leftmost digit
        int curPower = num.length() - 1;
        // Loop through the string hexadecimal from left to right and perform necessary operation
        for(int i = 0; i < num.length(); i++) {
            // Makes use of convertCharToHexInt function to get appropriate integer value from its hex character
            dec += (convertCharToHexInt(num.charAt(i))) * Math.pow(16, curPower);
            curPower--;
        }
        // Converts integer to string before return
        return Integer.toString(dec);
    }

    /**
     * Helper function which converts integer value to its equivalent hexadecimal character
     * @param num the integer to be converted
     * @return the appropriate hexadecimal character
     */
    char convertIntToHexChar(int num) {
        if(num < 10) {
            // Integers from 0 to 9 can be converted to appropriate HEX characters by adding 48
            return (char)(num + 48);
        }
        else if (num >= 10 && num < 16){
            // Integers from 10 to 15 can be converted to appropriate HEX characters by adding 55
            return (char)(num + 55);
        }
        else {
            // If integer exceeds 15, then invalid input
            inputError();
            return '#';
        }
    }

    /**
     * Helper function which converts hexadecimal character to its equivalent integer value
     * @param num the hexadecimal character to be converted
     * @return the appropriate integer value
     */
    int convertCharToHexInt(char num) {
        if(num >= '0' && num <= '9') {
            // Characters from '0' to '9' can be converted to appropriate integer value by subtracting 48
            return num - 48;
        }
        else if (num >= 'A' && num <= 'F') {
            // Characters from 'A' to 'F' can be converted to appropriate integer value by subtracting 55
            return num - 55;
        }
        else {
            // If char is greater than F, then invalid input;
            inputError();
            return '#';
        }
    }

    /**
     * Formats the output by adding necessary paddings and spaces.
     * @param num The input number to be formatted
     * @param groupBy Integer value that indicates by how many digits the number is to be grouped.
     * @return Formatted number.
     */
    public String formatOutput(String num, int groupBy) {
        // Format output

        if(num.length() % groupBy != 0) {
            // Checks if it is necessary to add leading 0.
            // If the length of input number is not divisible by the count of digit-grouping,
            // Then it is necessary to add leading 0s.
            int pad = (num.length() + groupBy) - (num.length() % groupBy);
            num = String.format("%" + pad + "s", num).replace(' ', '0');
        }
        // Groups the digits by the groupBy value.
        return(num.replaceAll("(.{" + groupBy + "})", "$1 ").trim());
    }


    /**
     * Function that handles input errors
     */
    public void inputError() {
        // Display on the program Invalid Input to notify users of the error
        lblSumBin.setText("Invalid Input");
        lblSumDec.setText("Invalid Input");
        lblSumHex.setText("Invalid Input");
        lblProdBin.setText("Invalid Input");
        lblProdDec.setText("Invalid Input");
        lblProdHex.setText("Invalid Input");

        // Throws IllegalArgumentException
        throw new IllegalArgumentException();
    }
}