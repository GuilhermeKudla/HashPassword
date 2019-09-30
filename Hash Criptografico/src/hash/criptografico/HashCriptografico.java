package hash.criptografico;

import Objects.Usuario;
import Objects.ObjectIO;
import java.util.ArrayList;
import java.util.List;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.DatatypeConverter;
import java.io.File;

public class HashCriptografico {
    public static ObjectIO objectIO = new ObjectIO();
    public static List<Usuario> listaUsuario = new ArrayList<Usuario>();

    public static void main(String[] args) throws NoSuchAlgorithmException {
        StartFile();
        
        CriaUsuario("log1", "pas1");
        CriaUsuario("log2", "pas2");
        CriaUsuario("log3", "pas3");
        CriaUsuario("log4", "pas4");
        
        VerificaLogin("log2", "pas2");
    }
    
    public static void StartFile() {
        File tmpDir = new File(objectIO.getFilePath());
        boolean existe = tmpDir.exists();
        if (!existe) 
            objectIO.WriteObjectToFile(listaUsuario);
        listaUsuario = (List<Usuario>) objectIO.ReadObjectFromFile(objectIO.getFilePath());
    }
    
    public static int CriaUsuario(String login, String senha) throws NoSuchAlgorithmException {
        for (int i = 0; i < listaUsuario.size(); i++) {
            if (login.equals(listaUsuario.get(i).getNome())) {
                System.out.println("Login ja existe!");
                return 0;
            }
        }
        if ((login.length() >4) || senha.length() > 4) {
            System.out.println("Login ou senha maior que 4 caracteres!");
            return 0;
        }
        Usuario usuario = new Usuario(login, ToHash(senha));
        listaUsuario.add(usuario);
        objectIO.WriteObjectToFile(listaUsuario);
        return 0;
    }
    
    public static void VerificaLogin(String login, String senha) throws NoSuchAlgorithmException {
        boolean check = false;
        for (int i = 0; i < listaUsuario.size(); i++) {
            if (login.equals(listaUsuario.get(i).getNome())) {
                if (ToHash(senha).equals(listaUsuario.get(i).getSenha())) {
                    System.out.println("Login realizado com sucesso!");
                    check = true;
                    break;
                }
            }
        }
        if (!check)
            System.out.println("Login ou senha incorretos.");
    }
    
    public static String ToHash(String string) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(string.getBytes());
        byte[] digest = md.digest();
        String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
        return myHash;

    }
    
}
