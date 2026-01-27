import java.sql.Connection;
import java.sql.PreparedStatement;

public class ProdutosDAO {

    public boolean cadastrarProduto(ProdutosDTO dto) {
        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";

        try (
            Connection conn = new conectaDAO().conectaBD();
            PreparedStatement pst = conn.prepareStatement(sql)
        ) {

            pst.setString(1, dto.getNome());
            pst.setInt(2, dto.getValor());
            pst.setString(3, dto.getStatus());

            int rows = pst.executeUpdate();
            return rows > 0;

        } catch (Exception e) {
            return false;
        }
    }
}