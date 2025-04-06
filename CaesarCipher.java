public class CaesarCipher {
    public static String encrypt(String text, int shift) {
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Character.isUpperCase(c)) {
                result.append((char)((c + shift - 65) % 26 + 65));
            } else if (Character.isLowerCase(c)) {
                result.append((char)((c + shift - 97) % 26 + 97));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    public static void bruteForce(String text) {
        for (int i = 1; i < 26; i++) {
            System.out.println("Shift " + i + ": " + encrypt(text, i));
        }
    }

    public static void main(String[] args) {
        String text = "Attack at dawn";
        String encrypted = encrypt(text, 3);
        System.out.println("Encrypted: " + encrypted);
        System.out.println("Brute Force:");
        bruteForce(encrypted);
    }
}
