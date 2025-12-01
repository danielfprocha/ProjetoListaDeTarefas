import java.util.Scanner;

import Classes.Usuario;
import Menus.MenuLogin;
import Menus.MenuPrincipal;

public class Main {
    
    public static void main(String[] args) {

        Scanner teclado = new Scanner (System.in);
        MenuLogin menuLogin = new MenuLogin(teclado);

        while (true) {
            Usuario usuario = menuLogin.login();
            if (usuario == null) {
                System.out.println("\nNenhum usuário encontrado.\n\nFim do Programa!");
                break;
            }
            MenuPrincipal principal = new MenuPrincipal(teclado, usuario);
            principal.exibirPaginaPrincipal();
        }

        teclado.close();
    }
}
