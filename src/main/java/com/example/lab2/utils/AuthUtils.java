package com.example.lab2.utils;

import com.example.lab2.AppManager;
import com.example.lab2.objects.AuthData;
import com.example.lab2.objects.Student;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.Date;
import java.util.Random;

public class AuthUtils {
    public static void generateStudentAuthData(Student student) {
        String email = generateEmail(student.getLastName(), student.getFirstName(), student.getDateOfBirth());
        String password = generatePassword();

        String hash = BCrypt.hashpw(password, BCrypt.gensalt());

        AuthData authData = new AuthData(email, hash, 2 /* type of user = student */);
        AppManager.getAuthDataDao().insert(authData);

        MailSenderUtils.sendPassword(email, password);
    }

//    public static void generateTeacherAuthData(Teacher teacher) {
//        String email = generateEmail(teacher.getLastName(), teacher.getFirstName(), teacher.getDateOfBirth());
//        String password = generatePassword();
//
//        String hash = BCrypt.hashpw(password, BCrypt.gensalt());
//
//        AuthData authData = new AuthData(email, hash, 1 /* type of user = teacher */);
//        AppManager.getAuthDataDao().insert(authData);
//
//        MailSenderUtils.sendPassword(email, password);
//    }

    public static void regeneratePassword(AuthData authData) {
        String password = generatePassword();
        String hash = BCrypt.hashpw(password, BCrypt.gensalt());
        authData.setPassword(hash);
        AppManager.getAuthDataDao().update(authData);

        MailSenderUtils.sendPassword(authData.getEmail(), password);
    }

    public static String generateEmail(String lastName, String firstName, Date dateOfBirth) {
        //TODO add pattern of email
        return  convertRusToEng(lastName).toLowerCase() + "." +
                convertRusToEng(firstName).toLowerCase().charAt(0) + "." +
                DateUtils.getYear(dateOfBirth) + "@stud.nstu.ru";
    }

    public static String convertRusToEng(String word) {
        char[] lettersRus  = {'а','б','в','г','д','е','ё', 'ж','з','и','й','к','л','м','н','о','п','р','с','т','у','ф','х', 'ц', 'ч', 'ш',  'щ','ъ','ы','ь','э', 'ю', 'я','А','Б','В','Г','Д','Е','Ё', 'Ж','З','И','Й','К','Л','М','Н','О','П','Р','С','Т','У','Ф','Х', 'Ц', 'Ч', 'Ш',  'Щ','Ъ','Ы','Ь','Э', 'Ю', 'Я'};
        String[] lettersEn = {"a","b","v","g","d","e","e","zh","z","i","y","k","l","m","n","o","p","r","s","t","u","f","h","ts","ch","sh","sch", "","i", "","e","ju","ja","A","B","V","G","D","E","E","Zh","Z","I","Y","K","L","M","N","O","P","R","S","T","U","F","H","Ts","Ch","Sh","Sch", "","I", "","E","Ju","Ja"};
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            for (int j = 0; j < lettersRus.length; j++) {
                if (word.charAt(i) == lettersRus[j] || word.charAt(i) == lettersEn[j].charAt(0)) {
                    string.append(lettersEn[j]);
                    break;
                }
            }
        }
        return string.toString();
    }

    public static String generatePassword() {
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!@#$%&*_+-.?;:";
        StringBuilder password = new StringBuilder();
        int length = 8;
        Random rnd = new Random();

        while (password.length() < length) {
            int index = (int) (rnd.nextFloat() * chars.length());
            password.append(chars.charAt(index));
        }
        return password.toString();
    }
}
