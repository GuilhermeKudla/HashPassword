package Objects;
import java.io.Serializable;

public class Usuario implements Serializable{
    private String nome;
    private String senha;
    
    public Usuario(String nome, String senha){
    this.nome = nome;
    this.senha = senha;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public void setSenha(String senha){
        this.senha = senha;
    }
    
    public String getNome(){
        return nome;
    }
    
    public String getSenha(){
        return senha;
    }
}