import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProdutosDAO {
    
    public boolean venderProduto(int id) {
    String sql = "UPDATE uc11.produtos SET status = 'Vendido' WHERE id = ?";

    try {
        Connection conn = new conectaDAO().conectaBD();
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1, id);

        int rows = pst.executeUpdate();

        pst.close();
        conn.close();

        return rows > 0;
    } catch (Exception e) {
        return false;
    }
}


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

    public ArrayList<ProdutosDTO> listarProdutosVendidos() {
    String sql = "SELECT id, nome, valor, status FROM produtos WHERE status = 'Vendido'";
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

    } catch (Exception e) {
        e.printStackTrace();
    }

    return lista;
    }
}