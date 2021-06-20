import static org.junit.jupiter.api.Assertions.*;

import javax.swing.JOptionPane;

import org.junit.jupiter.api.Test;

class TesteCategorias {
    
  public class TelaTeste extends TelaInicial{
    //Sobreposição do método para teste com String sem ser necessária a entrada por tela
    @Override 
    protected Categoria inserirNovaCategoria(Categoria categoria) {
      String prato = null;
      prato = "SubPratoTeste";
      if(prato == null || "".equals(prato)) {
        JOptionPane.showMessageDialog(framePrincipal, "O nome do prato ficou vazio.");
        return categoria;
      }
       String nome  = "SubCategoriaTeste";
      if(nome == null || "".equals(nome)) {
        JOptionPane.showMessageDialog(framePrincipal, "O tipo de prato ficou vazio.");
        return categoria;
      }
      categoria.addSubCategoria(new Categoria(nome, prato));  
      inseridoNovo = true;
      return categoria;
    }
  }
  @Test
  void test() {
    TelaTeste telaTeste = new TelaTeste();
    Categoria categoriaTeste = new Categoria("CategoriaTeste", "PratoTeste");
    //Categoria criada para ser comparada após inserção
    Categoria categoriaResult = new Categoria("CategoriaTeste", "PratoTeste");
    categoriaResult.addSubCategoria(new Categoria("SubCategoriaTeste", "SubPratoTeste"));
    
    assertEquals(categoriaResult.getSubCategorias().get(0).getNome(), telaTeste.inserirNovaCategoria(categoriaTeste).getSubCategorias().get(0).getNome());
    assertEquals(categoriaResult.getSubCategorias().get(0).getNome(), telaTeste.inserirNovaCategoria(categoriaTeste).getSubCategorias().get(0).getNome());
  }

}
