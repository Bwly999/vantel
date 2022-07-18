package cn.edu.xmu.vantel.core.util.entrypt;

public interface Coder {
    String encrypt(String content);

    String decrypt(String content);
}
