import dao.AlunoDAO;
import dao.LivroDAO;
import menu.MenuPrincipal;
import model.Aluno;
import model.Livro;
import utils.ConsoleUtils;

import java.sql.Date;
import java.sql.SQLException;
public class Main {
    public static void main(String[] args) throws SQLException {
        MenuPrincipal menuPrincipal = new MenuPrincipal();
        menuPrincipal.exibir();
    }
}
