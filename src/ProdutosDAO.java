import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProdutosDAO {

    public boolean cadastrarProduto(ProdutosDTO dto) {
        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";

        try {
            Connection conn = new conectaDAO().conectaBD();
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, dto.getNome());
            pst.setInt(2, dto.getValor());
            pst.setString(3, dto.getStatus());

            pst.executeUpdate();

            pst.close();
            conn.close();
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    public ArrayList<ProdutosDTO> listarProdutos() {
        String sql = "SELECT id, nome, valor, status FROM uc11.produtos";
        ArrayList<ProdutosDTO> lista = new ArrayList<>();

        try {
            Connection conn = new conectaDAO().conectaBD();
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                ProdutosDTO dto = new ProdutosDTO();
                dto.setId(rs.getInt("id"));
                dto.setNome(rs.getString("nome"));
                dto.setValor(rs.getInt("valor"));
                dto.setStatus(rs.getString("status"));
                lista.add(dto);
            }

            rs.close();
            pst.close();
            conn.close();

        } catch (Exception e) {
          
        }

        return lista;
    }
}