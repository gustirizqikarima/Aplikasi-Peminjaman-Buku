/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplikasi;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Gusti Rizqi Karima
 */
public class Config {
    private static final String URL = "jdbc:mysql://localhost:3306/nusabacafull";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    
    //Metode untuk menulis log ke file
    public static void writeLog(String message){
        //Menambahkan timestamp pada log
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timestamp = LocalDateTime.now().format(formatter);
        
        //kita coba try-cacth dua variabel hasil instance yaitu fwLog dan pwLog
        try (FileWriter fwLog = new FileWriter("log.txt", true);
        PrintWriter pwLog = new PrintWriter(fwLog);){
            pwLog.println(timestamp + "-" + message);
            System.out.println("Log berhasil ditulis.");
        } catch (IOException e) {
            System.out.println("Gagal menulis log:" + e.getMessage());
        }
    }
    
    public static Connection configDB(){
        Connection mySQLConfig = null;
        //Mencoba koneksi ke database
        try {
            mySQLConfig = DriverManager.getConnection(URL, USER, PASSWORD);
            if(mySQLConfig.isValid(2)){ // Cek validasi koneksi dengan timeout 2 detik
                writeLog("Koneksi berhasil ke database"); //Jika koneksi berhasil
            }
        } catch (SQLException e) {
            writeLog("Koneksi gagal:" + e.getMessage()); //Jika koneksi gagal
        }
        return mySQLConfig;
    }
}