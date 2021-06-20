import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class TelaInicial {
  JFrame framePrincipal = new JFrame();
  boolean inseridoNovo  = false;

  public static void main(String[] args) {
    new TelaInicial();
  }
    
  public TelaInicial() {
    
    JPanel painelTexto   = new JPanel();
    JPanel painelBotao   = new JPanel();
    JLabel textoInicio   = new JLabel("Pense em um prato que gosta");
    JButton botaoInicial = new JButton();

    Categoria categoriaMassa    = new Categoria("Massa", "Lasanha");
    Categoria categoriaNaoMassa = new Categoria("Não massa", "Bolo de Chocolate");
    
    botaoInicial.setSize(50, 40);
    botaoInicial.setText("OK");
    botaoInicial.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        iniciaJogo(categoriaMassa, categoriaNaoMassa);
      }
    });
    
    painelTexto.setLayout(new GridBagLayout());
    painelTexto.setBorder(new EmptyBorder(15, 5, 5, 5));
    painelTexto.add(textoInicio);
    
    painelBotao.setLayout(new GridBagLayout());
    painelBotao.setBorder(new EmptyBorder(5, 5, 20, 5));   
    painelBotao.add(botaoInicial);
    
    framePrincipal.setTitle("Jogo Gourmet");
    framePrincipal.setLayout(new BorderLayout());
    framePrincipal.add(painelTexto,BorderLayout.CENTER);
    framePrincipal.add(painelBotao,BorderLayout.SOUTH);
    framePrincipal.setSize(285, 130);
    framePrincipal.setResizable(false);
    framePrincipal.setVisible(true);
    framePrincipal.setLocationRelativeTo(null);
    framePrincipal.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
  }
  
  private void iniciaJogo(Categoria categoriaMassa, Categoria categoriaNaoMassa) {
    inseridoNovo = false;
    int respostaMassa = JOptionPane.showConfirmDialog(framePrincipal, "O prato que você pensou é massa?", "Confirm", JOptionPane.YES_NO_OPTION);
    if (respostaMassa == 0) {
      categoriaMassa = exploraCategoria(categoriaMassa);
    }else {
      categoriaNaoMassa = exploraCategoria(categoriaNaoMassa);
    }
  }
  
  //Percorre as categorias existentes para encontrar a desejada ou inserir nova
  private Categoria exploraCategoria(Categoria categoria) {
    if(categoria.getSubCategorias() != null && !categoria.getSubCategorias().isEmpty()) {
      for(Categoria categoriaTemp : categoria.getSubCategorias()) {
        if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(framePrincipal, "O prato que você pensou é " + categoriaTemp.getNome() + "?", "Confirm", JOptionPane.YES_NO_OPTION)) {
          categoriaTemp = exploraCategoria (categoriaTemp);
        }
        if (inseridoNovo) {
          return categoria;
        }
      }
      if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(framePrincipal, "O prato que você pensou é " + categoria.getPrato() + "?", "Confirm", JOptionPane.YES_NO_OPTION)) {
        mensagemAcerto();
      }else {
        return inserirNovaCategoria(categoria);
      }
    }else {
      if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(framePrincipal, "O prato que você pensou é " + categoria.getPrato() + "?", "Confirm", JOptionPane.YES_NO_OPTION)) {
        mensagemAcerto();
      }else {
        categoria = inserirNovaCategoria(categoria);
      }
    }
    return categoria;
  }
  //Método de inserção de nova categoria com novo prato
  protected Categoria inserirNovaCategoria(Categoria categoria) {
	inseridoNovo = true;
    String prato = null;
    prato = JOptionPane.showInputDialog(framePrincipal, "Qual prato você pensou?", "Desisto", JOptionPane.QUESTION_MESSAGE);
    if(prato == null || "".equals(prato)) {
      JOptionPane.showMessageDialog(framePrincipal, "O nome do prato ficou vazio.");
      return categoria;
    }
    String nome  = JOptionPane.showInputDialog(framePrincipal, prato + " é _______ mas " + categoria.getPrato() + " não.", "Complete", JOptionPane.QUESTION_MESSAGE);
    if(nome == null || "".equals(nome)) {
      JOptionPane.showMessageDialog(framePrincipal, "O tipo de prato ficou vazio.");
      return categoria;
    }
    categoria.addSubCategoria(new Categoria(nome, prato));  
    return categoria;
  }

  private void mensagemAcerto() {
    JOptionPane.showMessageDialog(framePrincipal, "Acertei de novo!", "Jogo Gourmet", 1);
  } 
}
