import java.util.ArrayList;
import java.util.List;

public class Categoria {
  public Categoria(String nome, String prato) {
    this.nome = nome;
    this.prato = prato;
    this.subCategorias = new ArrayList<Categoria>();
  }

  String nome;
  String prato;
  List<Categoria> subCategorias;

  public String getPrato() {
    return prato;
  }
  public void setPrato(String prato) {
    this.prato = prato;
  }
  public List<Categoria> getSubCategorias() {
    return subCategorias;
  }
  public void addSubCategoria(Categoria novaSubCategoria) {
    this.subCategorias.add(novaSubCategoria);
  }
  public String getNome() {
    return nome;
  }
  public void setNome(String nome) {
    this.nome = nome;
  }
}
