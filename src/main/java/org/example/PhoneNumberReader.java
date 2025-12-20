package org.example;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class PhoneNumberReader {

    public void readPhoneNumber(String filePath) {
        readPhoneNumber(new File(filePath));
    }
    public void readPhoneNumber(File file) {

        try( FileReader reader = new FileReader(file)) {

            List<String> buffer;

            if ((buffer = reader.readAllLines()) != null) {

                for (int i = 0; i < buffer.size(); i++) {
                    if (isTruePhonNumber(buffer.get(i))) {
                        System.out.println(buffer.get(i));
                    }
                }
            }
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    private boolean isTruePhonNumber(String phoneNumber) {

        if(phoneNumber.length() == 12) {

            return phoneNumber.charAt(3) == '-' && phoneNumber.charAt(7) == '-';
        }
        if(phoneNumber.length() == 14) {

            return phoneNumber.charAt(0) == '(' && phoneNumber.substring(4,6).equals(") ") && phoneNumber.charAt(9) == '-';
        }

        return false;
    }
}
