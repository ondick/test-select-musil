import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        //1. Ukol
        final String URL = "jdbc:mysql://localhost/archeologie";
        final String USER = "root";
        final String PASSWORD = "";


        //2. Ukol
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("Připojení bylo úspěšné.");

            String query = "SELECT nalezy.Nazev, nalezy.Material, nalezy.Stari FROM archeologie.nalezy ORDER BY nalezy.Stari ASC";

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                ResultSet rs = stmt.executeQuery();
                System.out.println(rs.next());
                while (rs.next()) {
                    String nazev = rs.getString("Nazev");
                    String material = rs.getString("Material");
                    String stari = rs.getString("Stari");
                    System.out.println("Nazev: " + nazev + ", Material: " + material + ", Stari: " + stari);

                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        } catch (SQLException e) {
            System.err.println("Nebylo úspěšně připojeno");
        }
    //3. Ukol
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("Připojení bylo úspěšné.");

            System.out.println("Napis material:");
            String matro = sc.nextLine();
            System.out.println("Napis minimalni hodnotu:");
            String minimal = sc.nextLine();


            String query1 = "SELECT nalezy.Nazev, nalezy.Hodnota FROM archeologie.nalezy WHERE Material = ? AND Hodnota >= ?";
            try (PreparedStatement stmt1 = conn.prepareStatement(query1)) {
                stmt1.setString(1, matro);
                stmt1.setString(2, minimal);
                ResultSet rs1 = stmt1.executeQuery();
                boolean nalezeno = false;
                while (rs1.next()) {
                    nalezeno = true;
                    String nazev1 = rs1.getString("Nazev");
                    String hodnota1 = rs1.getString("Hodnota");
                    System.out.println("Nazev: " + nazev1 + ", Hodnota: " + hodnota1);

                }
                if (!nalezeno) {
                    System.out.println("Nenalezen material");
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        } catch (SQLException e) {
            System.err.println("Nebylo úspěšně připojeno");
        }


        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("Připojení bylo úspěšné.");
            String query2 = "SELECT nalezy.Material FROM archeologie.nalezy";
            int zlato = 0;
            int keramika = 0;
            int stribro = 0;
            int kamen = 0;
            int zelezo = 0;
            int bronz = 0;
            try (PreparedStatement stmt = conn.prepareStatement(query2)) {
                ResultSet rs = stmt.executeQuery();
                System.out.println(rs.next());

                while (rs.next()) {
                    String material = rs.getString("Material");
                    if (material == "zlato") {
                        zlato ++;
                    }
                    else if (material == "keramika") {
                        keramika ++;
                    }
                    else if (material == "stříbro") {
                        stribro ++;
                    }
                    else if (material == "kámen") {
                        kamen ++;
                    }
                    else if (material == "železo") {
                        zelezo ++;
                    }
                    else if (material == "bronz") {
                        bronz ++;
                    }
                    System.out.println("Zlato: " + zlato+ "nálezy");
                    System.out.println("Keramika: " + keramika+ "nálezy");
                    System.out.println("Stribro: " + stribro+ "nálezy");
                    System.out.println("Kamen: " + kamen+ "nálezy");
                    System.out.println("Zelezo: " + zelezo+ "nálezy");
                    System.out.println("Bronz: " + bronz+ "nálezy");


                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }



        } catch (SQLException e) {
            System.err.println("Nebylo úspěšně připojeno");
        }
    }
}