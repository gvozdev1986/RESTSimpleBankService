package by.htp.hvozdzeu.dao.util;

/**
 * The class for rebase password to char array
 */
public final class RebasePassword {

    /**
     * Private constructor for realize Singleton pattern
     */
    private RebasePassword() {
    }

    /**
     * Singleton instance
     */
    private static volatile RebasePassword instance;

    /**
     * Singleton of rebasing password
     *
     * @return instance
     */
    public static RebasePassword getInstance() {
        if (instance == null) {
            synchronized (RebasePassword.class) {
                if (instance == null) {
                    instance = new RebasePassword();
                }
            }
        }
        return instance;
    }

    /**
     * The method for rebase password from String to char array
     * @param s String password
     * @return char array rebased password
     */
    public char[] rebasePSWD(String s){
        return s.toCharArray();
    }

    /**
     * The method for rebase to String from char array
     * @param s char array
     * @return String rebased password
     */
    public String rebasePSWD(char[] s){
        return String.valueOf(s);
    }

}

