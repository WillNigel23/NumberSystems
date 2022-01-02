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
        // Converts num to target base
        return Integer.toString(Integer.parseInt(num, base_from), base_to);
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
}