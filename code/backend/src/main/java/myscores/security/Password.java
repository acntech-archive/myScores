package myscores.security;

import myscores.util.Const;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA512Digest;
import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.util.encoders.Hex;

import java.util.UUID;

public class Password {

    private static final String MAGIC_BYTES = "arsenal_rules_";

    public String encryptPassword(String password, String salt) {
        if (password == null || password.isEmpty() || salt == null || salt.isEmpty()) {
            throw new PasswordException("Illegal password input");
        } else {
            byte[] hash = createHash(password, salt);
            return Hex.toHexString(hash);
        }
    }

    public boolean verifyPassword(String password, String salt, String passwordHash) {
        if (password == null || password.isEmpty() || salt == null || salt.isEmpty() || passwordHash == null || passwordHash.isEmpty()) {
            throw new PasswordException("Illegal password input");
        } else {
            byte[] hash = createHash(password, salt);
            String verifyPasswordHash = Hex.toHexString(hash);
            return passwordHash.equals(verifyPasswordHash);
        }
    }

    public String createSalt() {
        return Hex.toHexString(UUID.randomUUID().toString().getBytes(Const.UTF8));
    }

    private byte[] createHash(String password, String salt) {
        Digest digest = new SHA512Digest();
        byte[] data = applySalt(password, salt);
        byte[] output = new byte[digest.getDigestSize()];
        digest.update(data, 0, data.length);
        digest.doFinal(output, 0);
        return output;
    }

    private byte[] applySalt(String password, String salt) {
        String saltetPassword = MAGIC_BYTES + password + salt;
        return Base64.encode(saltetPassword.getBytes(Const.UTF8));
    }
}
